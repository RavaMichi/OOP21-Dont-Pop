package game.collider;

import java.util.HashSet;
import java.util.Set;

import game.model.AbstractGameObject;
import game.util.Point2D;

/**
 * Collider represented by a set of points.
 */
public class PointsCollider implements Collider {

	private Set<Point2D> points = new HashSet<>();
	private AbstractGameObject object;
	
	/**
	 * @param parent
	 * @param points - in world coordinates
	 * Creates a new Collider for parent, using points.
	 * The points are their relative position from the position of the parent
	 */
	public PointsCollider(final AbstractGameObject parent, Set<Point2D> points) {
		this.object = parent;
		this.points = points;
	}
	
	@Override
	public boolean checkCollision(CircleCollider player) {
		for (Point2D p : points) {
			var newP = Point2D.copyOf(this.object.getPosition());
			newP.add(p);
			if (Point2D.distance(newP, player.getCenter()) <= player.getRadius()) {
				return true;
			}
		}
		return false;
	}

}
