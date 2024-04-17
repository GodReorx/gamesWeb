package com.games.model.repository;

import com.games.model.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Integer> {
    /*@Query("SELECT p FROM Player p WHERE p.email = ?1 AND p.user = ?2")
    Player findPlayerByEmailAndUser(String email, String user);*/
}
