package com.games.model.services.player;

import com.games.exceptions.ExcpPlayerNotCreated;
import com.games.model.dto.DiceGameDTO;
import com.games.model.dto.PlayerDTO;
import com.games.model.entity.Player;
import com.games.model.repository.ManagerRepository;
import com.games.model.services.converter.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService{
    @Autowired
    ManagerRepository managerRepository;

    @Override
    public PlayerDTO createPlayer(Player player) {
        if(checkIfAnonymous(player)){
            player.setEmail("anonymous@withoutmail.com");
            player.setNickname("ANONYMOUS");
        } else {
            boolean existEmail = managerRepository.findPlayerByEmailBoolean(player.getEmail());
            if(existEmail){
                throw new ExcpPlayerNotCreated();
            }
        }
        return DtoConverter.newPlayerToDTO(managerRepository.save(player));
    }

    @Override
    public PlayerDTO modifyUsername(Player player) {
        Optional<Player> playerDB = managerRepository.readOne(player);
        Player playerUpdate = playerDB.get();
        playerUpdate.setNickname(player.getNickname());
        List<DiceGameDTO> diceGameDTOList = DtoConverter.diceGameDTOList(managerRepository.findByIdPlayerDice(player.getId()));
        return DtoConverter.playerToDTO(managerRepository.save(playerUpdate), diceGameDTOList);
    }

    @Override
    public List<PlayerDTO> getAllPlayers() {
        List<Player> playerList = managerRepository.readAll(Player.class);
        List<PlayerDTO> playerDTOList = new ArrayList<>();
        for(Player player : playerList){
            List<DiceGameDTO> diceGameDTOList = DtoConverter.diceGameDTOList(managerRepository.findByIdPlayerDice(player.getId()));
            playerDTOList.add(DtoConverter.playerToDTO(player, diceGameDTOList));
        }
        return playerDTOList;
    }

    private boolean checkIfAnonymous (Player player){
        if(player.getEmail() == null || player.getEmail().isEmpty() || player.getEmail().isBlank()){
            return true;
        } else{
            return false;
        }
    }

}
