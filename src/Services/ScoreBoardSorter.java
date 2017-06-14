package Services;

import java.util.Comparator;

public class ScoreBoardSorter implements Comparator<ScoreBoardRow> {
/**
 * Comparible to organise it in the scoreboard
 */
	@Override
	public int compare(ScoreBoardRow o1, ScoreBoardRow o2) {
		 if(o1.GetAttempts()<o2.GetAttempts()){return 1;}
		 else if (o1.GetAttempts()>o2.GetAttempts()){return -1;}
		 else if(o1.GetScore()<o2.GetScore()){return 1;}
		 else if(o1.GetScore()>o2.GetScore()){return -1;}
		  
		return CompareModes(o1,o2);
	}
	private int CompareModes(ScoreBoardRow o1, ScoreBoardRow o2){
		int t = 0;
		switch(o1.GetMode()){
		case "Easy": t=1; break;
		case "Normal": t=2; break;
		case "Hard": t=3; break;
		}
		int r = 0;
		switch(o1.GetMode()){
		case "Easy": r=1; break;
		case "Normal": r=2; break;
		case "Hard": r=3; break;
		}
		if (t>r){return 1;}
		if (t<r){return -1;}
		return 0;}

}
