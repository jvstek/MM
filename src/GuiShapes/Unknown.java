package GuiShapes;

import java.awt.Color;
import java.awt.Point;

public class Unknown extends Circle{

	private int place;
	public Unknown(Point center, int radius, Color color,int place) {
		super(center, radius, color);
		this.place =place;
	}
	@Override
	public void OnHover( ) {
	 
			// todo this circle represents 1 pin of the unknown secret code the AI made
	}
	public void SetColor(Color color){
		this.color = color;
	}
	public int GetPlace() {
		return place;
	}
}
