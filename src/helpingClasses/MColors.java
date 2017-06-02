package helpingClasses;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public enum MColors {
	gray(-2, Color.GRAY), white(-1, Color.WHITE), black(0, Color.BLACK), blue(1, Color.BLUE), green(2,
			Color.GREEN), red(3,
					Color.RED), yellow(4, Color.YELLOW), ping(5, Color.PINK), brown(6, new Color(139, 69, 19));

	private final int colorNumber;
	private final Color color;
	private static Map<Object, Object> map = new HashMap<>();

	MColors(int ColorNumber, Color Color) {
		this.colorNumber = ColorNumber;
		this.color = Color;
	}

	public final int GetColorNumber() {
		return colorNumber;
	}

	public Color GetColor() {
		return color;
	}

	// public static int GetColorNumberFromColor(Color color) { return
	// map.get(color) ; }
	public static MColors GetMColor(int ColorNumber) {
		return (MColors) map.get(ColorNumber);
	}

	static {
		for (MColors mColor : MColors.values()) {
			map.put(mColor.GetColorNumber(), mColor);
		}
	}

	public static MColors GetMNumber(Color Color) {
		return (MColors) map.get(Color);
	}

	static {
		for (MColors mColor : MColors.values()) {
			map.put(mColor.GetColor(), mColor);
		}
	}
}
