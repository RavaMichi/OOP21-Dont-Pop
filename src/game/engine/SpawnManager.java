package game.engine;

import game.model.AbstractGameObject;
import game.util.RandomInt;

public class SpawnManager {

	// COSTANTI E VARIBILI UTILIZZATE

	private static final int BULLET_ID = 2;
	private static final int LASER_ID = 3;

	private static final int POWERUP_SPAWN_TIME = 4; // OGNI 4 SECONDI

	private static final int LASER_SPAWN_LIMIT = 10;
	private static final double LASER_CHANGE_SPAWN_TIME = 35; // ogni 35 sec

	private static final double BULLET_CHANGE_SPAWN_TIME = 15; // ogni 15 sec
	private static final double BULLET_MIN_TIME_SPAWN = 0.5; // 0.5 sec

	private static final double TIME_SPAWN_LASER = 10; // ogni 10 sec
	private double tSpawnBullet = 2.5; // INIZIALMENTE ogni 2.5 sec
	private double bulletSpawnati = 1; // mi serve per moltipllicare la costante di tempo (quante volte ha
	// spawnato)//counter sballato di uno per iniziare il primo ciclo

	private int laserSpawnati = 1; // counter sballato di uno per iniziare il primo ciclo
	private int quantilaser = 2;
	private double counterCicloLaser = 0;

	// cambiare deltatime in double
	private double tempo_totale = 0; // + gameEngine.getCurrentTime();
	private final double tolleranza_frame = (1 / 60);// 1 frame: a volte un frame dura pi� di 1/60 di secondo ma non
														// credo ci possa
	// mettere il doppio del tempo per eseguirsi

	private double FIRST_LOOP_LIMIT = 3;// 3 secondi

	//
	private GameEngine gameEngine;
	private PoweupFactory powerUpfFactory = new PoweupFactory(this.gameEngine);
	private EnemyFactory enemyFactory = new EnemyFactory();
	private RandomInt randomInt = new RandomInt();

	public SpawnManager(final GameEngine gameEngine) {
		super();
		this.gameEngine = gameEngine;
	}

	public void advance() {
		if (tempo_totale >= FIRST_LOOP_LIMIT && tempo_totale <= FIRST_LOOP_LIMIT + tolleranza_frame) { // vuol dire che
																										// // ha
																										// aspettato //
																										// i tot secondi
			this.game_start();
		}
	}

	private void game_start() {
		double tempodasommare = gameEngine.getDeltaTime();
		tempo_totale += tempodasommare;
//BULLET		

		if (tempo_totale == tSpawnBullet * bulletSpawnati // AGGIUNGENDO UN FOR NE POSSO CREARE ANCHE DI PI�
				|| ((tSpawnBullet * bulletSpawnati + tolleranza_frame) <= tempo_totale
						&& tempo_totale >= (tSpawnBullet * bulletSpawnati + tolleranza_frame)
						&& (tempo_totale > BULLET_MIN_TIME_SPAWN))) {

			AbstractGameObject enemy = enemyFactory.GetEnemyObj(BULLET_ID); // NE HO CREATO UNO
			gameEngine.instantiate(enemy);
			bulletSpawnati++;
		}
		// SE NEL TEMPO + O - LA TOLLERANZA(0.25 SECONDI) //AL MASSIMO OGNI 0.25 SEC
		if (((BULLET_CHANGE_SPAWN_TIME + tolleranza_frame) >= tempo_totale
				&& tempo_totale <= (BULLET_CHANGE_SPAWN_TIME + tolleranza_frame)) && (tSpawnBullet > 0.25)) {
			// DEVO CAMPBIARE IL TEMPO DI SPAWN e lo diminuisco di 0.1 sec // CONTROLLARE LA
			// TOLLERANZA
			tSpawnBullet -= 0.1;
		}
//LASER	
		if (((tempo_totale <= ((TIME_SPAWN_LASER * counterCicloLaser) + tolleranza_frame)
				&& (tempo_totale >= ((TIME_SPAWN_LASER * counterCicloLaser) - tolleranza_frame)))
				&& laserSpawnati <= LASER_SPAWN_LIMIT)) { // faccio spawnare uno/due bullet ora sono 2

			counterCicloLaser++;
			for (int i = 0; i < quantilaser; i++) { // cos� spawna quanti laser voglio
				laserSpawnati++;
				AbstractGameObject enemy = enemyFactory.GetEnemyObj(LASER_ID); // NE HO CREATO UNO
				gameEngine.instantiate(enemy);
			}

			if ((tempo_totale >= LASER_CHANGE_SPAWN_TIME - tolleranza_frame)
					&& (tempo_totale <= LASER_CHANGE_SPAWN_TIME + tolleranza_frame)) {
				if (quantilaser < LASER_SPAWN_LIMIT) {
					quantilaser++;
				} else {
					quantilaser = LASER_SPAWN_LIMIT;
				}
			}
//FATTI LASER			
//POWERUP			randomici
			if ((tempo_totale >= POWERUP_SPAWN_TIME - tolleranza_frame)
					&& (tempo_totale <= POWERUP_SPAWN_TIME + tolleranza_frame)) {
				AbstractGameObject pwr = this.getPowerUp();
				// gameEngine.pwr.add(pwr);
				gameEngine.instantiate(pwr);
			}
		}
	}

	public AbstractGameObject getPowerUp() {
		/*
		 * I POWER UP SONO: PWRUP_SHIELD, //6 PWRUP_MULTIPLIER, //7 PWRUP_SWEEPER //8
		 */
		Integer typeOfPowerUp = randomInt.getRandomInt(6, 8);
		AbstractGameObject powerUP = powerUpfFactory.GetPowerUpObj(typeOfPowerUp);
		return powerUP;
	}

}
