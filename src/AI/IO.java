package AI;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import HelpingClasses.ScoreBoardRow;

public class IO {
	static String sourcePath = "Mastermind/";
	static String save = "SaveGame/";
	static String fileName = "mmScore.txt";

	public static void main(String[] args) {
		ScoreBoardRow row = new ScoreBoardRow("name", 1, 20, "easy");
		SaveGame(row);
	}

	public static void SaveGame(ScoreBoardRow sbr) {
		PrintWriter write = null;
		try {
			write = new PrintWriter(sourcePath + save+ fileName);
			write.println(sbr.toString());
		} catch (Exception e) {
			if (e instanceof FileNotFoundException) {
				System.out.println(sourcePath +save + fileName + " (Het systeem kan het opgegeven pad niet vinden)");
			} else {
				System.out.println(e);
			}

		}
	}
	public static ScoreBoardRow[] readScore( String sourcePath, String save,String fileName){
		
		return null;
	}
}
