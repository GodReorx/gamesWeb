package com.games.model.repository;

import com.games.model.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Integer> {

    Optional<Player> findUserByEmail(String email);
    boolean existsPlayerByEmail(String email);
    boolean existsPlayerByNickname(String nickname);
}
