package TryandError;

import java.awt.Color;
import java.util.ArrayList;

public class FailedCode {
	public FailedCode() {

	}

	private ArrayList<Color> RandomColors(int aantalkleuren) {
		ArrayList<Color> colors = new ArrayList<Color>();
		float mf = 1.0f;
		// 100/5 = 20
		for (int i = 0; i < aantalkleuren; i++) {
			int g = i;
			final float hue = mf / g;// random.nextFloat();
			final float saturation = 0.9f;// 1.0 for brilliant, 0.0 for dull
			final float luminance = 1.0f; // 1.0 for brighter, 0.0 for black
			Color cc = Color.getHSBColor(hue, saturation, luminance);
			colors.add(cc);
			System.out.println(cc);
		}
		// failed because some colors look to much alike.
		// stil could be usefull in the future
		return colors;
		// also need to put colors in a clas then and that would have been Much
		// easier then using color as enums!!
	}
}
