package com.games.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ExcpPlayerNotFound.class)
    public ResponseEntity<String> handleFlowerNotFoundException(ExcpPlayerNotFound ex){
        return new ResponseEntity<>("ERROR: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ExcpPlayerNotCreated.class)
    public ResponseEntity<String> handleFlowerNotCreated(ExcpPlayerNotCreated ex){
        return new ResponseEntity<>("ERROR: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ExcpPlayerNull.class)
    public ResponseEntity<String> handleFlowerIsNull(ExcpPlayerNull ex){
        return new ResponseEntity<>("ERROR: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ExcpNotGetAllPlayers.class)
    public ResponseEntity<String> handleFlowerNotGetAll(ExcpNotGetAllPlayers ex){
        return new ResponseEntity<>("ERROR: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}