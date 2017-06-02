package guiPackages;

import java.awt.GridLayout;
import java.lang.reflect.Field;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Services.IO;
import Services.ScoreBoardRow;
import Services.ScoreBoardSorter;

public class ScoreBoard extends JPanel { 
	// name // score //difficultie //time (when there is time)
	JLabel label;
	ScoreBoardRow SBR;
	IO io;
	public ScoreBoard() {
		io = new IO();
		ScoreBoardSorter sbs = new  ScoreBoardSorter();
		try{
		ArrayList<ScoreBoardRow> sbrl =io.readScore();
		 
		setLayout(new GridLayout(0,4));
		 sbrl.sort(sbs);
		addCollumNames(sbrl.get(0));
		writeRow(sbrl);
		}catch(Exception e){System.out.println("Something went wrong with the scoreboard");}
	}

	private void writeRow(ArrayList<ScoreBoardRow> sbrl) {
		for (ScoreBoardRow sbr : sbrl) {
		Field[] fields = sbr.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {JLabel label = new JLabel();
				field.setAccessible(true); // Additional line  
				label.setText( field.get(sbr).toString());
				this.add(label);
				label.setVisible(true);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}}
	}	
	private  void addCollumNames(ScoreBoardRow sbr){
		Field[] fields = sbr.getClass().getDeclaredFields();
		
		for (Field field : fields) { JLabel label = new JLabel();
				field.setAccessible(true); // Additional line 
				label.setText(field.getName());
				this.add(label);
				label.setVisible(true);
				
		}
	}
}
