package GuiShapes;

import java.awt.Graphics;

public interface  IShape {
	public abstract void Draw(Graphics g,float sizeY,float sizeX);
	public abstract boolean HasCollision(double coordinateX, double coordinateY,float sizeY,float sizeX);
}
