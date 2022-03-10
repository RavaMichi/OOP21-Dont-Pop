package game.model;

import game.util.Point2D;
/**
 * 
 * Represents an object used by the game engine.
 * all the classes that extends this class can be instantiated and updated by the game engine
 *
 */
public abstract class AbstractGameObject {
	
	private Point2D position;
	/**
	 * @param position
	 * Creates a new game object in position
	 */
	public AbstractGameObject(Point2D position) {
		this.position = position;
	}
	/**
	 * This method is called by the Game Engine every frame to update the state
	 * of this game object.
	 * Must be implemented in subclasses.
	 */
	abstract void update();
	/**
	 * @return the Point2D object representing the spatial coordinates of this game object
	 */
	public Point2D getPosition() {
		return this.position;
	}
	/**
	 * @param newPosition
	 * Set the position of this game object to newPosition
	 */
	public void setPosition(Point2D newPosition) {
		this.position = newPosition;
	}
}
