package masterMindGamesModes;

import java.util.List;

import Services.MColors;
import ruleSettings.ICheckScore;
import ruleSettings.MasterMindSettings;
import ruleSettings.SetSecret;

public abstract class MasterMind implements IMasterMind {
	protected boolean live;// check if game is live
	protected float score; // score
	protected int count; // count the attempts
	protected ICheckScore ICS;
	protected MasterMindSettings mms; // = new MasterMindSettings(7, 4, 10, 1,
										// (float) 0, 1, 3);
	protected SetSecret sus = new SetSecret();
	protected List<Integer> SecretCode;

	@Override
	public abstract void newgame();

	@Override
	public MasterMindSettings GetMasterMindSettings() {
		return mms;
	}

	@Override
	public int[] checkPlayPins(int[] colorValeus) {
		if (colorValeus.length != mms.getRowLenght()) {
			System.out.println("cheater");
			live = false;
		}
		if (live) {
			// Iterable<Integer> sc = SecretCode;
			int[] result = ICS.getScorePins(SecretCode, colorValeus);

			setScore(result);
			checkLive(result);
			return result;
		}
		return null;
	}

	@Override
	public boolean isLive() {
		return live;
	}

	@Override
	public double getScore() {
		double s = score;
		return s;
	}

	private void checkLive(int[] result) {
		checkCount();
		secretGeussed(result);
		// save game
	}

	private void checkCount() {
		count++;
		if (count >= mms.getMaxAttempts()) {
			live = false;
		}
	}

	private void secretGeussed(int[] result) {
		boolean b = true;
		// if not all black(todo)
		for (int r : result) {
			if (r != 0) {
				b = false;
			}
		}
		if (b) {
			live = false;
		}
	}

	private void setScore(int[] result) {
		// todo make a test. To see what way is faster
		// int grey = MColors.gray.GetColorNumber();
		for (int r : result) {
			if (r == MColors.gray.GetColorNumber()) {
				score = score + mms.getGreyScoreValue();
			} else if (r == MColors.white.GetColorNumber()) {
				score = score + mms.getWhiteScoreValue();
			} else if (r == MColors.black.GetColorNumber()) {
				score = score + mms.getBlackScoreValue();
			}

		}
	}
}
