package com.games.controllers;

import com.games.model.dto.PlayerDTO;
import com.games.model.entity.Player;
import com.games.model.services.ManagerService;
import com.games.security.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/players")
public class PlayerController {
    @Autowired
    ManagerService managerService;

    @Autowired
    JwtService jwtService;


    @PostMapping
    public ResponseEntity<?> createPlayer (@RequestBody Player player){
        Player playerDB = managerService.createPlayer(player);
        return new ResponseEntity<>(playerDB, HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<?> updatePlayerName(@RequestHeader("Authorization") String token, @RequestBody Map<String,String> nickname){
        Player playerdb = jwtService.returnPlayer(token);
        playerdb.setNickname(nickname.get("nickname"));
        PlayerDTO playerDTO = managerService.modifyUsername(playerdb);
        return new ResponseEntity<>(playerDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllPlayers(){
        List<PlayerDTO> playerDTOList = managerService.getAllPlayers();
        return new ResponseEntity<>(playerDTOList, HttpStatus.FOUND);
    }



}
