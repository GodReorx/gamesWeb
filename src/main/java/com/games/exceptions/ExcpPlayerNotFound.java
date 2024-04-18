package com.games.exceptions;

public class ExcpPlayerNotFound extends RuntimeException {
    public ExcpPlayerNotFound(Integer id){
        super("Player not found with ID: " + id );
    }
}
