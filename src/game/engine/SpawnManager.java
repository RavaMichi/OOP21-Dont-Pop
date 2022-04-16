package game.engine;

import game.model.*;
import game.model.AbstractGameObject.ObjectType;
import game.util.Point2D;
import game.util.RandomInt;

public class SpawnManager {

	// COSTANTI E VARIBILI UTILIZZATE
	private static final double LOOP_START_TIME = 3;// 3 secondi
	
	private static final double POWERUP_SPAWN_TIME = 7;

	private static final int LASER_SPAWN_LIMIT = 10;
	private static final double LASER_CICLE_TIME = 15;
	private static double LASER_SPAWN_TIME = 5;

	private static final double BULLET_CICLE_TIME = 4;
	private static double BULLET_SPAWN_TIME = 2;
	private static final double BULLET_DELTA_SPAWN_TIME = 0.1;
	private static final double BULLET_MIN_SPAWN_TIME = 0.3;

	private static final double THORNBALL_START_TIME = 20;
	private static double THORNBALL_SPAWN_TIME = 4;
	private static final double THORNBALL_CICLE_TIME = 15;
	private static final int THORNBALL_SPAWN_LIMIT = 5;
	
	//
	private GameEngine gameEngine;
	private PoweupFactory powerUpfFactory;
	private EnemyFactory enemyFactory;
	private RandomInt randomInt = new RandomInt();
		
	private double pwrupTimer = POWERUP_SPAWN_TIME;
	
	private double bulletTimer = BULLET_SPAWN_TIME;
	private double bulletCicleTimer = BULLET_CICLE_TIME;  //tempo per aumentare la difficolta'
	
	private int laserCount = 1;
	private double laserTimer = LASER_SPAWN_TIME;
	private double laserCicleTimer = LASER_CICLE_TIME;  //..

	private int thornballCount = 1;
	private double thornballTimer = THORNBALL_SPAWN_TIME;
	private double thornballCicleTimer = THORNBALL_CICLE_TIME;
	
	private boolean started = false;
	
	public SpawnManager(final GameEngine gameEngine) {
		this.gameEngine = gameEngine;
		this.powerUpfFactory = new PoweupFactory(this.gameEngine);
		this.enemyFactory = new EnemyFactory(this.gameEngine);
		
		//Crea il countdown (game object) iniziale
		this.gameEngine.instantiate(new StartTimerObj(Point2D.of(0.5, 0.5), 0.25, ObjectType.SCORE, gameEngine));
	}

	public void advance() {
		if (this.gameEngine.getTime() >= LOOP_START_TIME) {
			this.spawnLoop();
			if (!started) {
				this.gameEngine.getScoreCalc().setCalcStatus(true);
				started = true;
			}
		}
	}
	private void spawnLoop() {
		updateTime();
		
		//Power up
		if (this.pwrupTimer <= 0) {
			this.gameEngine.instantiate(this.enemyFactory.createRandomPowerUp());
			this.pwrupTimer = POWERUP_SPAWN_TIME;
		}
		//Bullet spawn
		if (this.bulletTimer <= 0) {
			this.gameEngine.instantiate(this.enemyFactory.createBullet());
			this.bulletTimer = BULLET_SPAWN_TIME;
		}
		//Bullet difficulty
		if (this.bulletCicleTimer <= 0) {
			if (BULLET_SPAWN_TIME > BULLET_MIN_SPAWN_TIME) {
				BULLET_SPAWN_TIME -= BULLET_DELTA_SPAWN_TIME;
			}
			this.bulletCicleTimer = BULLET_CICLE_TIME;
		}
		//Laser spawn
		if (this.laserTimer <= 0) {
			for (int i = 0; i < this.laserCount; i++) {
				this.gameEngine.instantiate(this.enemyFactory.createLaser());
			}
			this.laserTimer = LASER_SPAWN_TIME;
		}
		//Laser difficulty
		if (this.laserCicleTimer <= 0) {
			if (this.laserCount < LASER_SPAWN_LIMIT) {
				this.laserCount++;
			}
			this.laserCicleTimer = LASER_CICLE_TIME;
		}
		
		if (this.gameEngine.getTime() < THORNBALL_START_TIME) return;
		
		updateThornballTime();
		//Thornball spawn
		if (this.thornballTimer <= 0) {
			for (int i = 0; i < this.thornballCount; i++) {
				this.gameEngine.instantiate(this.enemyFactory.createThornball());
			}
			this.thornballTimer = THORNBALL_SPAWN_TIME;
		}
		//Thornball difficulty
		if (this.thornballCicleTimer <= 0) {
			if (this.thornballCount < THORNBALL_SPAWN_LIMIT) {
				this.thornballCount++;
			}
			this.thornballCicleTimer = THORNBALL_CICLE_TIME;
		}
	}

	public AbstractGameObject getPowerUp() throws Exception {
		/*
		 * I POWER UP SONO: PWRUP_SHIELD, //6 PWRUP_MULTIPLIER, //7 PWRUP_SWEEPER //8
		 */
		Integer typeOfPowerUp = randomInt.getRandomInt(6, 8);
		AbstractGameObject powerUP = powerUpfFactory.GetPowerUpObj(typeOfPowerUp);
		return powerUP;
	}

	private void updateTime() {
		this.pwrupTimer -= this.gameEngine.getDeltaTime();
		this.bulletTimer -= this.gameEngine.getDeltaTime();
		this.bulletCicleTimer -= this.gameEngine.getDeltaTime();
		this.laserTimer -= this.gameEngine.getDeltaTime();
		this.laserCicleTimer -= this.gameEngine.getDeltaTime();
	}
	private void updateThornballTime() {
		this.thornballTimer -= this.gameEngine.getDeltaTime();
		this.thornballCicleTimer -= this.gameEngine.getDeltaTime();
	}
}