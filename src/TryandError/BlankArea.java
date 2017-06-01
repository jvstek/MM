package TryandError;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Color;

public class BlankArea extends JLabel {
	Dimension minSize = new Dimension(100, 50);

	public BlankArea(Color color) {
		setBackground(color);
		setOpaque(true);
		setBorder(BorderFactory.createLineBorder(Color.black));
	}

	@Override
	public Dimension getMinimumSize() {
		return minSize;
	}

	@Override
	public Dimension getPreferredSize() {
		return minSize;
	}
}