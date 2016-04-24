package com.capstone.chesscommander.chesscommander.GameLogic;/*
 * Records move information in game
 * @author Eduardo
 */

public class Move {
	
	Piece movedPiece;
	int startSquareID;
	int endSquareID;
	boolean hasCapture;
	boolean wasWhiteKing;
	boolean wasBlackKing;
	boolean wasWhiteKingRookF;
	boolean wasBlackKingRookF;
	boolean wasWhiteQueenRookF;
	boolean wasBlackQueenRookF;
	boolean wasPawnMove;
	boolean isPromotion;
	int ply;
	int moveNumber;
	char moveDoneBy;
	Tile[] boardBeforeMove = new Tile [64];
	
	
	public Move(Piece p, int start, int end, boolean hC, boolean wk, boolean bk, boolean wkr, boolean bkr,
			boolean wqr, boolean bqr, boolean pm, boolean promo, int pl, int m, char c, Tile[] b){
		movedPiece = p;
		startSquareID = start;
		endSquareID = end;
		hasCapture = hC;
		wasWhiteKing = wk;
		wasBlackKing = bk;
		wasWhiteKingRookF = wkr;
		wasBlackKingRookF = bkr;
		wasWhiteQueenRookF = wqr;
		wasBlackQueenRookF = bqr;
		wasPawnMove = pm;
		isPromotion = promo;
		ply = pl;
		moveNumber = m;
		moveDoneBy= c;
		
		boardBeforeMove = b;
	}


	public Piece getMovedPiece() {
		return movedPiece;
	}


	public void setMovedPiece(Piece movedPiece) {
		this.movedPiece = movedPiece;
	}


	public int getStartSquareID() {
		return startSquareID;
	}


	public void setStartSquareID(int startSquareID) {
		this.startSquareID = startSquareID;
	}


	public int getEndSquareID() {
		return endSquareID;
	}


	public void setEndSquareID(int endSquareID) {
		this.endSquareID = endSquareID;
	}


	public boolean isHasCapture() {
		return hasCapture;
	}


	public void setHasCapture(boolean hasCapture) {
		this.hasCapture = hasCapture;
	}


	public boolean isWasWhiteKing() {
		return wasWhiteKing;
	}


	public void setWasWhiteKing(boolean wasWhiteKing) {
		this.wasWhiteKing = wasWhiteKing;
	}


	public boolean isWasBlackKing() {
		return wasBlackKing;
	}


	public void setWasBlackKing(boolean wasBlackKing) {
		this.wasBlackKing = wasBlackKing;
	}


	public boolean isWasWhiteKingRook() {
		return wasWhiteKingRookF;
	}


	public void setWasWhiteKingRook(boolean wasWhiteKingRook) {
		this.wasWhiteKingRookF = wasWhiteKingRook;
	}


	public boolean isWasBlackKingRook() {
		return wasBlackKingRookF;
	}


	public void setWasBlackKingRook(boolean wasBlackKingRook) {
		this.wasBlackKingRookF = wasBlackKingRook;
	}


	public boolean isWasWhiteQueenRook() {
		return wasWhiteQueenRookF;
	}


	public void setWasWhiteQueenRook(boolean wasWhiteQueenRook) {
		this.wasWhiteQueenRookF = wasWhiteQueenRook;
	}


	public boolean isWasBlackQueenRook() {
		return wasBlackQueenRookF;
	}


	public void setWasBlackQueenRook(boolean wasBlackQueenRook) {
		this.wasBlackQueenRookF = wasBlackQueenRook;
	}


	public boolean isWasPawnMove() {
		return wasPawnMove;
	}


	public void setWasPawnMove(boolean wasPawnMove) {
		this.wasPawnMove = wasPawnMove;
	}


	public boolean isPromotion() {
		return isPromotion;
	}


	public void setPromotion(boolean isPromotion) {
		this.isPromotion = isPromotion;
	}


	public int getPly() {
		return ply;
	}


	public void setPly(int ply) {
		this.ply = ply;
	}


	public int getMoveNumber() {
		return moveNumber;
	}


	public void setMoveNumber(int moveNumber) {
		this.moveNumber = moveNumber;
	}


	public char getMoveDoneBy() {
		return moveDoneBy;
	}


	public void setMoveDoneBy(char moveDoneBy) {
		this.moveDoneBy = moveDoneBy;
	}


	public Tile[] getBoardBeforeMove() {
		return boardBeforeMove;
	}


	public void setBoardBeforeMove(Tile[] boardBeforeMove) {
		this.boardBeforeMove = boardBeforeMove;
	}
	
}