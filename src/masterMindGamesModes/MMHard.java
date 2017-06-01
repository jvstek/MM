package masterMindGamesModes;

import HelpingClasses.MasterMindSettings;
import HelpingClasses.SettingValidationError;
import apiRuleSettings.CSRandom;

public class MMHard extends MasterMind{
	@Override
	public void newgame() {
		try {
			mms = new MasterMindSettings(7, 4, 10, 2, -1, 1, 5);
			this.live = true;
			SecretCode = sus.SetSecretCode(mms.getRowLenght(), mms.getSameColorAllowed(), mms.getPlaycolors());
			ICS = new CSRandom(mms.getRowLenght());
			count = 0;
		} catch (SettingValidationError e) {
			System.out.println(e);
		}
	}
}
