package com.games.model.repository.interfaces;

import com.games.model.document.DiceGame;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiceGameRepository extends MongoRepository<DiceGame,String> {
    @Query(value = "{'idPlayer' : ?0 }")
    List<DiceGame> findByIdPlayer(Integer idPlayer);
    @Query(value = "{'idPlayer' : ?0 }", delete = true)
    void deleteByIdPlayer(Integer idPlayer);
}
