package com.games.exceptions;

public class ExcpPlayerNotCreated extends RuntimeException{
    public ExcpPlayerNotCreated(){
        super("Can't create a new player at BBDD, check the information you send");
    }
}
