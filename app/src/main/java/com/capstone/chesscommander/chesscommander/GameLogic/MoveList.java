package com.capstone.chesscommander.chesscommander.GameLogic;

import java.util.ArrayList;
import java.util.List;

public class MoveList {

	Tile[] board = new Tile [64];
	Tile[] helpBoard = new Tile[64];
	Board helper = new Board();
	char color;
	List<Tile> pieces = new ArrayList<Tile>();
	List<Tile> whites = new ArrayList<Tile>();
	List<Tile> blacks = new ArrayList<Tile>();
	Tile wKing = new Tile();
	Tile bKing = new Tile();
	List<ShortMove> whiteMoves = new ArrayList<ShortMove>();
	List<ShortMove> blackMoves = new ArrayList<ShortMove>();
	List<ShortMove> wKingMoves = new ArrayList<ShortMove>();
	List<ShortMove> bKingMoves = new ArrayList<ShortMove>();
	List<ShortMove> hWhiteMoves = new ArrayList<ShortMove>();
	List<ShortMove> hBlackMoves = new ArrayList<ShortMove>();
	List<ShortMove> hwKingMoves = new ArrayList<ShortMove>();
	List<ShortMove> hbKingMoves = new ArrayList<ShortMove>();
	int doublePawnMove;
	boolean wkm;
	boolean bkm;
	boolean wkrm;
	boolean wqrm;
	boolean bkrm;
	boolean bqrm;
	boolean help;


	public MoveList(Tile[] t, char c, int dpm, boolean wk, boolean bk, boolean wkr, boolean wqr, boolean bkr, boolean bqr, boolean helper){
		board = t;
		color = c;
		wkm = wk;
		bkm = bk;
		wkrm = wkr;
		wqrm = wqr;
		bkrm = bkr;
		bqrm = bqr;
		help = helper;

		if(dpm != -1){
			doublePawnMove = dpm;
		}
		else{
			doublePawnMove = - 100;
		}


	}

	public void setList(){
		genHelpBoard();
		findOccupiedTiles();
		findWhiteTiles();
		findBlackTiles();
		findWhiteMoves();
		findBlackMoves();
		if(!help){
			findKingMoves();
		}
	}

	public void setHelpList(){
		whiteMoves = new ArrayList<ShortMove>();
		blackMoves = new ArrayList<ShortMove>();
		helpWhiteMoves();
		helpBlackMoves();
	}


	private void findKingMoves(){
		findWhiteKingMoves();
		findBlackKingMoves();
	}

	private void genHelpBoard(){
		int curr = -1;
		String  c;
		int  r;

		for(int i = 7; i >= 0; i--){
			for(int j = 0; j < 8; j++){
				curr++;
				switch (j){
					case 0: c = "a";
						break;
					case 1: c = "b";
						break;
					case 2: c = "c";
						break;
					case 3: c = "d";
						break;
					case 4: c = "e";
						break;
					case 5: c = "f";
						break;
					case 6: c = "g";
						break;
					case 7: c = "h";
						break;
					default: c = "x";
				}

				r = 1 + i;

				helpBoard[curr] = new Tile( c + r , (i == 0 || i == 7) , curr);
			}
		}
		for(int i = 0; i < 64; i++){
			if(board[i].getIfOccupied()){
				if(board[i].getPiece().getColor() == 'W'){
					char p = board[i].getPieceChar();
					switch(p){
						case 'P':
							helpBoard[i].setPiece(new Piece('P','W'));
							break;
						case 'R':
							helpBoard[i].setPiece(new Piece('R','W'));
							break;
						case 'B':
							helpBoard[i].setPiece(new Piece('B','W'));
							break;
						case 'N':
							helpBoard[i].setPiece(new Piece('N','W'));
							break;
						case 'K':
							helpBoard[i].setPiece(new Piece('K','W'));
							break;
						case 'Q':
							helpBoard[i].setPiece(new Piece('P','W'));
							break;
					}
				}
				else if(board[i].getPiece().getColor() == 'B'){
					char p = board[i].getPieceChar();
					switch(p){
						case 'p':
							helpBoard[i].setPiece(new Piece('p','B'));
							break;
						case 'r':
							helpBoard[i].setPiece(new Piece('r','B'));
							break;
						case 'b':
							helpBoard[i].setPiece(new Piece('b','B'));
							break;
						case 'n':
							helpBoard[i].setPiece(new Piece('n','B'));
							break;
						case 'k':
							helpBoard[i].setPiece(new Piece('k','B'));
							break;
						case 'q':
							helpBoard[i].setPiece(new Piece('q','B'));
							break;
					}
				}
			}
		}
	}

	private void findOccupiedTiles(){
		int n = 0;
		for(int i = 0; i < 64; i++){
			if(board[i].getIfOccupied()){
				pieces.add(board[i]);
				n++;
			}
		}

	}

	private void findWhiteTiles(){
		int n = 0;
		for(int i = 0; i <pieces.size(); i++){
			if(pieces.get(i).getPiece().getColor() == 'W'){
				whites.add(pieces.get(i));
				n++;
			}
		}
	}

	private void findBlackTiles(){
		int n = 0;
		for(int i = 0; i <pieces.size(); i++){
			if(pieces.get(i).getPiece().getColor() == 'B'){
				blacks.add(pieces.get(i));
				n++;
			}
		}
	}

	private boolean checkForPiece(int id){
		return board[id].getIfOccupied();
	}

	private boolean checkForWhitePiece(int id){
		if(board[id].containsPiece){
			if(board[id].getPiece().getColor() == 'W'){
				return true;
			}
			else{
				return false;
			}
		}
		else
			return false;
	}

	private boolean checkForBlackPiece(int id){
		if(board[id].containsPiece){
			if(board[id].getPiece().getColor() == 'B'){
				return true;
			}
			else{
				return false;
			}
		}
		else
			return false;
	}

