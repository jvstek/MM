package GuiShapes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

public class Square extends Shape {

	private int height;
	private int width;
	private float minDrawCheck = (float) 0.73 * 100;
	private float maxDrawCheck = (float) 1.50 * 100;
	private Color color;
	private boolean hover = false;

	public Square(Point center, Color color, int height, int width) {
		super(center);
		this.height = height;
		this.width = width;
		this.color = color;
	}

	@Override
	public void Draw(Graphics g, float sizeY, float sizeX) {
		g.setColor(color);
		int ox = (int) ((double) (x) * sizeX) / 100;
		int oy = (int) (y * sizeY) / 100;
		int w = (int) (width * sizeX) / 100;
		int h = (int) (height * sizeY) / 100;
		if (hover) {
			g.fillRect(ox, oy, w + 10, h + 10);
		} else {
			g.fillRect(ox, oy, w, h);
		}
		if (drawCheckString(sizeY, sizeX)) {
			g.setColor(Color.WHITE);
			// todo make fontsize grow accordingly if width and height stay
			// between a limits percentage
			g.setFont(new Font("Courier New", Font.BOLD, 17));
			g.drawString("Check", ox + (w / 7), oy + (h / 2));
		}
	}

	private boolean drawCheckString(float sizeY, float sizeX) {

		if (sizeY > minDrawCheck && sizeX > minDrawCheck) {
			if (sizeY < maxDrawCheck && sizeX < maxDrawCheck) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean HasCollision(double coordinateX, double coordinateY, float sizeY, float sizeX) {
		float ox = Calculate(x, sizeX);
		float oy = Calculate(y, sizeY);
		float oh = Calculate(height, sizeX);
		float ow = Calculate(width, sizeY);
		if (ox < coordinateX & oy < coordinateY) {
			if ((ox + ow) > coordinateX & (oy + oh) > coordinateY) {
				hover = true;
				return true;

			}
		}
		hover = false;
		return false;
	}

	public float Calculate(double base, float size) {
		return (float) (base * size) / 100;
	}
}