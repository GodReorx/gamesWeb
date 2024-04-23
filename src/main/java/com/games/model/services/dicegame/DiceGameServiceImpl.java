package com.games.model.services.dicegame;

import com.games.model.document.DiceGame;
import com.games.model.document.RankingDice;
import com.games.model.dto.DiceGameDTO;
import com.games.model.dto.PlayerDTO;
import com.games.model.entity.Player;
import com.games.model.repository.ManagerRepository;
import com.games.model.services.converter.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

//ToDo: Crear las excepciones
@Service
public class DiceGameServiceImpl implements DiceGameService {
    @Autowired
    ManagerRepository managerRepository;

    @Override
    public List<DiceGameDTO> getAllPlayerRolls(String id) {
        List<DiceGame> diceGameList = managerRepository.findByIdPlayerDice(Integer.parseInt(id));
        return diceGameList.stream().map(diceGame -> DtoConverter.diceGameToDTO(diceGame)).collect(Collectors.toList());
    }
    @Override
    public PlayerDTO rollDices(String id) {
        Optional<Player> player = managerRepository.readOne(Player.class, id);
        Random random = new Random();
        int dice1 = random.nextInt(6) + 1;
        int dice2 = random.nextInt(6) + 1;
        DiceGame diceGame = new DiceGame(player.get().getId(), dice1, dice2);
        managerRepository.save(diceGame);
        List<DiceGameDTO> diceGameDTOList = DtoConverter.diceGameDTOList(managerRepository.findByIdPlayerDice(player.get().getId()));
        PlayerDTO playerDTO = DtoConverter.playerToDTO(player.get(),diceGameDTOList);
        addToRanking(playerDTO);
        return playerDTO;
    }

    @Override
    public void deleteAllRolls(String id) {
        managerRepository.deleteByIdPlayerDice(id);
    }

    private void addToRanking(PlayerDTO playerDTO){
        Optional<RankingDice> rankingPlayerDB = managerRepository.readOne(RankingDice.class, playerDTO.getId().toString());
        if(rankingPlayerDB.isPresent()) {
            RankingDice rankingPlayerUpdate = rankingPlayerDB.get();
            rankingPlayerUpdate.setSuccessPercentage(playerDTO.getSuccessPercentage());
            managerRepository.save(rankingPlayerUpdate);
        } else {
            RankingDice newRankingPlayer = new RankingDice(playerDTO.getId(), playerDTO.getNickname(), playerDTO.getSuccessPercentage());
            managerRepository.save(newRankingPlayer);
        }
    }
}
