package ruleSettings;

import Services.MColors;

public class CSOrganised implements ICheckScore {
	private boolean[] secretChecked;
	private int[] result;

	public CSOrganised(int placesInRow) {
		secretChecked = new boolean[placesInRow];
		result = new int[placesInRow];
	}

	@Override
	public int[] getScorePins(Iterable<Integer> secret, int[] attempt) {
		prepareCheck();
		checkBlack(secret, attempt);
		checkWhite(secret, attempt);
		return result;
	}

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
					result[i] = MColors.black.GetColorNumber();
				}
				g++;
			}
		}
	}

	private void checkWhite(Iterable<Integer> secret, int[] attempt) {
		for (int i = 0; i < attempt.length; i++) {
			int g = 0;
			for (int s : secret) {
				if (attempt[i] == s && secretChecked[g] == false) {
					secretChecked[g] = true;
					result[i] =MColors.white.GetColorNumber();
				}
				g++;
			}
		}
	}
}
