package com.games.model.repository;

import com.games.model.document.DiceGame;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiceGameRepository extends MongoRepository<DiceGame,String> {
    @Query("{'idPlayer' : ?0 }") //Asi especificas que campo ha de mirar en mongo
    List<DiceGame> findByIdPlayer(Integer idPlayer);
}
