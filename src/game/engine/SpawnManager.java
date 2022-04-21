package game.engine;

import game.model.*;
import game.model.AbstractGameObject.ObjectType;
import game.util.Point2D;
import game.util.RandomInt;


/**
 * The Class SpawnManager. It manages the spawning mechnanics and logic
 */
public class SpawnManager {

	/** The Constant LOOP_START_TIME. */

	private static final double LOOP_START_TIME = 3;// 3 secondi

	/** The Constant POWERUP_SPAWN_TIME. */
	private static final double POWERUP_SPAWN_TIME = 7;

	/** The Constant LASER_SPAWN_LIMIT. */
	private static final int LASER_SPAWN_LIMIT = 10;
	
	/** The Constant LASER_CICLE_TIME. */
	private static final double LASER_CICLE_TIME = 15;
	
	/** The laser spawn time. */
	private static double LASER_SPAWN_TIME = 5;

	/** The Constant BULLET_CICLE_TIME. */
	private static final double BULLET_CICLE_TIME = 4;
	
	/** The bullet spawn time. */
	private double BULLET_SPAWN_TIME = 2;
	
	/** The Constant BULLET_DELTA_SPAWN_TIME. */
	private static final double BULLET_DELTA_SPAWN_TIME = 0.1;
	
	/** The Constant BULLET_MIN_SPAWN_TIME. */
	private static final double BULLET_MIN_SPAWN_TIME = 0.3;

	/** The Constant THORNBALL_START_TIME. */
	private static final double THORNBALL_START_TIME = 20;
	
	/** The thornball spawn time. */
	private static double THORNBALL_SPAWN_TIME = 4;
	
	/** The Constant THORNBALL_CICLE_TIME. */
	private static final double THORNBALL_CICLE_TIME = 15;
	
	/** The Constant THORNBALL_SPAWN_LIMIT. */
	private static final int THORNBALL_SPAWN_LIMIT = 5;
	
	/** The Constant EXPLOSION_START_TIME. */
	private static final double EXPLOSION_START_TIME = 60;
	
	/** The explosion spawn time. */
	private double EXPLOSION_SPAWN_TIME = 8;
	
	/** The Constant EXPLOSION_CICLE_TIME. */
	private static final double EXPLOSION_CICLE_TIME = 15;
	
	/** The Constant EXPLOSION_DELTA_SPAWN_TIME. */
	private static final double EXPLOSION_DELTA_SPAWN_TIME = 0.5;
	
	/** The Constant EXPLOSION_MIN_SPAWN_TIME. */
	private static final double EXPLOSION_MIN_SPAWN_TIME = 1;

	
	/** The game engine. */
	private GameEngine gameEngine;
	
	/** The power up factory. */
	private PoweupFactory powerUpFactory;
	
	/** The enemy factory. */
	private EnemyFactory enemyFactory;
	
	/** The random int. */
	private RandomInt randomInt = new RandomInt();

	/** The pwrup timer. */
	private double pwrupTimer = POWERUP_SPAWN_TIME;

	/** The bullet timer. */
	private double bulletTimer = BULLET_SPAWN_TIME;
	
	/** The bullet cicle timer. */
	private double bulletCicleTimer = BULLET_CICLE_TIME;  //tempo per aumentare la difficolta'

	/** The laser count. */
	private int laserCount = 1;
	
	/** The laser timer. */
	private double laserTimer = LASER_SPAWN_TIME;
	
	/** The laser cicle timer. */
	private double laserCicleTimer = LASER_CICLE_TIME;  //..

	/** The thornball count. */
	private int thornballCount = 1;
	
	/** The thornball timer. */
	private double thornballTimer = THORNBALL_SPAWN_TIME;
	
	/** The thornball cicle timer. */
	private double thornballCicleTimer = THORNBALL_CICLE_TIME;
	
	/** The explosion timer. */
	private double explosionTimer = EXPLOSION_SPAWN_TIME;
	
