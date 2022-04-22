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
		
		var pos = this.wheretospawn.getPowerUPSpawnPoint();
		Integer typeOfPowerUp = new RandomInt().getRandomInt(6, 8);
		if (typeOfPowerUp == 6) {
			return createShield(pos);
		} else if (typeOfPowerUp == 7) {
		    return createMultiplier(pos);
		} else {
			return createSweeper(pos);
		}
	}
	
	/**
	 * Creates a new Poweup object Shield.
	 * @param position
	 * @return the power up obj
	 */
	public PowerUpObj createShield(Point2D position) {
		
		return new PowerUpObj(position, ObjectType.PWRUP_SHIELD, gameEngine);
		}
	
/**
 * Creates a new Poweup object Multiplier.
 * @param position
 * @return the power up obj
 */
public PowerUpObj createMultiplier(Point2D position) {
		
	return new PowerUpObj(position, ObjectType.PWRUP_MULTIPLIER, gameEngine);
		}

/**
 * Creates a new Poweup object Sweeper.
 * @param position
 * @return the power up obj
 */
public PowerUpObj createSweeper(Point2D position) {
	return new PowerUpObj(position, ObjectType.PWRUP_SWEEPER, gameEngine);
		}

}
