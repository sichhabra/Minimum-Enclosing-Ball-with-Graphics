public class Circle {

	Point center;
	double radius;

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

}
