
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
		System.out.println("Expected Result: a1-b3, a1-c2, a8-b6, a8-c7, h1-g3, h1-f2, h8-g6, h8 - f7");
		System.out.println();
		b.list.printPieceMoves('N');
		System.out.println("Expected result is the console saying that the move is illegal");
		b.move(63, 57,'W');
		b.printVisualBoard();
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
	
	public void testRook(){
		System.out.println("Printing Test Board for Rook Movement.");
		System.out.println("// The letter R represents the rook        //");
		System.out.println("// The letter P represents the pawn        //");
		b = new Board();
		b.setRookTestBoard();
		b.printVisualBoard();
		System.out.println("Moves that appear in console should be legal moves. Ergo, rook should stop at a potential capture, and cannot capture, or go past, a friendly piece.");
		b.list.printPieceMoves('R');
		System.out.println("There should be a couple of illegal moves pointed out here");
		b.move(35, 37, 'W');
		b.move(35, 31, 'W');
		b.printVisualBoard();
		b.move(35, 33 , 'W');
		System.out.println("We move the piece, to verify if the resulting square opens up the appropiate moves");
		b.printVisualBoard();
		b.list.printPieceMoves('R');
		System.out.println("A few more checks for proper move presentation after a move");
		b.move(36, 39, 'B');
		b.printVisualBoard();
		b.list.printPieceMoves('R');
		System.out.println("Now let's capture the opposing rook, and that particular piece should then no longer present moves");
		b.move(33, 39, 'W');
		b.printVisualBoard();
		b.list.printPieceMoves('R');
	}
	
	public void testBishop(){
		System.out.println("Printing Test Board for Bishop Movement.");
		System.out.println("// The letter B represents the bishop        //");		
		b = new Board();
		System.out.println("We verify that the moves comply with the aforementioned rules");
		b.setBishopTestBoard();
		b.printVisualBoard();
		b.list.printPieceMoves('B');
		System.out.println("The console should return an illegal move");
		b.move(0, 63, 'W');
		System.out.println("Now we move the piece to the very center of the board to verify its near full scope");
		b.move(0, 27, 'W');
		b.printVisualBoard();
		b.list.printPieceMoves('B');
		System.out.println("And the piece will move again to a another corner to verify that its scope is properly cut");
		b.move(27, 48, 'W');
		b.printVisualBoard();
		b.list.printPieceMoves('B');
	}
	
	public void testQueen(){
		System.out.println("Printing Test Board for Queen Movement");
		System.out.println("// The letter Q represent the queen //");
		System.out.println("// The code for the queen is a perfect copy of the code of the bishop and rook //");
		System.out.println("// Then if the previous two tests passed, the queen should pass, we'll verify  //");
		System.out.println("// the initial position and the available moves anyway to check briefly.       //");
		b = new Board();
		b.setQueenTestBoard();
		b.printVisualBoard();
		b.list.printPieceMoves('Q');
	}
	
	public void testPawn(){
		System.out.println("Printing the Test Board for the Pawn Movement");
		b = new Board();
		b.setPawnTestBoard();
		b.printVisualBoard();
		b.list.printPieceMoves('P');
		System.out.println("The system to verify legal moves is based on the list, so it's safe to assume that illegal moves will be caught");
		System.out.println("Let's push the pawn double to go in capture range");
		b.move(52, 36, 'W');
		b.printVisualBoard();
		b.list.printPieceMoves('P');
		System.out.println("Let's finally verify if the code at the moment prompts for promotion");
		b.move(8, 0, 'W');
	}
}
