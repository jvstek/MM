package AI;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import GuiShapes.IShape;
import GuiShapes.Pin;
import GuiShapes.ScorePin;
import GuiShapes.Selector;
import GuiShapes.Unknown;
import HelpingClasses.MColors;
import HelpingClasses.MasterMindSetup;
import masterMindGamesModes.IMasterMind;
import masterMindGamesModes.MMEasy;

public class MastermindApi {

	// shows gives list to api to be draw
	// multiple list it might remove my bugs

	ArrayList<IShape> canvasShapes;
	MasterMindSetup mms;
	IMasterMind imm;
	private int activeRow;
	private int activePlace;
	private int colorNumber;

	public MastermindApi(String t) {
		mms = new MasterMindSetup();
		canvasShapes = new ArrayList<IShape>();
		activeRow = 0;
		activePlace = 0;
		colorNumber = 0;
	}

	public void StartEasyGame() {
		ResetGui();
		imm = new MMEasy();
		imm.newgame();
		SetupPlayField(imm);
		setSelectionPin(activeRow, activePlace, true);
	}

	private void ResetGui() {
		colorNumber = 0;
		activeRow = 0;
		activePlace = 0;
		CleanCanvas();
	}

	public Iterable<IShape> getCircles() {
		Iterable<IShape> cl = canvasShapes;
		return cl;
	}

	private void CleanCanvas() {
		canvasShapes.clear();
	}

	public void setActiveRow(int activeRow) {
		this.activeRow = activeRow;
	}

	private void SetupPlayField(IMasterMind imm) {
		canvasShapes.addAll(mms.FieldSetup(imm.GetMasterMindSettings().getRowLenght(),
				imm.GetMasterMindSettings().getMaxAttempts(), imm.GetMasterMindSettings().getPlaycolors()));

	}

	public void setSelectedCircle(IShape s) {
		// if it is a pin set a pin(if it is from a active row). if it is a
		// selector set color.
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

	public void setSelectedPin(Pin pin) {
		if (pin.GetRow() == this.activeRow) {
			setActivePin(activePlace, pin.GetPlace());
			pin.SetColor(MColors.GetMColor(colorNumber).GetColor());
		}
	}

	private void setPinColor(Pin pin) {
		pin.SetColor(MColors.GetMColor(colorNumber).GetColor());
		setActivePin(activePlace, activePlace + 1);
	}

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

	private void setSelectionPin(int row, int place, boolean selected) {
		getPin(row, place).SetSelected(selected);
	}

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

	public void CheckRow() {

		// get collor for every pin in the row.
		// check it
		// set the scorepin colors
		if (imm.isLive()) {
			try {
				setScorePin(activeRow, imm.checkPlayPins(getColorNrRow(activeRow)));
				if (imm.isLive()) {
					setNextRow();
				}else{ setUnknownCode( getColorNrRow(activeRow));}
			} catch (Exception e) {
				System.out.println("Little error " + e);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Please start a new game" + '\n' + " Your score: " + getScore(),
					"Game ended", JOptionPane.INFORMATION_MESSAGE);
		}

	}
	private void setUnknownCode(int[]colorsRow){
		for (IShape p : canvasShapes) {
			if (p instanceof Unknown) {
				for(int i =0;i<colorsRow.length;i++){
					if(((Unknown) p).GetPlace() ==i){
						((Unknown) p).SetColor(MColors.GetMColor(colorsRow[i]).GetColor());
					} 
				}
			}
		}
	}
	private void setNextRow() {
		setSelectionPin(activeRow, activePlace, false);
		activeRow++;
		setSelectionPin(activeRow, activePlace, true);
	}

	private int[] getColorNrRow(int row) {
		int[] ColorNrRow = new int[imm.GetMasterMindSettings().getRowLenght()];
		for (int i = 0; i < ColorNrRow.length; i++) {
			ColorNrRow[i] = MColors.GetMNumber(getPin(row, i).GetColor()).GetColorNumber();
		}
		return ColorNrRow;
	}

	private void setScorePin(int row, int[] result) {
		int place = 0;
		int length = result.length;
		while (place < length) {
			getScorePin(row, place, result[place]);
			place++;
		}
	}

	private void getScorePin(int row, int place, int colornr) {
		for (IShape p : canvasShapes) {
			if (p instanceof ScorePin) {
				if (((ScorePin) p).GetPlace() == place && ((ScorePin) p).GetRow() == row) {
					((ScorePin) p).SetColor(MColors.GetMColor(colornr).GetColor());
				}
			}
		}

	}

	public double getScore() {
		return imm.getScore();
	}

}
