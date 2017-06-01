package apiRuleSettings;

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
			result[place] = -2; // set default grey todo
			place++;
		}
	}

	private void checkBlack(Iterable<Integer> secret, int[] attempt) {
		for (int i = 0; i < attempt.length; i++) {
			int g = 0;
			for (int s : secret) {
				if (attempt[i] == s && i == g) {
					secretChecked[g] = true;
					result[i] = 0;// black color todo
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
					result[i] = -1;// set white todo
				}
				g++;
			}
		}
	}
	// private void filTheRest(Iterable<Integer>secret, int[]attempt){}
}
