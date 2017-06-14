package GuiShapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public abstract class Circle extends Shape {
	// the variables that all the circles need
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
	/**
	 * Checks if there is a collision. depending on the coordinates. Size y and x are the present frame sizes
	 * For further details in how you calculate a circle collision check 
	 * https://gamedevelopment.tutsplus.com/tutorials/when-worlds-collide-simulating-circle-circle-collisions--gamedev-769
	 * Note* area could also have been used. To make it easier
	 */
	@Override
	public boolean HasCollision(double coordinateX, double coordinateY, float sizeY, float sizeX) {
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
	/**
	 * Calculates the growing percentage so it can be used 
	 * @param base
	 * @param size
	 * @return
	 */
	public float Calculate(double base, float size) {
		return (float) (base * size) / 100;
	}

	public void OnHover() {
		// work in progress
	}
}