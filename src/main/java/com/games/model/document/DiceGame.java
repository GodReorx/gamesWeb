package com.games.model.document;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "dicegame")
public class DiceGame {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private Integer idPlayer;
    private int dice1;
    private int dice2;

    public DiceGame(Integer idPlayer, int dice1, int dice2) {
        this.idPlayer = idPlayer;
        this.dice1 = dice1;
        this.dice2 = dice2;
    }
}
