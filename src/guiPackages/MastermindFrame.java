package guiPackages;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import AI.MastermindApi;
import HelpingClasses.GameBar;

public class MastermindFrame extends JFrame {
	String title = "Game";
	MastermindApi MmA;
	int frameSizeX = 500;
	int frameSizeY = 800;
	JPanel[] panels;
	MastermindPanel MmP;
	ScoreBoard SB;

	public MastermindFrame(MastermindApi MmA) {
		this.setTitle(title);
		this.MmA = MmA;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(new GameBar().GameMenubar(this));
		panels = new JPanel[2];
		MmP = new MastermindPanel(MmA, frameSizeX, frameSizeY);
		panels[0] = MmP;
		panels[1] = new ScoreBoard(); // todo
		add(MmP);
		this.setSize(frameSizeX, frameSizeY);
		setVisible(true);
	}

	// can also create a method for each menu item....
	public void MenuItemClicked(ActionEvent e) {
		if (e.getSource() instanceof JMenuItem) {
			JMenuItem source = (JMenuItem) (e.getSource());
			switch (source.getText()) {
			case "Easy":
				MmA.StartEasyGame();
				MmP.repaint();
				showPanelNumber(0, panels);
				// switch mmpanel
				break;
			case "Normal":
				MmA.StartNormalGame();
				MmP.repaint();
				showPanelNumber(0, panels);
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
		panels[1] = new ScoreBoard();
		add(panels[1]);
		showPanelNumber(1, panels);
		// add(MmP).setVisible(false);
	}

	public void MenuGameClicked() {
		showPanelNumber(0, panels);
	}

	private boolean containsScoreBoard(JPanel[] panels) {
		for (JPanel jPanel : panels) {
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
