package com.games.model.services.rankingdice;

import com.games.exceptions.ExcpNotRanking;
import com.games.model.document.RankingDice;
import com.games.model.dto.RankingDiceDTO;
import com.games.model.repository.RankingDiceRespository;
import com.games.model.services.converter.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RankingDiceServiceImpl implements RankingDiceService {
    @Autowired
    private RankingDiceRespository rankingDiceRespository;

    @Override
    public RankingDiceDTO getLoser() {
        List<RankingDice> rankingDiceList = rankingDiceRespository.findOneByOrderBySuccessPercentageAsc();
        if (!rankingDiceList.isEmpty()) {
            return DtoConverter.rankingDiceToDTO(rankingDiceList.getFirst());
        } else {
            throw new ExcpNotRanking();
        }
    }

    @Override
    public RankingDiceDTO getWinner() {
        List<RankingDice> rankingDiceList = rankingDiceRespository.findOneByOrderBySuccessPercentageDesc();
        if (!rankingDiceList.isEmpty()) {
            return DtoConverter.rankingDiceToDTO(rankingDiceList.getFirst());
        } else {
            throw new ExcpNotRanking();
        }
    }

    @Override
    public List<RankingDiceDTO> getRanking() {
        List<RankingDice> rankingDiceList = rankingDiceRespository.findAll();
        if (!rankingDiceList.isEmpty()) {
            Collections.sort(rankingDiceList);
            return DtoConverter.rankingDiceDTOList(rankingDiceList);
        } else {
            throw new ExcpNotRanking();
        }
    }

}
