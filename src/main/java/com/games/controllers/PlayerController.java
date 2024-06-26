package com.games.controllers;

import com.games.model.dto.PlayerDTO;
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
    private ManagerService managerService;

    @Autowired
    private JwtService jwtService;


    @PutMapping
    public ResponseEntity<?> updatePlayerName(@RequestHeader("Authorization") String token, @RequestBody Map<String,String> nickname){
        String jwtToken = token.substring(7);
        PlayerDTO playerDTO = managerService.modifyUsername(jwtToken, nickname.get("nickname"));
        return new ResponseEntity<>(playerDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllPlayers(){
        List<PlayerDTO> playerDTOList = managerService.getAllPlayers();
        return new ResponseEntity<>(playerDTOList, HttpStatus.FOUND);
    }



}
