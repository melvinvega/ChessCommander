
public class PieceTest {
	
	Board b;
	
	public PieceTest(){
		
	}
	
	public void testKnights(){
		b = new Board();
		System.out.println("Printing Test Board for Knight Movement. Knights will be at the corner of the board");
		System.out.println("// Uppercase letter represents White pieces  //");
		System.out.println("// Lowercase letter represents Black pieces  //");
		System.out.println("// The letter N represents the knight        //");
		System.out.println("// x's represent empty squares               //");
		System.out.println();
		b.setKnightTestBoard();
		b.printVisualBoard();
		b.getMoveList('W');
		System.out.println("Expected Result: a1-b3, a1-c2, a8-b6, a8-c7, h1-g3, h1-f2, h8-g6, h8 - f7");
		System.out.println();
		b.list.printPieceMoves('N');
		b.move(0, 10,'W');
		b.move(56, 41,'B');
		b.move(10, 16,'W');
		System.out.println("White Knight has been moved twice, the range of movees should change in legality to the game.");
		System.out.println();
		b.printVisualBoard();
		b.list.printPieceMoves('N');
		b.move(41, 26, 'B');
		System.out.println("The opposite color knights should be able to capture each other, by having the move to the occupied space available.");
		System.out.println();
		b.printVisualBoard();
		b.list.printPieceMoves('N');
		b.move(63, 53,'W');
		b.move(7, 22, 'B');
		b.printVisualBoard();
		b.list.printPieceMoves('N');
		b.move(53, 43,'W');
		b.move(22, 28,'B');
		b.move(43, 33,'W');
		System.out.println("Expected Result: The Knight at a6 and at b4 are supposed to not be able to capture each other");
		System.out.println();
		b.printVisualBoard();
		b.list.printPieceMoves('N');
	}
}
