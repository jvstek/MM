package masterMindGamesModes;

import HelpingClasses.MasterMindSettings;
import HelpingClasses.SettingValidationError;
import apiRuleSettings.CSOrganised;

public class MMEasy extends MasterMind {
	@Override
	public void newgame() {
		try {
			mms = new MasterMindSettings(7, 4, 10, 1, 0, 1, 3);
			this.live = true;
			SecretCode = sus.SetSecretCode(mms.getRowLenght(), mms.getSameColorAllowed(), mms.getPlaycolors());
			ICS = new CSOrganised(mms.getRowLenght());
			count = 0;
		} catch (SettingValidationError e) {
			System.out.println(e);
		}
	}
}
