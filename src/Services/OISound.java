package Services;

import java.io.File; 
import java.io.IOException;
import java.util.ArrayList; 
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class OISound {

	private String sourcePath = "Mastermind/";
	private String sound = "SoundEffect/";
	private File folder = new File(sourcePath + sound);
	private static ArrayList<File> sounds;
	public OISound() {
		sounds = listFilesForFolder(folder);
	}
/**
 * Only plays mp3's. 
 * @param soundNr
 */
	public void playSound(int soundNr)  {
		
		 try {
	         // Open an audio input stream.
			 File soundFile = sounds.get(soundNr); //you could also get the sound file with an URL
	          AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);              
	         // Get a sound clip resource.
	         Clip clip = AudioSystem.getClip();
	         // Open audio clip and load samples from the audio input stream.
	         clip.open(audioIn);
	         clip.start();
		  } catch (UnsupportedAudioFileException e) {
		         e.printStackTrace();
		      } catch (IOException e) {
		         e.printStackTrace();
		      } catch (LineUnavailableException e) {
		         e.printStackTrace();
		      }
			 
		 
	}
/**
 * Reads all the files of a folder 
 * Todo check if it is a sound file before adding it to the list? 
 * @param folder
 * @return
 */
	public static ArrayList<File> listFilesForFolder(File folder) {
		ArrayList<File>  sounds = new ArrayList<File>();
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {  
				sounds.add(fileEntry);
			}
		}
		return sounds;
	}

	

	 
}
