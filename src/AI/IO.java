package AI;
 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import HelpingClasses.ScoreBoardRow;

public class IO {
	static String sourcePath = "Mastermind/";
	static String save = "SaveGame/";
	static String setting= "Easy"; //todo make objects from them? 
	static String fileName = "mmScore.txt";

	public static void main(ScoreBoardRow sbr) {
		 ArrayList<ScoreBoardRow> sbrl =readScore(sourcePath, save, fileName);
		System.out.println(readScore(sourcePath, save, fileName).size());
		
		//sbrl.add(sbr);
		// organize list
		SaveGame(sbrl);
	}
	public static void SaveGame(ArrayList<ScoreBoardRow>sbrl) {
		PrintWriter write = null;
		try {
			write = new PrintWriter(sourcePath + save+ fileName);
			for(ScoreBoardRow sbr: sbrl){
				write.print(sbr.getName() + ":" + sbr.getAttempts()+":"+sbr.getScore()+":"+sbr.getMode()+":");
			}
		} catch (Exception e) {
			if (e instanceof FileNotFoundException) {
				System.out.println(sourcePath +save + fileName + " (Het systeem kan het opgegeven pad niet vinden)");
			} else {
				System.out.println(e);
			}
		}
		finally {
			if (write != null) {
				write.close();
			}}
	}
	
	public static ArrayList<ScoreBoardRow> readScore( String sourcePath, String save,String fileName){
		File file = null;
		Scanner sc = null;
		ArrayList<ScoreBoardRow>  sbrl = new ArrayList<ScoreBoardRow>();
		try {
			file = new File(sourcePath + save+ fileName);
			sc = new Scanner(file);
			// : delimiter
			sc.useDelimiter(":");
			while (sc.hasNext()) {
				String Name = sc.next();
				int Attempts = Integer.parseInt(sc.next());
				double Score = Double.parseDouble(sc.next());
				 String Mode = sc.next(); 
				sbrl.add(new ScoreBoardRow(Name, Attempts, Score, Mode));
				// sc.nextLine();
			}
		} catch (Exception e) {
			if (e instanceof FileNotFoundException) {
				System.out.println("Bestand niet gevonden");
			}
			if (e instanceof NumberFormatException) {
				System.out.println("Er ging iets fout met het lezen van het bestand. " +sourcePath +save + fileName );
				// System.exit(0);
			}
		} finally { 
			if (sc != null) {
				sc.close();
			}
		}
		return sbrl;
	}
}
