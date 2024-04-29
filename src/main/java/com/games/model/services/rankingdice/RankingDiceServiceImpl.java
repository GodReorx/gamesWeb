package com.games.model.services.rankingdice;

import com.games.model.document.RankingDice;
import com.games.model.dto.RankingDiceDTO;
import com.games.model.repository.RankingDiceRespository;
import com.games.model.services.converter.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

//ToDo: Crear las excepciones
@Service
public class RankingDiceServiceImpl implements RankingDiceService {
    @Autowired
    RankingDiceRespository rankingDiceRespository;

    @Override
    public RankingDiceDTO getLoser() {
        List<RankingDice> rankingDiceList = rankingDiceRespository.findOneByOrderBySuccessPercentageAsc();
        return DtoConverter.rankingDiceToDTO(rankingDiceList.getFirst());
    }

    @Override
    public RankingDiceDTO getWinner() {
        List<RankingDice> rankingDiceList = rankingDiceRespository.findOneByOrderBySuccessPercentageDesc();
        return DtoConverter.rankingDiceToDTO(rankingDiceList.getFirst());
    }

    @Override
    public List<RankingDiceDTO> getRanking() {
        List<RankingDice> rankingDiceList = rankingDiceRespository.findAll();
        Collections.sort(rankingDiceList);
        return DtoConverter.rankingDiceDTOList(rankingDiceList);
    }

}
