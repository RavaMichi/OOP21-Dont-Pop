package game.engine;
import java.awt.geom.Point2D;

public class PoweupFactory {
	
	
	WhereToSpawn wheretospawn = new WhereToSpawn();
	
	Point2D spawnPosition = wheretospawn.getPowerUPSpawnPoint();//PUNTO RANDOM ALL'INTERNO DELLA MAPPA 
	
	public AbstractGameObject GetPowerUpObj(int n) {
		//modifica da fare AbstractGameObject
		
				if (n == AbstractGameObject.ObjectType.PWRUP_SHIELD.ordinal() ) {
					return new Shield(spawnPosition);		
				} else if ( n == AbstractGameObject.ObjectType.PWRUP_MULTIPLIER.ordinal() ) {
					return new Multiplier(spawnPosition);
				} else if ( n == AbstractGameObject.ObjectType.PWRUP_SWEEPER.ordinal() ) {
					return new Sweeper(spawnPosition);
				}
		
	}
	
}
