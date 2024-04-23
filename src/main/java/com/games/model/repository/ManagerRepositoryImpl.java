package com.games.model.repository;

import com.games.model.document.DiceGame;
import com.games.model.document.RankingDice;
import com.games.model.entity.Player;
import com.games.model.repository.interfaces.DiceGameRepository;
import com.games.model.repository.interfaces.PlayerRepository;
import com.games.model.repository.interfaces.RankingDiceRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ManagerRepositoryImpl implements ManagerRepository{
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    DiceGameRepository diceGameRepository;
    @Autowired
    RankingDiceRespository rankingDiceRespository;

    @Override
    public <T> T save(T t) {
        if (t instanceof Player) {
            return (T) playerRepository.save((Player) t);
        } else if (t instanceof DiceGame) {
            return (T) diceGameRepository.save((DiceGame) t);
        } else if (t instanceof RankingDice) {
            return (T) rankingDiceRespository.save((RankingDice) t);
        } else {
            throw new UnsupportedOperationException("Unsupported entity type: " + t.getClass());
        }
    }

    @Override
    public <T> Optional<T> readOne(Class<T> classRepo, String id) {
        if (classRepo.equals(Player.class)) {
            return (Optional<T>) playerRepository.findById(Integer.parseInt(id));
        } else if (classRepo.equals(DiceGame.class)) {
            return (Optional<T>) diceGameRepository.findById(id);
        } else if (classRepo.equals(RankingDice.class)) {
            return (Optional<T>) rankingDiceRespository.findByIdPlayer(Integer.parseInt(id));
        } else {
            throw new UnsupportedOperationException("Unsupported entity type: " + classRepo);
        }
    }

    @Override
    public <T> List<T> readAll(Class<T> classRepo) {
        if(classRepo.equals(Player.class)){
            return (List<T>) playerRepository.findAll();
        } else if(classRepo.equals(DiceGame.class)){
            return (List<T>) diceGameRepository.findAll();
        } else if (classRepo.equals(RankingDice.class)) {
            return (List<T>) rankingDiceRespository.findAll();
        } else {
            throw new UnsupportedOperationException("Unsupported entity type: " + classRepo);
        }
    }

    @Override
    public <T> void delete(T t) {
        if (t instanceof Player){
            playerRepository.deleteById(((Player) t).getId());
        } else if (t instanceof DiceGame) {
            diceGameRepository.deleteById(((DiceGame) t).getId());
        } else if (t instanceof RankingDice) {
            rankingDiceRespository.deleteById(((RankingDice) t).getId());
        } else {
            throw new UnsupportedOperationException("Unsupported entity type: " + t.getClass());
        }
    }

    @Override //Only for PlayerRepository
    public boolean findPlayerByEmailBoolean(String email) {
        return findPlayerByEmailBoolean(email);
    }

    @Override //Only for DiceGameRepository
    public List<DiceGame> findByIdPlayerDice(Integer idPlayer) {
        return diceGameRepository.findByIdPlayer(idPlayer);
    }

    @Override //Only for DiceGameRepository
    public void deleteByIdPlayerDice(String idPlayer) {
        diceGameRepository.deleteByIdPlayer(Integer.parseInt(idPlayer));
    }

    @Override //Only for RankingDice
    public RankingDice findMaxSuccessPercentage() {
        List<RankingDice> rankingDiceList = rankingDiceRespository.findOneByOrderBySuccessPercentageDesc();
        return rankingDiceList.get(0);
    }

    @Override //Only for RankingDice
    public RankingDice findMinsuccessPercentage() {
        List<RankingDice> rankingDiceList = rankingDiceRespository.findOneByOrderBySuccessPercentageAsc();
        return rankingDiceList.get(0);
    }
}
