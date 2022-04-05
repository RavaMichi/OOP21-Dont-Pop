package game.engine;
import java.awt.geom.Point2D;

public class EnemyFactory {
	/*
	 * ATTENZIONE::::: AGGIUNGERE COSTRUTTORE DELLA THORNBALL CHE VARIA DA:
	 * 
	 * THORNBALL CE L'HANNO GIA
	 *
	 * 
	 */
	GameEngine gameEngine = GameEngine.getGameEngine(); // uso del metodo dalla definizione di classe per avere il
														// riferimento al game engine corrente
	WhereToSpawn wheretospawn = new WhereToSpawn();
	Point2D spawnPosition = wheretospawn.getEnemySpawnPoint(wheretospawn.getRandomSide());// PUNTO RANDOM IN CUI
																							// SPAWNARE
	Point2D spawnDir = gameEngine.getPlayerPosition();
	Point2D spawnBomb = wheretospawn.getPowerUPSpawnPoint();

	float timeToDetonate = 1; // sec che poi puoi randomizzare //TEMPO DI DETONAZIONE DELLE ESPLOSIONI

	public AbstractGameObject GetEnemyObj(final int n) {

		if (n == AbstractGameObject.ObjectType.BULLET.ordinal()) {
			return new EnemyProjectileObj(spawnPosition, spawnDir, AbstractGameObject.ObjectType.BULLET, gameEngine); // OK

		} else if (n == AbstractGameObject.ObjectType.THORNBALL.ordinal()) {
			return new Thornball(spawnPosition); // APETTARE CREAZIONE CLASSE

		} else if (n == AbstractGameObject.ObjectType.EXPLOSION.ordinal()) {

			// UTULIZZO DEL METODO wheretospawn.getPowerUPSpawnPoint(); perch� cos� spawna
			// dentro

			return new EnemyBombObj(spawnBomb, timeToDetonate, gameEngine); // DA CAMBIARE PER SPAWNARE NELLO SCHERMO

		} else if (n == AbstractGameObject.ObjectType.LASER.ordinal()) {
			return new EnemyLineObj(spawnPosition, spawnDir, AbstractGameObject.ObjectType.LASER, gameEngine);// OK
		}

	}
}
