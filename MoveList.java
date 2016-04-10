import java.util.ArrayList;
import java.util.List;

public class MoveList {
	
	Tile[] board = new Tile [64];
	char color;
	Tile[] pieces = new Tile[32];
	Tile[] whites = new Tile[16];
	Tile[] blacks = new Tile[16];
	Tile wKing = new Tile();
	Tile bKing = new Tile();
	List<ShortMove> whiteMoves = new ArrayList<ShortMove>();
	List<ShortMove> blackMoves = new ArrayList<ShortMove>();
	ShortMove[] wKingMoves = new ShortMove[8];
	ShortMove[] bKingMoves = new ShortMove[8];
	
	
	
	public MoveList(Tile[] t, char c){
		board = t;
		color = c;
		findOccupiedTiles();
		findWhiteTiles();
		findBlackTiles();
		findWhiteMoves();
		findBlackMoves();
	}
	
	private void findOccupiedTiles(){
		int n = 0;
		for(int i = 0; i < 64; i++){
			if(board[i].getIfOccupied()){
				pieces[n] = board[i];
				n++;
			}
		}
		
	}
	
	private void findWhiteTiles(){
		int n = 0;
		for(int i = 0; i <32; i++){
			if(pieces[i].getPiece().getColor() == 'W'){
				whites[n] = pieces[i];
				n++;
			}
		}
	}
	
	private void findBlackTiles(){
		int n = 0;
		for(int i = 0; i <32; i++){
			if(pieces[i].getPiece().getColor() == 'B'){
				blacks[n] = pieces[i];
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
		int i = 0;
		for (Tile tile : whites){
			if(tile.getPieceChar() == 'Q'){
				// get queen moves
				continue;
			}
			else if(tile.getPieceChar() == 'R'){
				// get rook moves
				continue;
			}
			else if(tile.getPieceChar() == 'B'){
				//get bishop moves
				continue;
			}
			else if(tile.getPieceChar() == 'N'){
				//get knight moves
				continue;
			}
			else if(tile.getPieceChar() == 'P'){
				if(!checkForPiece(tile.getID() - 8)){
					whiteMoves.add(new ShortMove('W','P',tile.getID(),tile.getID() - 8));
					i++;
				}
				if(tile.getID() >= 48 && tile.getID() <= 55){
					if(!checkForPiece(tile.getID() - 16)){
						whiteMoves.add(new ShortMove('W','P',tile.getID(),tile.getID() - 16));
						i++;
					}
				}
				if(tile.getID() % 8 == 0){
					if(checkForBlackPiece(tile.getID() - 7)){
						whiteMoves.add(new ShortMove('W','P',tile.getID(), tile.getID() - 7));
						i++;
					}
				}
				if(tile.getID() % 8 == 7){
					if(checkForBlackPiece(tile.getID() - 9)){
						whiteMoves.add(new ShortMove('W','P',tile.getID(), tile.getID() - 9));
						i++;
					}
				}
				if(tile.getID() % 8 != 0 || tile.getID() % 8 != 7){
					if(checkForBlackPiece(tile.getID() - 7)){
						whiteMoves.add(new ShortMove('W','P',tile.getID(), tile.getID() - 7));
						i++;
					}
					if(checkForBlackPiece(tile.getID() - 9)){
						whiteMoves.add(new ShortMove('W','P',tile.getID(), tile.getID() - 9));
						i++;
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
		int j = 0;
		for (Tile tile : blacks){
			if(tile.getPieceChar() == 'q'){
				// get queen moves
				continue;
			}
			else if(tile.getPieceChar() == 'r'){
				// get rook moves
				continue;
			}
			else if(tile.getPieceChar() == 'b'){
				//get bishop moves
				continue;
			}
			else if(tile.getPieceChar() == 'n'){
				//get knight moves
				continue;
			}
			else if(tile.getPieceChar() == 'p'){
				if(!checkForPiece(tile.getID() + 8)){
					blackMoves.add(new ShortMove('B','p',tile.getID(),tile.getID() + 8));
					j++;
				}
				if(tile.getID() >= 8 && tile.getID() <= 15){
					if(!checkForPiece(tile.getID() + 16)){
						blackMoves.add(new ShortMove('B','p',tile.getID(),tile.getID() + 16));
						j++;
					}
				}
				if(tile.getID() % 8 == 0){
					if(checkForWhitePiece(tile.getID() + 9)){
						blackMoves.add(new ShortMove('B','p',tile.getID(), tile.getID() + 9));
						j++;
					}
				}
				if(tile.getID() % 8 == 7){
					if(checkForBlackPiece(tile.getID() + 7)){
						blackMoves.add(new ShortMove('B','p',tile.getID(), tile.getID() + 7));
						j++;
					}
				}
				if(tile.getID() % 8 != 0 || tile.getID() % 8 != 7){
					if(checkForWhitePiece(tile.getID() + 7)){
						blackMoves.add(new ShortMove('B','p',tile.getID(), tile.getID() + 7));
						j++;
					}
					if(checkForBlackPiece(tile.getID() + 9)){
						blackMoves.add(new ShortMove('B','p',tile.getID(), tile.getID() + 9));
						j++;
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
		//check for white's king moves in accordance to the movement of the opposing pieces
	}
	
	private void findBlackKingMoves(){
		//check for black's king moves in accordance to the movement of the opposing pieces
	}
	
	public boolean checkStalemate(char c){
		//checks for stalemate
		return false;
	}
	
	private boolean checkIfCheck(){
		//checks if a piece is currently attacking the king
		return false;
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
		
		tileNotation = tileNotation + (Integer.toString(rank + 1));
		
		return tileNotation;
	}
}
