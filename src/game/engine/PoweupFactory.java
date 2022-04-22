package game.engine;
import game.util.Point2D;
import game.util.RandomInt;
import game.model.PowerUpObj; //classe power up da mettere public

import game.model.AbstractGameObject;
import game.model.AbstractGameObject.ObjectType;
import game.util.WhereToSpawn;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating powerup objects using Factory method where it use a random type of powerup
 * and the class creates it.
 */
public class PoweupFactory {
	
	/** The game engine. */
	private GameEngine gameEngine; //da passare al costruttore
	
	/** The wheretospawn. */
	private WhereToSpawn wheretospawn = new WhereToSpawn();
	
	/** The spawn position. */
	private Point2D spawnPosition = wheretospawn.getPowerUPSpawnPoint();//PUNTO RANDOM ALL'INTERNO DELLA MAPPA 
	
	/**
	 * Instantiates a new poweup factory.
	 *
	 * @param gameEngine2 the game engine
	 */
	public PoweupFactory(GameEngine gameEngine2) {
		this.gameEngine=gameEngine2;
	}

	/**
	 * Instantiates a new poweup factory.
	 *
	 * @return the abstract game object
	 */
	
	public AbstractGameObject GetPowerUpObj()  {
		
		var pos = Point2D.of(Math.random()*0.6 + 0.2, Math.random()*0.6 + 0.2);
		Integer typeOfPowerUp = new RandomInt().getRandomInt(6, 8);
		if (typeOfPowerUp == 6) {
			createShield();
		} else if (typeOfPowerUp == 7) {
		    createMultiplier();
		} else {
			createSweeper();
		}
	}
	
	/**
	 * Creates a new Poweup object Shield.
	 *
	 * @return the power up obj
	 */
	public PowerUpObj createShield() {
		
		returnnew PowerUpObj(pos, ObjectType.PWRUP_SHIELD, gameEngine);
		}
	
/**
 * Creates a new Poweup object Multiplier.
 *
 * @return the power up obj
 */
public PowerUpObj createMultiplier() {
		
	return new PowerUpObj(pos, ObjectType.PWRUP_MULTIPLIER, gameEngine);
		}

/**
 * Creates a new Poweup object Sweeper.
 *
 * @return the power up obj
 */
public PowerUpObj createSweeper() {
	return new PowerUpObj(pos, ObjectType.PWRUP_SWEEPER, gameEngine);
		}

}
