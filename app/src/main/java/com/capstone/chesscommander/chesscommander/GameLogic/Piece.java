package com.capstone.chesscommander.chesscommander.GameLogic;

import java.lang.*;

/**
 * Representation of the pieces in a chess board.
 * @author Eduardo
 *
 */

public class Piece {
	
	/**
	 *  The type can be any of 6: pawn, rook, knight, bishop, queen and king.
	 *  White is represented by one of 6 capital letters, black by the lowercase letter:
	 *  K = King
	 *  Q = Queen
	 *  P = Pawn
	 *  R = Rook
	 *  N = Knight
	 *  B = Bishop
	 *  
	 *  If the character 'x' is returned, it is treated as a null piece;
	 */
	char type;

	public Piece(int i){
		switch (i){
			case 0: type = 'P';
				color = 'W';
				break;
			case 1: type = 'K';
				color = 'W';
				break;
			case 2: type = 'Q';
				color = 'W';
				break;
			case 3: type = 'B';
				color = 'W';
				break;
			case 4: type = 'N';
				color = 'W';
				break;
			case 5: type = 'R';
				color = 'W';
				break;
			case 6: type = 'p';
				color = 'B';
				break;
			case 7: type = 'k';
				color = 'B';
				break;
			case 8: type = 'q';
				color = 'B';
				break;
			case 9: type = 'b';
				color = 'B';
				break;
			case 10: type = 'n';
				color = 'B';
				break;
			case 11: type = 'r';
				color = 'B';
				break;
		}
	}

	public char getType() {
		return type;
	}

	public void setType(char t) {
		if(t == 'P' || t == 'K' || t == 'Q' || t == 'B' || t == 'N' || t == 'R'){
			if(color == 'W'){
			type = t;
			}
			else{
				type = Character.toLowerCase(t);
			}
		}
		else{
			type = 'P';
		}
	}

	public char getColor() {
		return color;
	}

	public void setColor(char c) {
		if(c == 'B'){
			color = c;
		}
		else{
			color = 'W';
		}
	}

	/**
	 *  The color is either white or black. Each is represented by one of 2 capital letters:
	 *  W = White
	 *  B = Black;
	 *  
	 *  The character 'x' means it's a null piece.
	 */
	char color;
	
	
	/**
	 *  The id is an integer given to identify a particular piece + color combination. 
	 *  White pieces hold id 0 to 5, while Black Pieces hold id from 6 to 11.
	 *  White Pawn = 0,  King = 1, Queen = 2, Bishop = 3, Knight = 4, Rook = 5
	 *  Black Pawn = 6, King = 7, Queen = 8, Bishop = 9, Knight = 10, Rook = 11
	 */
	int id;
	
	public Piece(){
		type = 'x';
		color = 'x';
	}

	public Piece(Piece copy){
		this.type = copy.type;
		this.color = copy.color;
		this.id = copy.id;
	}

	public Piece(char t, char c){
		if(t == 'P' || t == 'K' || t == 'Q' || t == 'B' || t == 'N' || t == 'R'){
			if(c == 'W'){
				type = t;
			}
			else{
				type = Character.toLowerCase(t);
			}
		}
		else{
			type = 'P';
		}
		
		if(c == 'B'){
			color = c;
		}
		else{
			color = 'W';
		}
		id = genID();
	}

	/**
	 * Generates the ID for the piece according to its type and color.
	 * @return id
	 */
	
	private int genID() {
		int s1 , s2;
		switch (type){
		case 'P': s1 = 0;
			break;
		case 'p': s1 = 0;
			break;
		case 'K': s1 = 1;
			break;
		case 'k': s1 = 1;
			break;
		case 'Q': s1 = 2;
			break;
		case 'q': s1 = 2;
			break;
		case 'B': s1 = 3;
			break;
		case 'b': s1 = 3;
			break;
		case 'N': s1 = 4;
			break;
		case 'n': s1 = 4;
			break;
		case 'R': s1 = 5;
			break;
		case 'r': s1 = 5;
			break;
		default: s1 = -1;
			break;
		}
		
		switch (color){
		case 'W': s2 = 0;
			break;
		case 'B': s2 = 1;
			break;
		default:  s2 = -1;
		}
		
		id = s1 + (s2 * 6);
		return id;
	}
	
	
	
}
