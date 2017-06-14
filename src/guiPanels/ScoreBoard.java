package guiPanels;

import java.awt.GridLayout;
import java.lang.reflect.Field;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Services.IOScore;
import Services.ScoreBoardRow;
import Services.ScoreBoardSorter;

public class ScoreBoard extends JPanel {
	// name // score //difficulties //time (when there is time) fix this
	JLabel label;
	ScoreBoardRow SBR;
	IOScore io;

	public ScoreBoard() {
		setLayout(new GridLayout(0, 4));
		this.removeAll();
		io = new IOScore();
		getScores();
	}
	/**
	 * Get the scores before and write them on the score board
	 * made public so it can be reassigned
	 * Todo* add the last score (the one that is also being saved) and add it here aswel. 
	 * So you dont need to load all te information the whole time
	 */
	public void getScores() {
		try {
			ArrayList<ScoreBoardRow> sbrl = io.readScore();
			ScoreBoardSorter sbs = new ScoreBoardSorter();

			sbrl.sort(sbs);
			addCollumNames(sbrl.get(0));
			writeRow(sbrl);
		} catch (Exception e) {
			System.out.println("There is no saved score");
			JLabel label = new JLabel("No valid Score");
		}

	}

	/**
	 * Writing all the row information on the scoreboard
	 * 
	 * @param sbrl
	 */
	private void writeRow(ArrayList<ScoreBoardRow> sbrl) {
		for (ScoreBoardRow sbr : sbrl) {
			Field[] fields = sbr.getClass().getDeclaredFields(); // get all
																	// "fields"
																	// properties
																	// from a
																	// class
			for (Field field : fields) {
				try {
					JLabel label = new JLabel();
					field.setAccessible(true); // Additional (if there are
												// private fields they are now
												// public
					label.setText(field.get(sbr).toString());
					this.add(label);
					label.setVisible(true);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					System.out.print(e + " foutmelding writeRow");
					// e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Get all field names from a the ScoreRow class and use them as Column
	 * names. Made this way so it is Generic and usable for multiple purposes.
	 * Saves time in the future.
	 * 
	 * @param sbr
	 */
	private void addCollumNames(ScoreBoardRow sbr) {
		Field[] fields = sbr.getClass().getDeclaredFields();

		for (Field field : fields) {
			JLabel label = new JLabel();
			field.setAccessible(true); // Additional line
			label.setText(field.getName());
			this.add(label);
			label.setVisible(true);

		}
	}
}
