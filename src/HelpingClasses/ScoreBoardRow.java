package HelpingClasses;

public class ScoreBoardRow {
	// name // score //mode //time (when there is time)
	private String name;

	private double score;
private int attempts;
	private String mode;

	public ScoreBoardRow(String name, int attempts,double score, String mode) {
		this.name = name;
		this.score = score;
		this.mode = mode;
	}

	public String getName() {
		return name;
	}

	public double getScore() {
		return score;
	}
public int getAttempts() {
	return attempts;
}
	public String getMode() {
		return mode;
	}
}
