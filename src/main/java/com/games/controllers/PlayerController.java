package com.games.controllers;

import com.games.model.dto.DiceGameDTO;
import com.games.model.dto.PlayerDTO;
import com.games.model.dto.RankingDiceDTO;
import com.games.model.entity.Player;
import com.games.model.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("")
public class PlayerController {

    @Autowired
    ManagerService managerService;

    @PostMapping("/players")
    public ResponseEntity<?> createPlayer (@RequestBody Player player){
        PlayerDTO playerDTO = managerService.createPlayer(player);
        return new ResponseEntity<>(playerDTO, HttpStatus.CREATED);
    }
    @PutMapping("/players")
    public ResponseEntity<?> updatePlayerName(@RequestBody Player player){
        PlayerDTO playerDTO = managerService.modifyUsername(player);
        return new ResponseEntity<>(playerDTO,HttpStatus.OK);
    }
    @PostMapping("/players/{id}/games")
    public ResponseEntity<?> rollDices(@PathVariable("id") String id){
        PlayerDTO playerDTO = managerService.rollDices(id);
        return new ResponseEntity<>(playerDTO,HttpStatus.OK);
    }
    @DeleteMapping("/players/{id}/games")
    public ResponseEntity<?> deleteAllRolls(@PathVariable("id") String id){
        managerService.deleteAllRolls(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/players")
    public ResponseEntity<?> getAllPlayers(){
        List<PlayerDTO> playerDTOList = managerService.getAllPlayers();
        return new ResponseEntity<>(playerDTOList, HttpStatus.FOUND);
    }
    @GetMapping("/players/{id}/games")
    public ResponseEntity<?> allPlayerRolls(@PathVariable("id") String id){
        List<DiceGameDTO> diceGameDTOList = managerService.getAllPlayerRolls(id);
        return new ResponseEntity<>(diceGameDTOList, HttpStatus.OK);
    }
    @GetMapping("/players/ranking")
    public ResponseEntity<?> getRanking(){
        List<RankingDiceDTO> rankingList = managerService.getRanking();
        return new ResponseEntity<>(rankingList,HttpStatus.OK);
    }
    @GetMapping("/players/ranking/loser")
    public ResponseEntity<?> getLoserPlayer(){
        RankingDiceDTO loser = managerService.getLoser();
        return new ResponseEntity<>(loser, HttpStatus.OK);
    }
    @GetMapping("/players/ranking/winner")
    public ResponseEntity<?> getWinnerPlayer(){
        RankingDiceDTO winner = managerService.getWinner();
        return new ResponseEntity<>(winner, HttpStatus.OK);
    }
}
