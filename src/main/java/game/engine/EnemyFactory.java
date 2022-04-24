package game.engine;

import game.model.AbstractGameObject.ObjectType;

import java.util.Random;

import game.model.*;
import game.util.Point2D;
import game.util.WhereToSpawn;

/**
 * A factory for creating Enemy objects using Factory method where we just
 * select the enemy type and the class creates it.
 */
public class EnemyFactory {

	/** The Constant LASER_DETONATION_TIME. */
	private static final float LASER_DETONATION_TIME = 1.2f;

	/** The Constant BULLET_VELOCITY. */
	private static final float BULLET_VELOCITY = 0.3f;

	/** The Constant EXPLOSION_DETONATION_TIME. */
	private static final float EXPLOSION_DETONATION_TIME = 2f;

	/** The spawn position. */
	private Point2D spawnPosition;

	/** The direction. */
	private Point2D direction;

	/** The game engine. */
	private final GameEngine gameEngine;

	/** The wheretospawn. */
	private final WhereToSpawn wheretospawn = new WhereToSpawn();

	/**
	 * Instantiates a new enemy factory.
	 *
	 * @param gameEngine - the game engine
	 */
	public EnemyFactory(final GameEngine gameEngine) {

		this.gameEngine = gameEngine;
	}

	/**
	 * Gets the enemy using factory method with the enemy type.
	 *
	 * @param type - the type
	 * @return the abstract game object of a enemy
	 */
	public AbstractGameObject getEnemyObj(final ObjectType type) {

		final ObjectType objectTyper = type;

		if (objectTyper == ObjectType.BULLET) {

			return this.createBullet();

		} else if (objectTyper == ObjectType.THORNBALL) {

			return this.createThornball();

		} else if (objectTyper == ObjectType.EXPLOSION) {

			return this.createExplosion();

		} else if (objectTyper == ObjectType.LASER) {

			return this.createLaser();
		}
		// default enemy
		return this.createBullet();
	}

	/**
	 * Creates a new Enemy object Bullet.
	 *
	 * @return the abstract game object
	 */
	public AbstractGameObject createBullet() {
		this.spawnPosition = this.wheretospawn.getEnemySpawnPoint(this.wheretospawn.getRandomSide());
		this.direction = Point2D.copyOf(this.gameEngine.getPlayerPosition());
		this.direction.sub(this.spawnPosition);
		return new EnemyProjectileObj(this.spawnPosition, this.direction, BULLET_VELOCITY, ObjectType.BULLET, this.gameEngine);
	}

	/**
	 * Creates a new Enemy object Laser.
	 *
	 * @return the abstract game object
	 */
	public AbstractGameObject createLaser() {
		final var r = new Random();
		this.spawnPosition = new Point2D(r.nextDouble(), r.nextDouble()); // (0, 0) -> (1, 1)
		this.direction = new Point2D(r.nextDouble() * 2 - 1, r.nextDouble() * 2 - 1); // (-1, -1) -> (1, 1)
		return new EnemyLineObj(this.spawnPosition, this.direction, LASER_DETONATION_TIME, ObjectType.LASER, this.gameEngine);
	}

	/**
	 * Creates a new Enemy object Thornball.
	 *
	 * @return the abstract game object
	 */
	public AbstractGameObject createThornball() {
		this.spawnPosition = this.wheretospawn.getEnemySpawnPoint(this.wheretospawn.getThornballRandomSide());
		return new EnemyBallObj(this.spawnPosition, ObjectType.THORNBALL, this.gameEngine);
	}

	/**
	 * Creates a new Enemy object EXPLOSION.
	 *
	 * @return the abstract game object
	 */
	public AbstractGameObject createExplosion() {
		final var r = new Random();
		this.spawnPosition = new Point2D(r.nextDouble(), r.nextDouble()); // (0, 0) -> (1, 1)
		return new EnemyBombObj(this.spawnPosition, EXPLOSION_DETONATION_TIME, ObjectType.EXPLOSION, this.gameEngine);
	}
}
