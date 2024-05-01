package com.games.exceptions;

public class ExcpNotRanking extends RuntimeException{
    public ExcpNotRanking(){
        super("There are no players in the ranking.");
    }
}
