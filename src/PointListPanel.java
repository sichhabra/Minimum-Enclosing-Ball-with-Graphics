import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.JPanel;

public class PointListPanel extends JPanel {

	private static final long serialVersionUID = 7626287636690309044L;

	public PointListPanel() {
		this.setPreferredSize(new Dimension(650, 50));
		this.setBackground(Color.GRAY);
		this.setName("Point List Panel");
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (State.currentCircle != null) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(Color.BLACK);
			g2.setFont(new Font("Helvetica Neue", Font.PLAIN, 25));
			double x = BigDecimal.valueOf(State.currentCircle.center.x).setScale(3, RoundingMode.HALF_UP).doubleValue();
			double y = BigDecimal.valueOf(State.currentCircle.center.y).setScale(3, RoundingMode.HALF_UP).doubleValue();
			double r = BigDecimal.valueOf(State.currentCircle.radius).setScale(3, RoundingMode.HALF_UP).doubleValue();
			String center = "Center: ( " + x + " , " + y + " )  Radius: " + r;
			g2.drawString(center, 20, 35);
		}
	}

}
