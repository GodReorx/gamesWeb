package com.games.model.services.dicegame;

import com.games.model.dto.DiceGameDTO;
import com.games.model.dto.PlayerDTO;
import com.games.model.entity.Player;

import java.util.List;


public interface DiceGameService {
    List<DiceGameDTO> getAllPlayerRolls (Player player);
    PlayerDTO rollDices(Player player);
    void deleteAllRolls(Player player);
}
