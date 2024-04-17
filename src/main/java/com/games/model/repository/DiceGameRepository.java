package com.games.model.repository;

import com.games.model.document.DiceGame;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DiceGameRepository extends MongoRepository<DiceGame,String> {
}
