package com.games.model.services.player;

import com.games.model.dto.PlayerDTO;
import com.games.model.entity.Player;

import java.util.List;

public interface PlayerService {
    Player createPlayer(Player player);
    PlayerDTO modifyUsername(Player player);
    List<PlayerDTO> getAllPlayers ();
}
