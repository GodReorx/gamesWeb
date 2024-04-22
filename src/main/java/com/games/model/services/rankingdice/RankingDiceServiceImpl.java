package com.games.model.services.rankingdice;

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

@Service
public class RankingDiceServiceImpl implements RankingDiceService {
    @Autowired
    ManagerRepository managerRepository;

    @Override
    public PlayerDTO getLoser() {
        RankingDice loser = managerRepository.findMinsuccessPercentage();
        Player playerDB = new Player();
        playerDB.setId(loser.getPlayerId());
        Optional<Player> playerLoser = managerRepository.readOne(playerDB);
        List<DiceGameDTO> diceGameDTOList = DtoConverter.diceGameDTOList(managerRepository.findByIdPlayerDice(playerDB.getId()));
        return DtoConverter.playerToDTO(playerLoser.get(),diceGameDTOList);
    }

    @Override
    public PlayerDTO getWinner() {
        RankingDice winner = managerRepository.findMaxSuccessPercentage();
        Player playerDB = new Player();
        playerDB.setId(winner.getPlayerId());
        Optional<Player> playerLoser = managerRepository.readOne(playerDB);
        List<DiceGameDTO> diceGameDTOList = DtoConverter.diceGameDTOList(managerRepository.findByIdPlayerDice(playerDB.getId()));
        return DtoConverter.playerToDTO(playerLoser.get(), diceGameDTOList);
    }

}
