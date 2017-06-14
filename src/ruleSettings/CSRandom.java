package ruleSettings;

import Services.MColors;
/**
 * 
 * @author jvs
 *The clasname provides a little miscommunication. 
 *It isn't random. But the result is being put on the first position and every next score is followed after that. 
 */
public class CSRandom implements ICheckScore {
	
	// think of a better way to do the checks...
	
	private boolean[] secretChecked;
	private boolean[]pinSet;
	private int[] result;
	private int place; // used for easily placing a pin. without needing to
						// check where the last pin is placed.

	public CSRandom(int placesInRow) {
		secretChecked = new boolean[placesInRow];
		pinSet= new boolean[placesInRow];
		result = new int[placesInRow];
		//place = 0;
	}
	/**
	 * checks score pins. Put the first result on the first position, the second result on the second etc. 
	 * This way there is no direct link to the score pins and the positions they have checked. 
	 * Put black first and then white. 
	 */
	@Override
	public int[] getScorePins(Iterable<Integer> secret, int[] attempt) {
		prepareCheck();
		checkBlack(secret, attempt);
		checkWhite(secret, attempt); 
		return result;
	}
	/**
	 * prepare the previously used lists. (you can also do this after each check. But you wil need to prepare the list then before hand aswel. 
	 */
	private void prepareCheck() {
		CleanBoolList(secretChecked);
		CleanBoolList(pinSet);
		CleanResultList(result);
		
		place = 0;
	}

	private void CleanBoolList(boolean[] boollist) {
		int place = 0;
		int length = result.length;
		while (place < length) {
			boollist[place] = false; // set default false
			place++;
		}
	}

	private void CleanResultList(int[] result) {
		int place = 0;
		int length = result.length;
		while (place < length) {
			result[place] = MColors.gray.GetColorNumber();
			place++;
		}
	}
	/**
	 * Check if the guessed color is at the same position as the unknown secret color. 
	 * If so put a black pin on the first position and  mark it as being checked. 
	 * @param secret
	 * @param attempt
	 */
	private void checkBlack(Iterable<Integer> secret, int[] attempt) {

		for (int i = 0; i < attempt.length; i++) {
			int g = 0;
			for (int s : secret) {
				if (attempt[i] == s && i == g) {
					pinSet[g] = true; 
					secretChecked[g] = true;
					result[place] = MColors.black.GetColorNumber();
					place++;
				}
				g++;
			}
		}
	}
	/**
	 * Check if the guessed color is in the unknown color string and if that location is not already checked. 
	 * If so put that location as checked and put in a white pin and the next location
	 * @param secret
	 * @param attempt
	 */
	private void checkWhite(Iterable<Integer> secret, int[] attempt) {
		for (int i = 0; i < attempt.length; i++) {
			int g = 0;
			for (int s : secret) { 
				if (attempt[i] == s && secretChecked[g] == false && pinSet[i] == false) {
					pinSet[i] = true; 
					secretChecked[g] = true;
					result[place] = MColors.white.GetColorNumber();
					place++;
				}
				g++;
			}
		}
	}
	// private void filTheRest(Iterable<Integer>secret, int[]attempt){}
}
