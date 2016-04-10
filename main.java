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
		b.setInitialPosition();
		b.printVisualBoard();
		b.printFEN();
	/*
		b.move(53, 37, 'W');
		b.printVisualBoard();
		b.printMove(1);
		b.printFEN();
		b.move(13, 29, 'B');
		b.printVisualBoard();
		b.printMove(2);
		b.printFEN();
		b.move(62, 45, 'W');
		b.printVisualBoard();
		b.printMove(3);
		b.printFEN();		
		b.move(1, 18, 'B');
		b.printVisualBoard();
		b.printMove(4);
		b.printFEN();
		b.move(49, 41, 'W');
		b.printVisualBoard();
		b.printMove(5);
		b.printFEN();
		*/
		b.getMoveList('W');
		b.list.printWhiteMoves();
		b.list.printBlackMoves();
			
		
	}

}
