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
	// name // score //difficultie //time (when there is time)
	JLabel label;
	ScoreBoardRow SBR;
	IOScore io;
	public ScoreBoard() {
		setLayout(new GridLayout(0,4));
		this.removeAll();
		io = new IOScore();
		 getScores();
		 }
	public void getScores(){
		try{
			ArrayList<ScoreBoardRow> sbrl =io.readScore();
			ScoreBoardSorter sbs = new  ScoreBoardSorter();
			
			 sbrl.sort(sbs);
			addCollumNames(sbrl.get(0));
			writeRow(sbrl);
			}catch(Exception e){System.out.println("There is no saved score"); JLabel label = new JLabel("No valid Score");}
		
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
