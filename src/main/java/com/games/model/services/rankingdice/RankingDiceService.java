package com.games.model.services.rankingdice;

import com.games.model.dto.RankingDiceDTO;

import java.util.List;


public interface RankingDiceService {
    RankingDiceDTO getLoser();
    RankingDiceDTO getWinner();
    List<RankingDiceDTO> getRanking();
}
