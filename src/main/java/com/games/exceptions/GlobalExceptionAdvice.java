package com.games.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ExcpPlayerNotFound.class)
    public ResponseEntity<String> handlePlayerNotFound(ExcpPlayerNotFound ex){
        return new ResponseEntity<>("ERROR: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ExcpPlayerNotCreated.class)
    public ResponseEntity<String> handlePlayerNotCreated(ExcpPlayerNotCreated ex){
        return new ResponseEntity<>("ERROR: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ExcpPlayerNull.class)
    public ResponseEntity<String> handlePlayerIsNull(ExcpPlayerNull ex){
        return new ResponseEntity<>("ERROR: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ExcpNotGetAllPlayers.class)
    public ResponseEntity<String> handleNotGetAllPlayers(ExcpNotGetAllPlayers ex){
        return new ResponseEntity<>("ERROR: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ExcpNotRanking.class)
    public ResponseEntity<String> handleNotRanking(ExcpNotRanking ex){
        return new ResponseEntity<>("ERROR: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ExcpPlayerExist.class)
    public ResponseEntity<String> handlePlayerExist(ExcpPlayerExist ex){
        return new ResponseEntity<>("ERROR: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ExcpIncoFormatEmail.class)
    public ResponseEntity<String> handleIncorFormatEmail(ExcpIncoFormatEmail ex){
        return new ResponseEntity<>("ERROR: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ExcpPlayerHaveNoRolls.class)
    public ResponseEntity<String> handleIncorFormatEmail(ExcpPlayerHaveNoRolls ex){
        return new ResponseEntity<>("ERROR: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}