package com.games.model.services;

import com.games.model.dto.DiceGameDTO;
import com.games.model.dto.PlayerDTO;
import com.games.model.dto.RankingDiceDTO;
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
    private PlayerService playerService;
    @Autowired
    private DiceGameService diceGameService;
    @Autowired
    private RankingDiceService rankingDiceService;
    @Override
    public Player createPlayer(Player player) {
        return playerService.createPlayer(player);
    }

    @Override
    public PlayerDTO modifyUsername(String token, String nickname) {
        return playerService.modifyUsername(token, nickname);
    }

    @Override
    public List<PlayerDTO> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @Override
    public List<DiceGameDTO> getAllPlayerRolls(Integer id) {
        return diceGameService.getAllPlayerRolls(id);
    }

    @Override
    public PlayerDTO rollDices(Integer id) {
        return diceGameService.rollDices(id);
    }

    @Override
    public void deleteAllRolls(Integer id) {
        diceGameService.deleteAllRolls(id);
    }

    @Override
    public RankingDiceDTO getLoser() {
        return rankingDiceService.getLoser();
    }

    @Override
    public RankingDiceDTO getWinner() {
        return rankingDiceService.getWinner();
    }

    @Override
    public List<RankingDiceDTO> getRanking() {
        return rankingDiceService.getRanking();
    }
}
