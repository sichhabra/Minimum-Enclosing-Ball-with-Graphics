import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class NotificationPanel extends JPanel {

	private static final long serialVersionUID = -2998074213804040713L;

	public NotificationPanel() {
		this.setPreferredSize(new Dimension(500, 50));
		this.setBackground(new Color(200, 200, 200));
		this.setName("Notification Panel");
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.BLACK);
		g2.setFont(new Font("Helvetica Neue", Font.PLAIN, 25));
		if (PointList.points.size() == 3)
			NotificationMessage.index = 1;
		g2.drawString(NotificationMessage.message[NotificationMessage.index], 200, 35);
	}

}
