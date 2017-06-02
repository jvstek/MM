package guiPackages;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import GuiShapes.IShape;
import control.MastermindControl;

//responsible for all the symbols shown on the panel
//Communicates with MastermindApi
public class MastermindPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int y; // given y as base for percentage calculating
	int x;// given x as base for percentage calculating
	MastermindControl mastermindApi;

	public MastermindPanel(MastermindControl ai, int sizeX, int sizeY) {
		this.mastermindApi = ai;
		y = sizeX;
		x = sizeY;

		this.addMouseListener(new java.awt.event.MouseAdapter() {

			@Override
			public void mouseReleased(java.awt.event.MouseEvent evt) {
				MouseReleased(evt);
			}
		});
		addMouseMotionListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseMoved(java.awt.event.MouseEvent evt) {
				MouseMoved(evt);
			}
			// public void mouseDragged(MouseEvent evt){
			// MouseMoved(evt);
			// }
		});

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			g.drawString("Score: " + mastermindApi.getScore(), 10, 10);
		} catch (Exception e) {// todo

		}
		for (IShape c : mastermindApi.getCircles()) {
			c.Draw(g, percentageCalculator(x, this.getHeight()), percentageCalculator(y, this.getWidth()));
		}

	}

	private float percentageCalculator(int base, int target) {
		return (target * 100 / base);
	}

	public void MouseReleased(MouseEvent e) {

		double coordinateX = e.getX();
		double coordinateY = e.getY();
		for (IShape c : mastermindApi.getCircles()) {
			if (c.HasCollision(coordinateX, coordinateY, percentageCalculator(x, this.getHeight()),
					percentageCalculator(y, this.getWidth()))) {
				switch (c.getClass().getName()) {
				case "GuiShapes.Selector":
					mastermindApi.setSelectedCircle(c);
					break;
				case "GuiShapes.Pin":
					mastermindApi.setSelectedCircle(c);
					break;
				case "GuiShapes.Square":
					mastermindApi.CheckRow();
					break;
				}
			}
		}
		repaint();
		// mastermindApi
	}

	public void MouseMoved(MouseEvent e) {
		double coordinateX = e.getX();
		double coordinateY = e.getY();
		for (IShape c : mastermindApi.getCircles()) {
			if (c.HasCollision(coordinateX, coordinateY, percentageCalculator(x, this.getHeight()),
					percentageCalculator(y, this.getWidth()))) {
			}
		}
		repaint();
	}
}
