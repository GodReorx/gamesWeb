package com.games.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiceGameDTO {
    private String id;
    private Integer idPlayer;
    private int dice1;
    private int dice2;
    private boolean win;

    public DiceGameDTO(String id, Integer idPlayer, int dice1, int dice2, boolean win){
        this.id = id;
        this.idPlayer = idPlayer;
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.win = win;
    }
    public DiceGameDTO(String id, Integer idPlayer, int dice1, int dice2){
        this.id = id;
        this.idPlayer = idPlayer;
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.win = (dice1 + dice2) == 7;
    }


}