	private void findWhiteMoves(){

		for (Tile tile : whites){
			if(tile.getPieceChar() == 'Q'){
				int colPos = tile.getID() % 8;
				int rankPos = (tile.getID() - (tile.getID() % 8)) / 8;
				for(int i = 1; colPos - i >= 0; i++){
					if(checkForWhitePiece(tile.getID() - i)){
						break;
					}
					if(checkForBlackPiece(tile.getID() - i)){
						if(!wouldBeCheck(tile.getID(), tile.getID() - i, 'W')) {
							whiteMoves.add(new ShortMove('W', 'Q', tile.getID(), tile.getID() - i));
						}
						break;
					}
					else{
						if(!wouldBeCheck(tile.getID(), tile.getID() - i, 'W')) {
							whiteMoves.add(new ShortMove('W', 'Q', tile.getID(), tile.getID() - i));
						}
					}
				}
				for(int i = 1; colPos + i <= 7; i++){
					if(checkForWhitePiece(tile.getID() + i)){
						break;
					}
					if(checkForBlackPiece(tile.getID() + i)){
						if(!wouldBeCheck(tile.getID(), tile.getID() + i, 'W')) {
							whiteMoves.add(new ShortMove('W', 'Q', tile.getID(), tile.getID() + i));
						}
						break;
					}
					else{
						if(!wouldBeCheck(tile.getID(), tile.getID() + i, 'W')) {
							whiteMoves.add(new ShortMove('W', 'Q', tile.getID(), tile.getID() + i));
						}
					}
				}
				for(int i = 1; rankPos - i >= 0; i++){
					if(checkForWhitePiece(tile.getID() - (8 * i))){
						break;
					}
					if(checkForBlackPiece(tile.getID() - (8 * i))){
						if(!wouldBeCheck(tile.getID(), tile.getID() - (8*i), 'W')) {
							whiteMoves.add(new ShortMove('W', 'Q', tile.getID(), tile.getID() - (8 * i)));
						}
						break;
					}
					else{
						if(!wouldBeCheck(tile.getID(), tile.getID() - (8*i), 'W')) {
							whiteMoves.add(new ShortMove('W', 'Q', tile.getID(), tile.getID() - (8 * i)));
						}
					}
				}
				for(int i = 1; rankPos + i <= 7; i++){
					if(checkForWhitePiece(tile.getID() + (8 * i))){
						break;
					}
					if(checkForBlackPiece(tile.getID() + (8 * i))){
						if(!wouldBeCheck(tile.getID(), tile.getID() + (8*i), 'W')) {
							whiteMoves.add(new ShortMove('W', 'Q', tile.getID(), tile.getID() + (8 * i)));
						}
						break;
					}
					else{
						if(!wouldBeCheck(tile.getID(), tile.getID() + (8*i), 'W')) {
							whiteMoves.add(new ShortMove('W', 'Q', tile.getID(), tile.getID() + (8 * i)));
						}
					}
				}

				for(int i = 1; ((rankPos - i) >= 0) && ((colPos - i) >= 0) ; i++){
					if(checkForWhitePiece(((rankPos - i) * 8) + (colPos - i))){
						break;
					}
					if(checkForBlackPiece(((rankPos - i) * 8) + (colPos - i))){
						if(!wouldBeCheck(tile.getID(), ((rankPos - i) * 8) + (colPos - i), 'W')) {
							whiteMoves.add(new ShortMove('W', 'Q', tile.getID(), ((rankPos - i) * 8) + (colPos - i)));
						}
						break;
					}
					else{
						if(!wouldBeCheck(tile.getID(), ((rankPos - i) * 8) + (colPos - i), 'W')) {
							whiteMoves.add(new ShortMove('W', 'Q', tile.getID(), ((rankPos - i) * 8) + (colPos - i)));
						}
					}
				}

				for(int i = 1; ((rankPos + i) <=7) && ((colPos + i) <= 7); i++){
					if(checkForWhitePiece(((rankPos + i)* 8) + (colPos + i))){
						break;
					}
					if(checkForBlackPiece(((rankPos + i)* 8) + (colPos + i))){
						if(!wouldBeCheck(tile.getID(), ((rankPos + i) * 8) + (colPos + i), 'W')) {
							whiteMoves.add(new ShortMove('W', 'Q', tile.getID(), ((rankPos + i) * 8) + (colPos + i)));
						}
						break;
					}
					else{
						if(!wouldBeCheck(tile.getID(), ((rankPos + i) * 8) + (colPos + i), 'W')) {
							whiteMoves.add(new ShortMove('W', 'Q', tile.getID(), ((rankPos + i) * 8) + (colPos + i)));
						}
					}
				}

				for(int i = 1; ((rankPos + i) <=7) && ((colPos - i) >= 0); i++){
					if(checkForWhitePiece(((rankPos + i)* 8) + (colPos - i))){
						break;
					}
					if(checkForBlackPiece(((rankPos + i)* 8) + (colPos - i))){
						if(!wouldBeCheck(tile.getID(), ((rankPos + i) * 8) + (colPos - i), 'W')) {
							whiteMoves.add(new ShortMove('W', 'Q', tile.getID(), ((rankPos + i) * 8) + (colPos - i)));
						}
						break;
					}
					else{
						if(!wouldBeCheck(tile.getID(), ((rankPos + i) * 8) + (colPos - i), 'W')) {
							whiteMoves.add(new ShortMove('W', 'Q', tile.getID(), ((rankPos + i) * 8) + (colPos - i)));
						}
					}
				}

				for(int i = 1; ((rankPos - i) >= 0) && ((colPos + i) <= 7); i++){
					if(checkForWhitePiece(((rankPos - i)* 8) + (colPos + i))){
						break;
					}
					if(checkForBlackPiece(((rankPos - i)* 8) + (colPos + i))){
						if(!wouldBeCheck(tile.getID(), ((rankPos - i) * 8) + (colPos + i), 'W')) {
							whiteMoves.add(new ShortMove('W', 'Q', tile.getID(), ((rankPos - i) * 8) + (colPos + i)));
						}
						break;
					}
					else{
						if(!wouldBeCheck(tile.getID(), ((rankPos - i) * 8) + (colPos + i), 'W')) {
							whiteMoves.add(new ShortMove('W', 'Q', tile.getID(), ((rankPos - i) * 8) + (colPos + i)));
						}
					}
				}
				continue;
			}
			else if(tile.getPieceChar() == 'R'){
				int colPos = tile.getID() % 8;
				int rankPos = (tile.getID() - (tile.getID() % 8)) / 8;
				for(int i = 1; colPos - i >= 0; i++){
					if(checkForWhitePiece(tile.getID() - i)){
						break;
					}
					if(checkForBlackPiece(tile.getID() - i)){
						if(!wouldBeCheck(tile.getID(),  tile.getID() - i, 'W')) {
							whiteMoves.add(new ShortMove('W', 'R', tile.getID(), tile.getID() - i));
						}
						break;
					}
					else{
						if(!wouldBeCheck(tile.getID(),  tile.getID() - i, 'W')) {
							whiteMoves.add(new ShortMove('W', 'R', tile.getID(), tile.getID() - i));
						}
					}
				}
				for(int i = 1; colPos + i <= 7; i++){
					if(checkForWhitePiece(tile.getID() + i)){
						break;
					}
					if(checkForBlackPiece(tile.getID() + i)){
						if(!wouldBeCheck(tile.getID(),  tile.getID() + i, 'W')) {
							whiteMoves.add(new ShortMove('W', 'R', tile.getID(), tile.getID() + i));
						}
						break;
					}
					else{
						if(!wouldBeCheck(tile.getID(),  tile.getID() + i, 'W')) {
							whiteMoves.add(new ShortMove('W', 'R', tile.getID(), tile.getID() + i));
						}
					}
				}
				for(int i = 1; rankPos - i >= 0; i++){
					if(checkForWhitePiece(tile.getID() - (8 * i))){
						break;
					}
					if(checkForBlackPiece(tile.getID() - (8 * i))){
						if(!wouldBeCheck(tile.getID(),  tile.getID() - (8 * i), 'W')) {
							whiteMoves.add(new ShortMove('W', 'R', tile.getID(), tile.getID() - (8 * i)));
						}
						break;
					}
					else{
						if(!wouldBeCheck(tile.getID(),  tile.getID() - (8 * i), 'W')) {
							whiteMoves.add(new ShortMove('W', 'R', tile.getID(), tile.getID() - (8 * i)));
						}
					}
				}
				for(int i = 1; rankPos + i <= 7; i++){
					if(checkForWhitePiece(tile.getID() + (8 * i))){
						break;
					}
					if(checkForBlackPiece(tile.getID() + (8 * i))){
						if(!wouldBeCheck(tile.getID(),  tile.getID() + (8 * i), 'W')) {
							whiteMoves.add(new ShortMove('W', 'R', tile.getID(), tile.getID() + (8 * i)));
						}
						break;
					}
					else{
						if(!wouldBeCheck(tile.getID(),  tile.getID() + (8 * i), 'W')) {
							whiteMoves.add(new ShortMove('W', 'R', tile.getID(), tile.getID() + (8 * i)));
						}
					}
				}
				continue;
			}
			else if(tile.getPieceChar() == 'B'){
				int colPos = tile.getID() % 8;
				int rankPos = (tile.getID() - (tile.getID() % 8)) / 8;

				for(int i = 1; ((rankPos - i) >= 0) && ((colPos - i) >= 0) ; i++){
					if(checkForWhitePiece(((rankPos - i) * 8) + (colPos - i))){
						break;
					}
					if(checkForBlackPiece(((rankPos - i) * 8) + (colPos - i))){
						if(!wouldBeCheck(tile.getID(),  ((rankPos - i) * 8) + (colPos - i), 'W')) {
							whiteMoves.add(new ShortMove('W', 'B', tile.getID(), ((rankPos - i) * 8) + (colPos - i)));
						}
						break;
					}
					else{
						if(!wouldBeCheck(tile.getID(),  ((rankPos - i) * 8) + (colPos - i), 'W')) {
							whiteMoves.add(new ShortMove('W', 'B', tile.getID(), ((rankPos - i) * 8) + (colPos - i)));
						}
					}
				}

				for(int i = 1; ((rankPos + i) <=7) && ((colPos + i) <= 7); i++){
					if(checkForWhitePiece(((rankPos + i)* 8) + (colPos + i))){
						break;
					}
					if(checkForBlackPiece(((rankPos + i)* 8) + (colPos + i))){
						if(!wouldBeCheck(tile.getID(),  ((rankPos + i) * 8) + (colPos + i), 'W')) {
							whiteMoves.add(new ShortMove('W', 'B', tile.getID(), ((rankPos + i) * 8) + (colPos + i)));
						}
						break;
					}
					else{
						if(!wouldBeCheck(tile.getID(),  ((rankPos + i) * 8) + (colPos + i), 'W')) {
							whiteMoves.add(new ShortMove('W', 'B', tile.getID(), ((rankPos + i) * 8) + (colPos + i)));
						}
					}
				}

				for(int i = 1; ((rankPos + i) <=7) && ((colPos - i) >= 0); i++){
					if(checkForWhitePiece(((rankPos + i)* 8) + (colPos - i))){
						break;
					}
					if(checkForBlackPiece(((rankPos + i)* 8) + (colPos - i))){
						if(!wouldBeCheck(tile.getID(),  ((rankPos + i) * 8) + (colPos - i), 'W')) {
							whiteMoves.add(new ShortMove('W', 'B', tile.getID(), ((rankPos + i) * 8) + (colPos - i)));
						}
						break;
					}
					else{
						if(!wouldBeCheck(tile.getID(),  ((rankPos + i) * 8) + (colPos - i), 'W')) {
							whiteMoves.add(new ShortMove('W', 'B', tile.getID(), ((rankPos + i) * 8) + (colPos - i)));
						}
					}
				}

				for(int i = 1; ((rankPos - i) >= 0) && ((colPos + i) <= 7); i++){
					if(checkForWhitePiece(((rankPos - i)* 8) + (colPos + i))){
						break;
					}
					if(checkForBlackPiece(((rankPos - i)* 8) + (colPos + i))){
						if(!wouldBeCheck(tile.getID(),  ((rankPos - i) * 8) + (colPos + i), 'W')) {
							whiteMoves.add(new ShortMove('W', 'B', tile.getID(), ((rankPos - i) * 8) + (colPos + i)));
						}
						break;
					}
					else{
						if(!wouldBeCheck(tile.getID(),  ((rankPos - i) * 8) + (colPos + i), 'W')) {
							whiteMoves.add(new ShortMove('W', 'B', tile.getID(), ((rankPos - i) * 8) + (colPos + i)));
						}
					}
				}

				continue;
			}
			else if(tile.getPieceChar() == 'N'){
				int colPos = tile.getID() % 8;
				int rankPos = (tile.getID() - (tile.getID() % 8)) / 8;

				// Tiles in area of columns c to f, and rank 3 to 6 (8 moves)
				if(colPos >= 2 && colPos <= 5 && rankPos >= 2 && rankPos <= 5){
					if(!checkForWhitePiece(tile.getID() - 15)  && !wouldBeCheck(tile.getID(),  tile.getID() -15 , 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 15));
					}
					if(!checkForWhitePiece(tile.getID() - 6) && !wouldBeCheck(tile.getID(), tile.getID() -6, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 6));
					}
					if(!checkForWhitePiece(tile.getID() + 10)&& !wouldBeCheck(tile.getID(), tile.getID() +10, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 10));
					}
					if(!checkForWhitePiece(tile.getID() + 17)&& !wouldBeCheck(tile.getID(), tile.getID() +17, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 17));
					}
					if(!checkForWhitePiece(tile.getID() + 15)&& !wouldBeCheck(tile.getID(), tile.getID() +15, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 15));
					}
					if(!checkForWhitePiece(tile.getID() + 6)&& !wouldBeCheck(tile.getID(), tile.getID() +6, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 6));
					}
					if(!checkForWhitePiece(tile.getID() - 10)&& !wouldBeCheck(tile.getID(), tile.getID() -10, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 10));
					}
					if(!checkForWhitePiece(tile.getID() - 17)&& !wouldBeCheck(tile.getID(), tile.getID() -17, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 17));
					}
				}

				// Tiles c through f in rank 7 (6 moves)
				else if(rankPos == 1 && colPos >= 2 && colPos <= 5){
					if(!checkForWhitePiece(tile.getID() - 10)&& !wouldBeCheck(tile.getID(), tile.getID() -10, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 10));
					}
					if(!checkForWhitePiece(tile.getID() - 6) && !wouldBeCheck(tile.getID(), tile.getID() -6, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 6));
					}
					if(!checkForWhitePiece(tile.getID() + 10)&& !wouldBeCheck(tile.getID(), tile.getID() +10, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 10));
					}
					if(!checkForWhitePiece(tile.getID() + 17)&& !wouldBeCheck(tile.getID(), tile.getID() +17, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 17));
					}
					if(!checkForWhitePiece(tile.getID() + 15)&& !wouldBeCheck(tile.getID(), tile.getID() +15, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 15));
					}
					if(!checkForWhitePiece(tile.getID() + 6)&& !wouldBeCheck(tile.getID(), tile.getID() +6, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 6));
					}
				}

				// Tiles c through f in rank 2
				else if(rankPos == 6 && colPos >= 2 && colPos <= 5 ){
					if(!checkForWhitePiece(tile.getID() - 15)  && !wouldBeCheck(tile.getID(),  tile.getID() -15 , 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 15));
					}
					if(!checkForWhitePiece(tile.getID() - 6) && !wouldBeCheck(tile.getID(), tile.getID() -6, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 6));
					}
					if(!checkForWhitePiece(tile.getID() + 10)&& !wouldBeCheck(tile.getID(), tile.getID() +10, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 10));
					}
					if(!checkForWhitePiece(tile.getID() + 6)&& !wouldBeCheck(tile.getID(), tile.getID() +6, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 6));
					}
					if(!checkForWhitePiece(tile.getID() - 10)&& !wouldBeCheck(tile.getID(), tile.getID() -10, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 10));
					}
					if(!checkForWhitePiece(tile.getID() - 17)&& !wouldBeCheck(tile.getID(), tile.getID() -17, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 17));
					}
				}

				// Tiles 3 through 6 in column b
				else if(colPos == 1 && rankPos >= 2 && rankPos <= 5){
					if(!checkForWhitePiece(tile.getID() - 15)  && !wouldBeCheck(tile.getID(),  tile.getID() -15 , 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 15));
					}
					if(!checkForWhitePiece(tile.getID() - 6) && !wouldBeCheck(tile.getID(), tile.getID() -6, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 6));
					}
					if(!checkForWhitePiece(tile.getID() + 10)&& !wouldBeCheck(tile.getID(), tile.getID() +10, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 10));
					}
					if(!checkForWhitePiece(tile.getID() + 17)&& !wouldBeCheck(tile.getID(), tile.getID() +17, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 17));
					}
					if(!checkForWhitePiece(tile.getID() + 15)&& !wouldBeCheck(tile.getID(), tile.getID() +15, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 15));
					}
					if(!checkForWhitePiece(tile.getID() - 17)&& !wouldBeCheck(tile.getID(), tile.getID() -17, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 17));
					}
				}

				// Tiles 3 through 6 in column g
				else if(colPos == 6 && rankPos >= 2 && rankPos <= 5){
					if(!checkForWhitePiece(tile.getID() - 15)  && !wouldBeCheck(tile.getID(),  tile.getID() -15 , 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 15));
					}
					if(!checkForWhitePiece(tile.getID() + 17)&& !wouldBeCheck(tile.getID(), tile.getID() +17, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 17));
					}
					if(!checkForWhitePiece(tile.getID() + 15)&& !wouldBeCheck(tile.getID(), tile.getID() +15, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 15));
					}
					if(!checkForWhitePiece(tile.getID() + 6)&& !wouldBeCheck(tile.getID(), tile.getID() +6, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 6));
					}
					if(!checkForWhitePiece(tile.getID() - 10)&& !wouldBeCheck(tile.getID(), tile.getID() -10, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 10));
					}
					if(!checkForWhitePiece(tile.getID() - 17)&& !wouldBeCheck(tile.getID(), tile.getID() -17, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 17));
					}
				}

				// Tile a1 (2 moves)
				else if(tile.getID() == 56){
					if(!checkForWhitePiece(tile.getID() - 15)  && !wouldBeCheck(tile.getID(),  tile.getID() -15 , 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 15));
					}
					if(!checkForWhitePiece(tile.getID() - 6) && !wouldBeCheck(tile.getID(), tile.getID() -6, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 6));
					}
				}
				// Tile a8
				else if(tile.getID() == 0){
					if(!checkForWhitePiece(tile.getID() + 10)&& !wouldBeCheck(tile.getID(), tile.getID() +10, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 10));
					}
					if(!checkForWhitePiece(tile.getID() + 17)&& !wouldBeCheck(tile.getID(), tile.getID() +17, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 17));
					}
				}

				// Tile h1
				else if(tile.getID() == 63){
					if(!checkForWhitePiece(tile.getID() - 10)&& !wouldBeCheck(tile.getID(), tile.getID() -10, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 10));
					}
					if(!checkForWhitePiece(tile.getID() - 17)&& !wouldBeCheck(tile.getID(), tile.getID() -17, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 17));
					}
				}

				// Tile h8
				else if(tile.getID() == 7){
					if(!checkForWhitePiece(tile.getID() + 15)&& !wouldBeCheck(tile.getID(), tile.getID() +15, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 15));
					}
					if(!checkForWhitePiece(tile.getID() + 6)&& !wouldBeCheck(tile.getID(), tile.getID() +6, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 6));
					}
				}

				// Tile a2 (3 moves)
				else if(tile.getID() == 48){
					if(!checkForWhitePiece(tile.getID() - 15)  && !wouldBeCheck(tile.getID(),  tile.getID() -15 , 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 15));
					}
					if(!checkForWhitePiece(tile.getID() - 6) && !wouldBeCheck(tile.getID(), tile.getID() -6, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 6));
					}
					if(!checkForWhitePiece(tile.getID() + 10)&& !wouldBeCheck(tile.getID(), tile.getID() +10, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 10));
					}
				}

				// Tile a7
				else if(tile.getID() == 8){
					if(!checkForWhitePiece(tile.getID() - 6) && !wouldBeCheck(tile.getID(), tile.getID() -6, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 6));
					}
					if(!checkForWhitePiece(tile.getID() + 10)&& !wouldBeCheck(tile.getID(), tile.getID() +10, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 10));
					}
					if(!checkForWhitePiece(tile.getID() + 17)&& !wouldBeCheck(tile.getID(), tile.getID() +17, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 17));
					}
				}

				// Tile b1
				else if(tile.getID() == 57){
					if(!checkForWhitePiece(tile.getID() - 15)  && !wouldBeCheck(tile.getID(),  tile.getID() -15 , 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 15));
					}
					if(!checkForWhitePiece(tile.getID() - 6) && !wouldBeCheck(tile.getID(), tile.getID() -6, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 6));
					}
					if(!checkForWhitePiece(tile.getID() - 17)&& !wouldBeCheck(tile.getID(), tile.getID() -17, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 17));
					}
				}

				// Tile b8
				else if(tile.getID() == 1){
					if(!checkForWhitePiece(tile.getID() + 10)&& !wouldBeCheck(tile.getID(), tile.getID() +10, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 10));
					}
					if(!checkForWhitePiece(tile.getID() + 17)&& !wouldBeCheck(tile.getID(), tile.getID() +17, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 17));
					}
					if(!checkForWhitePiece(tile.getID() + 15)&& !wouldBeCheck(tile.getID(), tile.getID() +15, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 15));
					}
				}

				// Tile g1
				else if(tile.getID() == 62){
					if(!checkForWhitePiece(tile.getID() - 15)  && !wouldBeCheck(tile.getID(),  tile.getID() -15 , 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 15));
					}
					if(!checkForWhitePiece(tile.getID() - 10)&& !wouldBeCheck(tile.getID(), tile.getID() -10, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 10));
					}
					if(!checkForWhitePiece(tile.getID() - 17)&& !wouldBeCheck(tile.getID(), tile.getID() -17, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 17));
					}
				}

				// Tile g8
				else if(tile.getID() == 6){
					if(!checkForWhitePiece(tile.getID() + 17)&& !wouldBeCheck(tile.getID(), tile.getID() +17, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 17));
					}
					if(!checkForWhitePiece(tile.getID() + 15)&& !wouldBeCheck(tile.getID(), tile.getID() +15, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 15));
					}
					if(!checkForWhitePiece(tile.getID() + 6)&& !wouldBeCheck(tile.getID(), tile.getID() +6, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 6));
					}

				}

				// Tile h2
				else if(tile.getID() == 55){
					if(!checkForWhitePiece(tile.getID() + 6)&& !wouldBeCheck(tile.getID(), tile.getID() +6, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 6));
					}
					if(!checkForWhitePiece(tile.getID() - 10)&& !wouldBeCheck(tile.getID(), tile.getID() -10, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 10));
					}
					if(!checkForWhitePiece(tile.getID() - 17)&& !wouldBeCheck(tile.getID(), tile.getID() -17, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 17));
					}
				}

				// Tile h7
				else if(tile.getID() == 15){
					if(!checkForWhitePiece(tile.getID() + 15)&& !wouldBeCheck(tile.getID(), tile.getID() +15, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 15));
					}
					if(!checkForWhitePiece(tile.getID() + 6)&& !wouldBeCheck(tile.getID(), tile.getID() +6, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 6));
					}
					if(!checkForWhitePiece(tile.getID() - 10)&& !wouldBeCheck(tile.getID(), tile.getID() -10, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 10));
					}
				}

				// Tiles 3 through 6 in column a (4 moves)
				else if(colPos == 0 && rankPos >= 2 && rankPos <= 5){
					if(!checkForWhitePiece(tile.getID() - 15)  && !wouldBeCheck(tile.getID(),  tile.getID() -15 , 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 15));
					}
					if(!checkForWhitePiece(tile.getID() - 6) && !wouldBeCheck(tile.getID(), tile.getID() -6, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 6));
					}
					if(!checkForWhitePiece(tile.getID() + 10)&& !wouldBeCheck(tile.getID(), tile.getID() +10, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 10));
					}
					if(!checkForWhitePiece(tile.getID() + 17)&& !wouldBeCheck(tile.getID(), tile.getID() +17, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 17));
					}
				}

				// Tiles c through f in rank 8
				else if(rankPos == 0 && colPos >= 2 && colPos <= 5){
					if(!checkForWhitePiece(tile.getID() + 10)&& !wouldBeCheck(tile.getID(), tile.getID() +10, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 10));
					}
					if(!checkForWhitePiece(tile.getID() + 17)&& !wouldBeCheck(tile.getID(), tile.getID() +17, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 17));
					}
					if(!checkForWhitePiece(tile.getID() + 15)&& !wouldBeCheck(tile.getID(), tile.getID() +15, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 15));
					}
					if(!checkForWhitePiece(tile.getID() + 6)&& !wouldBeCheck(tile.getID(), tile.getID() +6, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 6));
					}
				}

				// Tiles 3 through 6 in column h
				else if(colPos == 7 && rankPos >= 2 && rankPos <= 5){
					if(!checkForWhitePiece(tile.getID() + 15)&& !wouldBeCheck(tile.getID(), tile.getID() +15, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 15));
					}
					if(!checkForWhitePiece(tile.getID() + 6)&& !wouldBeCheck(tile.getID(), tile.getID() +6, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 6));
					}
					if(!checkForWhitePiece(tile.getID() - 10)&& !wouldBeCheck(tile.getID(), tile.getID() -10, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 10));
					}
					if(!checkForWhitePiece(tile.getID() - 17)&& !wouldBeCheck(tile.getID(), tile.getID() -17, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 17));
					}
				}

				// Tiles c through f in rank 1
				else if(rankPos == 8 && colPos >= 2 && colPos <=5){
					if(!checkForWhitePiece(tile.getID() - 15)  && !wouldBeCheck(tile.getID(),  tile.getID() -15 , 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 15));
					}
					if(!checkForWhitePiece(tile.getID() - 6) && !wouldBeCheck(tile.getID(), tile.getID() -6, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 6));
					}
					if(!checkForWhitePiece(tile.getID() - 10)&& !wouldBeCheck(tile.getID(), tile.getID() -10, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 10));
					}
					if(!checkForWhitePiece(tile.getID() - 17)&& !wouldBeCheck(tile.getID(), tile.getID() -17, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 17));
					}
				}

				// Tile b2
				else if(tile.getID() == 49){
					if(!checkForWhitePiece(tile.getID() - 15)  && !wouldBeCheck(tile.getID(),  tile.getID() -15 , 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 15));
					}
					if(!checkForWhitePiece(tile.getID() - 6) && !wouldBeCheck(tile.getID(), tile.getID() -6, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 6));
					}
					if(!checkForWhitePiece(tile.getID() + 10)&& !wouldBeCheck(tile.getID(), tile.getID() +10, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 10));
					}

					if(!checkForWhitePiece(tile.getID() - 17)&& !wouldBeCheck(tile.getID(), tile.getID() -17, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 17));
					}
				}

				// TIle b7
				else if(tile.getID() == 9){
					if(!checkForWhitePiece(tile.getID() - 6) && !wouldBeCheck(tile.getID(), tile.getID() -6, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 6));
					}
					if(!checkForWhitePiece(tile.getID() + 10)&& !wouldBeCheck(tile.getID(), tile.getID() +10, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 10));
					}
					if(!checkForWhitePiece(tile.getID() + 17)&& !wouldBeCheck(tile.getID(), tile.getID() +17, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 17));
					}
					if(!checkForWhitePiece(tile.getID() + 15)&& !wouldBeCheck(tile.getID(), tile.getID() +15, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 15));
					}
				}

				// Tile g2
				else if(tile.getID() == 54){
					if(!checkForWhitePiece(tile.getID() - 15)  && !wouldBeCheck(tile.getID(),  tile.getID() -15 , 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 15));
					}
					if(!checkForWhitePiece(tile.getID() + 6)&& !wouldBeCheck(tile.getID(), tile.getID() +6, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 6));
					}
					if(!checkForWhitePiece(tile.getID() - 10)&& !wouldBeCheck(tile.getID(), tile.getID() -10, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 10));
					}
					if(!checkForWhitePiece(tile.getID() - 17)&& !wouldBeCheck(tile.getID(), tile.getID() -17, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 17));
					}
				}

				// Tile g7
				else if(tile.getID() == 14){
					if(!checkForWhitePiece(tile.getID() + 17)&& !wouldBeCheck(tile.getID(), tile.getID() +17, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 17));
					}
					if(!checkForWhitePiece(tile.getID() + 15)&& !wouldBeCheck(tile.getID(), tile.getID() +15, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 15));
					}
					if(!checkForWhitePiece(tile.getID() + 6)&& !wouldBeCheck(tile.getID(), tile.getID() +6, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 6));
					}
					if(!checkForWhitePiece(tile.getID() - 10)&& !wouldBeCheck(tile.getID(), tile.getID() -10, 'W')){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 10));
					}
				}
				continue;
			}

			else if(tile.getPieceChar() == 'P'){
				if(!checkForPiece(tile.getID() - 8) && !wouldBeCheck(tile.getID(), tile.getID() - 8, 'W')){
					whiteMoves.add(new ShortMove('W','P',tile.getID(),tile.getID() - 8));
				}
				if(tile.getID() >= 48 && tile.getID() <= 55){
					if(!checkForPiece(tile.getID() - 16)&& !wouldBeCheck(tile.getID(), tile.getID() - 16, 'W')){
						whiteMoves.add(new ShortMove('W','P',tile.getID(),tile.getID() - 16));
					}
				}
				if(tile.getID() % 8 == 0){
					if(checkForBlackPiece(tile.getID() - 7)&& !wouldBeCheck(tile.getID(), tile.getID() - 7, 'W')){
						whiteMoves.add(new ShortMove('W','P',tile.getID(), tile.getID() - 7));
					}
				}
				if(tile.getID() % 8 == 7){
					if(checkForBlackPiece(tile.getID() - 9)&& !wouldBeCheck(tile.getID(), tile.getID() - 9, 'W')){
						whiteMoves.add(new ShortMove('W','P',tile.getID(), tile.getID() - 9));
					}
				}
				else if(tile.getID() % 8 != 0 && tile.getID() % 8 != 7){
					if(checkForBlackPiece(tile.getID() - 7)&& !wouldBeCheck(tile.getID(), tile.getID() - 7, 'W')){
						whiteMoves.add(new ShortMove('W','P',tile.getID(), tile.getID() - 7));
					}
					if(checkForBlackPiece(tile.getID() - 9)&& !wouldBeCheck(tile.getID(), tile.getID() - 9, 'W')){
						whiteMoves.add(new ShortMove('W','P',tile.getID(), tile.getID() - 9));
					}
				}
				if(tile.getID() % 8 == 0){
					if(doublePawnMove == tile.getID() - 7 && !wouldBeCheck(tile.getID(), tile.getID() - 7, 'W')){
						whiteMoves.add(new ShortMove('W','P',tile.getID(), tile.getID() - 7));
					}
				}
				if(tile.getID() % 8 == 7){
					if(doublePawnMove == tile.getID() - 9&& !wouldBeCheck(tile.getID(), tile.getID() - 9, 'W')){
						whiteMoves.add(new ShortMove('W','P',tile.getID(), tile.getID() - 9));
					}
				}
				else if(tile.getID() % 8 != 0 && tile.getID() % 8 != 7){
					if(doublePawnMove == tile.getID() - 7&& !wouldBeCheck(tile.getID(), tile.getID() - 7, 'W')){
						whiteMoves.add(new ShortMove('W','P',tile.getID(), tile.getID() - 7));
					}
					if(doublePawnMove == tile.getID() - 9&& !wouldBeCheck(tile.getID(), tile.getID() - 9, 'W')){
						whiteMoves.add(new ShortMove('W','P',tile.getID(), tile.getID() - 9));
					}
				}

				continue;
			}
			else if(tile.getPieceChar() == 'K'){
				wKing = tile;
			}

		}
	}

	private void findBlackMoves(){
		for (Tile tile : blacks){
			if(tile.getPieceChar() == 'q'){

				int colPos = tile.getID() % 8;
				int rankPos = (tile.getID() - (tile.getID() % 8)) / 8;
				for(int i = 1; colPos - i >= 0; i++){
					if(checkForBlackPiece(tile.getID() - i)){
						break;
					}
					if(checkForWhitePiece(tile.getID() - i)){
						if(!wouldBeCheck(tile.getID(), tile.getID() - i, 'B')) {
							blackMoves.add(new ShortMove('B', 'q', tile.getID(), tile.getID() - i));
						}
						break;
					}
					else{
						if(!wouldBeCheck(tile.getID(), tile.getID() - i, 'B')) {
							blackMoves.add(new ShortMove('B', 'q', tile.getID(), tile.getID() - i));
						}
					}
				}
				for(int i = 1; colPos + i <= 7; i++){
					if(checkForBlackPiece(tile.getID() + i)){
						break;
					}
					if(checkForWhitePiece(tile.getID() + i)){
						if(!wouldBeCheck(tile.getID(), tile.getID() + i, 'B')) {
							blackMoves.add(new ShortMove('B', 'q', tile.getID(), tile.getID() + i));
						}
						break;
					}
					else{
						if(!wouldBeCheck(tile.getID(), tile.getID() + i, 'B')) {
							blackMoves.add(new ShortMove('B', 'q', tile.getID(), tile.getID() + i));
						}
					}
				}
				for(int i = 1; rankPos - i >= 0; i++){
					if(checkForBlackPiece(tile.getID() - (8 * i))){
						break;
					}
					if(checkForWhitePiece(tile.getID() - (8 * i))){
						if(!wouldBeCheck(tile.getID(), tile.getID() - (8*i), 'B')) {
							blackMoves.add(new ShortMove('B', 'q', tile.getID(), tile.getID() - (8 * i)));
						}
						break;
					}
					else{
						if(!wouldBeCheck(tile.getID(), tile.getID() - (8*i), 'B')) {
							blackMoves.add(new ShortMove('B', 'q', tile.getID(), tile.getID() - (8 * i)));
						}
					}
				}
				for(int i = 1; rankPos + i <= 7; i++){
					if(checkForBlackPiece(tile.getID() + (8 * i))){
						break;
					}
					if(checkForWhitePiece(tile.getID() + (8 * i))){
						if(!wouldBeCheck(tile.getID(), tile.getID() + (8*i), 'B')) {
							blackMoves.add(new ShortMove('B', 'q', tile.getID(), tile.getID() + (8 * i)));
						}
						break;
					}
					else{
						if(!wouldBeCheck(tile.getID(), tile.getID() + (8*i), 'B')) {
							blackMoves.add(new ShortMove('B', 'q', tile.getID(), tile.getID() + (8 * i)));
						}
					}
				}

				for(int i = 1; ((rankPos - i) >= 0) && ((colPos - i) >= 0) ; i++){
					if(checkForBlackPiece(((rankPos - i) * 8) + (colPos - i))){
						break;
					}
					if(checkForWhitePiece(((rankPos - i) * 8) + (colPos - i))){
						if(!wouldBeCheck(tile.getID(), ((rankPos - i) * 8) + (colPos - i), 'B')) {
							blackMoves.add(new ShortMove('B', 'q', tile.getID(), ((rankPos - i) * 8) + (colPos - i)));
						}
						break;
					}
					else{
						if(!wouldBeCheck(tile.getID(), ((rankPos - i) * 8) + (colPos - i), 'B')) {
							blackMoves.add(new ShortMove('B', 'q', tile.getID(), ((rankPos - i) * 8) + (colPos - i)));
						}
					}
				}

				for(int i = 1; ((rankPos + i) <=7) && ((colPos + i) <= 7); i++){
					if(checkForBlackPiece(((rankPos + i)* 8) + (colPos + i))){
						break;
					}
					if(checkForWhitePiece(((rankPos + i)* 8) + (colPos + i))){
						if(!wouldBeCheck(tile.getID(), ((rankPos + i) * 8) + (colPos + i), 'B')) {
							blackMoves.add(new ShortMove('B', 'q', tile.getID(), ((rankPos + i) * 8) + (colPos + i)));
						}
						break;
					}
					else{
						if(!wouldBeCheck(tile.getID(), ((rankPos + i) * 8) + (colPos + i), 'B')) {
							blackMoves.add(new ShortMove('B', 'q', tile.getID(), ((rankPos + i) * 8) + (colPos + i)));
						}
					}
				}

				for(int i = 1; ((rankPos + i) <=7) && ((colPos - i) >= 0); i++){
					if(checkForBlackPiece(((rankPos + i)* 8) + (colPos - i))){
						break;
					}
					if(checkForWhitePiece(((rankPos + i)* 8) + (colPos - i))){
						if(!wouldBeCheck(tile.getID(), ((rankPos + i) * 8) + (colPos - i), 'B')) {
							blackMoves.add(new ShortMove('B', 'q', tile.getID(), ((rankPos + i) * 8) + (colPos - i)));
						}
						break;
					}
					else{
						if(!wouldBeCheck(tile.getID(), ((rankPos + i) * 8) + (colPos - i), 'B')) {
							blackMoves.add(new ShortMove('B', 'q', tile.getID(), ((rankPos + i) * 8) + (colPos - i)));
						}
					}
				}

				for(int i = 1; ((rankPos - i) >= 0) && ((colPos + i) <= 7); i++){
					if(checkForBlackPiece(((rankPos - i)* 8) + (colPos + i))){
						break;
					}
					if(checkForWhitePiece(((rankPos - i)* 8) + (colPos + i))){
						if(!wouldBeCheck(tile.getID(), ((rankPos - i) * 8) + (colPos + i), 'B')) {
							blackMoves.add(new ShortMove('B', 'q', tile.getID(), ((rankPos - i) * 8) + (colPos + i)));
						}
						break;
					}
					else{
						if(!wouldBeCheck(tile.getID(), ((rankPos - i) * 8) + (colPos + i), 'B')) {
							blackMoves.add(new ShortMove('B', 'q', tile.getID(), ((rankPos - i) * 8) + (colPos + i)));
						}
					}
				}
				continue;
			}

			else if(tile.getPieceChar() == 'r'){
				int colPos = tile.getID() % 8;
				int rankPos = (tile.getID() - (tile.getID() % 8)) / 8;
				for(int i = 1; colPos - i >= 0; i++){
					if(checkForBlackPiece(tile.getID() - i)){
						break;
					}
					if(checkForWhitePiece(tile.getID() - i)){
						if(!wouldBeCheck(tile.getID(),  tile.getID() - i, 'B')) {
							blackMoves.add(new ShortMove('B', 'r', tile.getID(), tile.getID() - i));
						}
						break;
					}
					else{
						if(!wouldBeCheck(tile.getID(),  tile.getID() - i, 'B')) {
							blackMoves.add(new ShortMove('B', 'r', tile.getID(), tile.getID() - i));
						}
					}
				}
				for(int i = 1; colPos + i <= 7; i++){
					if(checkForBlackPiece(tile.getID() + i)){
						break;
					}
					if(checkForWhitePiece(tile.getID() + i)){
						if(!wouldBeCheck(tile.getID(),  tile.getID() + i, 'B')) {
							blackMoves.add(new ShortMove('B', 'r', tile.getID(), tile.getID() + i));
						}
						break;
					}
					else{
						if(!wouldBeCheck(tile.getID(),  tile.getID() + i, 'B')) {
							blackMoves.add(new ShortMove('B', 'r', tile.getID(), tile.getID() + i));
						}
					}
				}
				for(int i = 1; rankPos - i >= 0; i++){
					if(checkForBlackPiece(tile.getID() - (8 * i))){
						break;
					}
					if(checkForWhitePiece(tile.getID() - (8 * i))){
						if(!wouldBeCheck(tile.getID(),  tile.getID() - (8 * i), 'B')) {
							blackMoves.add(new ShortMove('B', 'r', tile.getID(), tile.getID() - (8 * i)));
						}
						break;
					}
					else{
						if(!wouldBeCheck(tile.getID(),  tile.getID() - (8 * i), 'B')) {
							blackMoves.add(new ShortMove('B', 'r', tile.getID(), tile.getID() - (8 * i)));
						}
					}
				}
				for(int i = 1; rankPos + i <= 7; i++){
					if(checkForBlackPiece(tile.getID() + (8 * i))){
						break;
					}
					if(checkForWhitePiece(tile.getID() + (8 * i))){
						if(!wouldBeCheck(tile.getID(),  tile.getID() + (8 * i), 'B')) {
							blackMoves.add(new ShortMove('B', 'r', tile.getID(), tile.getID() + (8 * i)));
						}
						break;
					}
					else{
						if(!wouldBeCheck(tile.getID(),  tile.getID() + (8 * i), 'B')) {
							blackMoves.add(new ShortMove('B', 'r', tile.getID(), tile.getID() + (8 * i)));
						}
					}
				}
				continue;
			}
			else if(tile.getPieceChar() == 'b'){
				int colPos = tile.getID() % 8;
				int rankPos = (tile.getID() - (tile.getID() % 8)) / 8;

				for(int i = 1; ((rankPos - i) >= 0) && ((colPos - i) >= 0) ; i++){
					if(checkForBlackPiece(((rankPos - i) * 8) + (colPos - i))){
						break;
					}
					if(checkForWhitePiece(((rankPos - i) * 8) + (colPos - i))){
						if(!wouldBeCheck(tile.getID(),  ((rankPos - i) * 8) + (colPos - i), 'B')) {
							blackMoves.add(new ShortMove('B', 'b', tile.getID(), ((rankPos - i) * 8) + (colPos - i)));
						}
						break;
					}
					else{
						if(!wouldBeCheck(tile.getID(),  ((rankPos - i) * 8) + (colPos - i), 'B')) {
							blackMoves.add(new ShortMove('B', 'b', tile.getID(), ((rankPos - i) * 8) + (colPos - i)));
						}
					}
				}

				for(int i = 1; ((rankPos + i) <=7) && ((colPos + i) <= 7); i++){
					if(checkForBlackPiece(((rankPos + i)* 8) + (colPos + i))){
						break;
					}
					if(checkForWhitePiece(((rankPos + i)* 8) + (colPos + i))){
						if(!wouldBeCheck(tile.getID(),  ((rankPos + i) * 8) + (colPos + i), 'B')) {
							blackMoves.add(new ShortMove('B', 'b', tile.getID(), ((rankPos + i) * 8) + (colPos + i)));
						}
						break;
					}
					else{
						if(!wouldBeCheck(tile.getID(),  ((rankPos + i) * 8) + (colPos + i), 'B')) {
							blackMoves.add(new ShortMove('B', 'b', tile.getID(), ((rankPos + i) * 8) + (colPos + i)));
						}
					}
				}

				for(int i = 1; ((rankPos + i) <=7) && ((colPos - i) >= 0); i++){
					if(checkForBlackPiece(((rankPos + i)* 8) + (colPos - i))){
						break;
					}
					if(checkForWhitePiece(((rankPos + i)* 8) + (colPos - i))){
						if(!wouldBeCheck(tile.getID(),  ((rankPos + i) * 8) + (colPos - i), 'B')) {
							blackMoves.add(new ShortMove('B', 'b', tile.getID(), ((rankPos + i) * 8) + (colPos - i)));
						}
						break;
					}
					else{
						if(!wouldBeCheck(tile.getID(),  ((rankPos + i) * 8) + (colPos - i), 'B')) {
							blackMoves.add(new ShortMove('B', 'b', tile.getID(), ((rankPos + i) * 8) + (colPos - i)));
						}
					}
				}

				for(int i = 1; ((rankPos - i) >= 0) && ((colPos + i) <= 7); i++){
					if(checkForBlackPiece(((rankPos - i)* 8) + (colPos + i))){
						break;
					}
					if(checkForWhitePiece(((rankPos - i)* 8) + (colPos + i))){
						if(!wouldBeCheck(tile.getID(),  ((rankPos - i) * 8) + (colPos + i), 'B')) {
							blackMoves.add(new ShortMove('B', 'b', tile.getID(), ((rankPos - i) * 8) + (colPos + i)));
						}
						break;
					}
					else{
						if(!wouldBeCheck(tile.getID(),  ((rankPos - i) * 8) + (colPos + i), 'B')) {
							blackMoves.add(new ShortMove('B', 'b', tile.getID(), ((rankPos - i) * 8) + (colPos + i)));
						}
					}
				}

				continue;
			}
			else if(tile.getPieceChar() == 'n'){
				int colPos = tile.getID() % 8;
				int rankPos = (tile.getID() - (tile.getID() % 8)) / 8;

				// Tiles in area of columns c to f, and rank 3 to 6 (8 moves)
				if(colPos >= 2 && colPos <= 5 && rankPos >= 2 && rankPos <= 5){
					if(!checkForBlackPiece(tile.getID() - 15)  && !wouldBeCheck(tile.getID(),  tile.getID() -15 , 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 15));
					}
					if(!checkForBlackPiece(tile.getID() - 6) && !wouldBeCheck(tile.getID(), tile.getID() -6, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 6));
					}
					if(!checkForBlackPiece(tile.getID() + 10)&& !wouldBeCheck(tile.getID(), tile.getID() +10, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 10));
					}
					if(!checkForBlackPiece(tile.getID() + 17)&& !wouldBeCheck(tile.getID(), tile.getID() +17, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 17));
					}
					if(!checkForBlackPiece(tile.getID() + 15)&& !wouldBeCheck(tile.getID(), tile.getID() +15, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 15));
					}
					if(!checkForBlackPiece(tile.getID() + 6)&& !wouldBeCheck(tile.getID(), tile.getID() +6, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 6));
					}
					if(!checkForBlackPiece(tile.getID() - 10)&& !wouldBeCheck(tile.getID(), tile.getID() -10, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 10));
					}
					if(!checkForBlackPiece(tile.getID() - 17)&& !wouldBeCheck(tile.getID(), tile.getID() -17, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 17));
					}
				}

				// Tiles c through f in rank 7 (6 moves)
				else if(rankPos == 1 && colPos >= 2 && colPos <= 5){
					if(!checkForBlackPiece(tile.getID() - 10)&& !wouldBeCheck(tile.getID(), tile.getID() -10, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 10));
					}
					if(!checkForBlackPiece(tile.getID() - 6) && !wouldBeCheck(tile.getID(), tile.getID() -6, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 6));
					}
					if(!checkForBlackPiece(tile.getID() + 10)&& !wouldBeCheck(tile.getID(), tile.getID() +10, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 10));
					}
					if(!checkForBlackPiece(tile.getID() + 17)&& !wouldBeCheck(tile.getID(), tile.getID() +17, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 17));
					}
					if(!checkForBlackPiece(tile.getID() + 15)&& !wouldBeCheck(tile.getID(), tile.getID() +15, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 15));
					}
					if(!checkForBlackPiece(tile.getID() + 6)&& !wouldBeCheck(tile.getID(), tile.getID() +6, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 6));
					}
				}

				// Tiles c through f in rank 2
				else if(rankPos == 6 && colPos >= 2 && colPos <= 5 ){
					if(!checkForBlackPiece(tile.getID() - 15)  && !wouldBeCheck(tile.getID(),  tile.getID() -15 , 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 15));
					}
					if(!checkForBlackPiece(tile.getID() - 6) && !wouldBeCheck(tile.getID(), tile.getID() -6, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 6));
					}
					if(!checkForBlackPiece(tile.getID() + 10)&& !wouldBeCheck(tile.getID(), tile.getID() +10, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 10));
					}
					if(!checkForBlackPiece(tile.getID() + 6)&& !wouldBeCheck(tile.getID(), tile.getID() +6, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 6));
					}
					if(!checkForBlackPiece(tile.getID() - 10)&& !wouldBeCheck(tile.getID(), tile.getID() -10, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 10));
					}
					if(!checkForBlackPiece(tile.getID() - 17)&& !wouldBeCheck(tile.getID(), tile.getID() -17, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 17));
					}
				}

				// Tiles 3 through 6 in column b
				else if(colPos == 1 && rankPos >= 2 && rankPos <= 5){
					if(!checkForBlackPiece(tile.getID() - 15)  && !wouldBeCheck(tile.getID(),  tile.getID() -15 , 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 15));
					}
					if(!checkForBlackPiece(tile.getID() - 6) && !wouldBeCheck(tile.getID(), tile.getID() -6, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 6));
					}
					if(!checkForBlackPiece(tile.getID() + 10)&& !wouldBeCheck(tile.getID(), tile.getID() +10, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 10));
					}
					if(!checkForBlackPiece(tile.getID() + 17)&& !wouldBeCheck(tile.getID(), tile.getID() +17, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 17));
					}
					if(!checkForBlackPiece(tile.getID() + 15)&& !wouldBeCheck(tile.getID(), tile.getID() +15, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 15));
					}
					if(!checkForBlackPiece(tile.getID() - 17)&& !wouldBeCheck(tile.getID(), tile.getID() -17, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 17));
					}
				}

				// Tiles 3 through 6 in column g
				else if(colPos == 6 && rankPos >= 2 && rankPos <= 5){
					if(!checkForBlackPiece(tile.getID() - 15)  && !wouldBeCheck(tile.getID(),  tile.getID() -15 , 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 15));
					}
					if(!checkForBlackPiece(tile.getID() + 17)&& !wouldBeCheck(tile.getID(), tile.getID() +17, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 17));
					}
					if(!checkForBlackPiece(tile.getID() + 15)&& !wouldBeCheck(tile.getID(), tile.getID() +15, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 15));
					}
					if(!checkForBlackPiece(tile.getID() + 6)&& !wouldBeCheck(tile.getID(), tile.getID() +6, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 6));
					}
					if(!checkForBlackPiece(tile.getID() - 10)&& !wouldBeCheck(tile.getID(), tile.getID() -10, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 10));
					}
					if(!checkForBlackPiece(tile.getID() - 17)&& !wouldBeCheck(tile.getID(), tile.getID() -17, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 17));
					}
				}

				// Tile a1 (2 moves)
				else if(tile.getID() == 56){
					if(!checkForBlackPiece(tile.getID() - 15)  && !wouldBeCheck(tile.getID(),  tile.getID() -15 , 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 15));
					}
					if(!checkForBlackPiece(tile.getID() - 6) && !wouldBeCheck(tile.getID(), tile.getID() -6, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 6));
					}
				}
				// Tile a8
				else if(tile.getID() == 0){
					if(!checkForBlackPiece(tile.getID() + 10)&& !wouldBeCheck(tile.getID(), tile.getID() +10, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 10));
					}
					if(!checkForBlackPiece(tile.getID() + 17)&& !wouldBeCheck(tile.getID(), tile.getID() +17, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 17));
					}
				}

				// Tile h1
				else if(tile.getID() == 63){
					if(!checkForBlackPiece(tile.getID() - 10)&& !wouldBeCheck(tile.getID(), tile.getID() -10, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 10));
					}
					if(!checkForBlackPiece(tile.getID() - 17)&& !wouldBeCheck(tile.getID(), tile.getID() -17, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 17));
					}
				}

				// Tile h8
				else if(tile.getID() == 7){
					if(!checkForBlackPiece(tile.getID() + 15)&& !wouldBeCheck(tile.getID(), tile.getID() +15, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 15));
					}
					if(!checkForBlackPiece(tile.getID() + 6)&& !wouldBeCheck(tile.getID(), tile.getID() +6, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 6));
					}
				}

				// Tile a2 (3 moves)
				else if(tile.getID() == 48){
					if(!checkForBlackPiece(tile.getID() - 15)  && !wouldBeCheck(tile.getID(),  tile.getID() -15 , 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 15));
					}
					if(!checkForBlackPiece(tile.getID() - 6) && !wouldBeCheck(tile.getID(), tile.getID() -6, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 6));
					}
					if(!checkForBlackPiece(tile.getID() + 10)&& !wouldBeCheck(tile.getID(), tile.getID() +10, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 10));
					}
				}

				// Tile a7
				else if(tile.getID() == 8){
					if(!checkForBlackPiece(tile.getID() - 6) && !wouldBeCheck(tile.getID(), tile.getID() -6, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 6));
					}
					if(!checkForBlackPiece(tile.getID() + 10)&& !wouldBeCheck(tile.getID(), tile.getID() +10, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 10));
					}
					if(!checkForBlackPiece(tile.getID() + 17)&& !wouldBeCheck(tile.getID(), tile.getID() +17, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 17));
					}
				}

				// Tile b1
				else if(tile.getID() == 57){
					if(!checkForBlackPiece(tile.getID() - 15)  && !wouldBeCheck(tile.getID(),  tile.getID() -15 , 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 15));
					}
					if(!checkForBlackPiece(tile.getID() - 6) && !wouldBeCheck(tile.getID(), tile.getID() -6, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 6));
					}
					if(!checkForBlackPiece(tile.getID() - 17)&& !wouldBeCheck(tile.getID(), tile.getID() -17, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 17));
					}
				}

				// Tile b8
				else if(tile.getID() == 1){
					if(!checkForBlackPiece(tile.getID() + 10)&& !wouldBeCheck(tile.getID(), tile.getID() +10, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 10));
					}
					if(!checkForBlackPiece(tile.getID() + 17)&& !wouldBeCheck(tile.getID(), tile.getID() +17, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 17));
					}
					if(!checkForBlackPiece(tile.getID() + 15)&& !wouldBeCheck(tile.getID(), tile.getID() +15, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 15));
					}
				}

				// Tile g1
				else if(tile.getID() == 62){
					if(!checkForBlackPiece(tile.getID() - 15)  && !wouldBeCheck(tile.getID(),  tile.getID() -15 , 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 15));
					}
					if(!checkForBlackPiece(tile.getID() - 10)&& !wouldBeCheck(tile.getID(), tile.getID() -10, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 10));
					}
					if(!checkForBlackPiece(tile.getID() - 17)&& !wouldBeCheck(tile.getID(), tile.getID() -17, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 17));
					}
				}

				// Tile g8
				else if(tile.getID() == 6){
					if(!checkForBlackPiece(tile.getID() + 17)&& !wouldBeCheck(tile.getID(), tile.getID() +17, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 17));
					}
					if(!checkForBlackPiece(tile.getID() + 15)&& !wouldBeCheck(tile.getID(), tile.getID() +15, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 15));
					}
					if(!checkForBlackPiece(tile.getID() + 6)&& !wouldBeCheck(tile.getID(), tile.getID() +6, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 6));
					}

				}

				// Tile h2
				else if(tile.getID() == 55){
					if(!checkForBlackPiece(tile.getID() + 6)&& !wouldBeCheck(tile.getID(), tile.getID() +6, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 6));
					}
					if(!checkForBlackPiece(tile.getID() - 10)&& !wouldBeCheck(tile.getID(), tile.getID() -10, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 10));
					}
					if(!checkForBlackPiece(tile.getID() - 17)&& !wouldBeCheck(tile.getID(), tile.getID() -17, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 17));
					}
				}

				// Tile h7
				else if(tile.getID() == 15){
					if(!checkForBlackPiece(tile.getID() + 15)&& !wouldBeCheck(tile.getID(), tile.getID() +15, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 15));
					}
					if(!checkForBlackPiece(tile.getID() + 6)&& !wouldBeCheck(tile.getID(), tile.getID() +6, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 6));
					}
					if(!checkForBlackPiece(tile.getID() - 10)&& !wouldBeCheck(tile.getID(), tile.getID() -10, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 10));
					}
				}

				// Tiles 3 through 6 in column a (4 moves)
				else if(colPos == 0 && rankPos >= 2 && rankPos <= 5){
					if(!checkForBlackPiece(tile.getID() - 15)  && !wouldBeCheck(tile.getID(),  tile.getID() -15 , 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 15));
					}
					if(!checkForBlackPiece(tile.getID() - 6) && !wouldBeCheck(tile.getID(), tile.getID() -6, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 6));
					}
					if(!checkForBlackPiece(tile.getID() + 10)&& !wouldBeCheck(tile.getID(), tile.getID() +10, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 10));
					}
					if(!checkForBlackPiece(tile.getID() + 17)&& !wouldBeCheck(tile.getID(), tile.getID() +17, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 17));
					}
				}

				// Tiles c through f in rank 8
				else if(rankPos == 0 && colPos >= 2 && colPos <= 5){
					if(!checkForBlackPiece(tile.getID() + 10)&& !wouldBeCheck(tile.getID(), tile.getID() +10, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 10));
					}
					if(!checkForBlackPiece(tile.getID() + 17)&& !wouldBeCheck(tile.getID(), tile.getID() +17, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 17));
					}
					if(!checkForBlackPiece(tile.getID() + 15)&& !wouldBeCheck(tile.getID(), tile.getID() +15, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 15));
					}
					if(!checkForBlackPiece(tile.getID() + 6)&& !wouldBeCheck(tile.getID(), tile.getID() +6, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 6));
					}
				}

				// Tiles 3 through 6 in column h
				else if(colPos == 7 && rankPos >= 2 && rankPos <= 5){
					if(!checkForBlackPiece(tile.getID() + 15)&& !wouldBeCheck(tile.getID(), tile.getID() +15, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 15));
					}
					if(!checkForBlackPiece(tile.getID() + 6)&& !wouldBeCheck(tile.getID(), tile.getID() +6, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 6));
					}
					if(!checkForBlackPiece(tile.getID() - 10)&& !wouldBeCheck(tile.getID(), tile.getID() -10, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 10));
					}
					if(!checkForBlackPiece(tile.getID() - 17)&& !wouldBeCheck(tile.getID(), tile.getID() -17, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 17));
					}
				}

				// Tiles c through f in rank 1
				else if(rankPos == 8 && colPos >= 2 && colPos <=5){
					if(!checkForBlackPiece(tile.getID() - 15)  && !wouldBeCheck(tile.getID(),  tile.getID() -15 , 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 15));
					}
					if(!checkForBlackPiece(tile.getID() - 6) && !wouldBeCheck(tile.getID(), tile.getID() -6, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 6));
					}
					if(!checkForBlackPiece(tile.getID() - 10)&& !wouldBeCheck(tile.getID(), tile.getID() -10, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 10));
					}
					if(!checkForBlackPiece(tile.getID() - 17)&& !wouldBeCheck(tile.getID(), tile.getID() -17, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 17));
					}
				}

				// Tile b2
				else if(tile.getID() == 49){
					if(!checkForBlackPiece(tile.getID() - 15)  && !wouldBeCheck(tile.getID(),  tile.getID() -15 , 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 15));
					}
					if(!checkForBlackPiece(tile.getID() - 6) && !wouldBeCheck(tile.getID(), tile.getID() -6, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 6));
					}
					if(!checkForBlackPiece(tile.getID() + 10)&& !wouldBeCheck(tile.getID(), tile.getID() +10, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 10));
					}

					if(!checkForBlackPiece(tile.getID() - 17)&& !wouldBeCheck(tile.getID(), tile.getID() -17, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 17));
					}
				}

				// TIle b7
				else if(tile.getID() == 9){
					if(!checkForBlackPiece(tile.getID() - 6) && !wouldBeCheck(tile.getID(), tile.getID() -6, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 6));
					}
					if(!checkForBlackPiece(tile.getID() + 10)&& !wouldBeCheck(tile.getID(), tile.getID() +10, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 10));
					}
					if(!checkForBlackPiece(tile.getID() + 17)&& !wouldBeCheck(tile.getID(), tile.getID() +17, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 17));
					}
					if(!checkForBlackPiece(tile.getID() + 15)&& !wouldBeCheck(tile.getID(), tile.getID() +15, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 15));
					}
				}

				// Tile g2
				else if(tile.getID() == 54){
					if(!checkForBlackPiece(tile.getID() - 15)  && !wouldBeCheck(tile.getID(),  tile.getID() -15 , 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 15));
					}
					if(!checkForBlackPiece(tile.getID() + 6)&& !wouldBeCheck(tile.getID(), tile.getID() +6, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 6));
					}
					if(!checkForBlackPiece(tile.getID() - 10)&& !wouldBeCheck(tile.getID(), tile.getID() -10, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 10));
					}
					if(!checkForBlackPiece(tile.getID() - 17)&& !wouldBeCheck(tile.getID(), tile.getID() -17, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 17));
					}
				}

				// Tile g7
				else if(tile.getID() == 14){
					if(!checkForBlackPiece(tile.getID() + 17)&& !wouldBeCheck(tile.getID(), tile.getID() +17, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 17));
					}
					if(!checkForBlackPiece(tile.getID() + 15)&& !wouldBeCheck(tile.getID(), tile.getID() +15, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 15));
					}
					if(!checkForBlackPiece(tile.getID() + 6)&& !wouldBeCheck(tile.getID(), tile.getID() +6, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 6));
					}
					if(!checkForBlackPiece(tile.getID() - 10)&& !wouldBeCheck(tile.getID(), tile.getID() -10, 'B')){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 10));
					}
				}
				continue;
			}
			else if(tile.getPieceChar() == 'p'){
				if(!checkForPiece(tile.getID() + 8) && !wouldBeCheck(tile.getID(), tile.getID() + 8, 'B')){
					blackMoves.add(new ShortMove('B','p',tile.getID(),tile.getID() + 8));
				}
				if(tile.getID() >= 8 && tile.getID() <= 15){
					if(!checkForPiece(tile.getID() + 16)&& !wouldBeCheck(tile.getID(), tile.getID() + 16, 'B')){
						blackMoves.add(new ShortMove('B','p',tile.getID(),tile.getID() + 16));
					}
				}
				if(tile.getID() % 8 == 0){
					if(checkForBlackPiece(tile.getID() + 9)&& !wouldBeCheck(tile.getID(), tile.getID() + 9, 'B')){
						blackMoves.add(new ShortMove('B','p',tile.getID(), tile.getID() + 9));
					}
				}
				if(tile.getID() % 8 == 7){
					if(checkForBlackPiece(tile.getID() + 7)&& !wouldBeCheck(tile.getID(), tile.getID() + 7, 'B')){
						blackMoves.add(new ShortMove('B','p',tile.getID(), tile.getID() + 7));
					}
				}
				else if(tile.getID() % 8 != 0 && tile.getID() != 7){
					if(checkForBlackPiece(tile.getID() + 9)&& !wouldBeCheck(tile.getID(), tile.getID() + 9, 'B')){
						blackMoves.add(new ShortMove('B','p',tile.getID(), tile.getID() + 9));
					}
					if(checkForBlackPiece(tile.getID() + 7)&& !wouldBeCheck(tile.getID(), tile.getID() + 7, 'B')){
						blackMoves.add(new ShortMove('B','p',tile.getID(), tile.getID() + 7));
					}
				}
				if(tile.getID() % 8 == 0){
					if(doublePawnMove == tile.getID() + 9 && !wouldBeCheck(tile.getID(), tile.getID() + 9, 'B')){
						blackMoves.add(new ShortMove('B','p',tile.getID(), tile.getID() + 9));
					}
				}
				if(tile.getID() % 8 == 7){
					if(doublePawnMove == tile.getID() + 7&& !wouldBeCheck(tile.getID(), tile.getID() + 7, 'B')){
						blackMoves.add(new ShortMove('B','p',tile.getID(), tile.getID() + 7));
					}
				}
				else if(tile.getID() % 8 != 0 && tile.getID() % 8 != 7){
					if(doublePawnMove == tile.getID() + 7&& !wouldBeCheck(tile.getID(), tile.getID() + 7, 'B')){
						blackMoves.add(new ShortMove('B','p',tile.getID(), tile.getID() + 7));
					}
					if(doublePawnMove == tile.getID() + 9&& !wouldBeCheck(tile.getID(), tile.getID() + 9, 'B')){
						blackMoves.add(new ShortMove('B','p',tile.getID(), tile.getID() + 9));
					}
				}

				continue;
			}
			else if(tile.getPieceChar() == 'k'){
				bKing = tile;
			}

		}
	}

	private void helpWhiteMoves(){

		for (Tile tile : whites){
			if(tile.getPieceChar() == 'Q'){
				int colPos = tile.getID() % 8;
				int rankPos = (tile.getID() - (tile.getID() % 8)) / 8;
				for(int i = 1; colPos - i >= 0; i++){
					if(checkForWhitePiece(tile.getID() - i)){
						break;
					}
					if(checkForBlackPiece(tile.getID() - i)){
						if(true) {
							whiteMoves.add(new ShortMove('W', 'Q', tile.getID(), tile.getID() - i));
						}
						break;
					}
					else{
						if(true) {
							whiteMoves.add(new ShortMove('W', 'Q', tile.getID(), tile.getID() - i));
						}
					}
				}
				for(int i = 1; colPos + i <= 7; i++){
					if(checkForWhitePiece(tile.getID() + i)){
						break;
					}
					if(checkForBlackPiece(tile.getID() + i)){
						if(true) {
							whiteMoves.add(new ShortMove('W', 'Q', tile.getID(), tile.getID() + i));
						}
						break;
					}
					else{
						if(true) {
							whiteMoves.add(new ShortMove('W', 'Q', tile.getID(), tile.getID() + i));
						}
					}
				}
				for(int i = 1; rankPos - i >= 0; i++){
					if(checkForWhitePiece(tile.getID() - (8 * i))){
						break;
					}
					if(checkForBlackPiece(tile.getID() - (8 * i))){
						if(true) {
							whiteMoves.add(new ShortMove('W', 'Q', tile.getID(), tile.getID() - (8 * i)));
						}
						break;
					}
					else{
						if(true) {
							whiteMoves.add(new ShortMove('W', 'Q', tile.getID(), tile.getID() - (8 * i)));
						}
					}
				}
				for(int i = 1; rankPos + i <= 7; i++){
					if(checkForWhitePiece(tile.getID() + (8 * i))){
						break;
					}
					if(checkForBlackPiece(tile.getID() + (8 * i))){
						if(true) {
							whiteMoves.add(new ShortMove('W', 'Q', tile.getID(), tile.getID() + (8 * i)));
						}
						break;
					}
					else{
						if(true) {
							whiteMoves.add(new ShortMove('W', 'Q', tile.getID(), tile.getID() + (8 * i)));
						}
					}
				}

				for(int i = 1; ((rankPos - i) >= 0) && ((colPos - i) >= 0) ; i++){
					if(checkForWhitePiece(((rankPos - i) * 8) + (colPos - i))){
						break;
					}
					if(checkForBlackPiece(((rankPos - i) * 8) + (colPos - i))){
						if(true) {
							whiteMoves.add(new ShortMove('W', 'Q', tile.getID(), ((rankPos - i) * 8) + (colPos - i)));
						}
						break;
					}
					else{
						if(true){
							whiteMoves.add(new ShortMove('W', 'Q', tile.getID(), ((rankPos - i) * 8) + (colPos - i)));
						}
					}
				}

				for(int i = 1; ((rankPos + i) <=7) && ((colPos + i) <= 7); i++){
					if(checkForWhitePiece(((rankPos + i)* 8) + (colPos + i))){
						break;
					}
					if(checkForBlackPiece(((rankPos + i)* 8) + (colPos + i))){
						if(true) {
							whiteMoves.add(new ShortMove('W', 'Q', tile.getID(), ((rankPos + i) * 8) + (colPos + i)));
						}
						break;
					}
					else{
						if(true) {
							whiteMoves.add(new ShortMove('W', 'Q', tile.getID(), ((rankPos + i) * 8) + (colPos + i)));
						}
					}
				}

				for(int i = 1; ((rankPos + i) <=7) && ((colPos - i) >= 0); i++){
					if(checkForWhitePiece(((rankPos + i)* 8) + (colPos - i))){
						break;
					}
					if(checkForBlackPiece(((rankPos + i)* 8) + (colPos - i))){
						if(true) {
							whiteMoves.add(new ShortMove('W', 'Q', tile.getID(), ((rankPos + i) * 8) + (colPos - i)));
						}
						break;
					}
					else{
						if(true) {
							whiteMoves.add(new ShortMove('W', 'Q', tile.getID(), ((rankPos + i) * 8) + (colPos - i)));
						}
					}
				}

				for(int i = 1; ((rankPos - i) >= 0) && ((colPos + i) <= 7); i++){
					if(checkForWhitePiece(((rankPos - i)* 8) + (colPos + i))){
						break;
					}
					if(checkForBlackPiece(((rankPos - i)* 8) + (colPos + i))){
						if(true) {
							whiteMoves.add(new ShortMove('W', 'Q', tile.getID(), ((rankPos - i) * 8) + (colPos + i)));
						}
						break;
					}
					else{
						if(true) {
							whiteMoves.add(new ShortMove('W', 'Q', tile.getID(), ((rankPos - i) * 8) + (colPos + i)));
						}
					}
				}
				continue;
			}
			else if(tile.getPieceChar() == 'R'){
				int colPos = tile.getID() % 8;
				int rankPos = (tile.getID() - (tile.getID() % 8)) / 8;
				for(int i = 1; colPos - i >= 0; i++){
					if(checkForWhitePiece(tile.getID() - i)){
						break;
					}
					if(checkForBlackPiece(tile.getID() - i)){
						if(true) {
							whiteMoves.add(new ShortMove('W', 'R', tile.getID(), tile.getID() - i));
						}
						break;
					}
					else{
						if(true) {
							whiteMoves.add(new ShortMove('W', 'R', tile.getID(), tile.getID() - i));
						}
					}
				}
				for(int i = 1; colPos + i <= 7; i++){
					if(checkForWhitePiece(tile.getID() + i)){
						break;
					}
					if(checkForBlackPiece(tile.getID() + i)){
						if(true) {
							whiteMoves.add(new ShortMove('W', 'R', tile.getID(), tile.getID() + i));
						}
						break;
					}
					else{
						if(true) {
							whiteMoves.add(new ShortMove('W', 'R', tile.getID(), tile.getID() + i));
						}
					}
				}
				for(int i = 1; rankPos - i >= 0; i++){
					if(checkForWhitePiece(tile.getID() - (8 * i))){
						break;
					}
					if(checkForBlackPiece(tile.getID() - (8 * i))){
						if(true) {
							whiteMoves.add(new ShortMove('W', 'R', tile.getID(), tile.getID() - (8 * i)));
						}
						break;
					}
					else{
						if(true) {
							whiteMoves.add(new ShortMove('W', 'R', tile.getID(), tile.getID() - (8 * i)));
						}
					}
				}
				for(int i = 1; rankPos + i <= 7; i++){
					if(checkForWhitePiece(tile.getID() + (8 * i))){
						break;
					}
					if(checkForBlackPiece(tile.getID() + (8 * i))){
						if(true) {
							whiteMoves.add(new ShortMove('W', 'R', tile.getID(), tile.getID() + (8 * i)));
						}
						break;
					}
					else{
						if(true) {
							whiteMoves.add(new ShortMove('W', 'R', tile.getID(), tile.getID() + (8 * i)));
						}
					}
				}
				continue;
			}
			else if(tile.getPieceChar() == 'B'){
				int colPos = tile.getID() % 8;
				int rankPos = (tile.getID() - (tile.getID() % 8)) / 8;

				for(int i = 1; ((rankPos - i) >= 0) && ((colPos - i) >= 0) ; i++){
					if(checkForWhitePiece(((rankPos - i) * 8) + (colPos - i))){
						break;
					}
					if(checkForBlackPiece(((rankPos - i) * 8) + (colPos - i))){
						if(true) {
							whiteMoves.add(new ShortMove('W', 'B', tile.getID(), ((rankPos - i) * 8) + (colPos - i)));
						}
						break;
					}
					else{
						if(true) {
							whiteMoves.add(new ShortMove('W', 'B', tile.getID(), ((rankPos - i) * 8) + (colPos - i)));
						}
					}
				}

				for(int i = 1; ((rankPos + i) <=7) && ((colPos + i) <= 7); i++){
					if(checkForWhitePiece(((rankPos + i)* 8) + (colPos + i))){
						break;
					}
					if(checkForBlackPiece(((rankPos + i)* 8) + (colPos + i))){
						if(true) {
							whiteMoves.add(new ShortMove('W', 'B', tile.getID(), ((rankPos + i) * 8) + (colPos + i)));
						}
						break;
					}
					else{
						if(true) {
							whiteMoves.add(new ShortMove('W', 'B', tile.getID(), ((rankPos + i) * 8) + (colPos + i)));
						}
					}
				}

				for(int i = 1; ((rankPos + i) <=7) && ((colPos - i) >= 0); i++){
					if(checkForWhitePiece(((rankPos + i)* 8) + (colPos - i))){
						break;
					}
					if(checkForBlackPiece(((rankPos + i)* 8) + (colPos - i))){
						if(true) {
							whiteMoves.add(new ShortMove('W', 'B', tile.getID(), ((rankPos + i) * 8) + (colPos - i)));
						}
						break;
					}
					else{
						if(true) {
							whiteMoves.add(new ShortMove('W', 'B', tile.getID(), ((rankPos + i) * 8) + (colPos - i)));
						}
					}
				}

				for(int i = 1; ((rankPos - i) >= 0) && ((colPos + i) <= 7); i++){
					if(checkForWhitePiece(((rankPos - i)* 8) + (colPos + i))){
						break;
					}
					if(checkForBlackPiece(((rankPos - i)* 8) + (colPos + i))){
						if(true) {
							whiteMoves.add(new ShortMove('W', 'B', tile.getID(), ((rankPos - i) * 8) + (colPos + i)));
						}
						break;
					}
					else{
						if(true) {
							whiteMoves.add(new ShortMove('W', 'B', tile.getID(), ((rankPos - i) * 8) + (colPos + i)));
						}
					}
				}

				continue;
			}
			else if(tile.getPieceChar() == 'N'){
				int colPos = tile.getID() % 8;
				int rankPos = (tile.getID() - (tile.getID() % 8)) / 8;

				// Tiles in area of columns c to f, and rank 3 to 6 (8 moves)
				if(colPos >= 2 && colPos <= 5 && rankPos >= 2 && rankPos <= 5){
					if(!checkForWhitePiece(tile.getID() - 15)  ){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 15));
					}
					if(!checkForWhitePiece(tile.getID() - 6)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 6));
					}
					if(!checkForWhitePiece(tile.getID() + 10)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 10));
					}
					if(!checkForWhitePiece(tile.getID() + 17)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 17));
					}
					if(!checkForWhitePiece(tile.getID() + 15)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 15));
					}
					if(!checkForWhitePiece(tile.getID() + 6)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 6));
					}
					if(!checkForWhitePiece(tile.getID() - 10)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 10));
					}
					if(!checkForWhitePiece(tile.getID() - 17)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 17));
					}
				}

				// Tiles c through f in rank 7 (6 moves)
				else if(rankPos == 1 && colPos >= 2 && colPos <= 5){
					if(!checkForWhitePiece(tile.getID() - 10)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 10));
					}
					if(!checkForWhitePiece(tile.getID() - 6)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 6));
					}
					if(!checkForWhitePiece(tile.getID() + 10)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 10));
					}
					if(!checkForWhitePiece(tile.getID() + 17)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 17));
					}
					if(!checkForWhitePiece(tile.getID() + 15)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 15));
					}
					if(!checkForWhitePiece(tile.getID() + 6)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 6));
					}
				}

				// Tiles c through f in rank 2
				else if(rankPos == 6 && colPos >= 2 && colPos <= 5 ){
					if(!checkForWhitePiece(tile.getID() - 15)  ){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 15));
					}
					if(!checkForWhitePiece(tile.getID() - 6)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 6));
					}
					if(!checkForWhitePiece(tile.getID() + 10)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 10));
					}
					if(!checkForWhitePiece(tile.getID() + 6)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 6));
					}
					if(!checkForWhitePiece(tile.getID() - 10)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 10));
					}
					if(!checkForWhitePiece(tile.getID() - 17)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 17));
					}
				}

				// Tiles 3 through 6 in column b
				else if(colPos == 1 && rankPos >= 2 && rankPos <= 5){
					if(!checkForWhitePiece(tile.getID() - 15)  ){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 15));
					}
					if(!checkForWhitePiece(tile.getID() - 6)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 6));
					}
					if(!checkForWhitePiece(tile.getID() + 10)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 10));
					}
					if(!checkForWhitePiece(tile.getID() + 17)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 17));
					}
					if(!checkForWhitePiece(tile.getID() + 15)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 15));
					}
					if(!checkForWhitePiece(tile.getID() - 17)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 17));
					}
				}

				// Tiles 3 through 6 in column g
				else if(colPos == 6 && rankPos >= 2 && rankPos <= 5){
					if(!checkForWhitePiece(tile.getID() - 15)  ){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 15));
					}
					if(!checkForWhitePiece(tile.getID() + 17)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 17));
					}
					if(!checkForWhitePiece(tile.getID() + 15)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 15));
					}
					if(!checkForWhitePiece(tile.getID() + 6)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 6));
					}
					if(!checkForWhitePiece(tile.getID() - 10)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 10));
					}
					if(!checkForWhitePiece(tile.getID() - 17)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 17));
					}
				}

				// Tile a1 (2 moves)
				else if(tile.getID() == 56){
					if(!checkForWhitePiece(tile.getID() - 15)  ){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 15));
					}
					if(!checkForWhitePiece(tile.getID() - 6)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 6));
					}
				}
				// Tile a8
				else if(tile.getID() == 0){
					if(!checkForWhitePiece(tile.getID() + 10)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 10));
					}
					if(!checkForWhitePiece(tile.getID() + 17)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 17));
					}
				}

				// Tile h1
				else if(tile.getID() == 63){
					if(!checkForWhitePiece(tile.getID() - 10)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 10));
					}
					if(!checkForWhitePiece(tile.getID() - 17)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 17));
					}
				}

				// Tile h8
				else if(tile.getID() == 7){
					if(!checkForWhitePiece(tile.getID() + 15)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 15));
					}
					if(!checkForWhitePiece(tile.getID() + 6)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 6));
					}
				}

				// Tile a2 (3 moves)
				else if(tile.getID() == 48){
					if(!checkForWhitePiece(tile.getID() - 15)  ){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 15));
					}
					if(!checkForWhitePiece(tile.getID() - 6)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 6));
					}
					if(!checkForWhitePiece(tile.getID() + 10)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 10));
					}
				}

				// Tile a7
				else if(tile.getID() == 8){
					if(!checkForWhitePiece(tile.getID() - 6)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 6));
					}
					if(!checkForWhitePiece(tile.getID() + 10)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 10));
					}
					if(!checkForWhitePiece(tile.getID() + 17)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 17));
					}
				}

				// Tile b1
				else if(tile.getID() == 57){
					if(!checkForWhitePiece(tile.getID() - 15)  ){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 15));
					}
					if(!checkForWhitePiece(tile.getID() - 6)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 6));
					}
					if(!checkForWhitePiece(tile.getID() - 17)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 17));
					}
				}

				// Tile b8
				else if(tile.getID() == 1){
					if(!checkForWhitePiece(tile.getID() + 10)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 10));
					}
					if(!checkForWhitePiece(tile.getID() + 17)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 17));
					}
					if(!checkForWhitePiece(tile.getID() + 15)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 15));
					}
				}

				// Tile g1
				else if(tile.getID() == 62){
					if(!checkForWhitePiece(tile.getID() - 15)  ){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 15));
					}
					if(!checkForWhitePiece(tile.getID() - 10)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 10));
					}
					if(!checkForWhitePiece(tile.getID() - 17)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 17));
					}
				}

				// Tile g8
				else if(tile.getID() == 6){
					if(!checkForWhitePiece(tile.getID() + 17)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 17));
					}
					if(!checkForWhitePiece(tile.getID() + 15)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 15));
					}
					if(!checkForWhitePiece(tile.getID() + 6)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 6));
					}

				}

				// Tile h2
				else if(tile.getID() == 55){
					if(!checkForWhitePiece(tile.getID() + 6)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 6));
					}
					if(!checkForWhitePiece(tile.getID() - 10)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 10));
					}
					if(!checkForWhitePiece(tile.getID() - 17)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 17));
					}
				}

				// Tile h7
				else if(tile.getID() == 15){
					if(!checkForWhitePiece(tile.getID() + 15)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 15));
					}
					if(!checkForWhitePiece(tile.getID() + 6)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 6));
					}
					if(!checkForWhitePiece(tile.getID() - 10)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 10));
					}
				}

				// Tiles 3 through 6 in column a (4 moves)
				else if(colPos == 0 && rankPos >= 2 && rankPos <= 5){
					if(!checkForWhitePiece(tile.getID() - 15)  ){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 15));
					}
					if(!checkForWhitePiece(tile.getID() - 6)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 6));
					}
					if(!checkForWhitePiece(tile.getID() + 10)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 10));
					}
					if(!checkForWhitePiece(tile.getID() + 17)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 17));
					}
				}

				// Tiles c through f in rank 8
				else if(rankPos == 0 && colPos >= 2 && colPos <= 5){
					if(!checkForWhitePiece(tile.getID() + 10)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 10));
					}
					if(!checkForWhitePiece(tile.getID() + 17)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 17));
					}
					if(!checkForWhitePiece(tile.getID() + 15)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 15));
					}
					if(!checkForWhitePiece(tile.getID() + 6)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 6));
					}
				}

				// Tiles 3 through 6 in column h
				else if(colPos == 7 && rankPos >= 2 && rankPos <= 5){
					if(!checkForWhitePiece(tile.getID() + 15)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 15));
					}
					if(!checkForWhitePiece(tile.getID() + 6)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 6));
					}
					if(!checkForWhitePiece(tile.getID() - 10)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 10));
					}
					if(!checkForWhitePiece(tile.getID() - 17)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 17));
					}
				}

				// Tiles c through f in rank 1
				else if(rankPos == 8 && colPos >= 2 && colPos <=5){
					if(!checkForWhitePiece(tile.getID() - 15)  ){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 15));
					}
					if(!checkForWhitePiece(tile.getID() - 6)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 6));
					}
					if(!checkForWhitePiece(tile.getID() - 10)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 10));
					}
					if(!checkForWhitePiece(tile.getID() - 17)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 17));
					}
				}

				// Tile b2
				else if(tile.getID() == 49){
					if(!checkForWhitePiece(tile.getID() - 15)  ){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 15));
					}
					if(!checkForWhitePiece(tile.getID() - 6)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 6));
					}
					if(!checkForWhitePiece(tile.getID() + 10)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 10));
					}

					if(!checkForWhitePiece(tile.getID() - 17)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 17));
					}
				}

				// TIle b7
				else if(tile.getID() == 9){
					if(!checkForWhitePiece(tile.getID() - 6)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 6));
					}
					if(!checkForWhitePiece(tile.getID() + 10)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 10));
					}
					if(!checkForWhitePiece(tile.getID() + 17)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 17));
					}
					if(!checkForWhitePiece(tile.getID() + 15)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 15));
					}
				}

				// Tile g2
				else if(tile.getID() == 54){
					if(!checkForWhitePiece(tile.getID() - 15)  ){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 15));
					}
					if(!checkForWhitePiece(tile.getID() + 6)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 6));
					}
					if(!checkForWhitePiece(tile.getID() - 10)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 10));
					}
					if(!checkForWhitePiece(tile.getID() - 17)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 17));
					}
				}

				// Tile g7
				else if(tile.getID() == 14){
					if(!checkForWhitePiece(tile.getID() + 17)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 17));
					}
					if(!checkForWhitePiece(tile.getID() + 15)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 15));
					}
					if(!checkForWhitePiece(tile.getID() + 6)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() + 6));
					}
					if(!checkForWhitePiece(tile.getID() - 10)){
						whiteMoves.add(new ShortMove('W', 'N',tile.getID(), tile.getID() - 10));
					}
				}
				continue;
			}

			else if(tile.getPieceChar() == 'P'){
				if(!checkForPiece(tile.getID() - 8) ){
					whiteMoves.add(new ShortMove('W','P',tile.getID(),tile.getID() - 8));
				}
				if(tile.getID() >= 48 && tile.getID() <= 55){
					if(!checkForPiece(tile.getID() - 16)){
						whiteMoves.add(new ShortMove('W','P',tile.getID(),tile.getID() - 16));
					}
				}
				if(tile.getID() % 8 == 0){
					if(checkForBlackPiece(tile.getID() - 7)){
						whiteMoves.add(new ShortMove('W','P',tile.getID(), tile.getID() - 7));
					}
				}
				if(tile.getID() % 8 == 7){
					if(checkForBlackPiece(tile.getID() - 9)){
						whiteMoves.add(new ShortMove('W','P',tile.getID(), tile.getID() - 9));
					}
				}
				else if(tile.getID() % 8 != 0 && tile.getID() % 8 != 7){
					if(checkForBlackPiece(tile.getID() - 7)){
						whiteMoves.add(new ShortMove('W','P',tile.getID(), tile.getID() - 7));
					}
					if(checkForBlackPiece(tile.getID() - 9)){
						whiteMoves.add(new ShortMove('W','P',tile.getID(), tile.getID() - 9));
					}
				}

				if(tile.getID() % 8 == 0){
					if(doublePawnMove == tile.getID() - 7){
						whiteMoves.add(new ShortMove('W','P',tile.getID(), tile.getID() - 7));
					}
				}
				if(tile.getID() % 8 == 7){
					if(doublePawnMove == tile.getID() - 9){
						whiteMoves.add(new ShortMove('W','P',tile.getID(), tile.getID() - 9));
					}
				}
				if(tile.getID() % 8 != 0 && tile.getID() % 8 != 7){
					if(doublePawnMove == tile.getID() - 7){
						whiteMoves.add(new ShortMove('W','P',tile.getID(), tile.getID() - 7));
					}
					if(doublePawnMove == tile.getID() - 9){
						whiteMoves.add(new ShortMove('W','P',tile.getID(), tile.getID() - 9));
					}
				}

				continue;
			}
			else if(tile.getPieceChar() == 'K'){
				wKing = tile;
			}

		}
	}

	private void helpBlackMoves(){
		for (Tile tile : blacks){
			if(tile.getPieceChar() == 'q'){

				int colPos = tile.getID() % 8;
				int rankPos = (tile.getID() - (tile.getID() % 8)) / 8;
				for(int i = 1; colPos - i >= 0; i++){
					if(checkForBlackPiece(tile.getID() - i)){
						break;
					}
					if(checkForWhitePiece(tile.getID() - i)){
						if(true) {
							blackMoves.add(new ShortMove('B', 'q', tile.getID(), tile.getID() - i));
						}
						break;
					}
					else{
						if(true) {
							blackMoves.add(new ShortMove('B', 'q', tile.getID(), tile.getID() - i));
						}
					}
				}
				for(int i = 1; colPos + i <= 7; i++){
					if(checkForBlackPiece(tile.getID() + i)){
						break;
					}
					if(checkForWhitePiece(tile.getID() + i)){
						if(true) {
							blackMoves.add(new ShortMove('B', 'q', tile.getID(), tile.getID() + i));
						}
						break;
					}
					else{
						if(true) {
							blackMoves.add(new ShortMove('B', 'q', tile.getID(), tile.getID() + i));
						}
					}
				}
				for(int i = 1; rankPos - i >= 0; i++){
					if(checkForBlackPiece(tile.getID() - (8 * i))){
						break;
					}
					if(checkForWhitePiece(tile.getID() - (8 * i))){
						if(true) {
							blackMoves.add(new ShortMove('B', 'q', tile.getID(), tile.getID() - (8 * i)));
						}
						break;
					}
					else{
						if(true) {
							blackMoves.add(new ShortMove('B', 'q', tile.getID(), tile.getID() - (8 * i)));
						}
					}
				}
				for(int i = 1; rankPos + i <= 7; i++){
					if(checkForBlackPiece(tile.getID() + (8 * i))){
						break;
					}
					if(checkForWhitePiece(tile.getID() + (8 * i))){
						if(true) {
							blackMoves.add(new ShortMove('B', 'q', tile.getID(), tile.getID() + (8 * i)));
						}
						break;
					}
					else{
						if(true) {
							blackMoves.add(new ShortMove('B', 'q', tile.getID(), tile.getID() + (8 * i)));
						}
					}
				}

				for(int i = 1; ((rankPos - i) >= 0) && ((colPos - i) >= 0) ; i++){
					if(checkForBlackPiece(((rankPos - i) * 8) + (colPos - i))){
						break;
					}
					if(checkForWhitePiece(((rankPos - i) * 8) + (colPos - i))){
						if(true) {
							blackMoves.add(new ShortMove('B', 'q', tile.getID(), ((rankPos - i) * 8) + (colPos - i)));
						}
						break;
					}
					else{
						if(true) {
							blackMoves.add(new ShortMove('B', 'q', tile.getID(), ((rankPos - i) * 8) + (colPos - i)));
						}
					}
				}

				for(int i = 1; ((rankPos + i) <=7) && ((colPos + i) <= 7); i++){
					if(checkForBlackPiece(((rankPos + i)* 8) + (colPos + i))){
						break;
					}
					if(checkForWhitePiece(((rankPos + i)* 8) + (colPos + i))){
						if(true) {
							blackMoves.add(new ShortMove('B', 'q', tile.getID(), ((rankPos + i) * 8) + (colPos + i)));
						}
						break;
					}
					else{
						if(true) {
							blackMoves.add(new ShortMove('B', 'q', tile.getID(), ((rankPos + i) * 8) + (colPos + i)));
						}
					}
				}

				for(int i = 1; ((rankPos + i) <=7) && ((colPos - i) >= 0); i++){
					if(checkForBlackPiece(((rankPos + i)* 8) + (colPos - i))){
						break;
					}
					if(checkForWhitePiece(((rankPos + i)* 8) + (colPos - i))){
						if(true) {
							blackMoves.add(new ShortMove('B', 'q', tile.getID(), ((rankPos + i) * 8) + (colPos - i)));
						}
						break;
					}
					else{
						if(true) {
							blackMoves.add(new ShortMove('B', 'q', tile.getID(), ((rankPos + i) * 8) + (colPos - i)));
						}
					}
				}

				for(int i = 1; ((rankPos - i) >= 0) && ((colPos + i) <= 7); i++){
					if(checkForBlackPiece(((rankPos - i)* 8) + (colPos + i))){
						break;
					}
					if(checkForWhitePiece(((rankPos - i)* 8) + (colPos + i))){
						if(true) {
							blackMoves.add(new ShortMove('B', 'q', tile.getID(), ((rankPos - i) * 8) + (colPos + i)));
						}
						break;
					}
					else{
						if(true) {
							blackMoves.add(new ShortMove('B', 'q', tile.getID(), ((rankPos - i) * 8) + (colPos + i)));
						}
					}
				}
				continue;
			}

			else if(tile.getPieceChar() == 'r'){
				int colPos = tile.getID() % 8;
				int rankPos = (tile.getID() - (tile.getID() % 8)) / 8;
				for(int i = 1; colPos - i >= 0; i++){
					if(checkForBlackPiece(tile.getID() - i)){
						break;
					}
					if(checkForWhitePiece(tile.getID() - i)){
						if(true) {
							blackMoves.add(new ShortMove('B', 'r', tile.getID(), tile.getID() - i));
						}
						break;
					}
					else{
						if(true) {
							blackMoves.add(new ShortMove('B', 'r', tile.getID(), tile.getID() - i));
						}
					}
				}
				for(int i = 1; colPos + i <= 7; i++){
					if(checkForBlackPiece(tile.getID() + i)){
						break;
					}
					if(checkForWhitePiece(tile.getID() + i)){
						if(true) {
							blackMoves.add(new ShortMove('B', 'r', tile.getID(), tile.getID() + i));
						}
						break;
					}
					else{
						if(true) {
							blackMoves.add(new ShortMove('B', 'r', tile.getID(), tile.getID() + i));
						}
					}
				}
				for(int i = 1; rankPos - i >= 0; i++){
					if(checkForBlackPiece(tile.getID() - (8 * i))){
						break;
					}
					if(checkForWhitePiece(tile.getID() - (8 * i))){
						if(true) {
							blackMoves.add(new ShortMove('B', 'r', tile.getID(), tile.getID() - (8 * i)));
						}
						break;
					}
					else{
						if(true) {
							blackMoves.add(new ShortMove('B', 'r', tile.getID(), tile.getID() - (8 * i)));
						}
					}
				}
				for(int i = 1; rankPos + i <= 7; i++){
					if(checkForBlackPiece(tile.getID() + (8 * i))){
						break;
					}
					if(checkForWhitePiece(tile.getID() + (8 * i))){
						if(true) {
							blackMoves.add(new ShortMove('B', 'r', tile.getID(), tile.getID() + (8 * i)));
						}
						break;
					}
					else{
						if(true) {
							blackMoves.add(new ShortMove('B', 'r', tile.getID(), tile.getID() + (8 * i)));
						}
					}
				}
				continue;
			}
			else if(tile.getPieceChar() == 'b'){
				int colPos = tile.getID() % 8;
				int rankPos = (tile.getID() - (tile.getID() % 8)) / 8;

				for(int i = 1; ((rankPos - i) >= 0) && ((colPos - i) >= 0) ; i++){
					if(checkForBlackPiece(((rankPos - i) * 8) + (colPos - i))){
						break;
					}
					if(checkForWhitePiece(((rankPos - i) * 8) + (colPos - i))){
						if(true) {
							blackMoves.add(new ShortMove('B', 'b', tile.getID(), ((rankPos - i) * 8) + (colPos - i)));
						}
						break;
					}
					else{
						if(true) {
							blackMoves.add(new ShortMove('B', 'b', tile.getID(), ((rankPos - i) * 8) + (colPos - i)));
						}
					}
				}

				for(int i = 1; ((rankPos + i) <=7) && ((colPos + i) <= 7); i++){
					if(checkForBlackPiece(((rankPos + i)* 8) + (colPos + i))){
						break;
					}
					if(checkForWhitePiece(((rankPos + i)* 8) + (colPos + i))){
						if(true) {
							blackMoves.add(new ShortMove('B', 'b', tile.getID(), ((rankPos + i) * 8) + (colPos + i)));
						}
						break;
					}
					else{
						if(true) {
							blackMoves.add(new ShortMove('B', 'b', tile.getID(), ((rankPos + i) * 8) + (colPos + i)));
						}
					}
				}

				for(int i = 1; ((rankPos + i) <=7) && ((colPos - i) >= 0); i++){
					if(checkForBlackPiece(((rankPos + i)* 8) + (colPos - i))){
						break;
					}
					if(checkForWhitePiece(((rankPos + i)* 8) + (colPos - i))){
						if(true) {
							blackMoves.add(new ShortMove('B', 'b', tile.getID(), ((rankPos + i) * 8) + (colPos - i)));
						}
						break;
					}
					else{
						if(true) {
							blackMoves.add(new ShortMove('B', 'b', tile.getID(), ((rankPos + i) * 8) + (colPos - i)));
						}
					}
				}

				for(int i = 1; ((rankPos - i) >= 0) && ((colPos + i) <= 7); i++){
					if(checkForBlackPiece(((rankPos - i)* 8) + (colPos + i))){
						break;
					}
					if(checkForWhitePiece(((rankPos - i)* 8) + (colPos + i))){
						if(true) {
							blackMoves.add(new ShortMove('B', 'b', tile.getID(), ((rankPos - i) * 8) + (colPos + i)));
						}
						break;
					}
					else{
						if(true) {
							blackMoves.add(new ShortMove('B', 'b', tile.getID(), ((rankPos - i) * 8) + (colPos + i)));
						}
					}
				}

				continue;
			}
			else if(tile.getPieceChar() == 'n'){
				int colPos = tile.getID() % 8;
				int rankPos = (tile.getID() - (tile.getID() % 8)) / 8;

				// Tiles in area of columns c to f, and rank 3 to 6 (8 moves)
				if(colPos >= 2 && colPos <= 5 && rankPos >= 2 && rankPos <= 5){
					if(!checkForBlackPiece(tile.getID() - 15)  ){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 15));
					}
					if(!checkForBlackPiece(tile.getID() - 6) ){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 6));
					}
					if(!checkForBlackPiece(tile.getID() + 10)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 10));
					}
					if(!checkForBlackPiece(tile.getID() + 17)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 17));
					}
					if(!checkForBlackPiece(tile.getID() + 15)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 15));
					}
					if(!checkForBlackPiece(tile.getID() + 6)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 6));
					}
					if(!checkForBlackPiece(tile.getID() - 10)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 10));
					}
					if(!checkForBlackPiece(tile.getID() - 17)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 17));
					}
				}

				// Tiles c through f in rank 7 (6 moves)
				else if(rankPos == 1 && colPos >= 2 && colPos <= 5){
					if(!checkForBlackPiece(tile.getID() - 10)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 10));
					}
					if(!checkForBlackPiece(tile.getID() - 6) ){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 6));
					}
					if(!checkForBlackPiece(tile.getID() + 10)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 10));
					}
					if(!checkForBlackPiece(tile.getID() + 17)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 17));
					}
					if(!checkForBlackPiece(tile.getID() + 15)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 15));
					}
					if(!checkForBlackPiece(tile.getID() + 6)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 6));
					}
				}

				// Tiles c through f in rank 2
				else if(rankPos == 6 && colPos >= 2 && colPos <= 5 ){
					if(!checkForBlackPiece(tile.getID() - 15)  ){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 15));
					}
					if(!checkForBlackPiece(tile.getID() - 6) ){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 6));
					}
					if(!checkForBlackPiece(tile.getID() + 10)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 10));
					}
					if(!checkForBlackPiece(tile.getID() + 6)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 6));
					}
					if(!checkForBlackPiece(tile.getID() - 10)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 10));
					}
					if(!checkForBlackPiece(tile.getID() - 17)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 17));
					}
				}

				// Tiles 3 through 6 in column b
				else if(colPos == 1 && rankPos >= 2 && rankPos <= 5){
					if(!checkForBlackPiece(tile.getID() - 15)  ){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 15));
					}
					if(!checkForBlackPiece(tile.getID() - 6) ){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 6));
					}
					if(!checkForBlackPiece(tile.getID() + 10)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 10));
					}
					if(!checkForBlackPiece(tile.getID() + 17)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 17));
					}
					if(!checkForBlackPiece(tile.getID() + 15)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 15));
					}
					if(!checkForBlackPiece(tile.getID() - 17)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 17));
					}
				}

				// Tiles 3 through 6 in column g
				else if(colPos == 6 && rankPos >= 2 && rankPos <= 5){
					if(!checkForBlackPiece(tile.getID() - 15)  ){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 15));
					}
					if(!checkForBlackPiece(tile.getID() + 17)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 17));
					}
					if(!checkForBlackPiece(tile.getID() + 15)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 15));
					}
					if(!checkForBlackPiece(tile.getID() + 6)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 6));
					}
					if(!checkForBlackPiece(tile.getID() - 10)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 10));
					}
					if(!checkForBlackPiece(tile.getID() - 17)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 17));
					}
				}

				// Tile a1 (2 moves)
				else if(tile.getID() == 56){
					if(!checkForBlackPiece(tile.getID() - 15)  ){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 15));
					}
					if(!checkForBlackPiece(tile.getID() - 6) ){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 6));
					}
				}
				// Tile a8
				else if(tile.getID() == 0){
					if(!checkForBlackPiece(tile.getID() + 10)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 10));
					}
					if(!checkForBlackPiece(tile.getID() + 17)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 17));
					}
				}

				// Tile h1
				else if(tile.getID() == 63){
					if(!checkForBlackPiece(tile.getID() - 10)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 10));
					}
					if(!checkForBlackPiece(tile.getID() - 17)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 17));
					}
				}

				// Tile h8
				else if(tile.getID() == 7){
					if(!checkForBlackPiece(tile.getID() + 15)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 15));
					}
					if(!checkForBlackPiece(tile.getID() + 6)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 6));
					}
				}

				// Tile a2 (3 moves)
				else if(tile.getID() == 48){
					if(!checkForBlackPiece(tile.getID() - 15)  ){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 15));
					}
					if(!checkForBlackPiece(tile.getID() - 6) ){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 6));
					}
					if(!checkForBlackPiece(tile.getID() + 10)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 10));
					}
				}

				// Tile a7
				else if(tile.getID() == 8){
					if(!checkForBlackPiece(tile.getID() - 6) ){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 6));
					}
					if(!checkForBlackPiece(tile.getID() + 10)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 10));
					}
					if(!checkForBlackPiece(tile.getID() + 17)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 17));
					}
				}

				// Tile b1
				else if(tile.getID() == 57){
					if(!checkForBlackPiece(tile.getID() - 15)  ){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 15));
					}
					if(!checkForBlackPiece(tile.getID() - 6) ){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 6));
					}
					if(!checkForBlackPiece(tile.getID() - 17)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 17));
					}
				}

				// Tile b8
				else if(tile.getID() == 1){
					if(!checkForBlackPiece(tile.getID() + 10)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 10));
					}
					if(!checkForBlackPiece(tile.getID() + 17)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 17));
					}
					if(!checkForBlackPiece(tile.getID() + 15)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 15));
					}
				}

				// Tile g1
				else if(tile.getID() == 62){
					if(!checkForBlackPiece(tile.getID() - 15)  ){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 15));
					}
					if(!checkForBlackPiece(tile.getID() - 10)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 10));
					}
					if(!checkForBlackPiece(tile.getID() - 17)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 17));
					}
				}

				// Tile g8
				else if(tile.getID() == 6){
					if(!checkForBlackPiece(tile.getID() + 17)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 17));
					}
					if(!checkForBlackPiece(tile.getID() + 15)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 15));
					}
					if(!checkForBlackPiece(tile.getID() + 6)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 6));
					}

				}

				// Tile h2
				else if(tile.getID() == 55){
					if(!checkForBlackPiece(tile.getID() + 6)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 6));
					}
					if(!checkForBlackPiece(tile.getID() - 10)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 10));
					}
					if(!checkForBlackPiece(tile.getID() - 17)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 17));
					}
				}

				// Tile h7
				else if(tile.getID() == 15){
					if(!checkForBlackPiece(tile.getID() + 15)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 15));
					}
					if(!checkForBlackPiece(tile.getID() + 6)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 6));
					}
					if(!checkForBlackPiece(tile.getID() - 10)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 10));
					}
				}

				// Tiles 3 through 6 in column a (4 moves)
				else if(colPos == 0 && rankPos >= 2 && rankPos <= 5){
					if(!checkForBlackPiece(tile.getID() - 15)  ){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 15));
					}
					if(!checkForBlackPiece(tile.getID() - 6) ){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 6));
					}
					if(!checkForBlackPiece(tile.getID() + 10)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 10));
					}
					if(!checkForBlackPiece(tile.getID() + 17)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 17));
					}
				}

				// Tiles c through f in rank 8
				else if(rankPos == 0 && colPos >= 2 && colPos <= 5){
					if(!checkForBlackPiece(tile.getID() + 10)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 10));
					}
					if(!checkForBlackPiece(tile.getID() + 17)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 17));
					}
					if(!checkForBlackPiece(tile.getID() + 15)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 15));
					}
					if(!checkForBlackPiece(tile.getID() + 6)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 6));
					}
				}

				// Tiles 3 through 6 in column h
				else if(colPos == 7 && rankPos >= 2 && rankPos <= 5){
					if(!checkForBlackPiece(tile.getID() + 15)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 15));
					}
					if(!checkForBlackPiece(tile.getID() + 6)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 6));
					}
					if(!checkForBlackPiece(tile.getID() - 10)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 10));
					}
					if(!checkForBlackPiece(tile.getID() - 17)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 17));
					}
				}

				// Tiles c through f in rank 1
				else if(rankPos == 8 && colPos >= 2 && colPos <=5){
					if(!checkForBlackPiece(tile.getID() - 15)  ){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 15));
					}
					if(!checkForBlackPiece(tile.getID() - 6) ){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 6));
					}
					if(!checkForBlackPiece(tile.getID() - 10)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 10));
					}
					if(!checkForBlackPiece(tile.getID() - 17)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 17));
					}
				}

				// Tile b2
				else if(tile.getID() == 49){
					if(!checkForBlackPiece(tile.getID() - 15)  ){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 15));
					}
					if(!checkForBlackPiece(tile.getID() - 6) ){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 6));
					}
					if(!checkForBlackPiece(tile.getID() + 10)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 10));
					}

					if(!checkForBlackPiece(tile.getID() - 17)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 17));
					}
				}

				// TIle b7
				else if(tile.getID() == 9){
					if(!checkForBlackPiece(tile.getID() - 6) ){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 6));
					}
					if(!checkForBlackPiece(tile.getID() + 10)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 10));
					}
					if(!checkForBlackPiece(tile.getID() + 17)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 17));
					}
					if(!checkForBlackPiece(tile.getID() + 15)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 15));
					}
				}

				// Tile g2
				else if(tile.getID() == 54){
					if(!checkForBlackPiece(tile.getID() - 15)  ){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 15));
					}
					if(!checkForBlackPiece(tile.getID() + 6)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 6));
					}
					if(!checkForBlackPiece(tile.getID() - 10)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 10));
					}
					if(!checkForBlackPiece(tile.getID() - 17)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 17));
					}
				}

				// Tile g7
				else if(tile.getID() == 14){
					if(!checkForBlackPiece(tile.getID() + 17)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 17));
					}
					if(!checkForBlackPiece(tile.getID() + 15)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 15));
					}
					if(!checkForBlackPiece(tile.getID() + 6)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 6));
					}
					if(!checkForBlackPiece(tile.getID() - 10)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 10));
					}
				}
				continue;
			}
			else if(tile.getPieceChar() == 'p'){
				if(!checkForPiece(tile.getID() + 8)){
					blackMoves.add(new ShortMove('B','p',tile.getID(),tile.getID() + 8));
				}
				if(tile.getID() >= 8 && tile.getID() <= 15){
					if(!checkForPiece(tile.getID() + 16)){
						blackMoves.add(new ShortMove('B','p',tile.getID(),tile.getID() + 16));
					}
				}
				if(tile.getID() % 8 == 0){
					if(checkForBlackPiece(tile.getID() + 9)){
						blackMoves.add(new ShortMove('B','p',tile.getID(), tile.getID() + 9));
					}
				}

				if(tile.getID() % 8 == 7){
					if(checkForBlackPiece(tile.getID() + 7)){
						blackMoves.add(new ShortMove('B','p',tile.getID(), tile.getID() + 7));
					}
				}

				else if(tile.getID() % 8 != 0 && tile.getID() % 8 != 7){
					if(!checkForPiece(tile.getID() + 16)){
						blackMoves.add(new ShortMove('B','p',tile.getID(),tile.getID() + 16));
					}
					if(checkForBlackPiece(tile.getID() + 9)){
						blackMoves.add(new ShortMove('B','p',tile.getID(), tile.getID() + 9));
					}
				}

				if(tile.getID() % 8 == 0){
					if(doublePawnMove == tile.getID() + 9 ){
						blackMoves.add(new ShortMove('B','p',tile.getID(), tile.getID() + 9));
					}
				}
				if(tile.getID() % 8 == 7){
					if(doublePawnMove == tile.getID() + 7){
						blackMoves.add(new ShortMove('B','p',tile.getID(), tile.getID() + 7));
					}
				}
				if(tile.getID() % 8 != 0 && tile.getID() % 8 != 7){
					if(doublePawnMove == tile.getID() + 7){
						blackMoves.add(new ShortMove('B','p',tile.getID(), tile.getID() + 7));
					}
					if(doublePawnMove == tile.getID() + 9){
						blackMoves.add(new ShortMove('B','p',tile.getID(), tile.getID() + 9));
					}
				}

				continue;
			}
			else if(tile.getPieceChar() == 'k'){
				bKing = tile;
			}

		}
	}

	private void findWhiteKingMoves(){

		// king not at column a

		helper.setAsHelper(true);

		if(wKing.getID() % 8 != 0){
			if(!checkForWhitePiece(wKing.getID() - 1)){
				boolean wouldBeCheck = false;
				helper.setCustomBoard(helpBoard);
				helper.testMove(wKing.getID(), wKing.getID() - 1, 'W', true);
				hBlackMoves = helper.getMoveList('W',true).blackMoves;
				if(((wKing.getID() - 1) % 8 != 0) && (wKing.getID() - 2 == bKing.getID())){
					wouldBeCheck = true;
				}
				if(((wKing.getID() - 1) % 8 != 0) && ((wKing.getID() - 1) - 8 >= 0) && (wKing.getID() - 10 == bKing.getID())){
					wouldBeCheck = true;
				}
				if(((wKing.getID() - 1) % 8 != 0) && ((wKing.getID() - 1) + 8 <= 63) && (wKing.getID() + 6 == bKing.getID())){
					wouldBeCheck = true;
				}
				for(ShortMove s : hBlackMoves){
					if(s.getEndSquare() == wKing.getID() - 1){
						wouldBeCheck = true;
						break;
					}
				}
				if(!wouldBeCheck){
					wKingMoves.add(new ShortMove('W', 'K', wKing.getID(), wKing.getID() - 1));
					whiteMoves.add(new ShortMove('W', 'K', wKing.getID(), wKing.getID() - 1));
				}
			}

		}

		// king not at column h

		if(wKing.getID() % 8 != 7){
			if(!checkForWhitePiece(wKing.getID() + 1)){
				boolean wouldBeCheck = false;
				helper.setCustomBoard(helpBoard);
				helper.testMove(wKing.getID(), wKing.getID() + 1, 'W', true);
				hBlackMoves = helper.getMoveList('W', true).blackMoves;
				if(((wKing.getID() + 1) % 8 != 7) && (wKing.getID() + 2 == bKing.getID())){
					wouldBeCheck = true;
				}
				if(((wKing.getID() + 1) % 8 != 7) && ((wKing.getID() + 1) - 8 >= 0) && (wKing.getID() -6 == bKing.getID())){
					wouldBeCheck = true;
				}
				if(((wKing.getID() + 1) % 8 != 7) && ((wKing.getID() + 1) + 8 <= 63) && (wKing.getID() + 10 == bKing.getID())){
					wouldBeCheck = true;
				}
				for(ShortMove s : hBlackMoves){
					if(s.getEndSquare() == wKing.getID() + 1){
						wouldBeCheck = true;
						break;
					}
				}
				if(!wouldBeCheck){
					wKingMoves.add(new ShortMove('W', 'K', wKing.getID(), wKing.getID() + 1));
					whiteMoves.add(new ShortMove('W', 'K', wKing.getID(), wKing.getID() + 1));
				}
			}

		}

		// king not at rank 8

		if(wKing.getID() - 8 >= 0){
			if(!checkForWhitePiece(wKing.getID() - 8)){
				boolean wouldBeCheck = false;
				helper.setCustomBoard(helpBoard);
				helper.testMove(wKing.getID(), wKing.getID() - 8, 'W', true);
				hBlackMoves = helper.getMoveList('W', true).blackMoves;
				if(((wKing.getID()) - 16 >= 0) && (wKing.getID() - 16 == bKing.getID())){
					wouldBeCheck = true;
				}
				if(((wKing.getID()) - 16 >= 0) && (wKing.getID() % 8 != 0) && (wKing.getID() - 17 == bKing.getID())){
					wouldBeCheck = true;
				}
				if(((wKing.getID()) - 16 >= 0) && (wKing.getID() % 8 != 7) && (wKing.getID() - 15 == bKing.getID())){
					wouldBeCheck = true;
				}
				for(ShortMove s : hBlackMoves){
					if(s.getEndSquare() == wKing.getID() - 8){
						wouldBeCheck = true;
						break;
					}
				}
				if(!wouldBeCheck){
					wKingMoves.add(new ShortMove('W', 'K', wKing.getID(), wKing.getID() - 8));
					whiteMoves.add(new ShortMove('W', 'K', wKing.getID(), wKing.getID() - 8));
				}
			}

		}

		// king not at rank 1

		if(wKing.getID() + 8 <= 63){
			if(!checkForWhitePiece(wKing.getID() + 8)){
				boolean wouldBeCheck = false;
				helper.setCustomBoard(helpBoard);
				helper.testMove(wKing.getID(), wKing.getID() + 8, 'W', true);
				hBlackMoves = helper.getMoveList('W', true).blackMoves;
				if(((wKing.getID()) + 16 <= 63) && (wKing.getID() + 16 == bKing.getID())){
					wouldBeCheck = true;
				}
				if(((wKing.getID()) + 16 <= 63) && (wKing.getID() % 8 != 0 ) && (wKing.getID() + 15 == bKing.getID())){
					wouldBeCheck = true;
				}
				if(((wKing.getID()) - 16 >= 0) && (wKing.getID() % 8 != 7) && (wKing.getID() + 17 == bKing.getID())){
					wouldBeCheck = true;
				}
				for(ShortMove s : hBlackMoves){
					if(s.getEndSquare() == wKing.getID() + 8){
						wouldBeCheck = true;
						break;
					}
				}
				if(!wouldBeCheck){
					wKingMoves.add(new ShortMove('W', 'K', wKing.getID(), wKing.getID() + 8));
					whiteMoves.add(new ShortMove('W', 'K', wKing.getID(), wKing.getID() + 8));
				}
			}

		}

		// king not at a1 corner

		if(wKing.getID() - 8 >= 0 && wKing.getID() % 8 != 0){
			if(!checkForWhitePiece(wKing.getID() - 9)){
				boolean wouldBeCheck = false;
				helper.setCustomBoard(helpBoard);
				helper.testMove(wKing.getID(), wKing.getID() - 9, 'W', true);
				hBlackMoves = helper.getMoveList('W', true).blackMoves;
				if(((wKing.getID()) - 16 >= 0) && (wKing.getID() - 16 == bKing.getID())){
					wouldBeCheck = true;
				}
				if(((wKing.getID()) - 16 >= 0) && (wKing.getID() - 17 == bKing.getID())){
					wouldBeCheck = true;
				}
				if(((wKing.getID()) - 16 >= 0) && (wKing.getID() % 8 != 1) && (wKing.getID() - 18 == bKing.getID())){
					wouldBeCheck = true;
				}
				if((wKing.getID() % 8 != 1) && (wKing.getID() - 10 == bKing.getID())){
					wouldBeCheck = true;
				}
				if((wKing.getID() % 8 != 1) && (wKing.getID() - 2 == bKing.getID())){
					wouldBeCheck = true;
				}
				for(ShortMove s : hBlackMoves){
					if(s.getEndSquare() == wKing.getID() - 9){
						wouldBeCheck = true;
						break;
					}
				}
				if(!wouldBeCheck){
					wKingMoves.add(new ShortMove('W', 'K', wKing.getID(), wKing.getID() - 9));
					whiteMoves.add(new ShortMove('W', 'K', wKing.getID(), wKing.getID() - 9));
				}
			}

		}

		// king not at a8 corner

		if(wKing.getID() - 8 >= 0 && wKing.getID() % 8 != 7){
			if(!checkForWhitePiece(wKing.getID() - 7)){
				boolean wouldBeCheck = false;
				helper.setCustomBoard(helpBoard);
				helper.testMove(wKing.getID(), wKing.getID() - 7, 'W', true);
				hBlackMoves = helper.getMoveList('W', true).blackMoves;
				if(((wKing.getID()) - 16 >= 0) && (wKing.getID() - 16 == bKing.getID())){
					wouldBeCheck = true;
				}
				if(((wKing.getID()) - 16 >= 0) && (wKing.getID() - 15 == bKing.getID())){
					wouldBeCheck = true;
				}
				if(((wKing.getID()) - 16 >= 0) && (wKing.getID() % 8 != 6) && (wKing.getID() - 14 == bKing.getID())){
					wouldBeCheck = true;
				}
				if((wKing.getID() % 8 != 6) && (wKing.getID() - 6 == bKing.getID())){
					wouldBeCheck = true;
				}
				if((wKing.getID() % 8 != 6) && (wKing.getID() + 2 == bKing.getID())){
					wouldBeCheck = true;
				}
				for(ShortMove s : hBlackMoves){
					if(s.getEndSquare() == wKing.getID() - 7){
						wouldBeCheck = true;
						break;
					}
				}
				if(!wouldBeCheck){
					wKingMoves.add(new ShortMove('W', 'K', wKing.getID(), wKing.getID() - 7));
					whiteMoves.add(new ShortMove('W', 'K', wKing.getID(), wKing.getID() - 7));
				}
			}

		}

		// king not at h8 corner

		if(wKing.getID() + 8 <= 63 && wKing.getID() % 8 != 7){
			if(!checkForWhitePiece(wKing.getID() + 9)){
				boolean wouldBeCheck = false;
				helper.setCustomBoard(helpBoard);
				helper.testMove(wKing.getID(), wKing.getID() + 9, 'W', true);
				hBlackMoves = helper.getMoveList('W', true).blackMoves;
				if(((wKing.getID()) + 16 <= 63) && (wKing.getID() + 16 == bKing.getID())){
					wouldBeCheck = true;
				}
				if(((wKing.getID()) + 16 <= 63) && (wKing.getID() + 17 == bKing.getID())){
					wouldBeCheck = true;
				}
				if(((wKing.getID()) + 16 <= 63) && (wKing.getID() % 8 != 6) && (wKing.getID() + 18 == bKing.getID())){
					wouldBeCheck = true;
				}
				if((wKing.getID() % 8 != 6) && (wKing.getID() + 10 == bKing.getID())){
					wouldBeCheck = true;
				}
				if((wKing.getID() % 8 != 6) && (wKing.getID() + 2 == bKing.getID())){
					wouldBeCheck = true;
				}
				for(ShortMove s : hBlackMoves){
					if(s.getEndSquare() == wKing.getID() + 9){
						wouldBeCheck = true;
						break;
					}
				}
				if(!wouldBeCheck){
					wKingMoves.add(new ShortMove('W', 'K', wKing.getID(), wKing.getID() + 9));
					whiteMoves.add(new ShortMove('W', 'K', wKing.getID(), wKing.getID() + 9));
				}
			}

		}

		// king not at h1 corner

		if(wKing.getID() + 8 <= 63 && wKing.getID() % 8 != 0){
			if(!checkForWhitePiece(wKing.getID() + 7)){
				boolean wouldBeCheck = false;
				helper.setCustomBoard(helpBoard);
				helper.testMove(wKing.getID(), wKing.getID() + 7, 'W', true);
				hBlackMoves = helper.getMoveList('W', true).blackMoves;
				if(((wKing.getID()) + 16 <= 63) && (wKing.getID() + 16 == bKing.getID())){
					wouldBeCheck = true;
				}
				if(((wKing.getID()) + 16 <= 63) && (wKing.getID() + 15 == bKing.getID())){
					wouldBeCheck = true;
				}
				if(((wKing.getID()) + 16 <= 63) && (wKing.getID() % 8 != 1) && (wKing.getID() + 14 == bKing.getID())){
					wouldBeCheck = true;
				}
				if((wKing.getID() % 8 != 1) && (wKing.getID() + 6 == bKing.getID())){
					wouldBeCheck = true;
				}
				if((wKing.getID() % 8 != 1) && (wKing.getID() - 2 == bKing.getID())){
					wouldBeCheck = true;
				}
				for(ShortMove s : hBlackMoves){
					if(s.getEndSquare() == wKing.getID() + 7){
						wouldBeCheck = true;
						break;
					}
				}
				if(!wouldBeCheck){
					wKingMoves.add(new ShortMove('W', 'K', wKing.getID(), wKing.getID() + 7));
					whiteMoves.add(new ShortMove('W', 'K', wKing.getID(), wKing.getID() + 7));
				}
			}

		}

		if(!wkm && !wkrm && !checkIfCheck('W') && !board[61].getIfOccupied() && !board[62].getIfOccupied() && !isAttacked(61, 'W') && !isAttacked(62, 'W')){
			bKingMoves.add(new ShortMove('W', 'K', wKing.getID(), wKing.getID() + 2));
			whiteMoves.add(new ShortMove('W', 'K', wKing.getID(), wKing.getID() + 2));
		}

		if(!wkm && !wqrm && !checkIfCheck('W') && !board[59].getIfOccupied() && !board[58].getIfOccupied() && !board[57].getIfOccupied() && !isAttacked(59, 'W') && !isAttacked(58, 'W')){
			wKingMoves.add(new ShortMove('W', 'K', wKing.getID(), wKing.getID() - 2));
			whiteMoves.add(new ShortMove('W', 'K', wKing.getID(), wKing.getID() - 2));
		}


	}

	private void findBlackKingMoves(){

		helper.setAsHelper(true);
		// king not at column a

		if(bKing.getID() % 8 != 0){
			if(!checkForBlackPiece(bKing.getID() - 1)){
				boolean wouldBeCheck = false;
				helper.setCustomBoard(helpBoard);
				helper.testMove(bKing.getID(), bKing.getID() - 1, 'B', true);
				hBlackMoves = helper.getMoveList('B', true).blackMoves;
				if(((bKing.getID() - 1) % 8 != 0) && (bKing.getID() - 2 == wKing.getID())){
					wouldBeCheck = true;
				}
				if(((bKing.getID() - 1) % 8 != 0) && ((bKing.getID() - 1) - 8 >= 0) && (bKing.getID() - 10 == wKing.getID())){
					wouldBeCheck = true;
				}
				if(((bKing.getID() - 1) % 8 != 0) && ((bKing.getID() - 1) + 8 <= 63) && (bKing.getID() + 6 == wKing.getID())){
					wouldBeCheck = true;
				}
				for(ShortMove s : hBlackMoves){
					if(s.getEndSquare() == bKing.getID() - 1){
						wouldBeCheck = true;
						break;
					}
				}
				if(!wouldBeCheck){
					bKingMoves.add(new ShortMove('B', 'K', bKing.getID(), bKing.getID() - 1));
					blackMoves.add(new ShortMove('B', 'K', bKing.getID(), bKing.getID() - 1));
				}
			}

		}

		// king not at column h

		if(bKing.getID() % 8 != 7){
			if(!checkForBlackPiece(bKing.getID() + 1)){
				boolean wouldBeCheck = false;
				helper.setCustomBoard(helpBoard);
				helper.testMove(bKing.getID(), bKing.getID() + 1, 'B', true);
				hBlackMoves = helper.getMoveList('B', true).blackMoves;
				if(((bKing.getID() + 1) % 8 != 7) && (bKing.getID() + 2 == wKing.getID())){
					wouldBeCheck = true;
				}
				if(((bKing.getID() + 1) % 8 != 7) && ((bKing.getID() + 1) - 8 >= 0) && (bKing.getID() -6 == wKing.getID())){
					wouldBeCheck = true;
				}
				if(((bKing.getID() + 1) % 8 != 7) && ((bKing.getID() + 1) + 8 <= 63) && (bKing.getID() + 10 == wKing.getID())){
					wouldBeCheck = true;
				}
				for(ShortMove s : hBlackMoves){
					if(s.getEndSquare() == bKing.getID() + 1){
						wouldBeCheck = true;
						break;
					}
				}
				if(!wouldBeCheck){
					bKingMoves.add(new ShortMove('B', 'K', bKing.getID(), bKing.getID() + 1));
					blackMoves.add(new ShortMove('B', 'K', bKing.getID(), bKing.getID() + 1));
				}
			}

		}

		// king not at rank 8

		if(bKing.getID() - 8 >= 0){
			if(!checkForBlackPiece(bKing.getID() - 8)){
				boolean wouldBeCheck = false;
				helper.setCustomBoard(helpBoard);
				helper.testMove(bKing.getID(), bKing.getID() - 8, 'B', true);
				hBlackMoves = helper.getMoveList('B', true).blackMoves;
				if(((bKing.getID()) - 16 >= 0) && (bKing.getID() - 16 == wKing.getID())){
					wouldBeCheck = true;
				}
				if(((bKing.getID()) - 16 >= 0) && (bKing.getID() % 8 != 0) && (bKing.getID() - 17 == wKing.getID())){
					wouldBeCheck = true;
				}
				if(((bKing.getID()) - 16 >= 0) && (bKing.getID() % 8 != 7) && (bKing.getID() - 15 == wKing.getID())){
					wouldBeCheck = true;
				}
				for(ShortMove s : hBlackMoves){
					if(s.getEndSquare() == bKing.getID() - 8){
						wouldBeCheck = true;
						break;
					}
				}
				if(!wouldBeCheck){
					bKingMoves.add(new ShortMove('B', 'K', bKing.getID(), bKing.getID() - 8));
					blackMoves.add(new ShortMove('B', 'K', bKing.getID(), bKing.getID() - 8));
				}
			}

		}

		// king not at rank 1

		if(bKing.getID() + 8 <= 63){
			if(!checkForBlackPiece(bKing.getID() + 8)){
				boolean wouldBeCheck = false;
				helper.setCustomBoard(helpBoard);
				helper.testMove(bKing.getID(), bKing.getID() + 8, 'B', true);
				hBlackMoves = helper.getMoveList('B', true).blackMoves;
				if(((bKing.getID()) + 16 <= 63) && (bKing.getID() + 16 == wKing.getID())){
					wouldBeCheck = true;
				}
				if(((bKing.getID()) + 16 <= 63) && (bKing.getID() % 8 != 0 ) && (bKing.getID() + 15 == wKing.getID())){
					wouldBeCheck = true;
				}
				if(((bKing.getID()) - 16 >= 0) && (bKing.getID() % 8 != 7) && (bKing.getID() + 17 == wKing.getID())){
					wouldBeCheck = true;
				}
				for(ShortMove s : hBlackMoves){
					if(s.getEndSquare() == bKing.getID() + 8){
						wouldBeCheck = true;
						break;
					}
				}
				if(!wouldBeCheck){
					bKingMoves.add(new ShortMove('B', 'K', bKing.getID(), bKing.getID() + 8));
					blackMoves.add(new ShortMove('B', 'K', bKing.getID(), bKing.getID() + 8));
				}
			}

		}

		// king not at a1 corner

		if(bKing.getID() - 8 >= 0 && bKing.getID() % 8 != 0){
			if(!checkForBlackPiece(bKing.getID() - 9)){
				boolean wouldBeCheck = false;
				helper.setCustomBoard(helpBoard);
				helper.testMove(bKing.getID(), bKing.getID() - 9, 'B', true);
				hBlackMoves = helper.getMoveList('B', true).blackMoves;
				if(((bKing.getID()) - 16 >= 0) && (bKing.getID() - 16 == wKing.getID())){
					wouldBeCheck = true;
				}
				if(((bKing.getID()) - 16 >= 0) && (bKing.getID() - 17 == wKing.getID())){
					wouldBeCheck = true;
				}
				if(((bKing.getID()) - 16 >= 0) && (bKing.getID() % 8 != 1) && (bKing.getID() - 18 == wKing.getID())){
					wouldBeCheck = true;
				}
				if((bKing.getID() % 8 != 1) && (bKing.getID() - 10 == wKing.getID())){
					wouldBeCheck = true;
				}
				if((bKing.getID() % 8 != 1) && (bKing.getID() - 2 == wKing.getID())){
					wouldBeCheck = true;
				}
				for(ShortMove s : hBlackMoves){
					if(s.getEndSquare() == bKing.getID() - 9){
						wouldBeCheck = true;
						break;
					}
				}
				if(!wouldBeCheck){
					bKingMoves.add(new ShortMove('B', 'K', bKing.getID(), bKing.getID() - 9));
					blackMoves.add(new ShortMove('B', 'K', bKing.getID(), bKing.getID() - 9));
				}
			}

		}

		// king not at a8 corner

		if(bKing.getID() - 8 >= 0 && bKing.getID() % 8 != 7){
			if(!checkForBlackPiece(bKing.getID() - 7)){
				boolean wouldBeCheck = false;
				helper.setCustomBoard(helpBoard);
				helper.testMove(bKing.getID(), bKing.getID() - 7, 'B', true);
				hBlackMoves = helper.getMoveList('B', true).blackMoves;
				if(((bKing.getID()) - 16 >= 0) && (bKing.getID() - 16 == wKing.getID())){
					wouldBeCheck = true;
				}
				if(((bKing.getID()) - 16 >= 0) && (bKing.getID() - 15 == wKing.getID())){
					wouldBeCheck = true;
				}
				if(((bKing.getID()) - 16 >= 0) && (bKing.getID() % 8 != 6) && (bKing.getID() - 14 == wKing.getID())){
					wouldBeCheck = true;
				}
				if((bKing.getID() % 8 != 6) && (bKing.getID() - 6 == wKing.getID())){
					wouldBeCheck = true;
				}
				if((bKing.getID() % 8 != 6) && (bKing.getID() + 2 == wKing.getID())){
					wouldBeCheck = true;
				}
				for(ShortMove s : hBlackMoves){
					if(s.getEndSquare() == bKing.getID() - 7){
						wouldBeCheck = true;
						break;
					}
				}
				if(!wouldBeCheck){
					bKingMoves.add(new ShortMove('B', 'K', bKing.getID(), bKing.getID() - 7));
					blackMoves.add(new ShortMove('B', 'K', bKing.getID(), bKing.getID() - 7));
				}
			}

		}

		// king not at h8 corner

		if(bKing.getID() + 8 <= 63 && bKing.getID() % 8 != 7){
			if(!checkForBlackPiece(bKing.getID() + 9)){
				boolean wouldBeCheck = false;
				helper.setCustomBoard(helpBoard);
				helper.testMove(bKing.getID(), bKing.getID() + 9, 'B', true);
				hBlackMoves = helper.getMoveList('B', true).blackMoves;
				if(((bKing.getID()) + 16 <= 63) && (bKing.getID() + 16 == wKing.getID())){
					wouldBeCheck = true;
				}
				if(((bKing.getID()) + 16 <= 63) && (bKing.getID() + 17 == wKing.getID())){
					wouldBeCheck = true;
				}
				if(((bKing.getID()) + 16 <= 63) && (bKing.getID() % 8 != 6) && (bKing.getID() + 18 == wKing.getID())){
					wouldBeCheck = true;
				}
				if((bKing.getID() % 8 != 6) && (bKing.getID() + 10 == wKing.getID())){
					wouldBeCheck = true;
				}
				if((bKing.getID() % 8 != 6) && (bKing.getID() + 2 == wKing.getID())){
					wouldBeCheck = true;
				}
				for(ShortMove s : hBlackMoves){
					if(s.getEndSquare() == bKing.getID() + 9){
						wouldBeCheck = true;
						break;
					}
				}
				if(!wouldBeCheck){
					bKingMoves.add(new ShortMove('B', 'K', bKing.getID(), bKing.getID() + 9));
					blackMoves.add(new ShortMove('B', 'K', bKing.getID(), bKing.getID() + 9));
				}
			}

		}

		// king not at h1 corner

		if(bKing.getID() + 8 <= 63 && bKing.getID() % 8 != 0){
			if(!checkForBlackPiece(bKing.getID() + 7)){
				boolean wouldBeCheck = false;
				helper.setCustomBoard(helpBoard);
				helper.testMove(bKing.getID(), bKing.getID() + 7, 'B', true);
				hBlackMoves = helper.getMoveList('B', true).blackMoves;
				if(((bKing.getID()) + 16 <= 63) && (bKing.getID() + 16 == wKing.getID())){
					wouldBeCheck = true;
				}
				if(((bKing.getID()) + 16 <= 63) && (bKing.getID() + 15 == wKing.getID())){
					wouldBeCheck = true;
				}
				if(((bKing.getID()) + 16 <= 63) && (bKing.getID() % 8 != 1) && (bKing.getID() + 14 == wKing.getID())){
					wouldBeCheck = true;
				}
				if((bKing.getID() % 8 != 1) && (bKing.getID() + 6 == wKing.getID())){
					wouldBeCheck = true;
				}
				if((bKing.getID() % 8 != 1) && (bKing.getID() - 2 == wKing.getID())){
					wouldBeCheck = true;
				}
				for(ShortMove s : hBlackMoves){
					if(s.getEndSquare() == bKing.getID() + 7){
						wouldBeCheck = true;
						break;
					}
				}
				if(!wouldBeCheck){
					bKingMoves.add(new ShortMove('B', 'K', bKing.getID(), bKing.getID() + 7));
					blackMoves.add(new ShortMove('B', 'K', bKing.getID(), bKing.getID() + 7));
				}
			}

		}

		if(!bkm && !bkrm && !checkIfCheck('B') && !board[5].getIfOccupied() && !board[6].getIfOccupied() && !isAttacked(5, 'B') && !isAttacked(6, 'B')){
			bKingMoves.add(new ShortMove('B', 'K', bKing.getID(), bKing.getID() + 2));
			blackMoves.add(new ShortMove('B', 'K', bKing.getID(), bKing.getID() + 2));
		}

		if(!bkm && !bqrm && !checkIfCheck('B') && !board[3].getIfOccupied() && !board[2].getIfOccupied() && !board[1].getIfOccupied() && !isAttacked(3, 'B') && !isAttacked(2, 'B')){
			bKingMoves.add(new ShortMove('B', 'K', bKing.getID(), bKing.getID() - 2));
			blackMoves.add(new ShortMove('B', 'K', bKing.getID(), bKing.getID() - 2));
		}

	}

	public boolean checkStalemate(char c){
		if(c == 'W'){
			return (whiteMoves.isEmpty() && wKingMoves.isEmpty());
		}
		else if(c == 'B'){
			return (blackMoves.isEmpty() && bKingMoves.isEmpty());
		}
		else
			return false;
	}

	private boolean checkIfCheck(char c){
		if(c == 'W'){
			int king = wKing.getID();
			for(ShortMove s : blackMoves){
				if(s.getType() != 'p' && s.getEndSquare() == king){
					return true;
				}
				else if (king % 8 != 0 && s.getType() == 'p' && s.getStartSquare() == (king - 9)){
					return true;
				}
				else if (king % 8 != 7 && s.getType() == 'p' && s.getStartSquare() == (king - 7)){
					return true;
				}
			}
			return false;
		}
		else if(c == 'B'){
			int king = bKing.getID();
			for(ShortMove s : whiteMoves){
				if(s.getType() != 'p' && s.getEndSquare() == king){
					return true;
				}
				else if (king % 8 != 0 && s.getType() == 'p' && s.getStartSquare() == (king + 7)){
					return true;
				}
				else if (king % 8 != 7 && s.getType() == 'p' && s.getStartSquare() == (king + 9)){
					return true;
				}
			}
			return false;
		}
		else{
			return false;
		}
	}

	public boolean wouldBeCheck(int ss, int es, char c){

		helper.setCustomBoard(helpBoard);
		helper.setAsHelper(true);
		helper.testMove(ss, es, c, true);
		if(helper.getMoveList(c, true).checkIfCheck(c)){
			return true;
		}
		else {
			return false;
		}
	}

	public boolean verifyCheckmate(char c){
		return checkStalemate(c) && checkIfCheck(c);
	}

	public boolean isAttacked(int sq, char c){
		if(c == 'W'){
			for(ShortMove s : blackMoves){
				if(s.getEndSquare() == sq){
					return true;
				}
			}
			return false;
		}
		else if(c == 'B'){
			for(ShortMove s : whiteMoves){
				if(s.getEndSquare() == sq){
					return true;
				}
			}
			return false;
		}
		return false;
	}

	public int[] getMoveVoice(String piece, int esq, char c){
		char p;
		ArrayList<ShortMove> s = new ArrayList<ShortMove>();

		switch(piece){
			case "pawn": p = 'P';
				break;
			case "knight": p = 'N';
				break;
			case "rook": p = 'R';
				break;
			case "bishop": p = 'B';
				break;
			case "queen": p = 'Q';
				break;
			case "king": p = 'K';
				break;
			default: p = 'P';
				break;
		}
		if(c == 'W'){
			for (ShortMove sm : whiteMoves) {
				if (sm.getType() == p && sm.getEndSquare() == esq){
					s.add(sm);
				}
			}
		}
		else if(c == 'B'){
			for (ShortMove sm : blackMoves) {
				if (sm.getType() == Character.toLowerCase(p) && sm.getEndSquare() == esq){
					s.add(sm);
				}
			}
		}

		if(s.size() == 3){
			int[] ia = {s.get(0).getStartSquare(),s.get(0).getEndSquare(), 1, s.get(1).getStartSquare(),s.get(1).getEndSquare(), 1, s.get(2).getStartSquare(),s.get(2).getEndSquare(), 0};
			return ia;
		}
		if(s.size() == 2){
			int[] ia = {s.get(0).getStartSquare(),s.get(0).getEndSquare(), 1, s.get(1).getStartSquare(),s.get(1).getEndSquare(), 0, -1 , -1 , 0};
			return ia;
		}
		else if(s.size() == 1){
			int[] ia = {s.get(0).getStartSquare(),s.get(0).getEndSquare(), 0, - 1, -1, 0, -1, -1, 0};
			return ia;
		}
		int[] ia = {-1, -1, -0, -1, -1, 0, -1, -1, 0};
		return ia;
	}

	public boolean checkIfLegal(int s, int e, char c){
		if(c == 'W'){
			List<ShortMove> pieceMoves = new ArrayList<ShortMove>();
			for(ShortMove sm : whiteMoves){
				if(sm.getStartSquare() == s){
					pieceMoves.add(sm);
				}
			}
			for(ShortMove sm: pieceMoves){
				if(sm.getEndSquare() == e){
						return true;

				}
			}
			return false;
		}
		else if(c == 'B'){
			List<ShortMove> pieceMoves = new ArrayList<ShortMove>();
			for(ShortMove sm : blackMoves){
				if(sm.getStartSquare() == s){
					pieceMoves.add(sm);
				}
			}
			for(ShortMove sm: pieceMoves){
				if(sm.getEndSquare() == e){
						return true;
				}
			}
			return false;
		}
		else{
			return false;
		}
	}

	public void printWhiteIDMoves(){
		for(ShortMove s: whiteMoves){
			System.out.println("Piece: " + s.getType() + " Start: " + s.getStartSquare() + " End: " + s.getEndSquare());
		}
		System.out.println();
	}

	public void printBlackIDMoves(){
		for(ShortMove s: blackMoves){
			System.out.println("Piece: " + s.getType() + " Start: " + s.getStartSquare() + " End: " + s.getEndSquare());
		}
		System.out.println();
	}

	public void printWhiteMoves(){
		for(ShortMove s: whiteMoves){
			System.out.println("Piece: " + s.getType() + " Start: " + tileToNotation(s.getStartSquare()) + " End: " + tileToNotation(s.getEndSquare()));
		}
		System.out.println();
	}

	public void printBlackMoves(){
		for(ShortMove s: blackMoves){
			System.out.println("Piece: " + s.getType() + " Start: " + tileToNotation(s.getStartSquare()) + " End: " + tileToNotation(s.getEndSquare()));
		}
		System.out.println();
	}

	public void printPieceMoves(char t){
		for(ShortMove s: whiteMoves){
			if(s.getType() == t || s.getType() == Character.toUpperCase(t)){
				System.out.println("Piece: " + s.getType() + " Start: " + tileToNotation(s.getStartSquare()) + " End: " + tileToNotation(s.getEndSquare()));
			}
		}
		System.out.println();
		for(ShortMove s: blackMoves){
			if(s.getType() == t || s.getType() == Character.toLowerCase(t)){
				System.out.println("Piece: " + s.getType() + " Start: " + tileToNotation(s.getStartSquare()) + " End: " + tileToNotation(s.getEndSquare()));
			}
		}
		System.out.println();
	}

	public void printDividedPieceMoves(char t, char c){
		if(c == 'W'){
			for(ShortMove s: whiteMoves){
				if(s.getType() == t || s.getType() == Character.toUpperCase(t)){
					System.out.println("Piece: " + s.getType() + " Start: " + tileToNotation(s.getStartSquare()) + " End: " + tileToNotation(s.getEndSquare()));
				}
			}
			System.out.println();
		}
		else if(c == 'B'){
			for(ShortMove s: blackMoves){
				if(s.getType() == t || s.getType() == Character.toLowerCase(t)){
					System.out.println("Piece: " + s.getType() + " Start: " + tileToNotation(s.getStartSquare()) + " End: " + tileToNotation(s.getEndSquare()));
				}
			}
			System.out.println();
		}
	}

	ArrayList<ShortMove> pieceMoves = new ArrayList<ShortMove>();

	public boolean getPieceMoves(int id){
		pieceMoves = new ArrayList<ShortMove>();
		if(!board[id].containsPiece){
			return false;
		}
		else{
			if(board[id].getPiece().getColor() == 'W'){
				for(ShortMove sm : whiteMoves){
					if(sm.getStartSquare() == id){
						pieceMoves.add(sm);
					}
				}
			}
			else if(board[id].getPiece().getColor() == 'B'){
				for(ShortMove sm : blackMoves){
					if(sm.getStartSquare() == id){
						pieceMoves.add(sm);
					}
				}
			}
			return true;
		}
	}
	/*
	 * Function generates a list of all pieces of type 'p' and color 'c'.
	 * Returns false if the resulting list is empty. True if it contains something.
	 *
	 */

	public boolean getPieceMoves(char p, char c){
		pieceMoves = new ArrayList<ShortMove>();

		if(c == 'W'){
			for(ShortMove sm : whiteMoves){
				if(sm.getType() == Character.toUpperCase(p)){
					pieceMoves.add(sm);
				}
			}
		}
		else if(c == 'B'){
			for(ShortMove sm : blackMoves){
				if(sm.getType() == Character.toLowerCase(p)){
					pieceMoves.add(sm);
				}
			}
		}

		if(pieceMoves.isEmpty()){
			return false;
		}
		else{
			return true;
		}
	}

	public void printPieceMoves(){
		for(ShortMove s: pieceMoves){
			System.out.println("Piece: " + s.getType() + " Start: " + tileToNotation(s.getStartSquare()) + " End: " + tileToNotation(s.getEndSquare()));
		}
		System.out.println();
	}

	private String tileToNotation(int t) {
		String tileNotation = "";
		int column = t % 8;
		int rank = (t - column) / 8;
		switch(column){
			case 0: tileNotation = tileNotation + ("a");
				break;
			case 1: tileNotation = tileNotation + ("b");
				break;
			case 2: tileNotation = tileNotation + ("c");
				break;
			case 3: tileNotation = tileNotation + ("d");
				break;
			case 4: tileNotation = tileNotation + ("e");
				break;
			case 5: tileNotation = tileNotation + ("f");
				break;
			case 6: tileNotation = tileNotation + ("g");
				break;
			case 7: tileNotation = tileNotation + ("h");
				break;
			default: break;
		}

		tileNotation = tileNotation + (Integer.toString(8 - rank));

		return tileNotation;
	}
}
