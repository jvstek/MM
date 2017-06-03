package masterMindGamesModes;

import ruleSettings.CSRandom;
import ruleSettings.MasterMindSettings;
import ruleSettings.SettingValidationError;

public class MMHard extends MasterMind {
	@Override
	public void newgame() {
		try {
			mms = new MasterMindSettings(7, 5, 12, 2, -3, 1, 5);
			this.live = true;
			SecretCode = sus.SetSecretCode(mms.getRowLenght(), mms.getSameColorAllowed(), mms.getPlaycolors());
			ICS = new CSRandom(mms.getRowLenght());
			count = 0;
		} catch (SettingValidationError e) {
			System.out.println(e);
		}
	}
}
