package Services;

import java.awt.Point;
import java.util.ArrayList;

import GuiShapes.IShape;
import GuiShapes.Pin;
import GuiShapes.ScorePin;
import GuiShapes.Selector;
import GuiShapes.Square;
import GuiShapes.Unknown;
/**
 * 
 * @author jvs
 *This Clas creates the field shapes that are shown when a new game is started. 
 */
public class MasterMindSetup {
	//Todo Debug with increasing row numbers they eventually intersect?
	// set up information 
	private int xPosition = 80; // starting position of x
	private int yPostion = 120; // starting position of y

	private int playFieldPinMargin = 8; // space between circles
	private int playFieldPinSize = 23; // size of the circles on the playfield 
	// these are the base scores the rest is based upon. 

	private double ScoreSizeDifference = 0.5;//different sizes between the circle
	private double playFieldScoreMargin = playFieldPinMargin * ScoreSizeDifference;
	private double playFieldScoreSize = playFieldPinSize * ScoreSizeDifference;

	private double SelectorSizeDifference = 1.3;//different sizes between the circle
	private double playFieldSelectorMargin = playFieldPinMargin * SelectorSizeDifference;
	private double playFieldSelectorSize = playFieldPinSize * SelectorSizeDifference;

	private double SecretSizeDifference = 1.3;//different sizes between the circle
	private double playFieldSecretMargin = playFieldPinMargin * SecretSizeDifference;
	private double playFieldSecretSize = playFieldPinSize * SecretSizeDifference;
/**
 * 1 methodes to create start creating all the row and circles that are being shown when playing a game
 * @param RowLength
 * @param MaxAttempts
 * @param ColorAmount
 * @return
 */
	public ArrayList<IShape> FieldSetup(int RowLength, int MaxAttempts, int ColorAmount) {
		ArrayList<IShape> field = new ArrayList<IShape>();
		field.addAll(SetPlayFieldPin(RowLength, MaxAttempts));
		field.addAll(SetSelectors(RowLength, ColorAmount));
		field.addAll(SetScorePins(RowLength, MaxAttempts));
		field.addAll(SetUnknownSecret(RowLength));
		return field;

	}

	// set the pins that can submit score
	public ArrayList<IShape> SetPlayFieldPin(int RowLength, int RowAmount) {

		return SetPinRows(RowLength, RowAmount, xPosition, yPostion);
	}

	// double row needs additional logica when row is longer then standerd 4.
	// so it is as is for the time being
	public ArrayList<IShape> SetScorePins(int RowLength, int RowAmount) {
		Point StartingPoint = new Point(xPosition + RowLength * (playFieldPinSize * 2)+ (int)(RowLength * (playFieldPinMargin-1)), yPostion);
		return SetScoreRows(RowLength, RowAmount, StartingPoint);
	}

	// row length needed to get the starting position
	// Color to select
	public ArrayList<IShape> SetSelectors(int RowLength, int ColorAmount) {
		ArrayList<IShape> SelectorShapes = new ArrayList<IShape>();

		int x = (int) (yPostion + (RowLength * (playFieldPinSize * 2)) + playFieldSelectorSize
				+ (RowLength * (playFieldScoreSize * 2)+ (int)(RowLength * playFieldScoreMargin)));
		int y = xPosition + (int) playFieldSelectorMargin;
		for (int i = 0; i < ColorAmount; i++) {
			Selector c = new Selector(new Point(x, y), (int) playFieldSelectorSize, MColors.GetMColor(i).GetColor());
			SelectorShapes.add(c);
			y = (int) (y + (playFieldSelectorSize * 2) + playFieldSelectorMargin);
		}
		Square check = new Square(new Point((int) (x - playFieldSelectorSize), y), MColors.GetMColor(0).GetColor(),
				(int) (playFieldSelectorSize * SelectorSizeDifference),
				(int) (playFieldSelectorSize * SelectorSizeDifference * 2));
		SelectorShapes.add(check);
		return SelectorShapes;
	}
	/**
	 * Set the row of unknown secrets
	 * @param RowLength
	 * @return
	 */
	public ArrayList<IShape> SetUnknownSecret(int RowLength) {
		ArrayList<IShape> SecretShapes = new ArrayList<IShape>();

		int y = xPosition;
		int x = yPostion - (int) (playFieldSecretSize * 2);
		for (int i = 0; i < RowLength; i++) {
			Unknown c = new Unknown(new Point(y, x), (int) playFieldSecretSize, MColors.GetMColor(0).GetColor(), i);
			SecretShapes.add(c);
			y = (int) (y + (int) ((playFieldSecretSize * 2)) + playFieldSecretMargin);
		}
		return SecretShapes;
	}
	/**
	 * loop to go through the rows
	 */
	private ArrayList<IShape> SetPinRows(int RowLength, int RowAmount, int x, int y) {
		ArrayList<IShape> rowshapes = new ArrayList<IShape>();
		int row = 0;
		for (int i = 0; i < RowAmount; i++) {
			row = i;

			rowshapes.addAll(SetPinRow(RowLength, new Point(x, y), row));
			y = (y + (playFieldPinSize * 2) + playFieldPinMargin);
		}
		return rowshapes;
	}
	/**
	 * setpins in 1 row
	 * @param RowLength
	 * @param OfSet
	 * @param row
	 * @return
	 */
	private ArrayList<IShape> SetPinRow(int RowLength, Point OfSet, int row) {
		ArrayList<IShape> rowshapes = new ArrayList<IShape>();
		int x = (int) OfSet.getX();
		int y = (int) OfSet.getY();
		int place = 0;
		for (int i = 0; i < RowLength; i++) {
			place = i;
			Pin c = new Pin(new Point(x, y), playFieldPinSize, MColors.GetMColor(0).GetColor(), row, place);
			rowshapes.add(c);

			x = (x + (playFieldPinSize * 2) + playFieldPinMargin);

		}
		return rowshapes;
	}
/**
 * loop to go through the rows
 */
	private ArrayList<IShape> SetScoreRows(int RowLength, int RowAmount, Point OfSet) {
		ArrayList<IShape> rowshapes = new ArrayList<IShape>();
		int x = (int) OfSet.getX();
		int y = (int) OfSet.getY();
		int row = 0;
		for (int i = 0; i < RowAmount; i++) {
			row = i;
			rowshapes.addAll(SetScoreRow(RowLength, new Point(x, y), row));
			y = (y + (playFieldPinSize * 2) + playFieldPinMargin);
		}
		return rowshapes;
	}
/**
 * set scorepins in 1 row
 * @param RowLength
 * @param OfSet
 * @param row
 * @return
 */
	private ArrayList<IShape> SetScoreRow(int RowLength, Point OfSet, int row) {
		ArrayList<IShape> rowshapes = new ArrayList<IShape>();
		int x = (int) OfSet.getX();
		int y = (int) OfSet.getY();
		int place = 0;
		for (int i = 0; i < RowLength; i++) {
			place = i;
			ScorePin c = new ScorePin(new Point(x, y), (int) playFieldScoreSize, MColors.GetMColor(-2).GetColor(), row,
					place);
			rowshapes.add(c);
			x = (x + (int) ((playFieldScoreSize * 2) + playFieldScoreMargin));
		}
		return rowshapes;
	}

}
