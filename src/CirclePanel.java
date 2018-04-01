import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.JPanel;

public class CirclePanel extends JPanel {

	private static final long serialVersionUID = -5661723336377390244L;

	public CirclePanel() {

		this.setPreferredSize(new Dimension(650, 600));
		this.setBackground(Color.WHITE);
		this.setName("Circle Panel");

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				PointList.points.add(new Point(e.getX(), e.getY()));
				Component component[] = CirclePanel.this.getParent().getComponents();
				Arrays.sort(component, new Comparator<Component>() {
					@Override
					public int compare(Component o1, Component o2) {
						return o1.getName().compareTo(o2.getName());
					}
				});
				for (Component comp : component) {
					comp.repaint();
				}
			}
		});
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.RED);
		if (PointList.points.size() <= 3) {
			for (Point point : PointList.points) {
				g2.fillOval((int) point.x, (int) (point.y), 5, 5);
				g2.drawString("(" + point.x + "," + (point.y) + ")", (int) point.x, (int) (point.y));
			}
		}
		if (PointList.points.size() == 3) {
			g2.setColor(Color.BLUE);
			CircleUtil util = new CircleUtil();
			Circle circle = util.getCircle(PointList.points.get(0), PointList.points.get(1), PointList.points.get(2));
			int centerX = (int) circle.getCenter().getX();
			int centerY = (int) circle.getCenter().getY();
			int radius = (int) circle.getRadius();
			g2.drawOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
		}
	}

}
