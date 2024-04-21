package com.games.controllers;

import com.games.model.dto.PlayerDTO;
import com.games.model.entity.Player;
import com.games.model.services.player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @PostMapping("/players")
    public ResponseEntity<?> createPlayer (@RequestBody Player player){
        PlayerDTO playerDTO = playerService.createPlayer(player);
        return new ResponseEntity<>(playerDTO, HttpStatus.CREATED);
    }

    /*URL’s:
POST: /players: crea un jugador/a.
PUT /players: modifica el nom del jugador/a.
POST /players/{id}/games/ : un jugador/a específic realitza una tirada dels daus.
DELETE /players/{id}/games: elimina les tirades del jugador/a.
GET /players/: retorna el llistat de tots  els jugadors/es del sistema amb el seu  percentatge mitjà d’èxits.
GET /players/{id}/games: retorna el llistat de jugades per un jugador/a.
GET /players/ranking: retorna el ranking mig de tots els jugadors/es del sistema. És a dir, el  percentatge mitjà d’èxits.
GET /players/ranking/loser: retorna el jugador/a  amb pitjor percentatge d’èxit.
GET /players/ranking/winner: retorna el  jugador amb pitjor percentatge d’èxit. */
}
