package com.games.model.repository.interfaces;

import com.games.model.document.RankingDice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface RankingDiceRespository extends MongoRepository<RankingDice, String> {
    @Query(value = "{ $group: { _id: null, maxSuccess: { $max: '$successPercentage' } } }")
    RankingDice findMaxSuccessPercentage();
    @Query(value = "{ $group: { _id: null, minSuccess: { $min: '$successPercentage' } } }")
    RankingDice findMinsuccessPercentage();
}
