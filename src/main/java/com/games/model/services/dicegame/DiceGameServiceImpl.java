package com.games.model.services.dicegame;

import com.games.model.document.DiceGame;
import com.games.model.document.RankingDice;
import com.games.model.dto.DiceGameDTO;
import com.games.model.dto.PlayerDTO;
import com.games.model.entity.Player;
import com.games.model.repository.DiceGameRepository;
import com.games.model.repository.PlayerRepository;
import com.games.model.repository.RankingDiceRespository;
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
    DiceGameRepository diceGameRepository;
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    private RankingDiceRespository rankingDiceRespository;

    @Override
    public List<DiceGameDTO> getAllPlayerRolls(Integer id) {
        List<DiceGame> diceGameList = diceGameRepository.findByIdPlayer(id);
        return diceGameList.stream().map(DtoConverter::diceGameToDTO).collect(Collectors.toList());
    }
    @Override
    public PlayerDTO rollDices(Integer id) {
        Optional<Player> player = playerRepository.findById(id);
        Random random = new Random();
        int dice1 = random.nextInt(6) + 1;
        int dice2 = random.nextInt(6) + 1;
        DiceGame diceGame = new DiceGame(player.get().getId(), dice1, dice2);
        diceGameRepository.save(diceGame);
        List<DiceGameDTO> diceGameDTOList = DtoConverter.diceGameDTOList(diceGameRepository.findByIdPlayer(id));
        PlayerDTO playerDTO = DtoConverter.playerToDTO(player.get(),diceGameDTOList);
        addToRanking(playerDTO);
        return playerDTO;
    }

    @Override
    public void deleteAllRolls(Integer id) {
        diceGameRepository.deleteByIdPlayer(id);
    }

    private void addToRanking(PlayerDTO playerDTO){
        Optional<RankingDice> rankingPlayerDB = rankingDiceRespository.findByIdPlayer(playerDTO.getId());
        if(rankingPlayerDB.isPresent()) {
            RankingDice rankingPlayerUpdate = rankingPlayerDB.get();
            rankingPlayerUpdate.setSuccessPercentage(playerDTO.getSuccessPercentage());
            rankingDiceRespository.save(rankingPlayerUpdate);
        } else {
            RankingDice newRankingPlayer = new RankingDice(playerDTO.getId(), playerDTO.getNickname(), playerDTO.getSuccessPercentage());
            rankingDiceRespository.save(newRankingPlayer);
        }
    }
}
