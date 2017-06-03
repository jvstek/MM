package masterMindGamesModes;

import ruleSettings.CSOrganised;
import ruleSettings.MasterMindSettings;
import ruleSettings.SettingValidationError;
	
public class MMEasy extends MasterMind {
	@Override
	public void newgame() {
		try {
			mms = new MasterMindSettings(6, 3, 4, 1, 0, 1, 2);
			this.live = true;
			SecretCode = sus.SetSecretCode(mms.getRowLenght(), mms.getSameColorAllowed(), mms.getPlaycolors());
			ICS = new CSOrganised(mms.getRowLenght());
			count = 0;
		} catch (SettingValidationError e) {
			System.out.println(e);
		}
	}
}
