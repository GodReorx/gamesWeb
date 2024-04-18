package com.games.model.services;

import com.games.exceptions.ExcpPlayerNotCreated;
import com.games.model.document.DiceGame;
import com.games.model.dto.DiceGameDTO;
import com.games.model.dto.PlayerDTO;
import com.games.model.entity.Player;
import com.games.model.repository.DiceGameRepository;
import com.games.model.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService{
    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    DiceGameRepository diceGameRepository;

    @Override
    public PlayerDTO createPlayer(Player player) {
        if(checkIfAnonymous(player)){
            player.setEmail("anonymous@withoutmail.com");
            player.setNickname("ANONYMOUS");
        } else {
            boolean existEmail = playerRepository.findPlayerByEmailBoolean(player.getEmail());
            if(existEmail){
                throw new ExcpPlayerNotCreated();
            }
        }
        return newPlayerToDTO(playerRepository.save(player));
    }

    @Override
    public PlayerDTO modifyUsername(Player player) {
        Optional<Player> playerDB = playerRepository.findById(player.getId());
        Player playerUpdate = playerDB.get();
        playerUpdate.setNickname(player.getNickname());
        return playerToDTO(playerRepository.save(player)); //Modifica solo el nombre del usuario
    }

    @Override
    public PlayerDTO rollDices(Player player) {

        return null; //Realiza una tirada
    }

    @Override
    public void deleteAllRolls(Player player) {
        diceGameRepository.deleteById(player.getId().toString());
//Elimina toda las tiradas de un jugador
    }

    @Override
    public List<PlayerDTO> getAllPlayers() {
        List<Player> playerList = playerRepository.findAll();
        return playerList.stream().map(player -> playerToDTO(player)).collect(Collectors.toList());
    }

    @Override
    public List<DiceGameDTO> getAllPlayerRolls(Player player) {
        List<DiceGame> diceGameList = diceGameRepository.findByIdPlayer(player.getId());
        return diceGameList.stream().map(diceGame -> diceGameToDTO(diceGame)).collect(Collectors.toList());
    }

    @Override
    public PlayerDTO getLoser() {
        List<PlayerDTO> playerDTOList = getAllPlayers();
        PlayerDTO loser = null;
        float lowSuccesPercentage = Float.MAX_VALUE;
        for(PlayerDTO playerDTO : playerDTOList){
            if(playerDTO.getSuccessPercentage() < lowSuccesPercentage){
                lowSuccesPercentage = playerDTO.getSuccessPercentage();
                loser = playerDTO;
            }
        }
        return loser; //Retorna el jugador que tiene el porcentaje mas bajo
    }

    @Override
    public PlayerDTO getWinner() {
        List<PlayerDTO> playerDTOList = getAllPlayers();
        PlayerDTO winner = null;
        float lowSuccesPercentage = 0f;
        for(PlayerDTO playerDTO : playerDTOList){
            if(playerDTO.getSuccessPercentage() > lowSuccesPercentage){
                lowSuccesPercentage = playerDTO.getSuccessPercentage();
                winner = playerDTO;
            }
        }
        return winner; //Retorna el jugador que tiene el porcentaje mas alto
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
