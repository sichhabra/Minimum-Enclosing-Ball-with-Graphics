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
import java.util.List;

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
				Point p = new Point(e.getX(), e.getY());
				PointList.points.add(p);
				State.currentCircle = updateCircle(PointList.points, p);
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

	public Circle updateCircle(List<Point> points, Point p) {

		if (State.currentCircle != null && State.currentCircle.contains(p))
			return State.currentCircle;
		Circle c = null;
		for (int i = 0; i < points.size(); i++) {
			Point point = points.get(i);
			if (c == null || (!c.contains(point))) {
				c = makeCircleOnePoint(points.subList(0, i + 1), p);
			}
		}

		return c;
	}

	private static Circle makeCircleOnePoint(List<Point> points, Point p) {
		Circle c = new Circle(p, 0);
		for (int i = 0; i < points.size(); i++) {
			Point q = points.get(i);
			if (!c.contains(q)) {
				if (c.radius == 0D)
					c = makeDiameter(p, q);
				else
					c = makeCircleTwoPoints(points.subList(0, i + 1), p, q);
			}
		}
		return c;
	}

	static Circle makeDiameter(Point a, Point b) {
		Point c = new Point((a.x + b.x) / 2, (a.y + b.y) / 2);
		return new Circle(c, Math.max(c.distance(a), c.distance(b)));
	}

	private static Circle makeCircleTwoPoints(List<Point> points, Point p, Point q) {
		Circle circ = makeDiameter(p, q);
		Circle left = null;
		Circle right = null;

		// For each point not in the two-point circle
		Point pq = q.subtract(p);
		for (Point r : points) {
			if (circ.contains(r))
				continue;

			// Form a circumcircle and classify it on left or right side
			double cross = pq.cross(r.subtract(p));
			Circle c = CircleUtil.getCircle(p, q, r);
			if (c == null)
				continue;
			else if (cross > 0 && (left == null || pq.cross(c.center.subtract(p)) > pq.cross(left.center.subtract(p))))
				left = c;
			else if (cross < 0
					&& (right == null || pq.cross(c.center.subtract(p)) < pq.cross(right.center.subtract(p))))
				right = c;
		}

		// Select which circle to return
		if (left == null && right == null)
			return circ;
		else if (left == null)
			return right;
		else if (right == null)
			return left;
		else
			return left.radius <= right.radius ? left : right;
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
