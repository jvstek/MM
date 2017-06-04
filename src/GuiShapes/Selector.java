package GuiShapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Selector extends Circle {

	public Selector(Point center, int radius, Color color) {
		super(center, radius, color); 
	}

	@Override
	public void Draw(Graphics g, float sizeY, float sizeX) {
		g.setColor(color);
		int ox = (int) Calculate(x, sizeX);
		int oy = (int) Calculate(y, sizeY);
		int diameterx = (int) Calculate(diameter, sizeX);
		int diametery = (int) Calculate(diameter, sizeY);
		if (hover) {
			g.fillOval((int) (ox - (Calculate(5, sizeX) / 2)), (int) (oy - (Calculate(5, sizeX) / 2)),
					(int) (diameterx + Calculate(7, sizeX)), (int) (diametery + Calculate(7, sizeY)));
		} else {
			g.fillOval(ox, oy, diameterx, diametery);
		}
	}

	@Override
	public void OnHover() {
		// todo this is a color to select fil in a pin
	}

	public Color GetColor() {
		return this.color;
	}
}
