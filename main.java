
public class main {

	public static void main(String[] args){
		
		Board b = new Board();
		b.setInitialPosition();
		b.printVisualBoard();
		
		b.list.printWhiteMoves();
		
		b.move(52, 44, 'W', true);
		
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
