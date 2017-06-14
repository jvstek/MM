package ruleSettings;

import Services.MColors;

public class CSOrganised implements ICheckScore {
	private boolean[] secretChecked;
	private int[] result;

	public CSOrganised(int placesInRow) {
		secretChecked = new boolean[placesInRow];
		result = new int[placesInRow];
	}
	/**
	 * method used to check the score and put pins at the right position of the color that is being checked. 
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
		CleanCheckList(secretChecked);
		CleanResultList(result);
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
			result[place] = MColors.gray.GetColorNumber(); // set default grey
			place++;
		}
	}
	/**
	 * checked if the given int color is the same as the unknown secret int color 
	 * on the same position
	 * @param secret combination
	 * @param attempt combination
	 */
	private void checkBlack(Iterable<Integer> secret, int[] attempt) {
		for (int i = 0; i < attempt.length; i++) {
			int g = 0;
			for (int s : secret) {
				if (attempt[i] == s && i == g) {
					secretChecked[g] = true;
					result[i] = MColors.black.GetColorNumber();
				}
				g++;
			}
		}
	}
	/**
	 * checked if the given int color already checked. if so go to the next. 
	 * If the guessed int color isn't checked already. Check if the guess is present in the unknown secret. 
	 * If it is present And the position is not already checked. 
	 * Put a white pin on that location
	 * @param secret
	 * @param attempt
	 */
	private void checkWhite(Iterable<Integer> secret, int[] attempt) {
		for (int i = 0; i < attempt.length; i++) {
			int g = 0;
			for (int s : secret) {
				if (attempt[i] == s && secretChecked[g] == false &&result[i] ==MColors.gray.GetColorNumber()) {
					secretChecked[g] = true;
					result[i] =MColors.white.GetColorNumber();
				}
				g++;
			}
		}
	}
}
