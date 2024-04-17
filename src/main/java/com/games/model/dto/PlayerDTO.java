package com.games.model.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PlayerDTO {
    private Integer id;
    private String email;
    private String password;
    private String user;
    private Date registrationDate;
    private List<DiceGameDTO> diceGameDTOList;
    private float successPercentage;

    public PlayerDTO(Integer id, String email, String user, String password, Date registrationDate, List<DiceGameDTO> diceGameDTOList) {
        this.id = id;
        this.email = email;
        this.user = user;
        this.password = password;
        this.registrationDate = registrationDate;
        this.diceGameDTOList = diceGameDTOList;
        this.successPercentage = successPercentageCalc(diceGameDTOList);
    }

    public PlayerDTO(Integer id, String email, String user, String password, Date registrationDate) {
        this.id = id;
        this.email = email;
        this.user = user;
        this.password = password;
        this.registrationDate = registrationDate;
        this.diceGameDTOList = new ArrayList<>();
        this.successPercentage = successPercentageCalc(diceGameDTOList);
    }

    private float successPercentageCalc (List<DiceGameDTO> diceGameListCalc){
        float gamesMade = diceGameListCalc.size();
        float gamesWin = (int) diceGameListCalc.stream().filter(DiceGameDTO::isWin).count();
        return gamesWin/gamesMade;
    }

}
