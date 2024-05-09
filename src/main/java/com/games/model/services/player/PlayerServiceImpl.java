package com.games.model.services.player;

import com.games.exceptions.ExcpNotGetAllPlayers;
import com.games.exceptions.ExcpPlayerNotFound;
import com.games.model.dto.DiceGameDTO;
import com.games.model.dto.PlayerDTO;
import com.games.model.entity.Player;
import com.games.model.repository.DiceGameRepository;
import com.games.model.repository.PlayerRepository;
import com.games.model.services.converter.DtoConverter;
import com.games.security.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService{
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private DiceGameRepository diceGameRepository;

    @Autowired
    private JwtService jwtService;

    @Override
    public Player createPlayer(Player player) {

        if(checkIfAnonymous(player)){
            player.setEmail("");
            player.setNickname("ANONYMOUS");
            player.setPassword("");
            Player playerDB = playerRepository.save(player);
            playerDB.setEmail("anonymous"+ playerDB.getId() +"@withoutmail.com");
            return playerRepository.save(playerDB);
        } else {
            return playerRepository.save(player);
        }
    }

    @Override
    public PlayerDTO modifyUsername(String token, String nickname) {
        Optional<Player> playerDB = playerRepository.findById(jwtService.getPlayerId(token));
        if (playerDB.isPresent()) {
            Player playerUpdate = playerDB.get();
            playerUpdate.setNickname(nickname);
            List<DiceGameDTO> diceGameDTOList = DtoConverter.diceGameDTOList(diceGameRepository.findByIdPlayer(jwtService.getPlayerId(token)));
            return DtoConverter.playerToDTO(playerRepository.save(playerUpdate), diceGameDTOList);
        } else {
            throw new ExcpPlayerNotFound(jwtService.getPlayerId(token));
        }
    }

    @Override
    public List<PlayerDTO> getAllPlayers() {
        List<Player> playerList = playerRepository.findAll();
        if (!playerList.isEmpty()) {
            List<PlayerDTO> playerDTOList = new ArrayList<>();
            for (Player player : playerList) {
                List<DiceGameDTO> diceGameDTOList = DtoConverter.diceGameDTOList(diceGameRepository.findByIdPlayer(player.getId()));
                playerDTOList.add(DtoConverter.playerToDTO(player, diceGameDTOList));
            }
            return playerDTOList;
        } else {
            throw new ExcpNotGetAllPlayers();
        }
    }

    private boolean checkIfAnonymous (Player player){
        return player.getEmail() == null || player.getEmail().isEmpty() || player.getEmail().isBlank();
    }

}
