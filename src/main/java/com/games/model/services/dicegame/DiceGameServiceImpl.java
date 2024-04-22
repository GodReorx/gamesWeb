package com.games.model.services.dicegame;

import com.games.model.document.DiceGame;
import com.games.model.dto.DiceGameDTO;
import com.games.model.dto.PlayerDTO;
import com.games.model.entity.Player;
import com.games.model.repository.ManagerRepository;
import com.games.model.services.converter.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class DiceGameServiceImpl implements DiceGameService {
    @Autowired
    ManagerRepository managerRepository;

    @Override
    public List<DiceGameDTO> getAllPlayerRolls(Player player) {
        List<DiceGame> diceGameList = managerRepository.findByIdPlayerDice(player.getId());
        return diceGameList.stream().map(diceGame -> DtoConverter.diceGameToDTO(diceGame)).collect(Collectors.toList());
    }
    @Override
    public PlayerDTO rollDices(Player player) {
        Random random = new Random();
        int dice1 = random.nextInt(6) + 1;
        int dice2 = random.nextInt(6) + 1;
        //ToDO: Tiene que guardarse en la tabla de tiradas de Mongo y guardarse en el playerDTO.
        return null;
    }

    @Override
    public void deleteAllRolls(Player player) {
        managerRepository.deleteByIdPlayerDice(player.getId());
    }

}
