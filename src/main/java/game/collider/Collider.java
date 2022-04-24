package game.collider;

/**
 * Interface containing the collision detection system.
 */
public interface Collider {
	/**
	 * Method used for detecting the collisions with the player.
	 * @param player collider
	 * @return whether this collider has collided with player
	 */
	boolean checkCollision(CircleCollider player);
	
}