	/** The explosion cicle timer. */
	private double explosionCicleTimer = EXPLOSION_CICLE_TIME;

	/** The started. */
	private boolean started = false;
	
	/**
	 * Instantiates a new spawn manager.
	 *
	 * @param gameEngine the game engine
	 */
	public SpawnManager(final GameEngine gameEngine) {
		this.gameEngine = gameEngine;
		this.powerUpFactory = new PoweupFactory(this.gameEngine);
		this.enemyFactory = new EnemyFactory(this.gameEngine);
		
		//Crea il countdown (game object) iniziale
		this.gameEngine.instantiate(new StartTimerObj(Point2D.of(0.5, 0.5), 0.25, ObjectType.SCORE, gameEngine));
	}

	/**
	 * Advance in the game. refresh time and use the spawnLoop
	 * @see SpawnManager 
	 */
	public void advance() {
		if (this.gameEngine.getTime() >= LOOP_START_TIME) {
			this.spawnLoop();
			if (!started) {
				this.gameEngine.getScoreCalc().setCalcStatus(true);
				started = true;
			}
		}
	}
	
	/**
	 * Spawn loop where the spawnmanager spawns enemy and powerup using different timer.
	 */
	private void spawnLoop() {
		updateTime();

		//Power up
		if (this.pwrupTimer <= 0) {
			this.gameEngine.instantiate(this.powerUpFactory.GetPowerUpObj());
			this.pwrupTimer = POWERUP_SPAWN_TIME;
		}
		//Bullet spawn
		if (this.bulletTimer <= 0) {
			this.gameEngine.instantiate(this.enemyFactory.GetEnemyObj(ObjectType.BULLET));		//(this.enemyFactory.createBullet());
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
				this.gameEngine.instantiate(this.enemyFactory.GetEnemyObj(ObjectType.LASER));
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

		/*Thornball*/
		if (this.gameEngine.getTime() >= THORNBALL_START_TIME) {
			updateThornballTime();
			//Thornball spawn
			if (this.thornballTimer <= 0) {
				for (int i = 0; i < this.thornballCount; i++) {
					this.gameEngine.instantiate(this.enemyFactory.GetEnemyObj(ObjectType.THORNBALL));
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

		/*Explosion*/
		if (this.gameEngine.getTime() >= EXPLOSION_START_TIME) {
			updateExplosionTime();
			//Explosion spawn
			if (this.explosionTimer <= 0) {
				this.gameEngine.instantiate(this.enemyFactory.GetEnemyObj(ObjectType.EXPLOSION));
				this.explosionTimer = THORNBALL_SPAWN_TIME;
			}
			//Explosion difficulty
			if (this.explosionCicleTimer <= 0) {
				if (EXPLOSION_SPAWN_TIME > EXPLOSION_MIN_SPAWN_TIME) {
					EXPLOSION_SPAWN_TIME -= EXPLOSION_DELTA_SPAWN_TIME;
				}
				this.explosionCicleTimer = EXPLOSION_CICLE_TIME;
			}
		}
	}

	/**
	 * Update game time.
	 */
	private void updateTime() {
		this.pwrupTimer -= this.gameEngine.getDeltaTime();
		this.bulletTimer -= this.gameEngine.getDeltaTime();
		this.bulletCicleTimer -= this.gameEngine.getDeltaTime();
		this.laserTimer -= this.gameEngine.getDeltaTime();
		this.laserCicleTimer -= this.gameEngine.getDeltaTime();
	}
	
	/**
	 * Update thornball time.
	 */
	private void updateThornballTime() {
		this.thornballTimer -= this.gameEngine.getDeltaTime();
		this.thornballCicleTimer -= this.gameEngine.getDeltaTime();
	}
	
	/**
	 * Update explosion time.
	 */
	private void updateExplosionTime() {
		this.explosionTimer -= this.gameEngine.getDeltaTime();
		this.explosionCicleTimer -= this.gameEngine.getDeltaTime();
	}
} 
