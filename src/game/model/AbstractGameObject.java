package game.model;

import game.collider.Collider;
import game.engine.GameEngine;
import game.graphics.Renderer;
import game.util.Point2D;

/**
 * 
 * Represents an object used by the game engine. all the classes that extends
 * this class can be instantiated and updated by the game engine
 *
 */
public abstract class AbstractGameObject {
	
    /**
     * Represents the type of the game object Has functions to check the type of a
     * game object (AbstractGameObject).
     */
    public enum ObjectType {
        PLAYER,                                         // player
        BULLET, LASER, THORNBALL, EXPLOSION,            // enemies
        PWRUP_SHIELD, PWRUP_MULTIPLIER, PWRUP_SWEEPER,  // power ups
        SCORE;

        /**
         * Checks whether this object is a player.
         * @return true if object is a player
         */
        public boolean isPlayer() {
            return this == PLAYER;
        }

        /**
         * Checks whether this object is an enemy.
         * @return true if object is an enemy
         */
        public boolean isEnemy() {
            return this == BULLET || this == LASER || this == THORNBALL || this == EXPLOSION;
        }

        /**
         * Checks whether this object is a powerup.
         * @return true if object is a powerup
         */
        public boolean isPowerUp() {
            return this == PWRUP_SHIELD || this == PWRUP_MULTIPLIER || this == PWRUP_SWEEPER || this == SCORE;
        }
    }

    private Point2D position;
    private final ObjectType type;
    private Collider collider;
    private Renderer renderer;
    private final GameEngine gameEngine;

    /**
     * Creates a new game object in position <position>.
     * @param position
     * @param type
     * @param gameEngine 
     */
    public AbstractGameObject(final Point2D position, final ObjectType type, final GameEngine gameEngine) {
        this.position = position;
        this.type = type;
        this.gameEngine = gameEngine;
    }

    /**
     * This method is called by the Game Engine every frame to update the state of
     * this game object. Must be implemented in subclasses.
     */
    public abstract void update();

    /**
     * Gets the Point2D object representing the spatial coordinates of this game object.
     * @return position
     */
    public Point2D getPosition() {
        return this.position;
    }

    /**
     * Set the position of this game object to newPosition.
     * @param newPosition
     */
    public void setPosition(final Point2D newPosition) {
        this.position = newPosition;
    }

    /**
     * Set the collider of this game object.
     * @param collider
     */
    public void setCollider(final Collider collider) {
        this.collider = collider;
    }

    /**
     * Gets the collider of this game object.
     * @return the collider of this game object
     */
    public Collider getCollider() {
        return this.collider;
    }

    /**
     * Set the renderer of this game object.
     * @param renderer
     */
    public void setRenderer(final Renderer renderer) {
        this.renderer = renderer;
    }

    /**
     * Gets the renderer of this game object.
     * @return the renderer of this game object
     */
    public Renderer getRenderer() {
        return this.renderer;
    }

    /**
     * Gets the type of this game object.
     * @return the type of this game object
     */
    public ObjectType getType() {
        return this.type;
    }

    /**
     * Removes this game object from the game scene. 
     * It is managed by the GameEngine class.
     */
    public void destroy() {
        this.gameEngine.destroy(this);
    }

    /**
     * Gets GameEngine object (which instantiated this object).
     * @return game engine
     */
    public GameEngine getGameEngine() {
    	return this.gameEngine;
    }
}
