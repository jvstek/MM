package ruleSettings;

import Services.MColors;

public class CSRandom implements ICheckScore {
	// think of a better way to do the chekcs...
	private boolean[] secretChecked;
	private boolean[]pinSet;
	private int[] result;
	private int place; // used for easily placing a pin. without needing to
						// check where the last pin is placed.

	public CSRandom(int placesInRow) {
		secretChecked = new boolean[placesInRow];
		pinSet= new boolean[placesInRow];
		result = new int[placesInRow];
		place = 0;
	}

	@Override
	public int[] getScorePins(Iterable<Integer> secret, int[] attempt) {
		prepareCheck();
		checkBlack(secret, attempt);
		checkWhite(secret, attempt); 
		return result;
	}

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
