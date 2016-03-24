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
		// b.printBoard();
		b.setInitialPosition();
		//b.printBoard();
		//b.printVisualBoard();
		b.move(48,40,'W');
		//b.printVisualBoard();
		b.move(11, 19, 'B');
		//b.printVisualBoard();
		b.move(58, 3, 'W');
		b.printVisualBoard();
		b.move(19, 27, 'B');
		b.printVisualBoard();
		b.move(3, 58, 'W');
		b.printVisualBoard();
		
		
	}

}
