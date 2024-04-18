package com.games.exceptions;

public class ExcpPlayerNull extends RuntimeException{
    public ExcpPlayerNull(){
        super("Data cannot be empty");
    }
}
