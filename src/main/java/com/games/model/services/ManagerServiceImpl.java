package com.games.model.services;

import com.games.model.dto.DiceGameDTO;
import com.games.model.dto.PlayerDTO;
import com.games.model.entity.Player;
import com.games.model.services.dicegame.DiceGameService;
import com.games.model.services.player.PlayerService;
import com.games.model.services.rankingdice.RankingDiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService{
    @Autowired
    PlayerService playerService;
    @Autowired
    DiceGameService diceGameService;
    @Autowired
    RankingDiceService rankingDiceService;
    @Override
    public PlayerDTO createPlayer(Player player) {
        return playerService.createPlayer(player);
    }

    @Override
    public PlayerDTO modifyUsername(Player player) {
        return playerService.modifyUsername(player);
    }

    @Override
    public List<PlayerDTO> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @Override
    public List<DiceGameDTO> getAllPlayerRolls(Player player) {
        return diceGameService.getAllPlayerRolls(player);
    }

    @Override
    public PlayerDTO rollDices(Player player) {
        return diceGameService.rollDices(player);
    }

    @Override
    public void deleteAllRolls(Player player) {
        diceGameService.deleteAllRolls(player);
    }

    @Override
    public PlayerDTO getLoser() {
        return rankingDiceService.getLoser();
    }

    @Override
    public PlayerDTO getWinner() {
        return rankingDiceService.getWinner();
    }
}
