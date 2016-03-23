import chesspresso.*;
import chesspresso.game.Game;
import chesspresso.move.IllegalMoveException;
import chesspresso.move.Move;
import chesspresso.position.Position;

public class main {

	public static void main(String[] args){
		/*
		Position.createInitialPosition();
		Game newGame = new Game();
		Position pos = new Position();
		pos = Position.createInitialPosition();
		short[] moves = pos.getAllMoves();
		for (short move : moves){
			System.out.println(Move.getString(move));
		}
		
		
		Position pos = Position.createInitialPosition();
		short mov = Move.getRegularMove(Chess.D2, Chess.D4, false);
		try {
			pos.doMove(mov);
		} catch (IllegalMoveException e) {
			System.out.println("Illegal Move!");
		}
		*/
		
		Board b = new Board();
		b.printBoard();
	}

}
