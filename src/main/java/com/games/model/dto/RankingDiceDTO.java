package com.games.model.dto;

public class RankingDiceDTO implements Comparable<RankingDiceDTO> {
    Integer playerId;
    String namePlayer;
    float successPercentage;

    public RankingDiceDTO() {
    }

    public RankingDiceDTO(Integer playerId,String namePlayer, float successPercentage) {
        this.playerId = playerId;
        this.namePlayer = namePlayer;
        this.successPercentage = successPercentage;
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

    public String getNamePlayer() {
        return namePlayer;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    @Override
    public int compareTo(RankingDiceDTO o) {
        if(this.successPercentage < o.successPercentage){
            return 1;
        } else if (this.successPercentage == o.successPercentage){
            return 0;
        } else {
            return -1;
        }
    }
}
