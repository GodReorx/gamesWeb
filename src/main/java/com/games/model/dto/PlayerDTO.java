package com.games.model.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PlayerDTO {
    private Integer id;
    private String user;
    private String password;
    private Date registrationDate;
    private List<DiceGameDTO> diceGameDTOList;
    private float successPercentage;

    public PlayerDTO(Integer id, String user, String password, Date registrationDate, List<DiceGameDTO> diceGameDTOList) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.registrationDate = registrationDate;
        this.diceGameDTOList = diceGameDTOList;
        this.successPercentage = successPercentageCalc(diceGameDTOList);
    }

    private float successPercentageCalc (List<DiceGameDTO> diceGameListCalc){
        float gamesMade = diceGameListCalc.size();
        float gamesWin = (int) diceGameListCalc.stream().filter(DiceGameDTO::isWin).count();
        return gamesWin/gamesMade;
    }

}
