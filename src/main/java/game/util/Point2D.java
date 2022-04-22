package game.util;
/**
 * Represents a point or vector in a 2-dimensional space
 */
public final class Point2D {
        //never make these fields final
        private double x;
        private double y;

        /**
         * Creates a new point of coordinates (x, y)
         * @param x
         * @param y
         */
        public Point2D(final double x, final double y) {
                this.x = x;
                this.y = y;
        }

        /**
         * Creates a new point of coordinates (x, y). Can be called from a point to create another one.
         * @param x
         * @param y
         * @return a new point of coordinates (x, y)
         */
        public static Point2D of(final double x, final double y) {
                return new Point2D(x, y);
        }

	/**
	 * Creates a copy of a point.
	 * @param point to copy
	 * @return a new Point2D equal to point
	 */
	public static Point2D copyOf(final Point2D point) {
		return new Point2D(point.getX(), point.getY());
	}
	
	/**
	 * Gets the point opposite to the one specified.
	 * @param point
	 * @return a new Point2D opposite to point
	 */
	public static Point2D oppositeOf(final Point2D point) {
		final var p = Point2D.copyOf(point);
		p.mul(-1);
		return p;
	}
	
	/**
	 * Normalizes the given point.
	 * @param point
	 * @return a new Point2D equal to point with a magnitude of 1
	 */
	public static Point2D normalized(final Point2D point) {
		final var p = Point2D.copyOf(point);
		p.normalize();
		return p;
	}
	
	/**
	 * Calculates the distance between two points.
	 * @param pointA
	 * @param pointB
	 * @return the distance between pointA and pointB
	 */
	public static double distance(final Point2D pointA, final Point2D pointB) {
		final double x = pointB.getX() - pointA.getX();
		final double y = pointB.getY() - pointA.getY();
		return Math.sqrt(x * x + y * y);
	}
	
	/**
	 * Gets the x-axis coordinate.
	 * @return the x coordinate
	 */
	public double getX() {
		return this.x;
	}
	
	/**
	 * Gets the y-axis coordinate.
	 * @return the y coordinate
	 */
	public double getY() {
		return this.y;
	}
	
	/**
	 * Gets the magnitude of the vector (its distance from origin).
	 * @return the magnitude of this vector
	 */
	public double getMagnitude() {
		return Math.sqrt(this.x * this.x + this.y * this.y);
	}
	
	/**
	 * Sets the x coordinate to x.
	 * @param x
	 */
	public void setX(final double x) {
		this.x = x;
	}
	
	/**
	 * Sets the y coordinate to y.
	 * @param y
	 */
	public void setY(final double y) {
		this.y = y;
	}
	
	/**
	 * Adds point to this Point2D (Vector addition).
	 * @param point to add
	 */
	public void add(final Point2D point) {
		this.x += point.getX();
		this.y += point.getY();
	}
	
	/**
	 * Subtracts point to this Point2D (Vector addition).
	 * @param point to subtract
	 */
	public void sub(final Point2D point) {
		this.x -= point.getX();
		this.y -= point.getY();
	}
	
	/**
	 * Copied X and Y from another Point2D.
	 * @param point to set
	 */
	public void set(final Point2D point) {
		this.x = point.getX();
		this.y = point.getY();
	}
	
	/**
	 * Multiply this Point2D with scalar (Scalar multiplication).
	 * @param scalar to multiply
	 */
	public void mul(final double scalar) {
		this.x *= scalar;
		this.y *= scalar;
	}
	
	/**
	 * Divide this Point2D by scalar (Scalar multiplication) (Vector-Scalar division).
	 * @param scalar
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
	
	/**
	 * Returns a string representation of this object.
	 */
	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
}
