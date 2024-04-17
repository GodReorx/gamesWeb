package com.games.model.services;

import com.games.model.document.DiceGame;
import com.games.model.dto.DiceGameDTO;
import com.games.model.dto.PlayerDTO;
import com.games.model.entity.Player;
import com.games.model.repository.DiceGameRepository;
import com.games.model.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService{
    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    DiceGameRepository diceGameRepository;

    @Override
    public PlayerDTO createPlayer(Player player) {

        return null; //Crea un jugador
    }

    @Override
    public PlayerDTO modifyUsername(Player player) {
        return null; //Modifica solo el nombre del usuario
    }

    @Override
    public PlayerDTO rollDices(Player player) {
        return null; //Realiza una tirada
    }

    @Override
    public void deleteAllRolls(Player player) {
//Elimina toda las tiradas de un jugador
    }

    @Override
    public List<PlayerDTO> getAllPlayers() {
        return List.of(); //Recupera una lista de todos los jugadores actuales con su porcentaje de exito
    }

    @Override
    public List<DiceGameDTO> getAllPlayerRolls(Player player) {
        List<DiceGame> diceGameList = diceGameRepository.findByIdPlayer(player.getId());
        return List.of(); //Retorna una lista de todas las jugadas de un jugador
    }

    @Override
    public PlayerDTO getLoser() {
        return null; //Retorna el jugador que tiene el porcentaje mas bajo
    }

    @Override
    public PlayerDTO getWinner() {
        return null; //Retorna el jugador que tiene el porcentaje mas alto
    }

    private PlayerDTO newPlayerToDTO (Player player){
        return new PlayerDTO(player.getId(), player.getEmail(), player.getUser(), player.getPassword(), player.getRegistrationDate());
    }

   /* private PlayerDTO playerToDTO (Player player){
        List<DiceGame> diceGameList = diceGameRepository.
        return new PlayerDTO()
    }*/

}
