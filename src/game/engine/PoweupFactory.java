package game.engine;
import game.util.Point2D;
import game.model.PowerUpObj; //classe power up da mettere public

import game.model.AbstractGameObject;
import game.model.AbstractGameObject.ObjectType;
import game.util.WhereToSpawn;

public class PoweupFactory {
	
	GameEngine gameEngine; //da passare al costruttore
	WhereToSpawn wheretospawn = new WhereToSpawn();
	
	Point2D spawnPosition = wheretospawn.getPowerUPSpawnPoint();//PUNTO RANDOM ALL'INTERNO DELLA MAPPA 
	
	public PoweupFactory(GameEngine gameEngine2) {
		this.gameEngine=gameEngine2;
	}

	public AbstractGameObject GetPowerUpObj()  {
		
		var pos = Point2D.of(Math.random()*0.6 + 0.2, Math.random()*0.6 + 0.2);
		Integer typeOfPowerUp = randomInt.getRandomInt(6, 8);
		if (n == 6) {
			return new PowerUpObj(pos, ObjectType.PWRUP_SHIELD, gameEngine);
		} else if (n == 7) {
			return new PowerUpObj(pos, ObjectType.PWRUP_MULTIPLIER, gameEngine);
		} else {
			return new PowerUpObj(pos, ObjectType.PWRUP_SWEEPER, gameEngine);
		}
	}
}
