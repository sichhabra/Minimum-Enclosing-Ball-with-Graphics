import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class PointListPanel extends JPanel {

	private static final long serialVersionUID = 7626287636690309044L;

	public PointListPanel() {
		this.setPreferredSize(new Dimension(650, 50));
		this.setBackground(Color.GRAY);
		this.setName("Point List Panel");
	}

}
