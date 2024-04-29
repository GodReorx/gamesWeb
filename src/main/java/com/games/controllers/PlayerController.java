package com.games.controllers;

import com.games.model.dto.PlayerDTO;
import com.games.model.entity.Player;
import com.games.model.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    ManagerService managerService;

    @PostMapping
    public ResponseEntity<?> createPlayer (@RequestBody Player player){
        Player playerDB = managerService.createPlayer(player);
        return new ResponseEntity<>(playerDB, HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<?> updatePlayerName(@RequestBody Player player){
        PlayerDTO playerDTO = managerService.modifyUsername(player);
        return new ResponseEntity<>(playerDTO,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllPlayers(){
        List<PlayerDTO> playerDTOList = managerService.getAllPlayers();
        return new ResponseEntity<>(playerDTOList, HttpStatus.FOUND);
    }


}
