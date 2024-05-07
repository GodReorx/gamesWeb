package com.games.exceptions;

public class ExcpPlayerHaveNoRolls extends RuntimeException{
    public ExcpPlayerHaveNoRolls(){
        super("Player have no rolls");
    }
}
