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

    private boolean succesfulMove;

    Board past = new Board();
    Board current = new Board();
    Board holder = new Board();
    int[] lastMove;
    int[] lastMoveEx;
    // Engine class here
    String difficulty;

    public GameHandler(){
        ply = 0;
        turnNum = 1;
    }

    /*
   Gets the game going
    */
    public void startGame() {

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

        int co;
        char gameResult;
        if (standardGame) {
            past.setInitialPosition();
            //current =
            holder.setInitialPosition();

            // set up physical board here
        }
        else{
            // free play code here
            if(opponentType.equals("player")){
                gameType = "pvp";
            }
            else if(opponentType.equals("computer")){
                gameType = "pve";
            }
        }

        if(gameType.equals("pvp")){
            currentPlayer = "white";

            while(true){

                // code for physical move here
                // update so the move can only be performed if the attempted move is equal to currentPlayer
                // proceed only if move was performed. In other words if move returns true.

                if(succesfulMove){ //true is a placeholder for move
                    // save the values of the squares used in move for storage in last move later

                    // helper.move(this.startSquare, this.endSquare, currentPlayer, true);

                    if (current.list.verifyCheckmate((Character.toUpperCase(currentPlayer.charAt(0))))) {
                        gameResult = 'W';
                        break;
                    }
                    else if (current.list.checkStalemate(Character.toUpperCase(currentPlayer.charAt(0)))) {
                        gameResult = 'D';
                        break;
                    }
                    if(ply != 1){
                        if(lastMove[2] == 0){
                            past.move(lastMove[0],lastMove[1],'W',true);
                        }
                        else if(lastMove[2] == 1){
                            past.move(lastMove[0],lastMove[1],'B',true);
                        }
                    }

                    if(currentPlayer.equals("white")){
                        co = 0;
                    }
                    else{
                        co = 1;
                    }

                    lastMove[0] = 0; // Placeholder. Value of start square used in move
                    lastMove[1] = 0; // Placeholder. Value of end square used in move
                    lastMove[2] = co; //Contains value assigned to each color

                    if (currentPlayer.equals("white")) {
                        ply++;
                        currentPlayer = "black";
                    }
                    else{
                        ply++;
                        turnNum++;
                        currentPlayer = "white";
                    }
                    succesfulMove = false;
                }
            }
            succesfulMove = false;
            if(gameResult == 'D'){
                // code for actions upon game draw here
            }
            else if(gameResult == 'W'){
                // code for action upon checkmate here
            }

        }
        else if(gameType.equals("pve")){
            int waitTime;
            switch(difficulty){ // sets wait time for engine to think
                case "easy": waitTime = 1000;
                    break;
                case "medium": waitTime = 2500;
                    break;
                case "hard": waitTime = 10000;
                    break;
                default: waitTime = 2500;
                    break;
            }
            currentPlayer = "white";
            while(true) {
                if (!playerColor.equals(currentPlayer)) {
                    // code for engine functions here
                    // parse values from engine library to integers
                    // save value of move made
                    lastMoveEx[0] = 0;
                    lastMoveEx[1] = 0;
                    if(playerColor.equals("White")){
                        lastMoveEx[2] = 1;
                    }
                    else{
                        lastMoveEx[2] = 0;
                    }
                }

                if(true){ //true is a placeholder for move. Will always return true to an engine move.

                    if (current.list.verifyCheckmate((Character.toUpperCase(currentPlayer.charAt(0))))) {
                        gameResult = 'W';
                        break;
                    }
                    else if (current.list.checkStalemate(Character.toUpperCase(currentPlayer.charAt(0)))) {
                        gameResult = 'D';
                        break;
                    }

                    if(currentPlayer.equals(playerColor)){
                        if(turnNum!=1){
                            if(lastMove[2] == 0){
                                past.move(lastMove[0],lastMove[1],'W',true);
                                past.move(lastMoveEx[0], lastMoveEx[1], 'B', false);
                            }
                            else if(lastMove[2] == 1){
                                past.move(lastMove[0],lastMove[1],'B',true);
                                past.move(lastMoveEx[0],lastMoveEx[1],'W',false);
                            }
                        }
                    }

                    if (currentPlayer.equals("white")) {
                        currentPlayer = "black";
                    }
                    else{
                        currentPlayer = "white";
                    }
                }
            }

            if(gameResult == 'D'){
                // code for actions upon game draw here
            }
            else if(gameResult == 'W'){
                // code for action upon checkmate here
            }
        }
    }

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public String getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(String playerColor) {
        this.playerColor = playerColor;
    }

    public String getOpponentType() {
        return opponentType;
    }

    public void setOpponentType(String opponentType) {
        this.opponentType = opponentType;
    }

    public int getPly() {
        return ply;
    }

    public void setPly(int ply) {
        this.ply = ply;
    }

    public int getTurnNum() {
        return turnNum;
    }

    public void setTurnNum(int turnNum) {
        this.turnNum = turnNum;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public boolean isStandardGame() {
        return standardGame;
    }

    public void setStandardGame(boolean standardGame) {
        this.standardGame = standardGame;
    }

    public boolean isCastlingRightsWhiteKingRook() {
        return castlingRightsWhiteKingRook;
    }

    public void setCastlingRightsWhiteKingRook(boolean castlingRightsWhiteKingRook) {
        this.castlingRightsWhiteKingRook = castlingRightsWhiteKingRook;
    }

    public boolean isCastlingRightsWhiteQueenRook() {
        return castlingRightsWhiteQueenRook;
    }

    public void setCastlingRightsWhiteQueenRook(boolean castlingRightsWhiteQueenRook) {
        this.castlingRightsWhiteQueenRook = castlingRightsWhiteQueenRook;
    }

    public boolean isCastlingRightsBlackKingRook() {
        return castlingRightsBlackKingRook;
    }

    public void setCastlingRightsBlackKingRook(boolean castlingRightsBlackKingRook) {
        this.castlingRightsBlackKingRook = castlingRightsBlackKingRook;
    }

    public boolean isCastlingRIghtsBlackQueenRook() {
        return castlingRIghtsBlackQueenRook;
    }

    public void setCastlingRIghtsBlackQueenRook(boolean castlingRIghtsBlackQueenRook) {
        this.castlingRIghtsBlackQueenRook = castlingRIghtsBlackQueenRook;
    }

    public Board getPast() {
        return past;
    }

    public void setPast(Board past) {
        this.past = past;
    }

    public Board getCurrent() {
        return current;
    }

    public void setCurrent(Board current) {
        this.current = current;
    }

    public Board getHolder() {
        return holder;
    }

    public void setHolder(Board holder) {
        this.holder = holder;
    }

    public int[] getLastMove() {
        return lastMove;
    }

    public void setLastMove(int[] lastMove) {
        this.lastMove = lastMove;
    }

    public int[] getLastMoveEx() {
        return lastMoveEx;
    }

    public void setLastMoveEx(int[] lastMoveEx) {
        this.lastMoveEx = lastMoveEx;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setSuccesfulMove(boolean result){
        succesfulMove = result;
    }

    public String getCurrentPlayer(){
        return currentPlayer;
    }

}
