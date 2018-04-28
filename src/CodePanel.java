import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class CodePanel extends JPanel {

	private static final long serialVersionUID = -8598904166396947414L;

	public CodePanel() {
		this.setPreferredSize(new Dimension(500, 600));
		this.setBackground(new Color(230, 230, 230));
		this.setName("Code Panel");
	}
	
	public boolean check(Point p) {
		return (p.equals(State.p1)||p.equals(State.p2)||p.equals(State.p3));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
		int x_index = 15, y_index = 20;
		for (Point p : PointList.points) {
			if(check(p)) {
				g2.setColor(Color.RED);
				g2.drawRoundRect(x_index-4, y_index-17, 112, 25, 20, 20);
			}
			g2.setColor(Color.BLACK);
			g2.drawString("(" + p.getX() + "," + p.getY() + ")", x_index, y_index);
			x_index += 120;
			if (x_index >= 400) {
				y_index += 30;
				x_index = 15;
			}
		}
	}
}
