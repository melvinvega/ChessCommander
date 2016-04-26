package com.capstone.chesscommander.chesscommander.GameLogic;

/**
 * The class Tile contains information pertaining to the contents of 1 tile in the chess
 * board.
 * 
 * @author Eduardo Acevedo
 *
 */

public class Tile {

	boolean containsPiece;
	Piece pieceInTile;
	String notation;
	boolean isPromotionSquare;
	int id;
	
	/**
	 *  Blank constructor for the class. Tiles it generates will have id
	 *  value of -1.
	 */
	public Tile() {
		containsPiece = false;
		pieceInTile = new Piece();
		notation = "x0";
		isPromotionSquare = false;
		id = -1;
	}
	
	/**
	 * Recommended constructor for the class. 
	 * @param p - com.capstone.chesscommander.chesscommander.GameLogic.Piece to be set into the tile
	 * @param n - Algebraic notation to represent the tile
	 * @param ips - Sets whether or not the tile is a promotion square
	 * @param i - Numeric ID.
	 */
	public Tile(Piece p, String n, boolean ips, int i){
		pieceInTile = p;
		if(pieceInTile.getType() != 'x'){
			containsPiece = true;
		}
		else{
			containsPiece = false;
			}
		notation = n;
		isPromotionSquare = ips;
		id = i;
	}
	
	public Tile( String n, boolean ips, int i){
		containsPiece = false;
		notation = n;
		isPromotionSquare = ips;
		id = i;
	}
	
	public Tile(Tile copy){
		this.containsPiece = copy.containsPiece;
		this.pieceInTile = copy.pieceInTile;
		this.notation = copy.notation;
		this.isPromotionSquare = copy.isPromotionSquare;
		this.id = copy.id;
	}
	
	
	/**
	 * Method used to set the piece contained by the tile
	 * 
	 * @param in 
	 * @return containsPiece
	 */
	
	boolean setPiece(Piece in){
		pieceInTile = in;
		if(pieceInTile.getType() != 'x'){
			containsPiece = true;
		}
		return containsPiece;
	}
	
	public Piece getPiece(){
		return pieceInTile;
	}
	
	public char getPieceChar(){
		if(containsPiece){
		return pieceInTile.getType();
		}
		else
			return 'x';
	}
	
	
	/**
	 * Toggles whether or not the tile is a promotion tile. Returns what the function
	 * switched the variable to.
	 * @return 
	 */
	boolean setPromotionSquare(){
		isPromotionSquare = !isPromotionSquare;
		return isPromotionSquare;
	}
	
	/**
	 * Sets tile ID in terms of algebraic notation 
	 * @param n
	 * @return
	 */
	String setNotation(String n){
		notation = n;
		return notation;
	}
	
	/**
	 * Sets Tile ID number
	 * @param i
	 * @return id
	 */
	int setID(int i){
		id = i;
		return id;
	}
	
	/**
	 * Returns true if the tile is a promotion square, false if it isn't.
	 * @return isPromotionSquare
	 */
	boolean getIfPromotionSquare(){
		return isPromotionSquare;
	}
	
	/**
	 * Returns the algebraic notation that represents the tile.
	 * @return notation
	 */
	public String getNotation(){
		return notation;
	}
	
	/**
	 * Returns the ID value of the tile.
	 * @return id
	 */
	public int getID(){
		return id;
	}
	
	/**
	 * Returns true if the tile is occupied by a piece, false if it isn't.
	 * @return
	 */
	public boolean getIfOccupied(){
		return containsPiece;
	}
	
	boolean removePiece(){
		if(containsPiece){
			pieceInTile = null;
			containsPiece = false;
			return true;
		}
		else{
			return false;
		}
	}
}
