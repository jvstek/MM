package GuiShapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public abstract class Circle extends Shape {
	public int diameter;
	public int radius;
	public boolean hover;
	protected Color color;

	public Circle(Point center, int radius, Color color) {
		super(center);
		x = center.x - radius;
		y = center.y - radius;
		diameter = 2 * radius;
		this.radius = radius;
		this.color = color;
		hover = false;
	}

	@Override
	public void Draw(Graphics g, float sizeY, float sizeX) {
		int ox = (int) Calculate(x, sizeX);
		int oy = (int) Calculate(y, sizeY);
		int diameterx = (int) Calculate(diameter, sizeX);
		int diametery = (int) Calculate(diameter, sizeY);
		g.setColor(color);
		g.fillOval(ox, oy, diameterx, diametery);
		if (hover) {
			OnHover();
		}
	}

	// problem with the points because of the canvas not being the frame... it
	// needs a recalculation
	@Override
	public boolean HasCollision(double coordinateX, double coordinateY, float sizeY, float sizeX) {
		// Ellipse2D.Double test = new Ellipse2D.Double();//to easy
		// test.setFrame()
		// test.contains(p)
		float ox = Calculate(x, sizeX);
		float oy = Calculate(y, sizeY);
		float radiusx = Calculate(radius, sizeX);
		float radiusy = Calculate(radius, sizeY);
		float xDiff = (float) (ox - coordinateX + radiusx);
		float yDiff = (float) (oy - coordinateY + radiusy);

		double distancex = Math.sqrt((Math.pow(xDiff, 2) + Math.pow(xDiff, 2)));
		double distancey = Math.sqrt((Math.pow(yDiff, 2) + Math.pow(yDiff, 2)));

		boolean b = distancex < (radiusx + 1);
		boolean c = distancey < (radiusy + 1);
		if (b && c) {
			hover = true;
		} else {
			hover = false;
		}
		return hover;
	}

	public float Calculate(double base, float size) {
		return (float) (base * size) / 100;
	}

	public void OnHover() {
		// work in progress
	}
}