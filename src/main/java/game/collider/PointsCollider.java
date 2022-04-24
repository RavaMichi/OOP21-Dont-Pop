package game.collider;

import java.util.Set;

import game.model.AbstractGameObject;
import game.util.Point2D;

/**
 * Collider represented by a set of points.
 */
public class PointsCollider implements Collider {

	private final Set<Point2D> points;
	private final AbstractGameObject object;
	
	/**
	 * Creates a new Collider for parent, using points.
	 * The points are their relative position from the position of the parent
	 * @param parent
	 * @param points - in world coordinates
	 */
	public PointsCollider(final AbstractGameObject parent, final Set<Point2D> points) {
		this.object = parent;
		this.points = points;
	}
	
	/**
	 * Checks whether a collision has occurred with the player.
	 * @param player
	 */
	@Override
	public boolean checkCollision(final CircleCollider player) {
		for (final Point2D p : this.points) {
			final var newP = Point2D.copyOf(this.object.getPosition());
			newP.add(p);
			if (Point2D.distance(newP, player.getCenter()) <= player.getRadius()) {
				return true;
			}
		}
		return false;
	}

}
