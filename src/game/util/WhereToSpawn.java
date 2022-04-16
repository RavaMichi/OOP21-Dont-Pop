package game.util;

/**
 * The Class WhereToSpawn.
 */
public class WhereToSpawn { //RITORNA UN POINT 2D IN CUI FAR SPAWNARE IL NOSTRO ELEMENTO DI GIOCO 
			
	/**
  * Gets the random side of the game board.
  *
  * @return the random side
  */
		public int getRandomSide() {
			RandomInt randomInt= new RandomInt();
			int side = randomInt.getRandomInt(1,4);
			return side;
		}
		
		/**
		 * The Enum SideOfSpawn.
		 */
		private enum SideOfSpawn{
			
			/** The west. */
			WEST,
			/** The east. */
			EAST,
			/** The sud. */
			SUD ,
			
			/** The nord. */
			NORD
		}
		
		
		/**
		 * Gets the enemy spawn point.
		 *
		 * @param side the side where to spawn
		 * @return the enemy spawn point
		 */
		public  Point2D getEnemySpawnPoint(int side) {
			
			RandomInt randomInt= new RandomInt();
			int sideOfSpawn= this.getRandomSide();
			double randomNumber=(double)randomInt.getRandomInt(0,100) / 100 ;
			//SPAWNANO FUORI FDAL GAMEBOARD CON UNA DIFFERENZA DI double n= 0.2 (sia in positivo che in negativo)
		
			
			if (sideOfSpawn == 1 ) {
				return new Point2D(-0.2,randomNumber);
			} else if ( sideOfSpawn == 2 ) {
				return new Point2D(randomNumber,1.2);
			} else if ( sideOfSpawn == 3 ) {
				return new Point2D(1.2 , randomNumber);
			} else if (sideOfSpawn == 4 ) {
				return new Point2D(randomNumber,-0.2);
			}
			return new Point2D(0.2,-0.2); //IN CASO DI ERRORE SPAWN IN (0.2,-0.2)
			
		}


		/**
		 * Gets the power UP spawn point.
		 *
		 * @return the power UP spawn point
		 */
		public Point2D getPowerUPSpawnPoint() {
			RandomInt randomInt= new RandomInt();
			double x = randomInt.getRandomInt(0,100)/100;
			double y = randomInt.getRandomInt(0,100)/100;
			return new Point2D(x , y);
			}
		
		
		public int getThornballRandomSide() {
			RandomInt randomInt= new RandomInt();
			int side = randomInt.getRandomInt(1,2);
			return side;
		}
		
public  Point2D getThornballSpawnPoint(int side) {
			
			RandomInt randomInt= new RandomInt();
			int sideOfSpawn= this.getRandomSide();
			double randomNumber=randomInt.getRandomInt(1,100) / 100 ;
			if (sideOfSpawn == SideOfSpawn.WEST.ordinal() ) {
				return new Point2D(-0.2,randomNumber);
			} else if ( sideOfSpawn == SideOfSpawn.EAST.ordinal() ) {
				return new Point2D(1.2 , randomNumber);			
		}
			return new Point2D(0.2,-0.2); //IN CASO DI ERRORE SPAWN IN (0.2,-0.2)
  }
}
