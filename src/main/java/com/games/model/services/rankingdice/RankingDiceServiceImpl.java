package com.games.model.services.rankingdice;

import com.games.model.document.RankingDice;
import com.games.model.dto.RankingDiceDTO;
import com.games.model.repository.ManagerRepository;
import com.games.model.services.converter.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

//ToDo: Crear las excepciones
@Service
public class RankingDiceServiceImpl implements RankingDiceService {
    @Autowired
    ManagerRepository managerRepository;

    @Override
    public RankingDiceDTO getLoser() {
        RankingDice loser = managerRepository.findMinsuccessPercentage();
        return DtoConverter.rankingDiceToDTO(loser);
    }

    @Override
    public RankingDiceDTO getWinner() {
        RankingDice winner = managerRepository.findMaxSuccessPercentage();
        return DtoConverter.rankingDiceToDTO(winner);
    }

    @Override
    public List<RankingDiceDTO> getRanking() {
        List<RankingDice> rankingDiceList = managerRepository.readAll(RankingDice.class);
        Collections.sort(rankingDiceList);
        return DtoConverter.rankingDiceDTOList(rankingDiceList);
    }

}
