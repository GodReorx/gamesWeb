package com.games.model.document;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rankingDice")
public class RankingDice implements Comparable<RankingDice>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    String id;
    Integer idPlayer;
    String namePlayer;
    float successPercentage;

    public RankingDice(){}

    public RankingDice(Integer idPlayer, String namePlayer, float successPercentage) {
        this.idPlayer = idPlayer;
        this.namePlayer = namePlayer;
        this.successPercentage = successPercentage;
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

    public float getSuccessPercentage() {
        return successPercentage;
    }

    public void setSuccessPercentage(float successPercentage) {
        this.successPercentage = successPercentage;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    @Override
    public int compareTo(RankingDice o) {
        if(this.successPercentage < o.successPercentage){
            return 1;
        } else if (this.successPercentage == o.successPercentage){
            return 0;
        } else {
            return -1;
        }
    }
}
