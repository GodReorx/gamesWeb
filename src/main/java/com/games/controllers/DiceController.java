package com.games.controllers;

import com.games.model.dto.DiceGameDTO;
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
import java.util.Optional;
//ToDo: Usar el JwtService para chequear los usuarios, asi quitas los metodos de abajo
@RestController
@RequestMapping("/players/{id}/games")

public class DiceController {
    @Autowired
    ManagerService managerService;
    @Autowired
    PlayerRepository playerRepository;

    private static final String SECRET_KEY = "18D85BD7369C09B7F7F403EABC33B71BC0B938CAEE9E6E6C507290D06CF6DF7C";


    @PostMapping
    public ResponseEntity<?> rollDices(@RequestHeader("Authorization") String token, @PathVariable("id") Integer id){
        if(checkUser(token,id)) {
            PlayerDTO playerDTO = managerService.rollDices(id);
            return new ResponseEntity<>(playerDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAllRolls(@RequestHeader("Authorization") String token, @PathVariable("id") Integer id){
        if(checkUser(token,id)) {
            managerService.deleteAllRolls(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping
    public ResponseEntity<?> allPlayerRolls(@RequestHeader("Authorization") String token, @PathVariable("id") Integer id){
        if(checkUser(token,id)) {
            List<DiceGameDTO> diceGameDTOList = managerService.getAllPlayerRolls(id);
            return new ResponseEntity<>(diceGameDTOList, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    private boolean checkUser(String token, Integer id){
        String user = extractUser(token);
        Optional<Player> playerDB = playerRepository.findById(id);
        if(user.equals(playerDB.get().getEmail())){
            return true;
        } else {
            return false;
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
