package game.engine;

import game.model.AbstractGameObject.ObjectType;

import java.util.Random;

import game.model.*;
import game.util.Point2D;
import game.util.WhereToSpawn;


/**
 * A factory for creating Enemy objects using Factory method where we just select the enemy type
 * and the class creates it.
 */
public class EnemyFactory {
	
	/** The Constant LASER_DETONATION_TIME. */
	private static final float LASER_DETONATION_TIME = 1.2f;
	
	/** The Constant BULLET_VELOCITY. */
	private static final float BULLET_VELOCITY = 0.3f;
	
	/** The Constant EXPLOSION_DETONATION_TIME. */
	private static final float EXPLOSION_DETONATION_TIME = 2f;

	/** The spawn position. */
	Point2D spawnPosition;
	
	/** The direction. */
	Point2D direction;
	
	/** The game engine. */
	GameEngine gameEngine; 
	
	/** The wheretospawn. */
	WhereToSpawn wheretospawn = new WhereToSpawn();
	
	/**
	 * Instantiates a new enemy factory.
	 *
	 * @param ge the ge
	 */
	public EnemyFactory(final GameEngine gameengine) {
		
		this.gameEngine = gameengine;
	}

	/**
	 * Gets the enemy using factory method with the enemy type.
	 *
	 * @param type the type
	 * @return the abstract game object of a enemy
	 */
	public AbstractGameObject GetEnemyObj(final ObjectType type) {
		
		ObjectType objectTyper=type;
		
		if (objectTyper == ObjectType.BULLET) {
			
			spawnPosition = wheretospawn.getEnemySpawnPoint(wheretospawn.getRandomSide());
			direction = Point2D.copyOf(this.gameEngine.getPlayerPosition());
			direction.sub(spawnPosition);
			return new EnemyProjectileObj(spawnPosition, direction, BULLET_VELOCITY , ObjectType.BULLET, gameEngine);
			
		} else if (objectTyper == ObjectType.THORNBALL) {
			
			spawnPosition = wheretospawn.getEnemySpawnPoint(wheretospawn.getThornballRandomSide());
			return new EnemyBallObj(spawnPosition, ObjectType.THORNBALL, gameEngine);
		
		} else if (objectTyper == ObjectType.EXPLOSION) {

			var r = new Random();
			Point2D spawnPosition = new Point2D(r.nextDouble(), r.nextDouble()); //(0, 0) -> (1, 1)
			return new EnemyBombObj(spawnPosition, EXPLOSION_DETONATION_TIME, ObjectType.EXPLOSION, gameEngine);
			
		} else if (objectTyper == ObjectType.LASER) {
			
			var r = new Random();
			spawnPosition = new Point2D(r.nextDouble(), r.nextDouble()); //(0, 0) -> (1, 1)
			direction = new Point2D(r.nextDouble()*2 - 1, r.nextDouble()*2 - 1); //(-1, -1) -> (1, 1)
			return new EnemyLineObj(spawnPosition, direction, LASER_DETONATION_TIME, ObjectType.LASER, gameEngine);
		}
		//mettere new exception e creare classe per il test 
		return new EnemyProjectileObj(spawnPosition, direction, BULLET_VELOCITY , ObjectType.BULLET, gameEngine);
	}

}
