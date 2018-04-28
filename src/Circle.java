public class Circle {

	Point center;
	double radius;
	
	private static final double MULTIPLICATIVE_EPSILON = 1 + 1e-14;

	public Circle(Point center, double radius) {
		this.center = center;
		this.radius = radius;
	}

	public Point getCenter() {
		return center;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	@Override
	public String toString() {
		return (center.getX() + ":" + center.getY() + ":" + radius);
	}

	public boolean contains(Point p) {
		return (center.distance(p) <= (radius*MULTIPLICATIVE_EPSILON));
	}

}
