package control;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Services.GameBar;
import Services.OISound;
import guiPanels.MastermindPanel;
import guiPanels.ScoreBoard; 

public class MastermindFrameControl extends JFrame {
	private String title = "Game"; // title of the frame
	private MastermindControl MmA; // master mind controler that decides what is being showed on the frame
	private int frameSizeX = 550; //todo make the width depending on amount in a row
	private int frameSizeY = 800; ////todo make the heigth depending on amount in a row
	private JPanel[] panels;// used for panels being showed
	private MastermindPanel MmP; // panel where master mind game is being showed
	private OISound sound; // sound service
	public MastermindFrameControl(MastermindControl MmA) {
		this.setTitle(title);
		this.MmA = MmA;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(new GameBar().GameMenubar(this)); // sets the game bar
		panels = new JPanel[2];
		MmP = new MastermindPanel(MmA, frameSizeX, frameSizeY);
		panels[0] = MmP;
		panels[1] = new ScoreBoard(); // todo make a better way to to this
		add(MmP);
		this.setSize(frameSizeX, frameSizeY);
		setVisible(true);
		sound = new OISound();
	}

	// can also create a method for each menu item....
	/**
	 * The game bar menu items speak to this code when a action is performed. 
	 * so it can start the game and refresh the panels so the new game is being showed. 
	 * @param e
	 */
	public void MenuItemClicked(ActionEvent e) {
		if (e.getSource() instanceof JMenuItem) {
			JMenuItem source = (JMenuItem) (e.getSource());
			sound.playSound(0);
			switch (source.getText()) {
			case "Easy":
				MmA.StartEasyGame();
				MmP.repaint();
				//showPanelNumber(0, panels);
				break;
			case "Normal":
				MmA.StartNormalGame();
				MmP.repaint();
				//showPanelNumber(0, panels);
				break;
			case "Hard":
				MmA.StartHardGame();
				MmP.repaint();
				//showPanelNumber(0, panels);
				break;
			default:
				System.out.println("Action from unknown source");
				break;
			}
		} else {
			System.out.println("some other action  ");
		}
	}
	/**
	 * when the score menu is "clicked" or hovered over. 
	 */
	public void MenuScoreClicked() {
		sound.playSound(1);
		panels[1] = new ScoreBoard(); //stil needfixing. 
		add(panels[1]);
		showPanelNumber(1, panels);

	}
	/**
	 * When in the menubar "game" is clicked or hovered over. It performs this piece ofcode
	 */
	public void MenuGameClicked() {
		showPanelNumber(0, panels);
	}
	private boolean containsScoreBoard(JPanel[] panels) {
		for (JPanel jPanel : panels) {
			System.out.println(jPanel.getName());
			if (jPanel instanceof ScoreBoard) {
				return true;
			}
		}
		return false;
	}
	/**
	 * When depending on the given parameters is puts that panel visible and the rest are hidden. 
	 * (made so it is scalable) 
	 * @param panelNumber
	 * @param panels
	 */
	private void showPanelNumber(int panelNumber, JPanel[] panels) {
		
		for (int i = 0; i < panels.length; i++) {
			if (i == panelNumber) {
				panels[i].setVisible(true);
			} else {
				panels[i].setVisible(false);
			}
		}

	}

}
