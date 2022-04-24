package game.util;

/**
 * The Class WhereToSpawn. Provide a Point 2D where spawn an enemy or a powerup.
 * The enemies will be spawned outside the game window. The  Powerups will spawn inside the
 * game windows
 */
public class WhereToSpawn { //RITORNA UN POINT 2D IN CUI FAR SPAWNARE IL NOSTRO ELEMENTO DI GIOCO 
			
		/**
		* Gets the random side of the game board.
		* @return the random side
		*/
		public int getRandomSide() {
			final RandomInt randomInt= new RandomInt();
			final int side = randomInt.getRandomInt(1,4);
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
			/** The south. */
			SOUTH,
			/** The north. */
			NORTH
		}
		
		
		/**
		 * Gets the enemy spawn point.
		 *
		 * @param side the side where to spawn
		 * @return the enemy spawn point
		 */
		public  Point2D getEnemySpawnPoint(final int side) {
			
			final RandomInt randomInt= new RandomInt();
			//int sideOfSpawn= this.getRandomSide();
			final double randomNumber=(double)randomInt.getRandomInt(0,100) / 100 ;
			//SPAWNANO FUORI FDAL GAMEBOARD CON UNA DIFFERENZA DI double n= 0.2 (sia in positivo che in negativo)
		
			
			if (side == 1 ) {
				return new Point2D(-0.2,randomNumber);
			} else if ( side == 2 ) {
				return new Point2D(randomNumber,1.2);
			} else if ( side == 3 ) {
				return new Point2D(1.2 , randomNumber);
			} else if (side == 4 ) {
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
			return new Point2D(Math.random()*0.6 + 0.2, Math.random()*0.6 + 0.2);
			}
		
		/**
		 * Gets the thornball <spawn side.
		 *
		 * @return the power UP spawn point
		 */
		public int getThornballRandomSide() {
			return 1 + new RandomInt().getRandomInt(0, 1)*2;//1 o 3
		}
		
		/**
		 * Gets the thornball spawn point.
		 *
		 * @param side the side where to spawn
		 * @return the thornball spawn point
		 */
		public  Point2D getThornballSpawnPoint(final int side) {
			
			final RandomInt randomInt= new RandomInt();
			final int sideOfSpawn= this.getThornballRandomSide();
			final double randomNumber=randomInt.getRandomInt(1,100) / 100 ;
			if (sideOfSpawn == SideOfSpawn.WEST.ordinal() ) {
				return new Point2D(-0.2,randomNumber);
			} else if ( sideOfSpawn == SideOfSpawn.EAST.ordinal() ) {
				return new Point2D(1.2 , randomNumber);			
		}
			return new Point2D(0.2,-0.2); //default spawn point
  }
	
}
