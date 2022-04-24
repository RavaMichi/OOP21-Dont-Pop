package game.collider;

import game.model.AbstractGameObject;
import game.util.Point2D;
/**
 * Collider with the shape of a circle.
 */
public class CircleCollider implements Collider {

    private final double radius;
    private final AbstractGameObject object;
    private final Point2D offset;

    /**
	 * Creates a new CircleCollider with radius radius linked to the gameObject parent.
	 * @param parent
	 * @param radius
	 * @param offset
	 */
	public CircleCollider(final AbstractGameObject parent, final double radius, final Point2D offset) {
		this.radius = radius;
		this.object = parent;
		this.offset = offset;
	}
	
	/**
	 * Checks whether a collision has occurred with the player.
	 * @param player
	 */
	@Override
	public boolean checkCollision(final CircleCollider player) {
		final double distance = Point2D.distance(this.getCenter(), player.getCenter());
		return distance <= (this.radius + player.getRadius());
	}
	
	/**
	 * Gets the radius of the circle.
	 * @return the radius
	 */
	public double getRadius() {
		return this.radius;
	}
	
	/**
	 * Gets the center of this circle.
	 * @return the center
	 */
	public Point2D getCenter() {
		final var p = Point2D.copyOf(this.offset);
		p.add(this.object.getPosition());
		return p;
	}

}
