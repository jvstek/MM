package GameStarter;

import javax.swing.*;

import control.MastermindControl;
import control.MastermindFrameControl;

//import guiPackages.Shape;

public class Game {

	public static void createGameFrame() {

		MastermindControl mmA = new MastermindControl("test");
		MastermindFrameControl masterMindFrame = new MastermindFrameControl(mmA);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createGameFrame();
			}
		});
	}
	// mastermind + settings

}
