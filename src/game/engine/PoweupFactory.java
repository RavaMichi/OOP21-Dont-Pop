package game.engine;
import game.util.Point2D;
import game.model.PowerUpObj; //classe power up da mettere public

import game.model.AbstractGameObject;
import game.util.WhereToSpawn;

public class PoweupFactory {
	
	GameEngine gameEngine; //da passare al costruttore
	WhereToSpawn wheretospawn = new WhereToSpawn();
	
	Point2D spawnPosition = wheretospawn.getPowerUPSpawnPoint();//PUNTO RANDOM ALL'INTERNO DELLA MAPPA 
	
	public PoweupFactory(GameEngine gameEngine2) {
		/this.gameEngine=gameEngine2;
	}

	public AbstractGameObject GetPowerUpObj(int n) {
		//modifica da fare AbstractGameObject
		
				if (n == AbstractGameObject.ObjectType.PWRUP_SHIELD.ordinal() ) {
					return new PowerUpObj(spawnPosition, AbstractGameObject.ObjectType.PWRUP_SHIELD, this.gameEngine );		
				} else if ( n == AbstractGameObject.ObjectType.PWRUP_MULTIPLIER.ordinal() ) {
					return new PowerUpObj(spawnPosition, AbstractGameObject.ObjectType.PWRUP_MULTIPLIER, this.gameEngine );	
				} else if ( n == AbstractGameObject.ObjectType.PWRUP_SWEEPER.ordinal() ) {
					return new PowerUpObj(spawnPosition, AbstractGameObject.ObjectType.PWRUP_SWEEPER, this.gameEngine );
				}
		
	}
	
}
