package GuiShapes;

import java.awt.Point;

public abstract class Shape implements IShape {
	// basic variables that all shapes need
	public int x;
	public int y;

	public Shape(Point center) {
		x = center.x;
		y = center.y;
	}

}
