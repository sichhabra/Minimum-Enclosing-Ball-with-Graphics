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

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.WHITE);
		g2.setFont(new Font("Helvetica Neue", Font.PLAIN, 10));
		int x_index = 10, y_index = 10;
		for (Point p : PointList.points) {
			g2.drawString("(" + p.getX() + "," + p.getY() + ")", x_index, y_index);
			x_index += 70;
			if (x_index >= 600) {
				y_index += 10;
				x_index = 0;
			}
		}
	}

}
