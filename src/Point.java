public class Point {

	double x, y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
	public double distance(Point a) {
		return Math.hypot(x - a.x, y - a.y);
	}

	public Point mid(Point a) {
		return new Point((this.x + a.x) / 2.0D, (this.y + a.y) / 2.0D);
	}

	public double cross(Point p) {
		return x * p.y - y * p.x;
	}
	
	public Point subtract(Point a) {
		return new Point(this.x-a.x,this.y-a.y);
	}

}
