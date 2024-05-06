package com.games.controllers;

import com.games.model.dto.RankingDiceDTO;
import com.games.model.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/players/ranking")

public class RankingDiceController {

    @Autowired
    private ManagerService managerService;

    @GetMapping
    public ResponseEntity<?> getRanking(){
        List<RankingDiceDTO> rankingList = managerService.getRanking();
        return new ResponseEntity<>(rankingList, HttpStatus.OK);
    }
    @GetMapping("/loser")
    public ResponseEntity<?> getLoserPlayer(){
        RankingDiceDTO loser = managerService.getLoser();
        return new ResponseEntity<>(loser, HttpStatus.OK);
    }
    @GetMapping("/winner")
    public ResponseEntity<?> getWinnerPlayer(){
        RankingDiceDTO winner = managerService.getWinner();
        return new ResponseEntity<>(winner, HttpStatus.OK);
    }

}
