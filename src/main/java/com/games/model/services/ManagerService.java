package com.games.model.services;

import com.games.model.dto.DiceGameDTO;
import com.games.model.dto.PlayerDTO;
import com.games.model.dto.RankingDiceDTO;
import com.games.model.entity.Player;

import java.util.List;

public interface ManagerService {
    PlayerDTO createPlayer(Player player);
    PlayerDTO modifyUsername(Player player);
    List<PlayerDTO> getAllPlayers ();
    List<DiceGameDTO> getAllPlayerRolls (String id);
    PlayerDTO rollDices(String id);
    void deleteAllRolls(String id);
    RankingDiceDTO getLoser();
    RankingDiceDTO getWinner();
    List<RankingDiceDTO> getRanking();
}
