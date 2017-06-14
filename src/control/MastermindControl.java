package control;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import GuiShapes.IShape;
import GuiShapes.Pin;
import GuiShapes.ScorePin;
import GuiShapes.Selector;
import GuiShapes.Unknown;
import Services.IOScore;
import Services.MColors;
import Services.MasterMindSetup;
import masterMindGamesModes.IMasterMind;
import masterMindGamesModes.MMEasy;
import masterMindGamesModes.MMHard;
import masterMindGamesModes.MMNormal;

public class MastermindControl {

	// shows gives list to api to be draw
	private ArrayList<IShape> canvasShapes;
	private MasterMindSetup mms; // setup the playfield shapes
	private IMasterMind imm; // used to communicate with the mastermind modes
	private int activeRow; // keep track of the active row
	private int activePlace;// keep track of the Active place
	private int colorNumber;//keeps track of the last selected color as number
	private boolean scoreSaved; // todo decide where to put it properly. (if you put it in 
	private String gameType; // game used for saving the game.
	private IOScore oi ;// used to save the game 
	
	public MastermindControl() {
		mms = new MasterMindSetup();
		canvasShapes = new ArrayList<IShape>();
		activeRow = 0;
		activePlace = 0;
		colorNumber = 0;
		scoreSaved = false;
		gameType= "";
		oi = new IOScore();
	}
	//starting a new hard game
	public void StartHardGame() {
		imm = new MMHard();
		gameType= "Hard";
		gameStarter(imm);
	}
	//staring a new normal game
	public void StartNormalGame() {
		imm = new MMNormal();
		gameType= "Normal";
		gameStarter(imm);
	}
	// starting a new easy game
	public void StartEasyGame() { 
		imm = new MMEasy();
		gameType= "Easy";
		gameStarter(imm); 
	}
	
	private void gameStarter(IMasterMind IMm) {
		resetGui();
		imm.newgame();
		SetupPlayField(imm);
		setSelectionPin(activeRow, activePlace, true);
	}
	
	private void resetGui() {
		colorNumber = 0;
		activeRow = 0;
		activePlace = 0;
		canvasShapes.clear();
		scoreSaved = false;
	}
	public void saveGame(){
		
		if(!scoreSaved){
		String name = JOptionPane.showInputDialog("Enter name. To Save your score " + imm.getScore(),"Please Enter your name.");
		name = oi.stringChecker(name,"!:'|*\";`,?^@#$}{"); //to do
		if(name != null || name != ""){
		oi.AddGame(name, (imm.GetMasterMindSettings().getMaxAttempts()-imm.getCount()), imm.getScore(), gameType);
		scoreSaved = true;}
		}
	} 
	public Iterable<IShape> getShapes() {
		Iterable<IShape> cl = canvasShapes;
		return cl;
	}

	public void setActiveRow(int activeRow) {
		this.activeRow = activeRow;
	}

	private void SetupPlayField(IMasterMind imm) {
		canvasShapes.addAll(mms.FieldSetup(imm.GetMasterMindSettings().getRowLenght(),
				imm.GetMasterMindSettings().getMaxAttempts(), imm.GetMasterMindSettings().getPlaycolors()));

	}

