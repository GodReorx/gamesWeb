package com.games.exceptions;

public class ExcpPlayerExist extends RuntimeException{
    public ExcpPlayerExist(){
        super("This user exist, try another.");
    }
}
