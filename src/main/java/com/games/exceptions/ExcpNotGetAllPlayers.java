package com.games.exceptions;

public class ExcpNotGetAllPlayers extends RuntimeException{
    public ExcpNotGetAllPlayers(){
        super("There are no players in the database.");
    }
}
