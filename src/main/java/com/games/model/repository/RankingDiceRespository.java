package com.games.model.repository;

import com.games.model.document.RankingDice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RankingDiceRespository extends MongoRepository<RankingDice, String> {
}
