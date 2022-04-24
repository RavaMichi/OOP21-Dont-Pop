package game.collider;

import game.model.AbstractGameObject;
import game.util.Point2D;
/**
 * Collider representing a line in space.
 */
public class RayCollider implements Collider {

    private final AbstractGameObject origin;
    private final Point2D direction;
    /**
     * Creates a new RayCollider of direction direction and origin at origin's position.
	 * @param origin
	 * @param direction
	 */
	public RayCollider(final AbstractGameObject origin, final Point2D direction) {
		this.origin = origin;
		this.direction = Point2D.normalized(direction);
	}
	
	/**
	 * Checks whether a collision has occurred with the player.
	 * @param player
	 */
	@Override
	public boolean checkCollision(final CircleCollider player) {
		final double xDist = player.getCenter().getX() - this.getOrigin().getX();
		if (this.direction.getX() == 0) {
			//vertical ray; calculate only the distance on x-axis
			return Math.abs(xDist) <= player.getRadius();
		} else {
			final double m = this.direction.getY() / this.direction.getX();
			final double yDist = player.getCenter().getY() - this.getOrigin().getY();
			//distance point to ray
			final double distance = Math.abs(yDist - m * xDist) / Math.sqrt(1 + m * m);
			return distance <= player.getRadius();
		}
	}
	/**
	 * Gets the direction of this ray.
	 * @return the direction
	 */
	public Point2D getDirection() {
		return this.direction;
	}
	/**
	 * Gets the origin of the ray.
	 * @return the origin
	 */
	public Point2D getOrigin() {
		return this.origin.getPosition();
	}

}
