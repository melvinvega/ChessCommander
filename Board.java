
import chesspresso.*;

public class Board {
	
	Tile[] tiles = new Tile [64];
	
	public Board(){
		genBoard();
	}
	
	private Tile[] genBoard(){
		int curr;
		String  r;
		int   c;
		
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				curr = (8 * i) + j;
				switch (i){
				case 0: r = "h";
				case 1: r = "g";
				case 2: r = "f";
				case 3: r = "e";
				case 4: r = "d";
				case 5: r = "c";
				case 6: r = "b";
				case 7: r = "a";
				default: r = "x";
				}
				
				c = 1 + j;
				
				tiles[curr] = new Tile(null, r + c , (i == 0 || i == 7) , curr);
			}
		}
		return tiles;
	}
	
	private Tile[] setInitialPosition(){
		
		
		
		return tiles;
	}
	
	
	
	public void printBoard(){
		String rep = null;
		
		for(int i = 0; i < 64; i++){
			rep = rep + " " + tiles[i].getNotation() + " " +tiles[i].getPieceChar() + " " + tiles[i].getID() + "\n" ;
		}
		System.out.println(rep);
	}
}
