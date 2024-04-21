package com.games.model.document;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rankingDice")
public class RankingDice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    String id;
    Integer playerId;
    float successPercentage;

    public RankingDice(){}

    public RankingDice(String id, Integer playerId, float successPercentage) {
        this.id = id;
        this.playerId = playerId;
        this.successPercentage = successPercentage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public float getSuccessPercentage() {
        return successPercentage;
    }

    public void setSuccessPercentage(float successPercentage) {
        this.successPercentage = successPercentage;
    }
}
