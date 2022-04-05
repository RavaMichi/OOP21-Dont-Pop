package game.collider;

/**
 * Interface containing the collision detection system
 */
public interface Collider {
	/**
	 * @param player
	 * @return whether this collider has collided with player
	 */
	boolean checkCollision(CircleCollider player);
	
}
