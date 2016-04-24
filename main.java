
public class main {

	public static void main(String[] args){
		
		Board b = new Board();
		b.setInitialPosition();
		b.printVisualBoard();
		
		b.list.printWhiteMoves();
		
		b.testMove(48,24,'W',true);
		b.move(9,25,'B', false);
		b.move(24, 17, 'W', true);
		b.printVisualBoard();
		b.list.printWhiteMoves();
		
		
//		b.move(55,47,'W');
//		b.printVisualBoard();
//		b.moves.get(0).printVisualBoardBeforeMove();
		
//		PieceTest tester = new PieceTest();
//		tester.testKnights();
//		tester.testRook();
//		tester.testBishop();
//		tester.testQueen();
//		tester.testPawn();
	
	
	}

}
