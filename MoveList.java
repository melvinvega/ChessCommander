import java.util.ArrayList;
import java.util.List;

public class MoveList {
	
	Tile[] board = new Tile [64];
	char color;
	List<Tile> pieces = new ArrayList<Tile>();
	List<Tile> whites = new ArrayList<Tile>();
	List<Tile> blacks = new ArrayList<Tile>();
	Tile wKing = new Tile();
	Tile bKing = new Tile();
	List<ShortMove> whiteMoves = new ArrayList<ShortMove>();
	List<ShortMove> blackMoves = new ArrayList<ShortMove>();
	List<ShortMove> wKingMoves = new ArrayList<ShortMove>();;
	List<ShortMove> bKingMoves = new ArrayList<ShortMove>();;
	int doublePawnMove;
	boolean wkm;
	boolean bkm;
	boolean wkrm;
	boolean wqrm;
	boolean bkrm;
	boolean bqrm;
	
	
	public MoveList(Tile[] t, char c, int dpm, boolean wk, boolean bk, boolean wkr, boolean wqr, boolean bkr, boolean bqr){
		board = t;
		color = c;
		wkm = wk;
		bkm = bk;
		wkrm = wkr;
		wqrm = wqr;
		bkrm = bkr;
		bqrm = bqr;
		
		if(dpm != -1){
			doublePawnMove = dpm;
		}
		else{
			doublePawnMove = - 100;
		}
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
						whiteMoves.add(new ShortMove('W','Q',tile.getID(), tile.getID() - i));
						break;
					}
					else{
						whiteMoves.add(new ShortMove('W','Q',tile.getID(), tile.getID() - i));
					}
				}
				for(int i = 1; colPos + i <= 7; i++){
					if(checkForWhitePiece(tile.getID() + i)){
						break;
					}
					if(checkForBlackPiece(tile.getID() + i)){
						whiteMoves.add(new ShortMove('W','Q',tile.getID(), tile.getID() + i));
						break;
					}
					else{
						whiteMoves.add(new ShortMove('W','Q',tile.getID(), tile.getID() + i));
					}
				}
				for(int i = 1; rankPos - i >= 0; i++){
					if(checkForWhitePiece(tile.getID() - (8 * i))){
						break;
					}
					if(checkForBlackPiece(tile.getID() - (8 * i))){
						whiteMoves.add(new ShortMove('W','Q',tile.getID(), tile.getID() - (8 * i)));
						break;
					}
					else{
						whiteMoves.add(new ShortMove('W','Q',tile.getID(), tile.getID() - (8 * i)));
					}
				}
				for(int i = 1; rankPos + i <= 7; i++){
					if(checkForWhitePiece(tile.getID() + (8 * i))){
						break;
					}
					if(checkForBlackPiece(tile.getID() + (8 * i))){
						whiteMoves.add(new ShortMove('W','Q',tile.getID(), tile.getID() + (8 * i)));
						break;
					}
					else{
						whiteMoves.add(new ShortMove('W','Q',tile.getID(), tile.getID() + (8 * i)));
					}
				}
				
				for(int i = 1; ((rankPos - i) >= 0) && ((colPos - i) >= 0) ; i++){
					if(checkForWhitePiece(((rankPos - i) * 8) + (colPos - i))){
						break;
					}
					if(checkForBlackPiece((rankPos * i) + (colPos))){
						whiteMoves.add(new ShortMove('W', 'Q', tile.getID(),((rankPos - i) * 8) + (colPos - i)));
						break;
					}
					else{
						whiteMoves.add(new ShortMove('W', 'Q', tile.getID(),((rankPos - i) * 8) + (colPos - i)));
					}
				}
				
				for(int i = 1; ((rankPos + i) <=7) && ((colPos + i) <= 7); i++){
					if(checkForWhitePiece(((rankPos + i)* 8) + (colPos + i))){
						break;
					}
					if(checkForBlackPiece(((rankPos + i)* 8) + (colPos + i))){
						whiteMoves.add(new ShortMove('W', 'Q', tile.getID(),((rankPos + i)* 8) + (colPos + i)));
						break;
					}
					else{
						whiteMoves.add(new ShortMove('W', 'Q', tile.getID(),((rankPos + i)* 8) + (colPos + i)));
					}
				}
				
				for(int i = 1; ((rankPos + i) <=7) && ((colPos - i) >= 0); i++){
					if(checkForWhitePiece(((rankPos + i)* 8) + (colPos - i))){
						break;
					}
					if(checkForBlackPiece(((rankPos + i)* 8) + (colPos - i))){
						whiteMoves.add(new ShortMove('W', 'Q', tile.getID(),((rankPos + i)* 8) + (colPos - i)));
						break;
					}
					else{
						whiteMoves.add(new ShortMove('W', 'Q', tile.getID(),((rankPos + i)* 8) + (colPos - i)));
					}
				}
				
				for(int i = 1; ((rankPos - i) >= 0) && ((colPos + i) <= 7); i++){
					if(checkForWhitePiece(((rankPos - i)* 8) + (colPos + i))){
						break;
					}
					if(checkForBlackPiece(((rankPos - i)* 8) + (colPos + i))){
						whiteMoves.add(new ShortMove('W', 'Q', tile.getID(),((rankPos - i)* 8) + (colPos + i)));
						break;
					}
					else{
						whiteMoves.add(new ShortMove('W', 'Q', tile.getID(),((rankPos - i)* 8) + (colPos + i)));
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
						whiteMoves.add(new ShortMove('W','R',tile.getID(), tile.getID() - i));
						break;
					}
					else{
						whiteMoves.add(new ShortMove('W','R',tile.getID(), tile.getID() - i));
					}
				}
				for(int i = 1; colPos + i <= 7; i++){
					if(checkForWhitePiece(tile.getID() + i)){
						break;
					}
					if(checkForBlackPiece(tile.getID() + i)){
						whiteMoves.add(new ShortMove('W','R',tile.getID(), tile.getID() + i));
						break;
					}
					else{
						whiteMoves.add(new ShortMove('W','R',tile.getID(), tile.getID() + i));
					}
				}
				for(int i = 1; rankPos - i >= 0; i++){
					if(checkForWhitePiece(tile.getID() - (8 * i))){
						break;
					}
					if(checkForBlackPiece(tile.getID() - (8 * i))){
						whiteMoves.add(new ShortMove('W','R',tile.getID(), tile.getID() - (8 * i)));
						break;
					}
					else{
						whiteMoves.add(new ShortMove('W','R',tile.getID(), tile.getID() - (8 * i)));
					}
				}
				for(int i = 1; rankPos + i <= 7; i++){
					if(checkForWhitePiece(tile.getID() + (8 * i))){
						break;
					}
					if(checkForBlackPiece(tile.getID() + (8 * i))){
						whiteMoves.add(new ShortMove('W','R',tile.getID(), tile.getID() + (8 * i)));
						break;
					}
					else{
						whiteMoves.add(new ShortMove('W','R',tile.getID(), tile.getID() + (8 * i)));
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
					if(checkForBlackPiece((rankPos * i) + (colPos))){
						whiteMoves.add(new ShortMove('W', 'B', tile.getID(),((rankPos - i) * 8) + (colPos - i)));
						break;
					}
					else{
						whiteMoves.add(new ShortMove('W', 'B', tile.getID(),((rankPos - i) * 8) + (colPos - i)));
					}
				}
				
				for(int i = 1; ((rankPos + i) <=7) && ((colPos + i) <= 7); i++){
					if(checkForWhitePiece(((rankPos + i)* 8) + (colPos + i))){
						break;
					}
					if(checkForBlackPiece(((rankPos + i)* 8) + (colPos + i))){
						whiteMoves.add(new ShortMove('W', 'B', tile.getID(),((rankPos + i)* 8) + (colPos + i)));
						break;
					}
					else{
						whiteMoves.add(new ShortMove('W', 'B', tile.getID(),((rankPos + i)* 8) + (colPos + i)));
					}
				}
				
				for(int i = 1; ((rankPos + i) <=7) && ((colPos - i) >= 0); i++){
					if(checkForWhitePiece(((rankPos + i)* 8) + (colPos - i))){
						break;
					}
					if(checkForBlackPiece(((rankPos + i)* 8) + (colPos - i))){
						whiteMoves.add(new ShortMove('W', 'B', tile.getID(),((rankPos + i)* 8) + (colPos - i)));
						break;
					}
					else{
						whiteMoves.add(new ShortMove('W', 'B', tile.getID(),((rankPos + i)* 8) + (colPos - i)));
					}
				}
				
				for(int i = 1; ((rankPos - i) >= 0) && ((colPos + i) <= 7); i++){
					if(checkForWhitePiece(((rankPos - i)* 8) + (colPos + i))){
						break;
					}
					if(checkForBlackPiece(((rankPos - i)* 8) + (colPos + i))){
						whiteMoves.add(new ShortMove('W', 'B', tile.getID(),((rankPos - i)* 8) + (colPos + i)));
						break;
					}
					else{
						whiteMoves.add(new ShortMove('W', 'B', tile.getID(),((rankPos - i)* 8) + (colPos + i)));
					}
				}
				
				continue;
			}
			else if(tile.getPieceChar() == 'N'){
				int colPos = tile.getID() % 8;
				int rankPos = (tile.getID() - (tile.getID() % 8)) / 8;
				
				// Tiles in area of columns c to f, and rank 3 to 6 (8 moves) 
				if(colPos >= 2 && colPos <= 5 && rankPos >= 2 && rankPos <= 5){
					if(!checkForWhitePiece(tile.getID() - 15)){
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
					if(!checkForWhitePiece(tile.getID() - 15)){
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
					if(!checkForWhitePiece(tile.getID() - 15)){
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
					if(!checkForWhitePiece(tile.getID() - 15)){
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
					if(!checkForWhitePiece(tile.getID() - 15)){
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
					if(!checkForWhitePiece(tile.getID() - 15)){
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
					if(!checkForWhitePiece(tile.getID() - 15)){
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
					if(!checkForWhitePiece(tile.getID() - 15)){
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
					if(!checkForWhitePiece(tile.getID() - 15)){
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
					if(!checkForWhitePiece(tile.getID() - 15)){
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
					if(!checkForWhitePiece(tile.getID() - 15)){
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
					if(!checkForWhitePiece(tile.getID() - 15)){
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
				if(!checkForPiece(tile.getID() - 8)){
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
				if(tile.getID() % 8 != 0 || tile.getID() % 8 != 7){
					if(doublePawnMove == tile.getID() - 7){
						whiteMoves.add(new ShortMove('W','P',tile.getID(), tile.getID() - 7));
					}
					if(doublePawnMove == tile.getID() - 9){
						whiteMoves.add(new ShortMove('W','P',tile.getID(), tile.getID() - 9));
					}
				}
				if(tile.getID() % 8 != 0 || tile.getID() % 8 != 7){
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
						blackMoves.add(new ShortMove('W','q',tile.getID(), tile.getID() - i));
						break;
					}
					else{
						blackMoves.add(new ShortMove('W','q',tile.getID(), tile.getID() - i));
					}
				}
				for(int i = 1; colPos + i <= 7; i++){
					if(checkForBlackPiece(tile.getID() + i)){
						break;
					}
					if(checkForWhitePiece(tile.getID() + i)){
						blackMoves.add(new ShortMove('W','q',tile.getID(), tile.getID() + i));
						break;
					}
					else{
						blackMoves.add(new ShortMove('W','q',tile.getID(), tile.getID() + i));
					}
				}
				for(int i = 1; rankPos - i >= 0; i++){
					if(checkForBlackPiece(tile.getID() - (8 * i))){
						break;
					}
					if(checkForWhitePiece(tile.getID() - (8 * i))){
						blackMoves.add(new ShortMove('W','q',tile.getID(), tile.getID() - (8 * i)));
						break;
					}
					else{
						blackMoves.add(new ShortMove('W','q',tile.getID(), tile.getID() - (8 * i)));
					}
				}
				for(int i = 1; rankPos + i <= 7; i++){
					if(checkForBlackPiece(tile.getID() + (8 * i))){
						break;
					}
					if(checkForWhitePiece(tile.getID() + (8 * i))){
						blackMoves.add(new ShortMove('W','q',tile.getID(), tile.getID() + (8 * i)));
						break;
					}
					else{
						blackMoves.add(new ShortMove('W','q',tile.getID(), tile.getID() + (8 * i)));
					}
				}
				
				for(int i = 1; ((rankPos - i) >= 0) && ((colPos - i) >= 0) ; i++){
					if(checkForBlackPiece(((rankPos - i) * 8) + (colPos - i))){
						break;
					}
					if(checkForWhitePiece((rankPos * i) + (colPos))){
						blackMoves.add(new ShortMove('W', 'q', tile.getID(),((rankPos - i) * 8) + (colPos - i)));
						break;
					}
					else{
						blackMoves.add(new ShortMove('W', 'q', tile.getID(),((rankPos - i) * 8) + (colPos - i)));
					}
				}
				
				for(int i = 1; ((rankPos + i) <=7) && ((colPos + i) <= 7); i++){
					if(checkForBlackPiece(((rankPos + i)* 8) + (colPos + i))){
						break;
					}
					if(checkForWhitePiece(((rankPos + i)* 8) + (colPos + i))){
						blackMoves.add(new ShortMove('W', 'q', tile.getID(),((rankPos + i)* 8) + (colPos + i)));
						break;
					}
					else{
						blackMoves.add(new ShortMove('W', 'q', tile.getID(),((rankPos + i)* 8) + (colPos + i)));
					}
				}
				
				for(int i = 1; ((rankPos + i) <=7) && ((colPos - i) >= 0); i++){
					if(checkForBlackPiece(((rankPos + i)* 8) + (colPos - i))){
						break;
					}
					if(checkForWhitePiece(((rankPos + i)* 8) + (colPos - i))){
						blackMoves.add(new ShortMove('W', 'q', tile.getID(),((rankPos + i)* 8) + (colPos - i)));
						break;
					}
					else{
						blackMoves.add(new ShortMove('W', 'q', tile.getID(),((rankPos + i)* 8) + (colPos - i)));
					}
				}
				
				for(int i = 1; ((rankPos - i) >= 0) && ((colPos + i) <= 7); i++){
					if(checkForBlackPiece(((rankPos - i)* 8) + (colPos + i))){
						break;
					}
					if(checkForWhitePiece(((rankPos - i)* 8) + (colPos + i))){
						blackMoves.add(new ShortMove('W', 'q', tile.getID(),((rankPos - i)* 8) + (colPos + i)));
						break;
					}
					else{
						blackMoves.add(new ShortMove('W', 'q', tile.getID(),((rankPos - i)* 8) + (colPos + i)));
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
						blackMoves.add(new ShortMove('B','r',tile.getID(), tile.getID() - i));
						break;
					}
					else{
						blackMoves.add(new ShortMove('B','r',tile.getID(), tile.getID() - i));
					}
				}
				for(int i = 1; colPos + i <= 7; i++){
					if(checkForBlackPiece(tile.getID() + i)){
						break;
					}
					if(checkForWhitePiece(tile.getID() + i)){
						blackMoves.add(new ShortMove('B','r',tile.getID(), tile.getID() + i));
						break;
					}
					else{
						blackMoves.add(new ShortMove('B','r',tile.getID(), tile.getID() + i));
					}
				}
				for(int i = 1; rankPos - i >= 0; i++){
					if(checkForBlackPiece(tile.getID() - (8 * i))){
						break;
					}
					if(checkForWhitePiece(tile.getID() - (8 * i))){
						blackMoves.add(new ShortMove('B','r',tile.getID(), tile.getID() - (8 * i)));
						break;
					}
					else{
						blackMoves.add(new ShortMove('B','r',tile.getID(), tile.getID() - (8 * i)));
					}
				}
				for(int i = 1; rankPos + i <= 7; i++){
					if(checkForBlackPiece(tile.getID() + (8 * i))){
						break;
					}
					if(checkForWhitePiece(tile.getID() + (8 * i))){
						blackMoves.add(new ShortMove('B','r',tile.getID(), tile.getID() + (8 * i)));
						break;
					}
					else{
						blackMoves.add(new ShortMove('B','r',tile.getID(), tile.getID() + (8 * i)));
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
					if(checkForWhitePiece((rankPos * i) + (colPos))){
						blackMoves.add(new ShortMove('B', 'b', tile.getID(),((rankPos - i) * 8) + (colPos - i)));
						break;
					}
					else{
						blackMoves.add(new ShortMove('B', 'b', tile.getID(),((rankPos - i) * 8) + (colPos - i)));
					}
				}
				
				for(int i = 1; ((rankPos + i) <=7) && ((colPos + i) <= 7); i++){
					if(checkForBlackPiece(((rankPos + i)* 8) + (colPos + i))){
						break;
					}
					if(checkForWhitePiece(((rankPos + i)* 8) + (colPos + i))){
						blackMoves.add(new ShortMove('B', 'b', tile.getID(),((rankPos + i)* 8) + (colPos + i)));
						break;
					}
					else{
						blackMoves.add(new ShortMove('B', 'b', tile.getID(),((rankPos + i)* 8) + (colPos + i)));
					}
				}
				
				for(int i = 1; ((rankPos + i) <=7) && ((colPos - i) >= 0); i++){
					if(checkForBlackPiece(((rankPos + i)* 8) + (colPos - i))){
						break;
					}
					if(checkForWhitePiece(((rankPos + i)* 8) + (colPos - i))){
						blackMoves.add(new ShortMove('B', 'b', tile.getID(),((rankPos + i)* 8) + (colPos - i)));
						break;
					}
					else{
						blackMoves.add(new ShortMove('B', 'b', tile.getID(),((rankPos + i)* 8) + (colPos - i)));
					}
				}
				
				for(int i = 1; ((rankPos - i) >= 0) && ((colPos + i) <= 7); i++){
					if(checkForBlackPiece(((rankPos - i)* 8) + (colPos + i))){
						break;
					}
					if(checkForWhitePiece(((rankPos - i)* 8) + (colPos + i))){
						blackMoves.add(new ShortMove('B', 'b', tile.getID(),((rankPos - i)* 8) + (colPos + i)));
						break;
					}
					else{
						blackMoves.add(new ShortMove('B', 'b', tile.getID(),((rankPos - i)* 8) + (colPos + i)));
					}
				}
				continue;
			}
			else if(tile.getPieceChar() == 'n'){
				int colPos = tile.getID() % 8;
				int rankPos = (tile.getID() - (tile.getID() % 8)) / 8;
				
				// Tiles in area of columns c to f, and rank 3 to 6 (8 moves) 
				if(colPos >= 2 && colPos <= 5 && rankPos >= 2 && rankPos <= 5){
					if(!checkForBlackPiece(tile.getID() - 15)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 15));
					}
					if(!checkForBlackPiece(tile.getID() - 6)){
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
					if(!checkForBlackPiece(tile.getID() - 6)){
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
					if(!checkForBlackPiece(tile.getID() - 15)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 15));
					}
					if(!checkForBlackPiece(tile.getID() - 6)){
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
					if(!checkForBlackPiece(tile.getID() - 15)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 15));
					}
					if(!checkForBlackPiece(tile.getID() - 6)){
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
					if(!checkForBlackPiece(tile.getID() - 15)){
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
					if(!checkForBlackPiece(tile.getID() - 15)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 15));
					}
					if(!checkForBlackPiece(tile.getID() - 6)){
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
					if(!checkForBlackPiece(tile.getID() - 15)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 15));
					}
					if(!checkForBlackPiece(tile.getID() - 6)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 6));
					}
					if(!checkForBlackPiece(tile.getID() + 10)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() + 10));
					}
				}
				
				// Tile a7
				else if(tile.getID() == 8){
					if(!checkForBlackPiece(tile.getID() - 6)){
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
					if(!checkForBlackPiece(tile.getID() - 15)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 15));
					}
					if(!checkForBlackPiece(tile.getID() - 6)){
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
					if(!checkForBlackPiece(tile.getID() - 15)){
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
					if(!checkForBlackPiece(tile.getID() - 15)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 15));
					}
					if(!checkForBlackPiece(tile.getID() - 6)){
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
					if(!checkForBlackPiece(tile.getID() - 15)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 15));
					}
					if(!checkForBlackPiece(tile.getID() - 6)){
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
					if(!checkForBlackPiece(tile.getID() - 15)){
						blackMoves.add(new ShortMove('B', 'n',tile.getID(), tile.getID() - 15));
					}
					if(!checkForBlackPiece(tile.getID() - 6)){
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
					if(!checkForBlackPiece(tile.getID() - 6)){
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
					if(!checkForBlackPiece(tile.getID() - 15)){
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
					if(checkForWhitePiece(tile.getID() + 9)){
						blackMoves.add(new ShortMove('B','p',tile.getID(), tile.getID() + 9));
					}
				}
				if(tile.getID() % 8 == 7){
					if(checkForBlackPiece(tile.getID() + 7)){
						blackMoves.add(new ShortMove('B','p',tile.getID(), tile.getID() + 7));
					}
				}
				if(tile.getID() % 8 == 0){
					if(doublePawnMove == (tile.getID() + 9)){
						blackMoves.add(new ShortMove('B','p',tile.getID(), tile.getID() + 9));
					}
				}
				if(tile.getID() % 8 == 7){
					if(doublePawnMove == (tile.getID() + 7)){
						blackMoves.add(new ShortMove('B','p',tile.getID(), tile.getID() + 7));
					}
				}
				if(tile.getID() % 8 != 0 || tile.getID() % 8 != 7){
					if(checkForWhitePiece(tile.getID() + 7)){
						blackMoves.add(new ShortMove('B','p',tile.getID(), tile.getID() + 7));
					}
					if(checkForBlackPiece(tile.getID() + 9)){
						blackMoves.add(new ShortMove('B','p',tile.getID(), tile.getID() + 9));
					}
				}
				if(tile.getID() % 8 != 0 || tile.getID() % 8 != 7){
					if(doublePawnMove == (tile.getID() + 7)){
						blackMoves.add(new ShortMove('B','p',tile.getID(), tile.getID() + 7));
					}
					if(doublePawnMove == (tile.getID() + 9)){
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
		//check for white's king moves in accordance to the movement of the opposing pieces
	}
	
	private void findBlackKingMoves(){
		//check for black's king moves in accordance to the movement of the opposing pieces
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
					if(sm.getType() == 'K'){
						//Tile[] copy = board.clone();
						//if(copy[e].containsPiece){
							return false; // temporary
						//}
					}
					else
						if(!checkIfCheck('W')){
							return true;
						}	
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
					if(sm.getType() == 'K'){
						//Tile[] copy = board.clone();
						//if(copy[e].containsPiece){
							return false; // temporary
						//}
					}
					else
						if(!checkIfCheck('B')){
							return true;
						}	
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