	public void setSelectedCircle(IShape s) {
		switch (s.getClass().getName()) {
		case "GuiShapes.Pin":
			setSelectedPin(((Pin) s));
			break;
		case "GuiShapes.Selector":
			setSelectedColor((Selector) s);
			break;
		default:
			break;
		}
	}
	/**
	 * set selected pin the selected color
	 * @param pin
	 */
	private void setSelectedPin(Pin pin) {
		if (pin.GetRow() == this.activeRow) {
			setActivePin(activePlace, pin.GetPlace());
			pin.SetColor(MColors.GetMColor(colorNumber).GetColor());
		}
	}
/**
 * set the given pin a color
 * @param pin
 */
	private void setPinColor(Pin pin) {
		pin.SetColor(MColors.GetMColor(colorNumber).GetColor());
		setActivePin(activePlace, activePlace + 1);
	}
/**
 * set the active pin active and deactivate the last active pin
 * @param present
 * @param nextPin
 */
	private void setActivePin(int present, int nextPin) {
		// put last pin false
		setSelectionPin(activeRow, present, false);
		activePlace = nextPin;
		if (activePlace >= imm.GetMasterMindSettings().getRowLenght()) {
			activePlace = 0;
		}
		// put next pin active
		setSelectionPin(activeRow, activePlace, true);
	}
	/**
	 * set the pin given with the parameters as selected pin. 
	 * @param row
	 * @param place
	 * @param selected
	 */
	private void setSelectionPin(int row, int place, boolean selected) {
		getPin(row, place).SetSelected(selected);
	}
	/**
	 * set active pin the given selected pin color
	 * @param selector
	 */
	public void setSelectedColor(Selector selector) {

		int ColorNr = MColors.GetMNumber(selector.GetColor()).GetColorNumber();
		try {
			if (ColorNr >= 0 && ColorNr < imm.GetMasterMindSettings().getPlaycolors()) {
				colorNumber = ColorNr;
				setPinColor(getPin(activeRow, activePlace));
			}
		} catch (Exception e) {
			System.out.println("something went wrong with setting a color");
		}
	}
	/**
	 * get the pin according the variables
	 * @param row
	 * @param place
	 * @return
	 */
	private Pin getPin(int row, int place) {
		for (IShape p : canvasShapes) {
			if (p instanceof Pin) {
				if (((Pin) p).GetPlace() == place && ((Pin) p).GetRow() == row) {
					return ((Pin) p);
				}
			}
		}
		return null;
	}
	/** 
	 * Check the present color row to the unknown secret row if the game is life. 
	 * When the game code is guessed make sure it shows the unknown color.
	 * If the game is not live show a message box to inform the person 
	 */
	public void CheckRow() {
		// get color for every pin in the row.
		// check it
		// set the scorepin colors
		if (imm.isLive()) {
			try {
				setScorePin(activeRow, imm.checkPlayPins(getAllColorNumbersFromRow(activeRow)));
				if (imm.isLive()) {
					setNextRow();
				} else {
					setUnknownCode(getAllColorNumbersFromRow(activeRow));
				}
			} catch (Exception e) {
				System.out.println("Little error " + e);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Please start a new game" + '\n' + " Your score: " + getScore(),
					"Game ended", JOptionPane.INFORMATION_MESSAGE);
		}

	}
	/**
	 * if the secret codes is guessed show the solution
	 */
	private void setUnknownCode(int[] colorsRow) {
		for (IShape p : canvasShapes) {
			if (p instanceof Unknown) {
				for (int i = 0; i < colorsRow.length; i++) {
					if (((Unknown) p).GetPlace() == i) {
						((Unknown) p).SetColor(MColors.GetMColor(colorsRow[i]).GetColor());
					}
				}
			}
		}
	}
	/**
	 * After a check set the next row as active row
	 * Also you make change the active pin to the active pin in the next row
	 */
	private void setNextRow() {
		setSelectionPin(activeRow, activePlace, false);
		activeRow++;
		setSelectionPin(activeRow, activePlace, true);
	}
	/**
	 * Get all color numbers from the given row. 
	 * @param int as row count
	 * @return all the color numbers of the given row
	 */
	private int[] getAllColorNumbersFromRow(int row) {
		int[] ColorNrRow = new int[imm.GetMasterMindSettings().getRowLenght()];
		for (int i = 0; i < ColorNrRow.length; i++) {
			ColorNrRow[i] = MColors.GetMNumber(getPin(row, i).GetColor()).GetColorNumber();
		}
		return ColorNrRow;
	}
/**
 * go through the list of score pins and give them the right result color
 * @param row
 * @param result
 */
	private void setScorePin(int row, int[] result) {
		int place = 0;
		int length = result.length;
		while (place < length) {
			getScorePin(row, place, result[place]);
			place++;
		}
	}
/**
 * Get the right scorepin given the parameters
 * @param row
 * @param place
 * @param colornr
 */
	private void getScorePin(int row, int place, int colornr) {
		for (IShape p : canvasShapes) {
			if (p instanceof ScorePin) {
				if (((ScorePin) p).GetPlace() == place && ((ScorePin) p).GetRow() == row) {
					((ScorePin) p).SetColor(MColors.GetMColor(colornr).GetColor());
				}
			}
		}

	}
/**
 * Return the score so it can be shown on the screen
 * @return
 */
	public double getScore() {
		return imm.getScore();
	}
	/**
	 * return if the game is live.
	 * @return
	 */
	public boolean isLive(){
		return imm.isLive();
	}
	
}
