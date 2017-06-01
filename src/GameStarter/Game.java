package GameStarter;

import javax.swing.*;

import AI.MastermindApi;
import guiPackages.MastermindFrame;

//import guiPackages.Shape;

public class Game {

	public static void createGameFrame() {

		MastermindApi mmA = new MastermindApi("test");
		MastermindFrame masterMindFrame = new MastermindFrame(mmA);
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
