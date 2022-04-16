package game.collider;

import game.model.AbstractGameObject;
import game.util.Point2D;
/**
 * Collider with the shape of a circle
 */
public class CircleCollider implements Collider {

	private double radius;
	private AbstractGameObject object;
	private Point2D offset;
	/**
	 * @param parent
	 * @param radius
	 * Creates a new CircleCollider with radius radius linked to the gameObject parent
	 */
	public CircleCollider(final AbstractGameObject parent, final double radius, Point2D offset) {
		this.radius = radius;
		this.object = parent;
		this.offset = offset;
	}
	
	@Override
	public boolean checkCollision(CircleCollider player) {
		double distance = Point2D.distance(getCenter(), player.getCenter());
		return distance <= (this.radius + player.getRadius());
	}
	/**
	 * @return the radius of the circle
	 */
	public double getRadius() {
		return this.radius;
	}
	/**
	 * @return the center of this circle
	 */
	public Point2D getCenter() {
		var p = Point2D.copyOf(this.offset);
		p.add(this.object.getPosition());
		return p;
	}

}
