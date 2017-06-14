package guiPanels;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import GuiShapes.IShape;
import control.MastermindControl;

//responsible for all the symbols shown on the panel
//Communicates with MastermindApi
public class MastermindPanel extends JPanel {

	
	private static final long serialVersionUID = 1L;

	private int y; // given y as base for percentage calculating
	private int x;// given x as base for percentage calculating
	private MastermindControl mastermindApi;
	
	public MastermindPanel(MastermindControl ai, int sizeX, int sizeY) {
		this.mastermindApi = ai;
		y = sizeX;
		x = sizeY;
		// Listener that listens to the mouse being released
		this.addMouseListener(new java.awt.event.MouseAdapter() {

			@Override
			public void mouseReleased(java.awt.event.MouseEvent evt) {
				MouseReleased(evt);
			}
		});
		// listerner that tracks the motion of the mouse
		addMouseMotionListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseMoved(java.awt.event.MouseEvent evt) {
				MouseMoved(evt);
			}
			// public void mouseDragged(MouseEvent evt){
			// MouseMoved(evt);
			// } //todo
		});

	}
	/**
	 * Cals the paint of the shapes on the  that creates visual aspect of the mastermind game.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			g.drawString("Score: " + mastermindApi.getScore(), 10, 10);
		} catch (Exception e) {// todo

		}
		for (IShape c : mastermindApi.getShapes()) {
			c.Draw(g, percentageCalculator(x, this.getHeight()), percentageCalculator(y, this.getWidth()));
		}

	}
	/**
	 * needed to calculate the base % of the gui. So the shapes can be adjusted to the result 
	 * of the calculations below. 
	 * When the frame gets bigger the shapes grow aswel % based. 
	 * @param base
	 * @param target
	 * @return
	 */
	private float percentageCalculator(int base, int target) {
		return (target * 100 / base);
	}
	/**
	 * When the mouse is being released the event is being fired. 
	 * It checkes the coordinates and looks if there has been a collision with the shapes in de mastermincontrol
	 * If there is a collision it checks with what it collided and performs the action depended on that. 
	 * @param e
	 */
	public void MouseReleased(MouseEvent e) {

		double coordinateX = e.getX();
		double coordinateY = e.getY();
		for (IShape c : mastermindApi.getShapes()) {
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
		if(!mastermindApi.isLive()){
		mastermindApi.saveGame(); }// if the game is not life anymore check if the game is saved  todo (better in check row?)
	}
	/**
	 * when the mouse is moved check if there is a collision
	 * This simulates a hover event above a shape. 
	 * A hover means that the mouse is above a certain object. What is in this case a shape
	 * @param e
	 */
	public void MouseMoved(MouseEvent e) {
		double coordinateX = e.getX();
		double coordinateY = e.getY();
		for (IShape c : mastermindApi.getShapes()) {
			if (c.HasCollision(coordinateX, coordinateY, percentageCalculator(x, this.getHeight()),
					percentageCalculator(y, this.getWidth()))) {
				
			}
		}
		repaint();
	}
}
