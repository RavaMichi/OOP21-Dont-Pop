package test;

import game.engine.EnemyFactory;
import game.engine.GameEngine;
import game.engine.PoweupFactory;
import game.model.*;
import game.model.AbstractGameObject.ObjectType;
import game.util.Point2D;
import game.util.RandomInt;

public class SpawnManager {

	// COSTANTI E VARIBILI UTILIZZATE
	private static final double LOOP_START_TIME = 3;// 3 secondi
	
	private static final double POWERUP_SPAWN_TIME = 7;

	private static final int LASER_SPAWN_LIMIT = 6;
	private static final double LASER_INCREASE_TIME = 15;
	private static double LASER_SPAWN_TIME = 5;

	private static final double BULLET_INCREASE_TIME = 10;
	private static double BULLET_SPAWN_TIME = 2;
	private static final double BULLET_DELTA_SPAWN_TIME = 0.1;
	private static final double BULLET_MIN_SPAWN_TIME = 0.5;

	//
	private GameEngine gameEngine;
	private PoweupFactory powerUpfFactory;
	private EnemyFactory enemyFactory;
	private RandomInt randomInt = new RandomInt();
	
	private int laserCount = 1;
	private double bulletTimer = BULLET_SPAWN_TIME;
	private double bulletCicleTimer = BULLET_INCREASE_TIME;  //tempo per aumentare la difficolta'
	private double laserTimer = LASER_SPAWN_TIME;
	private double laserCicleTimer = LASER_INCREASE_TIME;  //..
	private double pwrupTimer = POWERUP_SPAWN_TIME;
	
	public SpawnManager(final GameEngine gameEngine) {
		this.gameEngine = gameEngine;
		this.powerUpfFactory = new PoweupFactory(this.gameEngine);
		this.enemyFactory = new EnemyFactory(this.gameEngine);
		
		//Crea il countdown (game object) iniziale
		this.gameEngine.instantiate(new StartTimerObj(Point2D.of(0.5, 0.5), 0.25, ObjectType.SCORE, gameEngine), 2);
	}

	public void advance() {
		if (this.gameEngine.getTime() >= LOOP_START_TIME) {
			this.spawnLoop();
		}
	}

	private void spawnLoop() {
		//Power Up
		checkCicle(pwrupTimer, () -> {
			//spawna pwr up randomico//
			pwrupTimer = POWERUP_SPAWN_TIME;
		});
		//Bullet spawn
		checkCicle(bulletTimer, () -> {
			//spawna bullet verso player//
			bulletTimer = BULLET_SPAWN_TIME;
		});
		//Bullet difficulty
		checkCicle(bulletCicleTimer, () -> {
			BULLET_SPAWN_TIME -= (BULLET_SPAWN_TIME <= BULLET_MIN_SPAWN_TIME) ? BULLET_DELTA_SPAWN_TIME : 0;
		});
	}

	public AbstractGameObject getPowerUp() throws Exception {
		/*
		 * I POWER UP SONO: PWRUP_SHIELD, //6 PWRUP_MULTIPLIER, //7 PWRUP_SWEEPER //8
		 */
		Integer typeOfPowerUp = randomInt.getRandomInt(6, 8);
		AbstractGameObject powerUP = powerUpfFactory.GetPowerUpObj(typeOfPowerUp);
		return powerUP;
	}

	private void checkCicle(double timer, Runnable spawnAction) {
		timer -= this.gameEngine.getDeltaTime();
		if (timer <= 0) {
			spawnAction.run();
		}
	}
	
}
