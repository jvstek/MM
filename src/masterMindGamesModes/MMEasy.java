package masterMindGamesModes;

import ruleSettings.CSOrganised;
import ruleSettings.MasterMindSettings;
import ruleSettings.SettingValidationError;
	
public class MMEasy extends MasterMind {
	@Override
	public void newgame() {
		try { 
			int PlayColors = 5;
			int RowLenght = 3;
			int MaxAttempt= 4;
			int SameColorAllowed = 1;
			float GreyScoreValue = 0;
			float WhiteScoreValue = 1;
			float BlackScoreValue = 2;
			mms = new MasterMindSettings(PlayColors ,RowLenght,MaxAttempt,SameColorAllowed,GreyScoreValue,WhiteScoreValue,BlackScoreValue);
			this.live = true;
			SecretCode = SecretSetter.SetSecretCode(mms.getRowLenght(), mms.getSameColorAllowed(), mms.getPlaycolors());
			ICS = new CSOrganised(mms.getRowLenght());
			//ICS = new CSRandom(mms.getRowLenght());
			count = 0;
		} catch (SettingValidationError e) {
			System.out.println(e);
		}
	}
}
