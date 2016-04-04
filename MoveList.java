
public class MoveList {
	
	Tile[] board = new Tile [64];
	char color;
	Tile[] pieces = new Tile[32];
	Tile[] whites = new Tile[16];
	Tile[] blacks = new Tile[16];
	Tile wKing = new Tile();
	Tile bKing = new Tile();
	ShortMove[] whiteMoves = new ShortMove[1000];
	ShortMove[] blackMoves = new ShortMove[1000];
	ShortMove[] wKingMoves = new ShortMove[8];
	ShortMove[] bKingMoves = new ShortMove[8];
	
	
	
	public MoveList(Tile[] t, char c){
		board = t;
		color = c;
		findOccupiedTiles();
		findWhiteTiles();
		findBlackTiles();
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
					whiteMoves[i] = new ShortMove('W','P',tile.getID(),tile.getID() - 8);
					i++;
				}
				if(tile.getID() >= 48 && tile.getID() <= 55){
					if(!checkForPiece(tile.getID() - 16)){
						whiteMoves[i] = new ShortMove('W','P',tile.getID(),tile.getID() - 16);
						i++;
					}
				}
				if(tile.getID() % 8 == 0){
					if(checkForBlackPiece(tile.getID() - 7)){
						whiteMoves[i] = new ShortMove('W','P',tile.getID(), tile.getID() - 7);
						i++;
					}
				}
				if(tile.getID() % 8 == 7){
					if(checkForBlackPiece(tile.getID() - 9)){
						whiteMoves[i] = new ShortMove('W','P',tile.getID(), tile.getID() - 9);
						i++;
					}
				}
				if(tile.getID() % 8 != 0 || tile.getID() % 8 != 7){
					if(checkForBlackPiece(tile.getID() - 7)){
						whiteMoves[i] = new ShortMove('W','P',tile.getID(), tile.getID() - 7);
						i++;
					}
					if(checkForBlackPiece(tile.getID() - 9)){
						whiteMoves[i] = new ShortMove('W','P',tile.getID(), tile.getID() - 9);
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
					blackMoves[j] = new ShortMove('B','p',tile.getID(),tile.getID() + 8);
					j++;
				}
				if(tile.getID() >= 8 && tile.getID() <= 15){
					if(!checkForPiece(tile.getID() + 16)){
						blackMoves[j] = new ShortMove('B','p',tile.getID(),tile.getID() + 16);
						j++;
					}
				}
				if(tile.getID() % 8 == 0){
					if(checkForWhitePiece(tile.getID() + 9)){
						blackMoves[j] = new ShortMove('B','p',tile.getID(), tile.getID() + 9);
						j++;
					}
				}
				if(tile.getID() % 8 == 7){
					if(checkForBlackPiece(tile.getID() + 7)){
						blackMoves[j] = new ShortMove('B','p',tile.getID(), tile.getID() + 7);
						j++;
					}
				}
				if(tile.getID() % 8 != 0 || tile.getID() % 8 != 7){
					if(checkForWhitePiece(tile.getID() + 7)){
						blackMoves[j] = new ShortMove('B','p',tile.getID(), tile.getID() + 7);
						j++;
					}
					if(checkForBlackPiece(tile.getID() + 9)){
						blackMoves[j] = new ShortMove('B','p',tile.getID(), tile.getID() + 9);
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
	
	public void printMoves(){
		for 
	}
	
}
