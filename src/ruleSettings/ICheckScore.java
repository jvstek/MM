package ruleSettings;

public interface ICheckScore {
	public int[] getScorePins(Iterable<Integer> secret, int[] attempt);
}
