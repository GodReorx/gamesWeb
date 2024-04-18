package com.games.model.dto;




import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PlayerDTO {
    private Integer id;
    private String email;
    private String password;
    private String nickname;
    private Date registrationDate;
    private List<DiceGameDTO> diceGameDTOList;
    private float successPercentage;

    public PlayerDTO(Integer id, String email, String nickname, String password, Date registrationDate, List<DiceGameDTO> diceGameDTOList) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.registrationDate = registrationDate;
        this.diceGameDTOList = diceGameDTOList;
        this.successPercentage = successPercentageCalc(diceGameDTOList);
    }

    public PlayerDTO(Integer id, String email, String nickname, String password, Date registrationDate) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.registrationDate = registrationDate;
        this.diceGameDTOList = new ArrayList<>();
        this.successPercentage = 0f;
    }

    private float successPercentageCalc (List<DiceGameDTO> diceGameListCalc){
        float gamesMade = diceGameListCalc.size();
        float gamesWin = 0;
        for(DiceGameDTO game : diceGameListCalc){
            if(game.isWin()){
                gamesWin++;
            }
        }
        return gamesWin/gamesMade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<DiceGameDTO> getDiceGameDTOList() {
        return diceGameDTOList;
    }

    public void setDiceGameDTOList(List<DiceGameDTO> diceGameDTOList) {
        this.diceGameDTOList = diceGameDTOList;
    }

    public float getSuccessPercentage() {
        return successPercentage;
    }

    public void setSuccessPercentage(float successPercentage) {
        this.successPercentage = successPercentage;
    }
}
