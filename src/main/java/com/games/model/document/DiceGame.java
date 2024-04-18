package com.games.model.document;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "dicegame")
public class DiceGame {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private Integer idPlayer;
    private int dice1;
    private int dice2;

    public DiceGame() {
    }

    public DiceGame(Integer idPlayer, int dice1, int dice2) {
        this.idPlayer = idPlayer;
        this.dice1 = dice1;
        this.dice2 = dice2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(Integer idPlayer) {
        this.idPlayer = idPlayer;
    }

    public int getDice1() {
        return dice1;
    }

    public void setDice1(int dice1) {
        this.dice1 = dice1;
    }

    public int getDice2() {
        return dice2;
    }

    public void setDice2(int dice2) {
        this.dice2 = dice2;
    }
}
