package ruleSettings;

public class SettingValidationError extends Exception {

	private static final long serialVersionUID = 1L;
	// a way of checking for errors only used in mastermind settings.
	public SettingValidationError(String string) {
		super(string);
	}

}
