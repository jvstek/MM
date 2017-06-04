package masterMindGamesModes;
 
import ruleSettings.CSRandom;
import ruleSettings.MasterMindSettings;
import ruleSettings.SettingValidationError;

public class MMNormal extends MasterMind {
	@Override
	public void newgame() {
		try {
			mms = new MasterMindSettings(6, 4, 10, 2, 0, 1, 3);
			this.live = true;
			SecretCode = SecretSetter.SetSecretCode(mms.getRowLenght(), mms.getSameColorAllowed(), mms.getPlaycolors());
			ICS = new CSRandom(mms.getRowLenght());
			count = 0;
		} catch (SettingValidationError e) {
			System.out.println(e);
		}
	}
}
