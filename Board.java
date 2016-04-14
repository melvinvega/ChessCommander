
import java.util.Scanner;



/**
 * Board abstraction class. Contains the 64 tiles in the board, as well as its contents.
 * Note it is a WIP.
 * 1. Custom Board creator
 * 
 * @author Eduardo
 *
 */
public class Board {
	Game myGame;
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
	int halfmove = 0;
	int fullmove = 1;
	int playerMove = 0;
	int doubleMoveTile = -1;
	String FEN;
	MoveList list;
	
	Tile[] tiles = new Tile [64];
	Moves[] moves = new Moves [1000];
	
	int moveNum = 1;
	int ply = 1;
	
	
	public Board(){
		genBoard();
		myGame = new Game();
	}
	
	
	public MoveList getMoveList(char c){
		list = new MoveList(tiles, c, doubleMoveTile, whiteKingMoved, blackKingMoved, whiteKingRookMoved,
				whiteQueenRookMoved, blackKingRookMoved, blackQueenRookMoved);
		return list;
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
			}
		}
		return tiles;
	}
	
	/*
	 * Takes the board object, and fills it with the initial position in standard 
	 * chess games.
	 */
	
	public Tile[] setInitialPosition(){
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
		for(int i = 48; i < 64; i++){
			tiles[i].setPiece(pieces[j]);
			j++;
		}
		getMoveList('W');
		returnFEN();
		return tiles;
	}
	
	public boolean move(int start, int end, char c){
		boolean capture = false;
		boolean promotion = false;
		
		if(c == 'W'){
			if(!tiles[start].getIfOccupied()){
				return false;
			}
			else{
				if(!list.checkIfLegal(start, end, c)){
						System.out.println("Illegal Move!");
						return false;
					}
				if(tiles[start].getPiece().getColor() == 'W'){
					
					halfmove ++;
					if(tiles[start].getPieceChar() == 'P'){
						halfmove = 0;
					}
					if(tiles[end].getIfOccupied()){
						if(tiles[end].getPiece().getColor() == 'B'){
							capture = true;
							halfmove = 0;
							promotion = checkIfPromotion(tiles[start].getPiece(), tiles[end].getID());
							moves[ply] = new Moves(tiles[start].getPiece(), start, end, capture, 
									tiles[start].getPiece().getType() == 'K' && tiles[start].getID() == 60,
									tiles[start].getPiece().getType() == 'K' && tiles[start].getID() == 4,
									tiles[start].getPiece().getType() == 'R' && tiles[start].getID() == 56, 
									tiles[start].getPiece().getType() == 'R' && tiles[start].getID() == 0, 
									tiles[start].getPiece().getType() == 'R' && tiles[start].getID() == 63, 
									tiles[start].getPiece().getType() == 'R' && tiles[start].getID() == 7, 
									tiles[start].getPiece().getType() == 'P', 
									promotion, 
									ply, moveNum, c, tiles);
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
								char n = askForPromotion();
								tiles[end].pieceInTile.setType(n);
							}
							tiles[start].removePiece();
							ply++;
							playerMove = 1;
							returnFEN();
							list = getMoveList('B');
							return true;
						}
						else{
							return false;
						}
					}
					promotion = checkIfPromotion(tiles[start].getPiece(), tiles[end].getID());
					moves[ply] = new Moves(tiles[start].getPiece(), start, end, capture, 
							tiles[start].getPiece().getType() == 'K' && tiles[start].getID() == 60,
							tiles[start].getPiece().getType() == 'K' && tiles[start].getID() == 4,
							tiles[start].getPiece().getType() == 'R' && tiles[start].getID() == 56, 
							tiles[start].getPiece().getType() == 'R' && tiles[start].getID() == 0, 
							tiles[start].getPiece().getType() == 'R' && tiles[start].getID() == 63, 
							tiles[start].getPiece().getType() == 'R' && tiles[start].getID() == 7, 
							tiles[start].getPiece().getType() == 'P', 
							promotion, 
							ply, moveNum, c, tiles);
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
						char n = askForPromotion();
						tiles[end].pieceInTile.setType(n);
					}
					tiles[start].removePiece();
					ply++;
					playerMove = 1;
					returnFEN();
					list = getMoveList('B');
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
							promotion = checkIfPromotion(tiles[start].getPiece(), tiles[end].getID());
							moves[ply] = new Moves(tiles[start].getPiece(), start, end, capture, 
									tiles[start].getPiece().getType() == 'K' && tiles[start].getID() == 60,
									tiles[start].getPiece().getType() == 'K' && tiles[start].getID() == 4,
									tiles[start].getPiece().getType() == 'R' && tiles[start].getID() == 56, 
									tiles[start].getPiece().getType() == 'R' && tiles[start].getID() == 0, 
									tiles[start].getPiece().getType() == 'R' && tiles[start].getID() == 63, 
									tiles[start].getPiece().getType() == 'R' && tiles[start].getID() == 7, 
									tiles[start].getPiece().getType() == 'P', 
									tiles[end].getID() <= 7 && tiles[end].getID() >=0, 
									ply, moveNum, c, tiles);
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
								char n = askForPromotion();
								tiles[end].pieceInTile.setType(n);
							}
							tiles[start].removePiece();
							ply++;
							moveNum++;
							fullmove++;
							playerMove = 0;
							returnFEN();
							list = getMoveList('W');
							return true;
						}
						else{
							return false;
						}
					}
					promotion = checkIfPromotion(tiles[start].getPiece(), tiles[end].getID());
					moves[ply] = new Moves(tiles[start].getPiece(), start, end, capture, 
							tiles[start].getPiece().getType() == 'K' && tiles[start].getID() == 60,
							tiles[start].getPiece().getType() == 'K' && tiles[start].getID() == 4,
							tiles[start].getPiece().getType() == 'R' && tiles[start].getID() == 56, 
							tiles[start].getPiece().getType() == 'R' && tiles[start].getID() == 0, 
							tiles[start].getPiece().getType() == 'R' && tiles[start].getID() == 63, 
							tiles[start].getPiece().getType() == 'R' && tiles[start].getID() == 7, 
							tiles[start].getPiece().getType() == 'P', 
							tiles[end].getID() <= 7 && tiles[end].getID() >=0, 
							ply, moveNum, c, tiles);
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
						char n = askForPromotion();
						tiles[end].pieceInTile.setType(n);
					}
					tiles[start].removePiece();
					ply++;
					moveNum++;
					fullmove++;
					playerMove = 0;
					returnFEN();
					list = getMoveList('W');
					return true;
				}
				else{
					return false;
				}
			}
		}
	}
	

	private char askForPromotion() {
		
		System.out.println("Promotion!");
//		Scanner s = new Scanner(System.in);
//		char in = 'x';
//		while(true){
//			System.out.println("Insert Promotion Piece");
//			in = s.nextLine().charAt(0);
//			if(in == 'Q' || in == 'R' || in == 'B' ||in != 'N'){
//					break;
//					}
//		}
//		return in;
		return 'q';
	}

	public Tile[] getBoard(){
		return tiles;
	}
	
	public Tile getTile(int id){
		return tiles[id];
	}
	
	public MoveList checkAllLegal(char c){
		MoveList ml = new MoveList(tiles, c, doubleMoveTile, whiteKingMoved, blackKingMoved, whiteKingRookMoved,
				whiteQueenRookMoved, blackKingRookMoved, blackQueenRookMoved);
		return ml;
	}
	
	private boolean checkIfPromotion(Piece p, int t){
		if(p.getType() == 'P' && (t >= 0 && t <= 7)){
			return true;
		}
		else if(p.getType() == 'p' && (t >= 56 && t <=63)){
			return true;
		}
		else{
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
		
		if(doubleMoveTile == -1){
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
		System.out.println("Notation / Piece / ID / Promotion / Occupied ");
		for(int i = 0; i < 64; i++){
		System.out.println(tiles[i].getNotation() + " " +tiles[i].getPieceChar() + " " + 
		tiles[i].getID() + " " + tiles[i].getIfPromotionSquare() + " "+ tiles[i].getIfOccupied());
		}
	}
	
	/*
	 * For debugging purposes. Prints contents of a move object.
	 */
	public void printMove(int m){
		System.out.println("Move # " + moves[m].getMoveNumber());
		System.out.println("Ply # " + moves[m].getPly() );
		System.out.println(moves[m].getStartSquareID());
		System.out.println(moves[m].getEndSquareID());
		System.out.println(moves[m].getMovedPiece().getType());
		System.out.println(moves[m].getMoveDoneBy());
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
	
	public Tile[] setKnightTestBoard(){
		tiles[0].setPiece(new Piece('N','W'));
		tiles[63].setPiece(new Piece('N','W'));
		tiles[7].setPiece(new Piece('N','B'));
		tiles[56].setPiece(new Piece('N','B'));
		getMoveList('W');
		return tiles;
	}
	
	public Tile[] setBishopTestBoard(){
		tiles[0].setPiece(new Piece('B','W'));
		tiles[1].setPiece(new Piece('B','W'));
		tiles[2].setPiece(new Piece('B','B'));
		tiles[3].setPiece(new Piece('B','B'));
		tiles[30].setPiece(new Piece('P','B'));
		tiles[38].setPiece(new Piece('P','W'));
		tiles[54].setPiece(new Piece('P','W'));
		tiles[46].setPiece(new Piece('P','B'));
		getMoveList('W');
		return tiles;
	}
	
	public Tile[] setRookTestBoard(){
	
		tiles[35].setPiece(new Piece('R','W'));
		tiles[36].setPiece(new Piece('R','B'));
		tiles[19].setPiece(new Piece('P','W'));
		tiles[20].setPiece(new Piece('P','B'));
		tiles[51].setPiece(new Piece('P','B'));
		tiles[52].setPiece(new Piece('P','W'));
		getMoveList('W');
		return tiles;
	}
	
	public Tile[] setQueenTestBoard(){
		tiles[35].setPiece(new Piece('Q','W'));
		tiles[36].setPiece(new Piece('Q','B'));
		tiles[19].setPiece(new Piece('P','W'));
		tiles[20].setPiece(new Piece('P','B'));
		tiles[51].setPiece(new Piece('P','B'));
		tiles[52].setPiece(new Piece('P','W'));
		tiles[33].setPiece(new Piece('P','W'));
		tiles[38].setPiece(new Piece('P','B'));
		getMoveList('W');
		return tiles;
	}
	
	public Tile[] setCastlingTestBoard(){
		tiles[60].setPiece(new Piece('K','W'));
		tiles[63].setPiece(new Piece('R','W'));
		tiles[4].setPiece(new Piece('K','B'));
		tiles[0].setPiece(new Piece('R', 'B'));
		getMoveList('W');
		return tiles;
	}
	
	public Tile[] setKingTestBoard(){
		tiles[35].setPiece(new Piece('K','W'));
		tiles[37].setPiece(new Piece('K','B'));
		tiles[4].setPiece(new Piece('K','B'));
		getMoveList('W');
		return tiles;
	}
	
	public Tile[] setPawnTestBoard(){
		tiles[52].setPiece(new Piece('P','W'));
		tiles[8].setPiece(new Piece('P','W'));
		tiles[29].setPiece(new Piece('P','B'));
		getMoveList('W');
		return tiles;
	}
}