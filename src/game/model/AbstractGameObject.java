package game.model;

import game.util.Point2D;

public abstract class AbstractGameObject {
	
	private Point2D position;
	
	public AbstractGameObject(Point2D position) {
		this.position = position;
	}
	
	public Point2D getPosition() {
		return this.position;
	}
	
	public void setPosition(Point2D newPosition) {
		this.position = newPosition;
	}
}
