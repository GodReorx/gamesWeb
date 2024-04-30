package com.games.controllers;

import com.games.exceptions.ExcpPlayerNull;
import com.games.model.dto.PlayerDTO;
import com.games.model.entity.Player;
import com.games.model.repository.PlayerRepository;
import com.games.model.services.ManagerService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/players")
public class PlayerController {
    @Autowired
    ManagerService managerService;
    @Autowired
    PlayerRepository playerRepository;
    private static final String SECRET_KEY = "18D85BD7369C09B7F7F403EABC33B71BC0B938CAEE9E6E6C507290D06CF6DF7C";


    @PostMapping
    public ResponseEntity<?> createPlayer (@RequestBody Player player){
        Player playerDB = managerService.createPlayer(player);
        return new ResponseEntity<>(playerDB, HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<?> updatePlayerName(@RequestHeader("Authorization") String token, @RequestBody Map<String,String> nickname){
        Player playerdb = returnPlayer(token);
        playerdb.setNickname(nickname.get("nickname"));
        PlayerDTO playerDTO = managerService.modifyUsername(playerdb);
        return new ResponseEntity<>(playerDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllPlayers(){
        List<PlayerDTO> playerDTOList = managerService.getAllPlayers();
        return new ResponseEntity<>(playerDTOList, HttpStatus.FOUND);
    }

    public boolean checkUser(String token, Integer id){
        String user = extractUser(token);
        Optional<Player> playerDB = playerRepository.findById(id);
        if(user.equals(playerDB.get().getEmail())){
            return true;
        } else {
            return false;
        }
    }
    public Player returnPlayer(String token){
        String user = extractUser(token);
        Optional<Player> playerDB = playerRepository.findUserByEmail(user);
        if(playerDB.isPresent()){
            return playerDB.get();
        } else {
            throw new ExcpPlayerNull();
        }
    }
    private String extractUser(String token){
        String jwtToken = token.substring(7);
        Claims claims = Jwts.parser().verifyWith(getSignInKey()).build().parseSignedClaims(jwtToken).getPayload();
        String authUser = claims.getSubject();
        return authUser;
    }
    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
