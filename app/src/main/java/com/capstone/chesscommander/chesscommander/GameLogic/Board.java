package com.capstone.chesscommander.chesscommander.GameLogic;

import java.util.ArrayList;



/**
 * Board abstraction class. Contains the 64 tiles in the board, as well as its contents.
 * Note it is a WIP.
 * 1. Custom Board creator
 * 
 * @author Eduardo
 *
 */
public class Board {
	public MoveList list;
	GameHandler myGame;
	boolean whiteKingCastle = true;
	boolean blackKingCastle = true;
	boolean whiteQueenCastle = true;
	boolean blackQueenCastle = true;
	boolean whiteKingMoved = false;
	boolean whiteKingRookMoved = false;
	boolean whiteQueenRookMoved = false;
	boolean blackKingMoved = false;
	boolean blackKingRookMoved = false;
	boolean blackQueenRookMoved = false;
	boolean helpBoard = false;
	int halfmove = 0;
	int fullmove = 1;
	int playerMove = 0;
	int doubleMoveTile = -100;
	String FEN;
	ArrayList<String> shortFenList = new ArrayList<String>();
	boolean isTurnPlayer;
	Moves lastPlayerMove;
	char setPromotionTo = 'Q';

	Tile[] tiles = new Tile [64];
	Tile[] helpTiles = new Tile[64];
	
	ArrayList<Moves> moves = new ArrayList<Moves>();
	
	int moveNum = 1;
	int ply = 1;
//#############Melvin##############



	
	public Board(){
		genBoard();
		//myGame = new GameHandler();
	}
	
	public Board(Board original){
		this.myGame = original.myGame;
		this.whiteKingCastle = original.whiteKingCastle;
		this.blackKingCastle = original.blackKingCastle;
		this.whiteQueenCastle = original.whiteQueenCastle;
		this.blackQueenCastle = original.blackQueenCastle;
		this.whiteKingMoved = original.whiteKingMoved;
		this.whiteKingRookMoved = original.whiteKingRookMoved;
		this.whiteQueenRookMoved = original.whiteQueenRookMoved;
		this.blackKingMoved = original.blackKingMoved;
		this.blackKingRookMoved = original.blackKingRookMoved;
		this.blackQueenRookMoved = original.blackQueenRookMoved;
		this.halfmove = original.halfmove;
		this.fullmove = original.fullmove;
		this.playerMove = original.playerMove;
		this.doubleMoveTile = original.doubleMoveTile;
		this.FEN = original.FEN;
		this.list = original.list;
		
		this.tiles = new Tile [64];
		this.moves = new ArrayList<Moves>();
		
		this.moveNum = 1;
		this.ply = 1;

	}
	
	private Board copyBoard(){
		return new Board(this);
	}

	public boolean setAsHelper(boolean h){
		helpBoard = h;
		return helpBoard;
	}

	public Tile[] setCustomBoard(int[] num){
		genBoard();
		if(num.length == 64){
			for(int i = 0; i <64; i++){
				if(num[i] != -1){
					tiles[i].setPiece(new Piece(num[i]));
				}
			}
		}
		shortFenList.clear();
		moves.clear();
		halfmove = 0;
		moveNum = 1;
		fullmove=1;
		ply=1;
		playerMove=0;
		doubleMoveTile = -100;
		whiteKingCastle = false;
		blackKingCastle = false;
		whiteQueenCastle = false;
		blackQueenCastle = false;
		whiteKingMoved = true;
		whiteKingRookMoved = true;
		whiteQueenRookMoved = true;
		blackKingMoved = true;
		blackKingRookMoved = true;
		blackQueenRookMoved = true;
		getMoveList('W',false);
		returnFEN();
		addShortFEN();
		return tiles;
	}


	public Tile[] setCustomBoard(Tile[] t){
		genBoard();
		for(int i = 0; i < 64; i++){
			tiles[i].removePiece();

		}
		for(int i = 0; i < 64; i++){
			if(t[i].containsPiece){
				tiles[i].setPiece(new Piece(t[i].getPieceChar(),t[i].getPiece().getColor()));
			}
		}
		//printVisualBoard();
		return tiles;
	}
	
