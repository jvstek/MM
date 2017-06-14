package GuiShapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Pin extends Circle {
	private boolean selected;
	private int row;
	private int place;

	public Pin(Point center, int radius, Color color, int row, int place) {
		super(center, radius, color);
		this.row = row;
		this.place = place; 
	}
	/**
	 * Drawing pin on the field
	 * It is a little bit different because when the pin is selected it needs to be drawn a differently 
	 * Todo change it in the hover method so the way the circle is drawn is stil the same.
	 */
	@Override
	public void Draw(Graphics g, float sizeY, float sizeX) {
		g.setColor(color);
		int ox = (int) Calculate(x, sizeX);
		int oy = (int) Calculate(y, sizeY);
		int diameterx = (int) Calculate(diameter, sizeX);
		int diametery = (int) Calculate(diameter, sizeY);
		if (hover || selected) {
			g.fillOval((int) (ox - (Calculate(5, sizeX) / 2)), (int) (oy - (Calculate(5, sizeX) / 2)),
					(int) (diameterx + Calculate(7, sizeX)), (int) (diametery + Calculate(7, sizeY)));
		} else {
			g.fillOval(ox, oy, diameterx, diametery);
		}
	}
	// what to do when a pin is selected 
	public boolean SetSelected(boolean select) {
		selected = select;
		return selected;
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

	public Color GetColor() {
		return this.color;
	}
}
