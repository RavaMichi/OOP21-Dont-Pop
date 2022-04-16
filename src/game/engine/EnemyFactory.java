package game.engine;

import game.model.AbstractGameObject;
import game.model.EnemyBallObj;
import game.model.EnemyBombObj;
import game.model.EnemyLineObj;
import game.model.EnemyProjectileObj;
import game.util.Point2D;
import game.util.WhereToSpawn;

public class EnemyFactory {
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
	float timeToDetonate = 1; // sec che poi puoi randomizzare //TEMPO DI DETONAZIONE DELLE ESPLOSIONI
	
	public EnemyFactory(final GameEngine ge) {
		this.gameEngine = ge;
	}

	public AbstractGameObject GetEnemyObj(final int n) {
		
		Point2D spawnPosition = wheretospawn.getEnemySpawnPoint(wheretospawn.getRandomSide());// PUNTO RANDOM IN CUI
		Point2D thornPosition=wheretospawn.getThornballSpawnPoint(wheretospawn.getThornballRandomSide());	
		// SPAWNARE
		Point2D spawnDir = gameEngine.getPlayerPosition();
		Point2D spawnBomb = wheretospawn.getPowerUPSpawnPoint();
		
		if (n == AbstractGameObject.ObjectType.BULLET.ordinal()) {
			return new EnemyProjectileObj(spawnPosition, spawnDir, (float) 0.02 , AbstractGameObject.ObjectType.BULLET, gameEngine); // dir � di 0.due decimi di schermo a chiamata

		} else if (n == AbstractGameObject.ObjectType.THORNBALL.ordinal()) {
			return new EnemyBallObj(thornPosition, AbstractGameObject.ObjectType.THORNBALL, gameEngine); // APETTARE CREAZIONE CLASSE

		} else if (n == AbstractGameObject.ObjectType.EXPLOSION.ordinal()) {

			// UTULIZZO DEL METODO wheretospawn.getPowerUPSpawnPoint(); perch� cos� spawna
			// dentro

			return new EnemyBombObj(spawnBomb, timeToDetonate, AbstractGameObject.ObjectType.EXPLOSION ,  gameEngine); // DA CAMBIARE PER SPAWNARE NELLO SCHERMO

		} else if (n == AbstractGameObject.ObjectType.LASER.ordinal()) {
			return new EnemyLineObj(spawnPosition, spawnDir,timeToDetonate, AbstractGameObject.ObjectType.LASER, gameEngine);// 5 � il tempo  di detonazione
		}
		return null; //da cambiare immagino

	}
}
