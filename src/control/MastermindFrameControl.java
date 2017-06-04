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
	private String title = "Game";
	private MastermindControl MmA;
	private int frameSizeX = 550; //todo make the width depending on amount in a row
	private int frameSizeY = 800; ////todo make the heigth depending on amount in a row
	private JPanel[] panels;
	private MastermindPanel MmP; 
	private OISound sound;
	public MastermindFrameControl(MastermindControl MmA) {
		this.setTitle(title);
		this.MmA = MmA;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(new GameBar().GameMenubar(this));
		panels = new JPanel[2];
		MmP = new MastermindPanel(MmA, frameSizeX, frameSizeY);
		panels[0] = MmP;
		panels[1] = new ScoreBoard(); // think of a better way to do this. 
		add(MmP);
		this.setSize(frameSizeX, frameSizeY);
		setVisible(true);
		sound = new OISound();
	}

	// can also create a method for each menu item....
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

	public void MenuScoreClicked() {
		sound.playSound(1);
		panels[1] = new ScoreBoard(); //stil needfixing. 
		add(panels[1]);
		showPanelNumber(1, panels);

	}

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
