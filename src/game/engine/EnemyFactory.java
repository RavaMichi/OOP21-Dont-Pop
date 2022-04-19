package game.engine;

import game.model.AbstractGameObject.ObjectType;

import java.util.Random;

import game.model.*;
import game.util.Point2D;
import game.util.WhereToSpawn;

public class EnemyFactory {
	
	private static final float LASER_DETONATION_TIME = 1.2f;
	private static final float BULLET_VELOCITY = 0.3f;
	private static final float EXPLOSION_DETONATION_TIME = 2f;

	Point2D spawnPosition;
	Point2D direction;
	GameEngine gameEngine; 
	WhereToSpawn wheretospawn = new WhereToSpawn();
	
	public EnemyFactory(final GameEngine ge) {
		
		this.gameEngine = ge;
	}

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
		

	}
	
/*
public AbstractGameObject createBullet() {
		Point2D spawnPosition = wheretospawn.getEnemySpawnPoint(wheretospawn.getRandomSide());
		Point2D direction = Point2D.copyOf(this.gameEngine.getPlayerPosition());
		direction.sub(spawnPosition);
		return new EnemyProjectileObj(spawnPosition, direction, BULLET_VELOCITY , ObjectType.BULLET, gameEngine);
	}
	public AbstractGameObject createLaser() {
		var r = new Random();
		Point2D spawnPosition = new Point2D(r.nextDouble(), r.nextDouble()); //(0, 0) -> (1, 1)
		Point2D direction = new Point2D(r.nextDouble()*2 - 1, r.nextDouble()*2 - 1); //(-1, -1) -> (1, 1)
		return new EnemyLineObj(spawnPosition, direction, LASER_DETONATION_TIME, ObjectType.LASER, gameEngine);
	}
	public AbstractGameObject createThornball() {
		Point2D spawnPosition = wheretospawn.getEnemySpawnPoint(wheretospawn.getThornballRandomSide());
		return new EnemyBallObj(spawnPosition, ObjectType.THORNBALL, gameEngine);
	}
	public AbstractGameObject createExplosion() {
		var r = new Random();
		Point2D spawnPosition = new Point2D(r.nextDouble(), r.nextDouble()); //(0, 0) -> (1, 1)
		return new EnemyBombObj(spawnPosition, EXPLOSION_DETONATION_TIME, ObjectType.EXPLOSION, gameEngine);
	}	
	*/
}
