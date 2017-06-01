package apiRuleSettings;

import HelpingClasses.MColors;

public class CSRandom implements ICheckScore{

	private boolean[] secretChecked;
	private int[] result;
	private int place; // used for easily placing a pin. without needing to check where the last pin is placed. 
	public CSRandom(int placesInRow) {
		secretChecked = new boolean[placesInRow];
		result = new int[placesInRow];
		place = 0;
	}

	public int[] getScorePins(Iterable<Integer> secret, int[] attempt) {
		prepareCheck();
		checkBlack(secret, attempt);
		checkWhite(secret, attempt);
		// filTheRest();you dont need to fil the rest because it is default
		// graypin color when preparing
		// for(int i = 0;i<result.length;i++){
		// System.out.println(i + "plaats " + result[i] + " resultaat");
		// }
		return result;
	}

	private void prepareCheck() {
		CleanCheckList(secretChecked);
		CleanResultList(result);
		place = 0;
	}
	private void CleanCheckList(boolean[] checked) {
		int place = 0;
		int length = result.length;
		while (place < length) {
			checked[place] = false; // set default false
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
				System.out.println(s + " secret " + attempt[i]);
				if (attempt[i] == s && secretChecked[g] == false) {
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

	

