package com.games.exceptions;

public class ExcpNicknameExist extends RuntimeException{
    public ExcpNicknameExist(){
        super("This nickname is already in use, try another.");
    }
}
