package HelpingClasses;

public class MasterMindSettings {
	private int sameColorAllowed; // when making a new game
	// private float multiplier; // score multiplier? To be decided later on
	private float greyScoreValue; // give each there own value? or a standard
									// value then increase it with multiplier?
	private float whiteScoreValue;
	private float blackScoreValue;
	private int maxAttempts; // maximum attempts
	private int rowLenght;// Standard row length could change with the
							// difficulty of the game
	private int playcolors; // amount of colors in play or make it max?
							// (cause there i a fix amount of colors)
	// validation errors can be caused when custom games are implemented....

	public MasterMindSettings(int PlayColors, int RowLenght, int MaxAttempt, int SameColorAllowed, float GreyScoreValue,
			float WhiteScoreValue, float BlackScoreValue) throws SettingValidationError {
		if (!settingCheck(PlayColors, RowLenght, MaxAttempt, SameColorAllowed)) {
			throw new SettingValidationError("SomeSettings aint good enough");
		}
		if (!checkValidation(PlayColors, RowLenght, SameColorAllowed)) {
			throw new SettingValidationError("More Places in a row then Colors Availeble");
		}
		if (!checkScoreValue(GreyScoreValue, WhiteScoreValue, BlackScoreValue)) {
			throw new SettingValidationError(
					"valeu of grey needs to be smaller then white and white smaller then black");
		}

		playcolors = PlayColors;
		rowLenght = RowLenght;
		maxAttempts = MaxAttempt;
		// multiplier = Multiplier;
		sameColorAllowed = SameColorAllowed;
		greyScoreValue = GreyScoreValue;
		whiteScoreValue = WhiteScoreValue;
		blackScoreValue = BlackScoreValue;

	}

	private boolean checkValidation(int Playcolors, int RowLenght, int SameColorAllowed) {
		// if rowlength is longer then the playcolors * sameColorAllower game
		// may not be made
		return RowLenght < (Playcolors * SameColorAllowed);
	}

	private boolean checkScoreValue(float grey, float white, float black) {
		// valeu of grey needs to be smaller then white and white smaller then
		// black
		if (grey < white) {
			return white < black;
		}
		return false;
	}

	private boolean settingCheck(int PlayColors, int RowLength, int MaxAttempt, int SameColorAllowed) {
		if (PlayColors < 3 || PlayColors > 7) {
			return false;
		}
		if (RowLength < 3) {
			return false;
		}
		if (MaxAttempt < 4) {
			return false;
		}
		if (SameColorAllowed < 1) {
			return false;
		}
		return true;
	}
	// public float getMultiplier() {
	// return multiplier;
	// }

	public int getRowLenght() {
		return rowLenght;
	}

	public int getPlaycolors() {
		return playcolors;
	}

	public int getSameColorAllowed() {
		return sameColorAllowed;
	}

	public int getMaxAttempts() {
		return maxAttempts;
	}

	public float getGreyScoreValue() {
		return greyScoreValue;
	}

	public float getWhiteScoreValue() {
		return whiteScoreValue;
	}

	public float getBlackScoreValue() {
		return blackScoreValue;
	}
}
