package GuiShapes;

import java.awt.Color;
import java.awt.Point;

public class ScorePin extends Circle {
	private int row;
	private int place;

	public ScorePin(Point center, int radius, Color color, int row, int place) {
		super(center, radius, color);
		this.row = row;
		this.place = place; 	
	}

	public int GetRow() {
		return row;
	}

	public int GetPlace() {
		return place;
	}

	public void SetColor(Color c) {
		this.color = c;
	}
}
