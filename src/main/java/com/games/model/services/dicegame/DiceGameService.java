package com.games.model.services.dicegame;

import com.games.model.dto.PlayerDTO;
import com.games.model.entity.Player;


public interface DiceGameService {
    PlayerDTO rollDices(Player player);
    void deleteAllRolls(Player player);
}
