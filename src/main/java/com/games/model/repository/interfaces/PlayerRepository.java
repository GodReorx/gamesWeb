package com.games.model.repository.interfaces;

import com.games.model.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Integer> {
    @Query("SELECT COUNT(p) > 0 FROM Player p WHERE p.email = ?1")
    boolean findPlayerByEmailBoolean(String email);
}
