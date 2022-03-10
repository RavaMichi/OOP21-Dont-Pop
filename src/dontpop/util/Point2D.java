package dontpop.util;

import progettoOOP.util.Point2D;

public class Point2D {

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
	
	@Override
	public String toString() {
		return "("+this.x+", "+this.y+")";
	}
}
