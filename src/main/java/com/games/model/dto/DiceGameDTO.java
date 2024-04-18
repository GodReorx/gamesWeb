package com.games.model.dto;

public class DiceGameDTO {
    private String id;
    private Integer idPlayer;
    private int dice1;
    private int dice2;
    private boolean win;

    public DiceGameDTO(String id, Integer idPlayer, int dice1, int dice2){
        this.id = id;
        this.idPlayer = idPlayer;
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.win = (dice1 + dice2) == 7;
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

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }
}
