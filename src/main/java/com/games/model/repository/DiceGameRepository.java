package com.games.model.repository;

import com.games.model.document.DiceGame;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiceGameRepository extends MongoRepository<DiceGame,String> {
    List<DiceGame> findByIdPlayer(Integer idPlayer);
    void deleteByIdPlayer(Integer idPlayer);
}
