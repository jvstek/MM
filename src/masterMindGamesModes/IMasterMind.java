package masterMindGamesModes;

import ruleSettings.MasterMindSettings;

public interface IMasterMind {

	public abstract MasterMindSettings GetMasterMindSettings();

	public abstract void newgame();

	public abstract int[] checkPlayPins(int[] colorValeus);

	public abstract boolean isLive();

	public abstract double getScore();
}
