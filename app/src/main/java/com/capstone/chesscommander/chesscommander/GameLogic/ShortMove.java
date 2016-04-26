package com.capstone.chesscommander.chesscommander.GameLogic;

public class ShortMove {
	char color;
	int startSquare;
	int endSquare;
	char type;
	
	
	public ShortMove(char c, char t, int ss, int es){
		color = c;
		startSquare = ss;
		endSquare = es;
		type = t;
	}


	public char getColor() {
		return color;
	}


	public void setColor(char color) {
		this.color = color;
	}


	public int getStartSquare() {
		return startSquare;
	}


	public void setStartSquare(int startSquare) {
		this.startSquare = startSquare;
	}


	public int getEndSquare() {
		return endSquare;
	}


	public void setEndSquare(int endSquare) {
		this.endSquare = endSquare;
	}


	public char getType() {
		return type;
	}

	public String getFullType() {
		String pieceType="";
		switch (type){
			case 'P':
				pieceType =  "pawn";
				break;
			case 'K':
				pieceType =  "king";
				break;
			case 'Q':
				pieceType =  "queen";
				break;
			case 'B':
				pieceType =  "bishop";
				break;
			case 'N':
				pieceType =  "knight";
				break;
			case 'R':
				pieceType =  "rook";
				break;
			case 'p':
				pieceType =  "pawn";
				break;
			case 'k':
				pieceType =  "king";
				break;
			case 'q':
				pieceType =  "queen";
				break;
			case 'b':
				pieceType =  "bishop";
				break;
			case 'n':
				pieceType =  "knight";
				break;
			case 'r':
				pieceType =  "rook";
				break;
		}
		return pieceType;
	}

	public void setType(char type) {
		this.type = type;
	}
	
	
}
