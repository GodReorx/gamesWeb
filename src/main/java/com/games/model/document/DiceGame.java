package com.games.model.document;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "dicegame")
public class DiceGame {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private Integer idPlayer;
    private int dice1;
    private int dice2;
}
