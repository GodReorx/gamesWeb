package com.games.model.services.rankingdice;

import com.games.model.dto.PlayerDTO;


public interface RankingDiceService {
    PlayerDTO getLoser();
    PlayerDTO getWinner();
}
