package com.games.controllers;

import com.games.model.dto.DiceGameDTO;
import com.games.model.dto.PlayerDTO;
import com.games.model.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players/{id}/games")

public class DiceController {
    @Autowired
    ManagerService managerService;

    @PostMapping
    public ResponseEntity<?> rollDices(@PathVariable("id") String id){
        PlayerDTO playerDTO = managerService.rollDices(id);
        return new ResponseEntity<>(playerDTO, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAllRolls(@PathVariable("id") String id){
        managerService.deleteAllRolls(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> allPlayerRolls(@PathVariable("id") String id){
        List<DiceGameDTO> diceGameDTOList = managerService.getAllPlayerRolls(id);
        return new ResponseEntity<>(diceGameDTOList, HttpStatus.OK);
    }
}
