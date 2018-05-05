import java.util.List;

public class CircleUtil {

	public static Circle getCircle(Point p1, Point p2, Point p3) {

		double a = p2.getX() - p1.getX();
		double b = p2.getY() - p1.getY();
		double c = p3.getX() - p1.getX();
		double d = p3.getY() - p1.getY();

		double e = a * (p1.getX() + p2.getX()) + b * (p1.getY() + p2.getY());
		double f = c * (p1.getX() + p3.getX()) + d * (p1.getY() + p3.getY());
		double g = 2 * (a * (p3.getY() - p2.getY()) - b * (p3.getX() - p2.getX()));

		if (g == 0)
			return null;

		double centerX = (d * e - b * f) / g;
		double centerY = (a * f - c * e) / g;
		double radius = Math
				.sqrt((p1.getX() - centerX) * (p1.getX() - centerX) + (p1.getY() - centerY) * (p1.getY() - centerY));
		Circle circle = new Circle(new Point(centerX, centerY), radius);
		return circle;

	}

	public static Circle updateCircle(List<Point> points, Point p) {

		if (State.currentCircle == null) {
			return new Circle(p, 0);
		}

		else if (State.currentCircle != null && State.currentCircle.contains(p)) {
			NotificationMessage.index = 0;
			return State.currentCircle;
		}

		NotificationMessage.index = 1;
		Circle c = new Circle(p, 0);

		if (!c.contains(p) && c.radius == 0D) {
			State.p1=p;
			c = CircleUtil.getCircle(c.center, p);
		} else {
			for (int i = 0; i < points.size(); i++) {
				Point q = points.get(i);
				if (!c.contains(q)) {
					if (c.radius == 0D) {
						c = CircleUtil.getCircle(p, q);
						State.p1=p;
						State.p2=q;
					} else {
						c = randomizedIncremental(points.subList(0, i + 1), p, q);
					}
				}
			}
		}
		return c;
	}

	//Used https://www.nayuki.io/page/smallest-enclosing-circle.
	
	private static Circle randomizedIncremental(List<Point> points, Point p, Point q) {
		Circle circ = CircleUtil.getCircle(p, q);
		State.p1 = p;
		State.p2 = q;
		Circle left = null;
		Circle right = null;

		Point pq = q.subtract(p);
		Point l_r = null, r_r = null;
		for (Point r : points) {
			if (circ.contains(r))
				continue;

			double cross = pq.cross(r.subtract(p));
			Circle c = CircleUtil.getCircle(p, q, r);
			State.p1 = p;
			State.p2 = q;
			State.p3 = r;
			if (c == null)
				continue;
			else if (cross > 0
					&& (left == null || pq.cross(c.center.subtract(p)) > pq.cross(left.center.subtract(p)))) {
				left = c;
				l_r = r;
			} else if (cross < 0
					&& (right == null || pq.cross(c.center.subtract(p)) < pq.cross(right.center.subtract(p)))) {
				right = c;
				r_r = r;
			}
		}

		if (left == null && right == null) {
			return circ;
		} else if (left == null) {
			State.p1 = p;
			State.p2 = q;
			State.p3 = r_r;
			return right;
		} else if (right == null) {
			State.p1 = p;
			State.p2 = q;
			State.p3 = l_r;
			return left;
		} else {
			if (left.radius <= right.radius) {
				State.p1 = p;
				State.p2 = q;
				State.p3 = l_r;
				return left;
			} else {
				State.p1 = p;
				State.p2 = q;
				State.p3 = r_r;
				return right;
			}
		}
	}

	public static Circle getCircle(Point a, Point b) {
		Point c = new Point((a.x + b.x) / 2.0, (a.y + b.y) / 2.0);
		return new Circle(c, Math.max(c.distance(a), c.distance(b)));
	}

}
