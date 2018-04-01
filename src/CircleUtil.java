public class CircleUtil {

	public Circle getCircle(Point p1, Point p2, Point p3) {

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

}
