package game.collider;

import game.model.AbstractGameObject;
import game.util.Point2D;
/**
 * Collider representing a line in space
 */
public class RayCollider implements Collider {

	private AbstractGameObject origin;
	private Point2D direction;
	/**
	 * Creates a new RayCollider of direction direction and origin at origin's position
	 * @param origin
	 * @param direction
	 */
	public RayCollider(final AbstractGameObject origin, final Point2D direction) {
		this.origin = origin;
		this.direction = Point2D.normalized(direction);
	}
	
	@Override
	public boolean checkCollision(CircleCollider player) {
		double xDist = player.getCenter().getX() - this.getOrigin().getX();
		if (this.direction.getX() == 0) {
			//vertical ray; calculate only the distance on x-axis
			return Math.abs(xDist) <= player.getRadius();
		} else {
			double m = this.direction.getY()/this.direction.getX();
			double yDist = player.getCenter().getY() - this.getOrigin().getY();
			//distance point to ray
			double distance = Math.abs(yDist - m*xDist)/Math.sqrt(1 + m*m);
			return distance <= player.getRadius();
		}
	}
	/**
	 * Gets the direction of this ray
	 * @return the direction
	 */
	public Point2D getDirection() {
		return this.direction;
	}
	/**
	 * Gets the origin of the ray
	 * @return the origin
	 */
	public Point2D getOrigin() {
		return this.origin.getPosition();
	}

}
