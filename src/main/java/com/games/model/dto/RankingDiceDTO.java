package com.games.model.dto;

public class RankingDiceDTO {
    Integer playerId;
    float successPercentage;

    public RankingDiceDTO() {
    }

    public RankingDiceDTO(Integer playerId, float successPercentage) {
        this.playerId = playerId;
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
}
