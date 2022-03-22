package game.model;

import game.controller.GameEngine;
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
     * game object (AbstractGameObject)
     */
    public enum ObjectType {
        PLAYER,                                         // player
        BULLET, LASER, THORNBALL, EXPLOSION,            // enemies
        PWRUP_SHIELD, PWRUP_MULTIPLIER, PWRUP_SWEEPER;  // power ups

        /**
         * @return true if object is a player
         */
        public boolean isPlayer() {
            return this == PLAYER;
        }

        /**
         * @return true if object is an enemy
         */
        public boolean isEnemy() {
            return this == BULLET || this == LASER || this == THORNBALL || this == EXPLOSION;
        }

        /**
         * @return true if object is a powerup
         */
        public boolean isPowerUp() {
            return this == PWRUP_SHIELD || this == PWRUP_MULTIPLIER || this == PWRUP_SWEEPER;
        }
    }

    private Point2D position;
    private final ObjectType type;
    private Collider collider = null;
    private Renderer renderer = null;
    private final GameEngine gameEngine;

    /**
     * @param position
     * @param type
     * @param gameEngine Creates a new game object in position
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
    abstract void update();

    /**
     * @return the Point2D object representing the spatial coordinates of this game
     *         object
     */
    public Point2D getPosition() {
        return this.position;
    }

    /**
     * @param newPosition Set the position of this game object to newPosition
     */
    public void setPosition(final Point2D newPosition) {
        this.position = newPosition;
    }

    /**
     * @param coll Set the collider of this game object
     */
    public void setCollider(final Collider coll) {
        this.collider = coll;
    }

    /**
     * @return the collider of this game object
     */
    public Collider getCollider() {
        return this.collider;
    }

    /**
     * @param rend Set the renderer of this game object
     */
    public void setRenderer(final Renderer rend) {
        this.renderer = rend;
    }

    /**
     * @return the renderer of this game object
     */
    public Renderer getRenderer() {
        return this.renderer;
    }

    /**
     * @return the type of this game object
     */
    public ObjectType getType() {
        return this.type;
    }

    /**
     * Removes this game object from the game scene. It is managed by the GameEngine
     * class
     */
    public void destroy() {
        this.gameEngine.destroy(this);
    }
}