	public MoveList getMoveList(char c, boolean h){
		list = new MoveList(tiles, moves , c, doubleMoveTile, whiteKingMoved, blackKingMoved, whiteKingRookMoved,
				whiteQueenRookMoved, blackKingRookMoved, blackQueenRookMoved, h);

			list.setList();

		return list;
	}



	public ArrayList<Moves> getGameMoveList(){
		return moves;
	}

	/*
	 *  Generates and returns an empty board of 64 tiles. They'll all have null Pieces;
	 */
	private Tile[] genBoard(){
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
				
				tiles[curr] = new Tile( c + r , (i == 0 || i == 7) , curr);
				helpTiles[curr] = new Tile( c + r , (i == 0 || i == 7) , curr);
			}
		}
		return tiles;
	}
	
	/*
	 * Takes the board object, and fills it with the initial position in standard 
	 * chess games.
	 */
	
	public Tile[] setInitialPosition(){
		doubleMoveTile = -100;
		whiteKingCastle = true;
		blackKingCastle = true;
		whiteQueenCastle = true;
		blackQueenCastle = true;
		whiteKingMoved = false;
		whiteKingRookMoved = false;
		whiteQueenRookMoved = false;
		blackKingMoved = false;
		blackKingRookMoved = false;
		blackQueenRookMoved = false;
		shortFenList.clear();
		Piece[] pieces = new Piece [32];
		pieces[0] = new Piece('R','B');
		pieces[1] = new Piece('N','B');
		pieces[2] = new Piece('B','B');
		pieces[3] = new Piece('Q','B');
		pieces[4] = new Piece('K','B');
		pieces[5] = new Piece('B','B');
		pieces[6] = new Piece('N','B');
		pieces[7] = new Piece('R','B');
		for(int i = 8; i < 16; i++){
			pieces[i] = new Piece('P','B');
		}
		for(int i = 16; i < 24; i++){
			pieces[i] = new Piece('P','W');
		}
		pieces[24] = new Piece('R','W');
		pieces[25] = new Piece('N','W');
		pieces[26] = new Piece('B','W');
		pieces[27] = new Piece('Q','W');
		pieces[28] = new Piece('K','W');
		pieces[29] = new Piece('B','W');
		pieces[30] = new Piece('N','W');
		pieces[31] = new Piece('R','W');
		
		int j = 0;
		
		for(int i = 0; i < 16; i++){
			tiles[i].setPiece(pieces[j]);
			j++;
		}
        for(int i = 16; i < 48; i++){
            tiles[i].removePiece();

        }
		for(int i = 48; i < 64; i++){
			tiles[i].setPiece(pieces[j]);
			j++;
		}
		moves.clear();
		halfmove = 0;
		moveNum = 1;
		fullmove=1;
		ply=1;
		playerMove=0;
		getMoveList('W', false);
		returnFEN();
		addShortFEN();
		return tiles;
	}

    public boolean move(int start, int end, char c, boolean ip){
		isTurnPlayer = true;
		boolean capture = false;
		boolean promotion = false;
		boolean castleKing = false;
		boolean castleQueen = false;
		boolean enPassant = false;


		if(!list.checkIfLegal(start, end, c)){
			System.out.println("Illegal Move! ");
			System.out.println();
			return false;
		}

		if(tiles[start].getPieceChar() == 'P' && end == doubleMoveTile){
		enPassant = true;
		}
		
		if(c == 'W'){
			if(!tiles[start].getIfOccupied()){
				return false;
			}
			else{
				if(tiles[start].getPiece().getColor() == 'W'){
					if(tiles[start].getPieceChar() == 'K' && start == 60 && end == 62){
						castleKing = true;
					}
					if(tiles[start].getPieceChar() == 'K' && start == 60 && end == 58){
						castleQueen = true;
					}
					halfmove ++;
					if(tiles[start].getPieceChar() == 'P'){
						halfmove = 0;
					}
					if(tiles[end].getIfOccupied()){
						if(tiles[end].getPiece().getColor() == 'B'){
							capture = true;
							halfmove = 0;
							promotion = checkIfPromotion(start, end);
							addShortFEN();
							moves.add(new Moves(tiles[start].getPiece(), start, end, capture, 
									tiles[start].getPiece().getType() == 'K' && tiles[start].getID() == 60,
									tiles[start].getPiece().getType() == 'K' && tiles[start].getID() == 4,
									tiles[start].getPiece().getType() == 'R' && tiles[start].getID() == 56, 
									tiles[start].getPiece().getType() == 'R' && tiles[start].getID() == 0, 
									tiles[start].getPiece().getType() == 'R' && tiles[start].getID() == 63, 
									tiles[start].getPiece().getType() == 'R' && tiles[start].getID() == 7, 
									tiles[start].getPiece().getType() == 'P', 
									promotion, 
									ply, moveNum, c, FEN));
							if(tiles[start].getPieceChar() == 'P' && start - end == 16){
								doubleMoveTile = start - 8;
							}
							else{
								doubleMoveTile = -100;
							}
							if(tiles[start].getID() == 60){
								whiteKingMoved = true;
							}
							if(tiles[start].getID() == 63){
								whiteKingRookMoved = true;
							}
							if(tiles[start].getID() == 56){
								whiteQueenRookMoved = true;
							}
							tiles[end].removePiece();
							tiles[end].setPiece(tiles[start].getPiece());
							if(promotion){
								tiles[end].removePiece();
								tiles[end].setPiece(new Piece(c,setPromotionTo));
							}
							tiles[start].removePiece();
							if(enPassant){
								tiles[end + 8].removePiece();
							}
							ply++;
							playerMove = 1;
							returnFEN();
							addShortFEN();
							list = getMoveList('B', false);
							return true;
						}
						else{
							return false;
						}
					}
					if(tiles[start].getPieceChar() == 'K' && start == 60 && end == 62){
						castleKing = true;
					}
					if(tiles[start].getPieceChar() == 'K' && start == 60 && end == 58){
						castleQueen = true;
					}
					promotion = checkIfPromotion(start, end);
					moves.add(new Moves(tiles[start].getPiece(), start, end, capture, 
							tiles[start].getPiece().getType() == 'K' && tiles[start].getID() == 60,
							tiles[start].getPiece().getType() == 'K' && tiles[start].getID() == 4,
							tiles[start].getPiece().getType() == 'R' && tiles[start].getID() == 56, 
							tiles[start].getPiece().getType() == 'R' && tiles[start].getID() == 0, 
							tiles[start].getPiece().getType() == 'R' && tiles[start].getID() == 63, 
							tiles[start].getPiece().getType() == 'R' && tiles[start].getID() == 7, 
							tiles[start].getPiece().getType() == 'P', 
							promotion, 
							ply, moveNum, c, FEN));
					if(tiles[start].getPieceChar() == 'P' && start - end == 16){
						doubleMoveTile = start - 8;
					}
					else{
						doubleMoveTile = -100;
					}
					if(tiles[start].getID() == 60){
						whiteKingMoved = true;
					}
					if(tiles[start].getID() == 63){
						whiteKingRookMoved = true;
					}
					if(tiles[start].getID() == 56){
						whiteQueenRookMoved = true;
					}
					tiles[end].setPiece(tiles[start].getPiece());
					if(promotion){
						tiles[end].removePiece();
						tiles[end].setPiece(new Piece(c,setPromotionTo));
					}
					tiles[start].removePiece();
					if(enPassant){
						tiles[end + 8].removePiece();
					}
					if(castleKing){
						testMove(63, 61, 'W', false);
					}
					if(castleQueen){
						testMove(56, 59, 'W', false);
					}
					ply++;
					playerMove = 1;
					returnFEN();
					addShortFEN();
					list = getMoveList('B', false);
					return true;
				}
				else{
					return false;
				}
			}
		}
		else{
			if(!tiles[start].getIfOccupied()){
				return false;
			}
			else{
				halfmove ++;
				if(tiles[start].getPiece().getColor() == 'B'){
					if(tiles[start].getPieceChar() == 'P'){
						halfmove = 0;
					}
					if(tiles[end].getIfOccupied()){
						if(tiles[end].getPiece().getColor() == 'W'){
							capture = true;
							halfmove = 0;
							promotion = checkIfPromotion(start, end);
							moves.add(new Moves(tiles[start].getPiece(), start, end, capture, 
									tiles[start].getPiece().getType() == 'K' && tiles[start].getID() == 60,
									tiles[start].getPiece().getType() == 'K' && tiles[start].getID() == 4,
									tiles[start].getPiece().getType() == 'R' && tiles[start].getID() == 56, 
									tiles[start].getPiece().getType() == 'R' && tiles[start].getID() == 0, 
									tiles[start].getPiece().getType() == 'R' && tiles[start].getID() == 63, 
									tiles[start].getPiece().getType() == 'R' && tiles[start].getID() == 7, 
									tiles[start].getPiece().getType() == 'P', 
									tiles[end].getID() <= 7 && tiles[end].getID() >=0, 
									ply, moveNum, c, FEN));
							if(tiles[start].getPieceChar() == 'p' && end - start == 16){
								doubleMoveTile = start + 8;
							}
							else{
								doubleMoveTile = -100;
							}
							if(tiles[start].getID() == 4){
								blackKingMoved = true;
							}
							if(tiles[start].getID() == 7){
								blackKingRookMoved = true;
							}
							if(tiles[start].getID() == 0){
								blackQueenRookMoved = true;
							}
							tiles[end].removePiece();
							tiles[end].setPiece(tiles[start].getPiece());
							if(promotion){
								tiles[end].removePiece();
								tiles[end].setPiece(new Piece(c,setPromotionTo));
							}
							tiles[start].removePiece();
							if(enPassant){
								tiles[end - 8].removePiece();
							}
							ply++;
							moveNum++;
							fullmove++;
							playerMove = 0;
							returnFEN();
							addShortFEN();
							list = getMoveList('W', false);
							return true;
						}
						else{
							return false;
						}
					}
					if(tiles[start].getPieceChar() == 'k' && start == 4 && end == 6){
						castleKing = true;
					}
					if(tiles[start].getPieceChar() == 'k' && start == 4 && end == 2){
						castleQueen = true;
					}
					promotion = checkIfPromotion(start, end);
					moves.add(new Moves(tiles[start].getPiece(), start, end, capture, 
							tiles[start].getPiece().getType() == 'K' && tiles[start].getID() == 60,
							tiles[start].getPiece().getType() == 'K' && tiles[start].getID() == 4,
							tiles[start].getPiece().getType() == 'R' && tiles[start].getID() == 56, 
							tiles[start].getPiece().getType() == 'R' && tiles[start].getID() == 0, 
							tiles[start].getPiece().getType() == 'R' && tiles[start].getID() == 63, 
							tiles[start].getPiece().getType() == 'R' && tiles[start].getID() == 7, 
							tiles[start].getPiece().getType() == 'P', 
							tiles[end].getID() <= 7 && tiles[end].getID() >=0, 
							ply, moveNum, c, FEN));
					if(tiles[start].getPieceChar() == 'p' && end - start == 16){
						doubleMoveTile = start + 8;
					}
					else{
						doubleMoveTile = -100;
					}
					if(tiles[start].getID() == 4){
						blackKingMoved = true;
					}
					if(tiles[start].getID() == 7){
						blackKingRookMoved = true;
					}
					if(tiles[start].getID() == 0){
						blackQueenRookMoved = true;
					}
					tiles[end].setPiece(tiles[start].getPiece());
					if(promotion){
						tiles[end].removePiece();
						tiles[end].setPiece(new Piece(c,setPromotionTo));
					}
					tiles[start].removePiece();
					if(enPassant){
						tiles[end + 8].removePiece();
					}
					if(castleKing){
						testMove(7, 5, 'B', false);
					}
					if(castleQueen){
						testMove(0, 3, 'B', false);
					}
					ply++;
					moveNum++;
					fullmove++;
					playerMove = 0;
					returnFEN();
					addShortFEN();
					list = getMoveList('W', false);
					return true;
				}
				else{
					return false;
				}
			}
		}
	}
	
	public boolean testMove(int start, int end, char c, boolean ip){

		isTurnPlayer = true;
		boolean capture = false;
		boolean promotion = false;
		
		if(c == 'W'){
			if(!tiles[start].getIfOccupied()){
				return false;
			}
			else{
				if(tiles[start].getPiece().getColor() == 'W'){
					if(tiles[end].getIfOccupied()){
						if(tiles[end].getPiece().getColor() == 'B'){
							capture = true;
							
							promotion = checkIfPromotion(start, end);
							tiles[end].removePiece();
							tiles[end].setPiece(tiles[start].getPiece());

							tiles[start].removePiece();
							returnFEN();
							list = getMoveList('B', true);
							return true;
						}
						else{
							return false;
						}
					}
					promotion = checkIfPromotion(start, end);
					tiles[end].setPiece(tiles[start].getPiece());

					tiles[start].removePiece();
					returnFEN();
					list = getMoveList('B', true);
					return true;
				}
				else{
					return false;
				}
			}
		}
		else{
			if(!tiles[start].getIfOccupied()){
				return false;
			}
			else{
				halfmove ++;
				if(tiles[start].getPiece().getColor() == 'B'){
					if(tiles[end].getIfOccupied()){
						if(tiles[end].getPiece().getColor() == 'W'){
							capture = true;
							promotion = checkIfPromotion(start, end);
							tiles[end].removePiece();
							tiles[end].setPiece(tiles[start].getPiece());

							tiles[start].removePiece();
							returnFEN();
							list = getMoveList('W', true);
							return true;
						}
						else{
							return false;
						}
					}
					promotion = checkIfPromotion(start, end);

					tiles[end].setPiece(tiles[start].getPiece());

					tiles[start].removePiece();
					ply++;
					returnFEN();
					list = getMoveList('W', true);
					return true;
				}
				else{
					return false;
				}
			}
		}
	}

	public boolean verifyIfCheck(char c){
		return list.checkIfCheck(c);
	}

	public Board getBoardBoard(){return this;}

	public Tile[] getBoard(){
		return tiles;
	}

	public void setBoard(Board b){
		this.tiles = b.getBoard();
	}
	
	public Tile[] getHelpBoard(){
		return helpTiles;
	}
	
	public Tile getTile(int id){
		return tiles[id];
	}
	
	public MoveList checkAllLegal(char c){
		MoveList ml = new MoveList(tiles,moves, c, doubleMoveTile, whiteKingMoved, blackKingMoved, whiteKingRookMoved,
				whiteQueenRookMoved, blackKingRookMoved, blackQueenRookMoved, false);

			ml.setList();


		return ml;
	}
	
	public boolean checkIfPromotion(int ss, int es){
			if(!tiles[ss].getIfOccupied()){
				return false;
			}
			if (tiles[ss].getPieceChar() == 'P' && (es >= 0 && es <= 7)) {
				return true;
			}
			else if (tiles[ss].getPieceChar() == 'p' && (es >= 56 && es <= 63)) {
				return true;
			}
			else {
				return false;
			}
		}

	
	public String returnFEN(){
		String fen = "";
		for(int i = 0; i<8; i++){
			int consecutiveEmpty = 0;
			for(int j = 0; j < 8; j++){
				if(tiles[8*i + j].containsPiece){
					if(consecutiveEmpty > 0){
						fen = fen + Integer.toString(consecutiveEmpty);
						consecutiveEmpty = 0;
					}
					fen = fen + Character.toString(tiles[8*i + j].getPieceChar());
				}
				else{
					consecutiveEmpty++;
				}
			}
			if(consecutiveEmpty > 0){
				fen = fen + Integer.toString(consecutiveEmpty);
				consecutiveEmpty = 0;
			}
			if(i < 7){
			fen = fen + "/";
			}
		}
		
		fen = fen + " ";
		
		if(playerMove == 0){
			fen = fen + "w";
		}
		else if(playerMove == 1){
			fen = fen + "b";
		}
		
		fen = fen + " ";
		
		if(whiteKingCastle){
			fen = fen + "K";
		}
		if(whiteQueenCastle){
			fen = fen + "Q";
		}
		if(blackKingCastle){
			fen = fen + "k";
		}
		if(blackQueenCastle){
			fen = fen + "q";
		}
		if(!whiteKingCastle && !whiteQueenCastle && !blackKingCastle && !blackQueenCastle){
			fen = fen + "-";
		}
		
		fen = fen + " ";
		
		if(doubleMoveTile == -100){
			fen = fen + "-";
		}
		else{
			fen = fen + tileToNotation(doubleMoveTile);
		}
		
		fen = fen + " ";
		fen = fen + Integer.toString(halfmove);
		fen = fen + " ";
		fen = fen + Integer.toString(fullmove);
		
		FEN = fen;
		return fen;
	}

	public String getShortFEN(){
		String[] sf = FEN.split(" ");
		return sf[0];
	}

	public ArrayList<String> addShortFEN(){
		shortFenList.add(getShortFEN());
		return shortFenList;
	}

	public boolean checkIfDraw(char c){
		if(checkIfFiftyMoveRule() ||  checkForDrawnTable() || checkIfStaleMate(c) || checkThreefoldRepetition()){
			return true;
		}
		else{
			return false;
		}
	}

	public boolean checkIfFiftyMoveRule(){
		if(halfmove >= 50){
			System.out.println("50 Move");
			return true;
		}
		else{
			return false;
		}
	}

	public boolean checkForCheckmate(char c){
		if(!verifyIfCheck(c)){
			return false;
		}
		if(c == 'W') {
			for (ShortMove sm : list.whiteMoves){
				if(!quickMove(sm.getStartSquare(),sm.getEndSquare(),sm.getColor())){
					return false;
				}
			}
		}
		else{
			for(ShortMove sm: list.blackMoves){
				if(!quickMove(sm.getStartSquare(),sm.getEndSquare(),sm.getColor())){
					return false;
				}
			}
		}
		return true;
	}

	public boolean checkIfStaleMate(char c){

		if(verifyIfCheck(c)){
			return false;
		}
		if(c == 'W') {
			for (ShortMove sm : list.whiteMoves){
				if(Character.toUpperCase(sm.getType()) == 'P' || Character.toUpperCase(sm.getType()) == 'Q' ||
						Character.toUpperCase(sm.getType()) == 'N' ||
						Character.toUpperCase(sm.getType()) == 'B' ||
						Character.toUpperCase(sm.getType()) == 'R'){
					return false;
				}
				if(Character.toUpperCase(sm.getType()) == 'K'){
					if(!quickMove(sm.getStartSquare(),sm.getEndSquare(),'W')){
						return false;
					}
				}
			}
		}
		else{
			for(ShortMove sm: list.blackMoves){
				if(Character.toLowerCase(sm.getType()) == 'p' || Character.toLowerCase(sm.getType()) == 'q' ||
						Character.toLowerCase(sm.getType()) == 'n' || Character.toLowerCase(sm.getType()) == 'b' ||
						Character.toLowerCase(sm.getType()) == 'r'){
					return false;
				}
				if(Character.toLowerCase(sm.getType()) == 'k'){
					if(!quickMove(sm.getStartSquare(),sm.getEndSquare(),'B')){
						return false;
					}
				}
			}
		}

		System.out.println("Stalemate");
		return true;
	}

	public boolean checkForDrawnTable(){

		if(list.pieces.size() > 2){
			return false;
		}
		System.out.println("Table");
		return true;
	}

	/*
	Does a temporary move. Returns true if the temporary move would be legal. False if otherwise
	 */
	public boolean quickMove(int ss, int es, char c){

		if(tiles[es].containsPiece) {
			if(tiles[es].getPiece().getColor() == c){
				return true;
			}
			Piece temp = new Piece(tiles[es].getPiece());
			Piece temp2 = new Piece(tiles[ss].getPiece());
			boolean isCheck;
			boolean promo = false;
			tiles[es].removePiece();
			tiles[es].setPiece(tiles[ss].getPiece());

			tiles[ss].removePiece();
			MoveList hList = new MoveList(tiles, moves , c, doubleMoveTile, whiteKingMoved, blackKingMoved, whiteKingRookMoved,
					whiteQueenRookMoved, blackKingRookMoved, blackQueenRookMoved, false);
			hList.setList();
			isCheck = hList.checkIfCheck(c);

			tiles[es].removePiece();
			tiles[es].setPiece(new Piece(temp));
			return isCheck;
		}
		else{
			boolean isCheck;
			Piece temp2 = new Piece(tiles[ss].getPiece());
			boolean promo = false;
			tiles[es].setPiece(tiles[ss].getPiece());

			tiles[ss].removePiece();
			MoveList hList = new MoveList(tiles, moves , c, doubleMoveTile, whiteKingMoved, blackKingMoved, whiteKingRookMoved,
					whiteQueenRookMoved, blackKingRookMoved, blackQueenRookMoved, false);
			hList.setList();
			isCheck = hList.checkIfCheck(c);

			tiles[es].removePiece();
			return isCheck;
		}
	}

	public boolean checkThreefoldRepetition(){
		for(int i = 0; i < shortFenList.size() - 1; i++){
			int rep = 0;
			String test = new String(shortFenList.get(i));
			for(String s : shortFenList){
				if(test.equals(s)){
					rep++;
				}
			}
			if(rep >= 3){
				System.out.println("Three Fold");
				return true;
			}
		}

		return false;
	}

	public char setPromotionPiece(char p){
		switch(p){
			case 'Q': setPromotionTo = 'Q';
				break;
			case 'B': setPromotionTo = 'B';
				break;
			case 'N': setPromotionTo = 'N';
				break;
			case 'R': setPromotionTo = 'R';
				break;
			default: setPromotionTo = 'Q';
		}
		return setPromotionTo;
	}

	public Tile setPromotionPieceInTile(int id){
		tiles[id].removePiece();
		if(playerMove == 1) {
			getMoveList('B', false);
			tiles[id].setPiece(new Piece('W', setPromotionTo));
		}
		else{
			getMoveList('W', false);
			tiles[id].setPiece(new Piece('B', setPromotionTo));
		}
		return tiles[id];
	}
	public Tile setPromotionPieceInTile(int id, char c){
		tiles[id].removePiece();
		char opp;
		if(c == 'W'){
			opp = 'B';
		}
		else{
			opp = 'W';
		}

		tiles[id].setPiece(new Piece(setPromotionTo, c));
		getMoveList(opp, false);

		return tiles[id];
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

	/*
	 * For debugging purposes. Prints the contents of the board.
	 */
	public void printBoard(){
		System.out.println("Notation / com.capstone.chesscommander.chesscommander.GameLogic.Piece / ID / Promotion / Occupied ");
		for(int i = 0; i < 64; i++){
		System.out.println(tiles[i].getNotation() + " " +tiles[i].getPieceChar() + " " + 
		tiles[i].getID() + " " + tiles[i].getIfPromotionSquare() + " "+ tiles[i].getIfOccupied());
		}
	}
	
	/*
	 * For debugging purposes. Prints contents of a move object.
	 */
	public void printMove(int m){
		System.out.println("Move # " + moves.get(m).getMoveNumber());
		System.out.println("Ply # " + moves.get(m).getPly() );
		System.out.println(moves.get(m).getStartSquareID());
		System.out.println(moves.get(m).getEndSquareID());
		System.out.println(moves.get(m).getMovedPiece().getType());
		System.out.println(moves.get(m).getMoveDoneBy());
		System.out.println();
	}
	
	public void printFEN(){
		System.out.println(FEN);
		System.out.println();
	}
	
	public void printVisualBoard(){
		System.out.println("X | a | b | c | d | e | f | g | h |");
		System.out.println("-----------------------------------");
		for(int i = 0; i < 8; i++){
		System.out.println((8 - i) + " | " + tiles[0 + (i*8)].getPieceChar() + " | " + tiles[1 + (i*8)].getPieceChar() + 
				" | " + tiles[2 + (i*8)].getPieceChar() + " | " + tiles[3 + (i*8)].getPieceChar() + 
				" | " + tiles[4 + (i*8)].getPieceChar() + " | " + tiles[5 + (i*8)].getPieceChar() + 
				" | " + tiles[6 + (i*8)].getPieceChar() + " | " + tiles[7 + (i*8)].getPieceChar() + " |"); 
		}
		System.out.println();
	}
	



}