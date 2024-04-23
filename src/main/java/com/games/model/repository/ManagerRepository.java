package com.games.model.repository;

import com.games.model.document.DiceGame;
import com.games.model.document.RankingDice;

import java.util.List;
import java.util.Optional;

public interface ManagerRepository {

    <T> T save(T t);
    <T> Optional<T> readOne(Class<T> classRepo, String id);
    <T> List<T> readAll(Class<T> classRepo);
    <T> void delete(T t);

    //Only for PlayerRepository
    boolean findPlayerByEmailBoolean(String email);

    //Only for DiceGame
    List<DiceGame> findByIdPlayerDice(Integer idPlayer);
    void deleteByIdPlayerDice(String id);

    //Only for RankingDice
    RankingDice findMaxSuccessPercentage();
    RankingDice findMinsuccessPercentage();
}
