package com.games.model.services.player;

import com.games.model.dto.DiceGameDTO;
import com.games.model.dto.PlayerDTO;
import com.games.model.entity.Player;

import java.util.List;

public interface PlayerService {
    PlayerDTO createPlayer(Player player);
    PlayerDTO modifyUsername(Player player);
    List<PlayerDTO> getAllPlayers ();
    List<DiceGameDTO> getAllPlayerRolls (Player player);
}
