
import java.util.Scanner;
import chesspresso.*;
import chesspresso.position.Position;
import chesspresso.move.Move;


/**
 * Board abstraction class. Contains the 64 tiles in the board, as well as its contents.
 * Note it is a WIP.
 * 1. Starting position gen - complete
 * 2. Piece Movement
 * 3. FEN generator
 * 4. Custom Board creator
 * 
 * @author Eduardo
 *
 */
public class Board {
	Game myGame;
	Position myPosition;
	Move myMove;
	
	Tile[] tiles = new Tile [64];
	Moves[] moves = new Moves [1000];
	
	int moveNum = 1;
	int ply = 1;
	
	
	public Board(){
		genBoard();
		myGame = new Game();
		myPosition = new Position();
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
		myPosition = Position.createInitialPosition();
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
				if(tiles[start].getPiece().getColor() == 'W'){
					if(tiles[end].getIfOccupied()){
						if(tiles[end].getPiece().getColor() == 'B'){
							capture = true;
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
							tiles[end].removePiece();
							tiles[end].setPiece(tiles[start].getPiece());
							if(promotion){
								char n = askForPromotion();
								tiles[end].pieceInTile.setType(n);
							}
							tiles[start].removePiece();
							ply++;
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
					tiles[end].setPiece(tiles[start].getPiece());
					if(promotion){
						char n = askForPromotion();
						tiles[end].pieceInTile.setType(n);
					}
					tiles[start].removePiece();
					ply++;
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
				if(tiles[start].getPiece().getColor() == 'B'){
					if(tiles[end].getIfOccupied()){
						if(tiles[end].getPiece().getColor() == 'W'){
							capture = true;
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
							tiles[end].removePiece();
							tiles[end].setPiece(tiles[start].getPiece());
							if(promotion){
								char n = askForPromotion();
								tiles[end].pieceInTile.setType(n);
							}
							tiles[start].removePiece();
							ply++;
							moveNum++;
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
					tiles[end].setPiece(tiles[start].getPiece());
					if(promotion){
						char n = askForPromotion();
						tiles[end].pieceInTile.setType(n);
					}
					tiles[start].removePiece();
					ply++;
					moveNum++;
					return true;
				}
				else{
					return false;
				}
			}
		}
	}
	

	private char askForPromotion() {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		char in = 'x';
		while(true){
			System.out.println("Insert Promotion Piece");
			in = s.nextLine().charAt(0);
			if(in == 'Q' || in == 'R' || in == 'B' ||in != 'N'){
					break;
					}
		}
		return in;
	}

	public Tile[] getBoard(){
		return tiles;
	}
	
	public Tile getTile(int id){
		return tiles[id];
	}
	
	public MoveList checkAllLegal(char c){
		MoveList ml = new MoveList(tiles, c);
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
	
	private static int getChesspressoSquare(int id){
		switch (id){
		case 0: return Chess.H1;
		case 1: return Chess.H2;
		case 2: return Chess.H3;
		case 3: return Chess.H4;
		case 4: return Chess.H5;
		case 5: return Chess.H6;
		case 6: return Chess.H7;
		case 7: return Chess.H8;
		case 8: return Chess.G1;
		case 9: return Chess.G2;
		case 10: return Chess.G3;
		case 11: return Chess.G4;
		case 12: return Chess.G5;
		case 13: return Chess.G6;
		case 14: return Chess.G7;
		case 15: return Chess.G8;
		case 16: return Chess.F1;
		case 17: return Chess.F2;
		case 18: return Chess.F3;
		case 19: return Chess.F4;
		case 20: return Chess.F5;
		case 21: return Chess.F6;
		case 22: return Chess.F7;
		case 23: return Chess.F8;
		case 24: return Chess.E1;
		case 25: return Chess.E2;
		case 26: return Chess.E3;
		case 27: return Chess.E4;
		case 28: return Chess.E5;
		case 29: return Chess.E6;
		case 30: return Chess.E7;
		case 31: return Chess.E8;
		case 32: return Chess.D1;
		case 33: return Chess.D2;
		case 34: return Chess.D3;
		case 35: return Chess.D4;
		case 36: return Chess.D5;
		case 37: return Chess.D6;
		case 38: return Chess.D7;
		case 39: return Chess.D8;
		case 40: return Chess.C1;
		case 41: return Chess.C2;
		case 42: return Chess.C3;
		case 43: return Chess.C4;
		case 44: return Chess.C5;
		case 45: return Chess.C6;
		case 46: return Chess.C7;
		case 47: return Chess.C8;
		case 48: return Chess.B1;
		case 49: return Chess.B2;
		case 50: return Chess.B3;
		case 51: return Chess.B4;
		case 52: return Chess.B5;
		case 53: return Chess.B6;
		case 54: return Chess.B7;
		case 55: return Chess.B8;
		case 56: return Chess.A1;
		case 57: return Chess.A2;
		case 58: return Chess.A3;
		case 59: return Chess.A4;
		case 60: return Chess.A5;
		case 61: return Chess.A6;
		case 62: return Chess.A7;
		case 63: return Chess.A8;
		default: return Chess.NO_SQUARE;
		}
		
	} 
	
	
	
	/*
	 * For debugging purposes. Prints the contents of the board.
	 */
	
	public void printBoard(){
		String rep = null;
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
	}
	
	public void printVisualBoard(){
		for(int i = 0; i < 8; i++){
		System.out.println("| " + tiles[0 + (i*8)].getPieceChar() + " | " + tiles[1 + (i*8)].getPieceChar() + 
				" | " + tiles[2 + (i*8)].getPieceChar() + " | " + tiles[3 + (i*8)].getPieceChar() + 
				" | " + tiles[4 + (i*8)].getPieceChar() + " | " + tiles[5 + (i*8)].getPieceChar() + 
				" | " + tiles[6 + (i*8)].getPieceChar() + " | " + tiles[7 + (i*8)].getPieceChar() + " |"); 
		}
		System.out.println();
	}
}