package GuiShapes;
 
import java.awt.Point; 

public abstract class Shape implements IShape {
	public int x;
	public int y; 

	public Shape(Point center) {
		x = center.x;
		y = center.y; 
	}
 
}
