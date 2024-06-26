package com.games.model.services.dicegame;

import com.games.model.dto.DiceGameDTO;
import com.games.model.dto.PlayerDTO;

import java.util.List;


public interface DiceGameService {
    List<DiceGameDTO> getAllPlayerRolls (Integer id);
    PlayerDTO rollDices(Integer id);
    void deleteAllRolls(Integer id);
}
