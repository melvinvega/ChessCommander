package com.capstone.chesscommander.chesscommander.GameLogic;

/*
 * Moves the game. 
 */
public class GameHandler {
    String currentPlayer;
    String playerColor;
    String opponentType;
    int ply;
    int turnNum;
    String gameType;
    boolean standardGame;
    boolean castlingRightsWhiteKingRook;
    boolean castlingRightsWhiteQueenRook;
    boolean castlingRightsBlackKingRook;
    boolean castlingRIghtsBlackQueenRook;

    Board past = new Board();
    Board current = new Board();
    Board holder = new Board();
    int[] lastMove;
    // Engine class here
    String difficulty;

    public GameHandler(String cp, String pc, String gt, String d){
        currentPlayer = cp;
        playerColor = pc;
        ply = 0;
        turnNum = 1;
        gameType = gt;
        if(!gameType.equals("fp")) {
            standardGame = true;
        }
        else{
            standardGame = false;
        }
        if(standardGame){
            castlingRightsBlackKingRook = true;
            castlingRightsWhiteQueenRook = true;
            castlingRightsWhiteKingRook = true;
            castlingRIghtsBlackQueenRook = true;
        }
        else{
            castlingRightsBlackKingRook = false;
            castlingRightsWhiteQueenRook = false;
            castlingRightsWhiteKingRook = false;
            castlingRIghtsBlackQueenRook = false;
        }

        difficulty = d;

        startGame();
    }

    public void startGame() {
        if (standardGame) {
            past.setInitialPosition();
            current.setInitialPosition();
            holder.setInitialPosition();

            // set up physical board here
        }
        else{
            // free play code here
        }

        if(gameType.equals("pvp")){
            currentPlayer = "white";
            // code for physical move here
            // update so the move can only be performed if the attempted move is equal to currentPlayer

            if(current.list.checkStalemate(Character.toUpperCase(currentPlayer.charAt(0)))){
                // code for draw
            }

        }
        else if(gameType.equals("pve")){
            currentPlayer = "white";
            if(playerColor.equals(currentPlayer)){
                //code for physical move here
                // update so the move can only be performed if the attempted move is equal to currentPlayer
            }
        }
    }
}
