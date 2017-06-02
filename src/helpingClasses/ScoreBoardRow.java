package helpingClasses;

public class ScoreBoardRow {
	// name // score //mode //time (when there is time)
	private String Name;
	private int AttemptsLeft;
	private double Score;
	private String Mode;

	public ScoreBoardRow(String name, int attempts, double score, String mode) {
		this.Name = name;
		this.Score = score;
		this.Mode = mode;
		this.AttemptsLeft = attempts;
	}

	public String GetName() {
		return Name;
	}

	public double GetScore() {
		return Score;
	}

	public int GetAttempts() {
		return AttemptsLeft;
	}

	public String GetMode() {
		return Mode;
	}
}
