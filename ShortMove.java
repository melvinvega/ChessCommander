
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


	public void setType(char type) {
		this.type = type;
	}
	
	
}
