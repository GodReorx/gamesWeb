package com.games.model.repository;

import com.games.model.document.RankingDice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface RankingDiceRespository extends MongoRepository<RankingDice, String> {

    List<RankingDice> findOneByOrderBySuccessPercentageDesc();

    List<RankingDice> findOneByOrderBySuccessPercentageAsc();
    @Query(value = "{'idPlayer' : ?0 }")
    Optional<RankingDice> findByIdPlayer(Integer idPlayer);

    void deleteByIdPlayer(Integer idPlayer);
}
