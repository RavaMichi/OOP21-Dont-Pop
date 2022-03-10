package game.util;
/**
 * Represents a point or vector in a 2-dimensional space
 */
public class Point2D {
	/**
	 * @param x
	 * @param y
	 * @return a new point of coordinates (x, y)
	 */
	public static Point2D of(final double x, final double y) {
		return new Point2D(x,y);
	}
	/**
	 * @param point to copy
	 * @return a new Point2D equal to point
	 */
	public static Point2D copyOf(final Point2D point) {
		return new Point2D(point.getX(), point.getY());
	}
	/**
	 * @param point
	 * @return a new Point2D opposite to point
	 */
	public static Point2D oppositeOf(final Point2D point) {
		var p = Point2D.copyOf(point);
		p.mul(-1);
		return p;
	}
	/**
	 * @param point
	 * @return a new Point2D equal to point with a magnitude of 1
	 */
	public static Point2D normalized(final Point2D point) {
		var p = Point2D.copyOf(point);
		p.normalize();
		return p;
	}
	/**
	 * @param pointA
	 * @param pointB
	 * @return the distance between pointA and pointB
	 */
	public static double distance(final Point2D pointA, final Point2D pointB) {
		double x = pointB.getX() - pointA.getX();
		double y = pointB.getY() - pointA.getY();
		return Math.sqrt(x*x + y*y);
	}
	
	private double x;
	private double y;
	/**
	 * @param x
	 * @param y
	 * Creates a new point of coordinates (x, y)
	 */
	public Point2D(final double x, final double y) {
		this.x = x;
		this.y = y;
	}
	/**
	 * @return the x coordinate
	 */
	public double getX() {
		return this.x;
	}
	/**
	 * @return the y coordinate
	 */
	public double getY() {
		return this.y;
	}
	/**
	 * @return the magnitude of this vector
	 */
	public double getMagnitude() {
		return Math.sqrt(this.x*this.x + this.y*this.y);
	}
	/**
	 * @param x
	 * Sets the x coordinate to x
	 */
	public void setX(double x) {
		this.x = x;
	}
	/**
	 * @param y
	 * Sets the y coordinate to y
	 */
	public void setY(double y) {
		this.y = y;
	}
	/**
	 * @param point to add
	 * Adds point to this Point2D (Vector addition)
	 */
	public void add(final Point2D point) {
		this.x += point.getX();
		this.y += point.getY();
	}
	/**
	 * @param point to subtract
	 * Subtracts point to this Point2D (Vector addition)
	 */
	public void sub(final Point2D point) {
		this.x -= point.getX();
		this.y -= point.getY();
	}
	/**
	 * @param scalar to multiply
	 * Multiply this Point2D with scalar (Scalar multiplication)
	 */
	public void mul(final double scalar) {
		this.x *= scalar;
		this.y *= scalar;
	}
	/**
	 * @param scalar
	 * Divide this Point2D by scalar (Scalar multiplication)
	 */
	public void div(final double scalar) {
		this.x /= scalar;
		this.y /= scalar;
	}
	/**
	 * Makes this vector have a magnitude of 1 (Normalization)
	 */
	public void normalize() {
		this.div(this.getMagnitude());
	}
	
	@Override
	public String toString() {
		return "("+this.x+", "+this.y+")";
	}
}
