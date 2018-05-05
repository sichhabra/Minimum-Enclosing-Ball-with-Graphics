import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.JPanel;
import javax.swing.Timer;

public class CirclePanel extends JPanel {

	private static final long serialVersionUID = -5661723336377390244L;

	ActionListener taskPerformer = new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			double rand_x = (Math.random() * 640.0) + 4.0;
			double rand_y = (Math.random() * 578.0) + 6.0;
			rand_x = BigDecimal.valueOf(rand_x).setScale(3, RoundingMode.HALF_UP).doubleValue();
			rand_y = BigDecimal.valueOf(rand_y).setScale(3, RoundingMode.HALF_UP).doubleValue();
			Point p = new Point(rand_x, rand_y);
			PointList.points.add(p);
			State.currentCircle = CircleUtil.updateCircle(PointList.points, p);
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
	};

	public CirclePanel() {

		this.setPreferredSize(new Dimension(650, 600));
		this.setBackground(Color.WHITE);
		this.setName("Circle Panel");

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//new Timer(100, taskPerformer).start();
				Point p = new Point(e.getX(), e.getY());
				PointList.points.add(p);
				State.currentCircle = CircleUtil.updateCircle(PointList.points, p);
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
		for (Point point : PointList.points) {
			g2.fillOval((int) point.x, (int) (point.y), 5, 5);
			g2.drawString("(" + point.x + "," + (point.y) + ")", (int) point.x, (int) (point.y));
		}

		Circle circle = State.currentCircle;
		if (circle != null) {
			g2.setColor(Color.BLUE);
			int centerX = (int) circle.getCenter().getX();
			int centerY = (int) circle.getCenter().getY();
			int radius = (int) circle.getRadius();
			g2.drawOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
		}
	}

}
