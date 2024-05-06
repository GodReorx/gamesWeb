package com.games.controllers;

import com.games.model.dto.DiceGameDTO;
import com.games.model.dto.PlayerDTO;
import com.games.model.services.ManagerService;
import com.games.security.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players/{id}/games")

public class DiceController {
    @Autowired
    private ManagerService managerService;
    @Autowired
    private JwtService jwtService;

    private static final String SECRET_KEY = "18D85BD7369C09B7F7F403EABC33B71BC0B938CAEE9E6E6C507290D06CF6DF7C";


    @PostMapping
    public ResponseEntity<?> rollDices(@RequestHeader("Authorization") String token, @PathVariable("id") Integer id){
        if(jwtService.checkUser(token,id)) {
            PlayerDTO playerDTO = managerService.rollDices(id);
            return new ResponseEntity<>(playerDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAllRolls(@RequestHeader("Authorization") String token, @PathVariable("id") Integer id){
        if(jwtService.checkUser(token,id)) {
            managerService.deleteAllRolls(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping
    public ResponseEntity<?> allPlayerRolls(@RequestHeader("Authorization") String token, @PathVariable("id") Integer id){
        if(jwtService.checkUser(token,id)) {
            List<DiceGameDTO> diceGameDTOList = managerService.getAllPlayerRolls(id);
            return new ResponseEntity<>(diceGameDTOList, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
