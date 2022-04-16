package game.engine;

import game.model.AbstractGameObject.ObjectType;

import java.util.Random;

import game.model.*;
import game.util.Point2D;
import game.util.WhereToSpawn;

public class EnemyFactory {
	
	private static final float LASER_DETONATION_TIME = 1.2f; 
	private static final float BULLET_VELOCITY = 0.3f;
	
	/*
	 * ATTENZIONE::::: AGGIUNGERE COSTRUTTORE DELLA THORNBALL CHE VARIA DA:
	 * 
	 * THORNBALL CE L'HANNO GIA
	 *
	 * 
	 */
	GameEngine gameEngine; //= GameEngine.getGameEngine(); // uso del metodo dalla definizione di classe per avere il
														// riferimento al game engine corrente
	WhereToSpawn wheretospawn = new WhereToSpawn();
	
	public EnemyFactory(final GameEngine ge) {
		this.gameEngine = ge;
	}

	public AbstractGameObject GetEnemyObj(final int n) {
		
		if (n == AbstractGameObject.ObjectType.BULLET.ordinal()) {
			return createBullet();
		} else if (n == AbstractGameObject.ObjectType.THORNBALL.ordinal()) {
			//return new EnemyBallObj(thornPosition, AbstractGameObject.ObjectType.THORNBALL, gameEngine); // APETTARE CREAZIONE CLASSE

		} else if (n == AbstractGameObject.ObjectType.EXPLOSION.ordinal()) {

			// UTULIZZO DEL METODO wheretospawn.getPowerUPSpawnPoint(); perch� cos� spawna
			// dentro

			//return new EnemyBombObj(spawnBomb, timeToDetonate, AbstractGameObject.ObjectType.EXPLOSION ,  gameEngine); // DA CAMBIARE PER SPAWNARE NELLO SCHERMO

		} else if (n == AbstractGameObject.ObjectType.LASER.ordinal()) {
			return createLaser();
		}
		return null; //da cambiare immagino

	}
	
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
}
