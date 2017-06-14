	package Services;
 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner; 

public class IOScore {

	private String sourcePath = "Mastermind/";//todo make it more dynamic
	private String save = "SaveGame/"; 
	private String fileName = "mmScore.txt";
	private String toRemove = "!:'|*\";`,?^@#$}{" ;//etc
	 
	public void AddGame(String name, int attempts, double score, String mode) {
		 ArrayList<ScoreBoardRow> sbrl =readScore(); //haal de lijst met scores op. 
		sbrl.add(new ScoreBoardRow(name,attempts,score,mode)); // voeg de nieuwe score toe
		SaveGame(sbrl); // save score

	}
	/**
	 * Saves the game according to the given score list. 
	 * @param sbrl
	 */
	private void SaveGame(ArrayList<ScoreBoardRow>sbrl) {
		PrintWriter write = null; 
		try {
			write = new PrintWriter(sourcePath + save+ fileName);   
			for(ScoreBoardRow sbr: sbrl){
				// check de naam op rare strings om fouten te verkomen
				write.println(stringChecker(sbr.GetName(),toRemove) + ":" + sbr.GetAttempts()+":"+sbr.GetScore()+":"+sbr.GetMode()+":");
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
	/**
	 * Reads the score row
	 * @return
	 */
	public ArrayList<ScoreBoardRow> readScore(){ //String sourcePath, String save,String fileName){
		File file = null;
		Scanner sc = null;
		ArrayList<ScoreBoardRow>  sbrl = new ArrayList<ScoreBoardRow>();
	 
		try {
			file = new File(sourcePath + save+ fileName);
			sc = new Scanner(file);
		 
			sc.useDelimiter(":");
			while (sc.hasNext()) {
				String Name = sc.next();
				int Attempts = Integer.parseInt(sc.next());
				double Score = Double.parseDouble(sc.next());
				 String Mode = sc.next(); 
				sbrl.add(new ScoreBoardRow(Name, Attempts, Score, Mode)); 
			}
		} catch (Exception e) {
			if (e instanceof FileNotFoundException) {
				System.out.println("Bestand niet gevonden");
			}
			if (e instanceof NumberFormatException) {
				System.out.println("Er ging iets fout met het lezen van het bestand. " +sourcePath +save + fileName );
				// System.exit(0);
			}else {
				System.out.println(e);
			}
		} finally { 
			if (sc != null) {
				sc.close();
			}
		} 
		return sbrl;
	}
	/**
	 * RECURSION because it is a requirement. 
	 * (there are faster ways to do this. But it serves it's purpose. 
	 * @param Name
	 * @param check
	 * @return
	 */
	public String stringChecker(String Name , String check) {
		if (check.length() <1){
			return Name;
		}else {
			String s =check.substring(check.length()-1,check.length());
			if(Name.contains(s)){
				Name = Name.replace(s, "") ;
				System.out.println(s);
			}
			return stringChecker(Name,check.substring(0,check.length()-1));
		}
	} 
}
