package game.engine;

import game.model.AbstractGameObject;
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
	Point2D spawnPosition = wheretospawn.getEnemySpawnPoint(wheretospawn.getRandomSide());// PUNTO RANDOM IN CUI
																							// SPAWNARE
	Point2D spawnDir = gameEngine.getPlayerPosition();
	Point2D spawnBomb = wheretospawn.getPowerUPSpawnPoint();

	float timeToDetonate = 1; // sec che poi puoi randomizzare //TEMPO DI DETONAZIONE DELLE ESPLOSIONI

	public AbstractGameObject GetEnemyObj(final int n) {

		if (n == AbstractGameObject.ObjectType.BULLET.ordinal()) {
			return new EnemyProjectileObj(spawnPosition, spawnDir, (float) 0.02 , AbstractGameObject.ObjectType.BULLET, gameEngine); // dir è di 0.due decimi di schermo a chiamata

		} else if (n == AbstractGameObject.ObjectType.THORNBALL.ordinal()) {
			return new Thornball(spawnPosition); // APETTARE CREAZIONE CLASSE

		} else if (n == AbstractGameObject.ObjectType.EXPLOSION.ordinal()) {

			// UTULIZZO DEL METODO wheretospawn.getPowerUPSpawnPoint(); perchï¿½ cosï¿½ spawna
			// dentro

			return new EnemyBombObj(spawnBomb, timeToDetonate, AbstractGameObject.ObjectType.EXPLOSION ,  gameEngine); // DA CAMBIARE PER SPAWNARE NELLO SCHERMO

		} else if (n == AbstractGameObject.ObjectType.LASER.ordinal()) {
			return new EnemyLineObj(spawnPosition, spawnDir,timeToDetonate, AbstractGameObject.ObjectType.LASER, gameEngine);// 5 è il tempo  di detonazione
		}

	}
}
