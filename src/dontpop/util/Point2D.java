package dontpop.util;

public class Point2D {

	public static Point2D of(final double x, final double y) {
		return new Point2D(x,y);
	}
	
	public static Point2D copyOf(final Point2D point) {
		return new Point2D(point.getX(), point.getY());
	}
	
	public static Point2D oppositeOf(final Point2D point) {
		var p = Point2D.copyOf(point);
		p.mul(-1);
		return p;
	}
	
	public static Point2D normalized(final Point2D point) {
		var p = Point2D.copyOf(point);
		p.normalize();
		return p;
	}
	
	public static double distance(final Point2D pointA, final Point2D pointB) {
		double x = pointB.getX() - pointA.getX();
		double y = pointB.getY() - pointA.getY();
		return Math.sqrt(x*x + y*y);
	}
	
	private double x;
	private double y;
	
	public Point2D(final double x, final double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public double getMagnitude() {
		return Math.sqrt(this.x*this.x + this.y*this.y);
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void add(final Point2D point) {
		this.x += point.getX();
		this.y += point.getY();
	}
	
	public void sub(final Point2D point) {
		this.x -= point.getX();
		this.y -= point.getY();
	}
	
	public void mul(final double scalar) {
		this.x *= scalar;
		this.y *= scalar;
	}
	
	public void div(final double scalar) {
		this.x /= scalar;
		this.y /= scalar;
	}
	
	public void normalize() {
		this.div(this.getMagnitude());
	}
	
	@Override
	public String toString() {
		return "("+this.x+", "+this.y+")";
	}
}
