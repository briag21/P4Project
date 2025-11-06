package com.tictactoe;

public class ApiRequest {
    char[][] board;
    char player;

    public ApiRequest(char[][]board, char player){
        this.board = board;
        this.player = player;
    }
}
