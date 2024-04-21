package com.games.model.services.dicegame;

import com.games.model.document.DiceGame;
import com.games.model.dto.DiceGameDTO;
import com.games.model.dto.PlayerDTO;
import com.games.model.entity.Player;
import com.games.model.repository.DiceGameRepository;
import com.games.model.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiceGameServiceImpl implements DiceGameService {
    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    DiceGameRepository diceGameRepository;

    @Override
    public PlayerDTO rollDices(Player player) {

        return null; //Realiza una tirada
    }

    @Override
    public void deleteAllRolls(Player player) {
        diceGameRepository.deleteById(player.getId().toString());
//Elimina toda las tiradas de un jugador
    }

    private PlayerDTO newPlayerToDTO (Player player){
        return new PlayerDTO(player.getId(), player.getEmail(), player.getNickname(), player.getPassword(), player.getRegistrationDate());
    }

   private PlayerDTO playerToDTO (Player player){
        List<DiceGame> diceGameList =  diceGameRepository.findByIdPlayer(player.getId());
        List<DiceGameDTO> diceGameDTOList = diceGameList.stream().map(diceGame -> diceGameToDTO(diceGame)).collect(Collectors.toList());
        return new PlayerDTO(player.getId(), player.getEmail(), player.getNickname(), player.getPassword(), player.getRegistrationDate(),diceGameDTOList);
    }

    private DiceGameDTO diceGameToDTO (DiceGame diceGame){
        return new DiceGameDTO(diceGame.getId(), diceGame.getIdPlayer(),diceGame.getDice1(), diceGame.getDice2());
    }
    private boolean checkIfAnonymous (Player player){
        if(player.getEmail() == null || player.getEmail().isEmpty() || player.getEmail().isBlank()){
            return true;
        } else{
            return false;
        }
    }

}
