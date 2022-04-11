package game.engine;

import java.util.ArrayList;
import java.util.List;

import game.util.Leaderboard;
import game.util.Point2D;
import game.collider.CircleCollider;
import game.ui.GameScene;
import game.model.*;
import game.util.ScoreCalc;
import game.engine.GameApplication;
import game.model.ScoreDisplayObj;

/** 
 * GameEngine is the class that makes the game work.
*/
public class GameEngine extends Thread {
//or, extends Runnable - then call it from a thread
    
    private double gameTime;	//game time, starting from 0
    private final PlayerObj player;
    private final SpawnManager spawnManager;
    private final ScoreCalc scoreCalc;
    private final GameScene gameScene;
    private final GameApplication application;
    private final ScoreDisplayObj scoreDisplay;
    private final List<AbstractGameObject> enemies;
    private final List<AbstractGameObject> powerups;	//to change in PowerUpObject
    private final List<AbstractGameObject> destroyQueue;

    private static final int INITIAL_SIZE = 50;
    ////private static final int MULTIPLIER_TIME = 5;       //five seconds of multiplier
    private static final long TIME_CONST_60_HZ_MS = 1000 / 60;
    private static final double START_X = 0.5;
    private static final double START_Y = 0.5;
    private static final double SCORE_POS_X = 0.1;
    private static final double SCORE_POS_Y = 0.1;

    private boolean hasShield;		//false
    private boolean hasMultiplier;	//false
    ////private double multiplierTime; //mette il tempo in secondi della durata del multiplier (time goes down over time)

    /**
     * Duration of a frame (in seconds).
     */
    private double deltaTime;

    /**
     * Creates a new GameEngine object and initializes its fields.
     */
    public GameEngine(final GameApplication application, final GameScene gameScene, final ScoreCalc scoreCalc) {
        this.application = application;
        this.gameScene = gameScene;
        this.scoreCalc = scoreCalc;
        this.scoreDisplay = new ScoreDisplayObj(new Point2D(SCORE_POS_X, SCORE_POS_Y), AbstractGameObject.ObjectType.SCORE, this);
        this.spawnManager = new SpawnManager(this);
        this.player = new PlayerObj(new Point2D(START_X, START_Y), AbstractGameObject.ObjectType.PLAYER, this);
        this.enemies = new ArrayList<>(INITIAL_SIZE);
        this.powerups = new ArrayList<>();   //default size: 10
        this.destroyQueue = new ArrayList<>(INITIAL_SIZE);
        //likely add fps in future
    }

    /**
     * Starts the game loop (aka the engine).
     */
    public void startGameLoop() {
        while (true) {
            //interval between "frames"
            final long startTime = System.currentTimeMillis();

            this.incTime();					     	            //updates game time
            this.scoreCalc.calculateScore(deltaTime);   		//multiplier time management
            this.spawnManager.advance();	            		//advance spawnManager (enemy spawning)
            this.updateAllGameObjects();
            this.removeObjectsInDestroyQueue();
            this.checkPowerupCollision();	            		//powerups

            //game over: breaking loop
            if (this.checkEnemyCollision()) {
            	//TODO: consider changing everything to a continue loop, 
            	//putting powerups in an if statement (if (!gameOver)) and 
            	//setting a flag like while(!gameOver) at the beginning of the loop
            	
            	if (this.hasShield) {
            		this.hasShield = false;
            	} else {
            		//end game loop
            		break;
            	}
            	
            	//////thread sleeps for remaining frame duration
            	////final long endTime = System.currentTimeMillis();
                ////this.putThreadToSleep(startTime, endTime);
                
                //////calculate frame duration
                ////final long endFrame = System.currentTimeMillis();
                ////this.deltaTime = this.deltaTime(endFrame, startTime) / 1000;
            }
            
            ////this.scoreCalc.incScore();		//score increment
            this.render();					//rendering changes
            
            //thread sleeps for remaining frame duration
            final long endTime = System.currentTimeMillis();
            this.putThreadToSleep(startTime, endTime);

            //calculate frame duration
            final long endFrame = System.currentTimeMillis();
            this.deltaTime = this.deltaTime(endFrame, startTime) / 1000;
        }
    }

    @Override
    public void run() {
    	//start game loop
    	this.startGameLoop();
    	//on gameover, set score in application
    	this.application.score(this.scoreCalc.getScore());
    	//TODO: print score
        //TODO: go back to menu
    }
    
    /**
     * Calculates the time that passes between two frames, given that frames will not always be computed in the same time.
     * @param startFrame frame time
     * @param endFrame frame time
     * @return time difference between two frames (in ms)
     */
    public long deltaTime(final long startFrame, final long endFrame) {
        return endFrame - startFrame;
    }

    /**
     * Gets time difference between start and end of a frame (aka the frame duration).
     * @return deltaTime
     */
    public double getDeltaTime() {
        return this.deltaTime;
    }

    /**
     * Creates a game object.
     * @param obj
     */
    public void instantiate(final AbstractGameObject obj) {
        if (obj.getType().isEnemy()) {
            this.enemies.add(obj);
        } else if (obj.getType().isPowerUp()) {
            this.powerups.add(obj);
        }
        //player does not get instantiated
    }

    /**
     * Destroys a game object.
     * @param obj
     */
    public void destroy(final AbstractGameObject obj) {
        	this.destroyQueue.add(obj);
    }

    /**
     * Applies a powerup after checking its object type in AbstractGameObject.ObjectType (enum).
     * @param pwrup
     */
    public void applyPwrUp(final AbstractGameObject pwrup) {
        switch (pwrup.getType()) {
            case PWRUP_SHIELD:
                this.hasShield = true;
                break;
            case PWRUP_MULTIPLIER:
                this.hasMultiplier = true;
                //sets multiplier value (duration: 5 seconds)
                this.scoreCalc.setMultiplier();
                break;
            case PWRUP_SWEEPER: 
                this.enemies.clear();
                break;
            default:
                //does nothing
        }
    }
    
    /**
     * Ends the game on game over.
     * Prints the score and kills the player.
     */
    public void endGame() {
    	this.application.score();
    	this.player.die();
    }

    /**
     * @return time in seconds
     */
    public double getTime() {
        return this.gameTime;
    }

    /**
     * @return object player
     */
    public PlayerObj getPlayer() {
        return this.player;
    }

    /**
     * @return player position
     */
    public Point2D getPlayerPosition() {
        return this.player.getPosition();
    }

	/**
	 * Gets mouse position.
	 * @return mouse position
	 */
	public Point2D getMousePosition() {
		return this.gameScene.getMouseWorldPosition();
	}
	
	/**
	 * Gets the score calculator.
	 * @return object scoreCalc
	 */
	public ScoreCalc getScoreCalc() {
		return this.scoreCalc;
	}
	
	/**
	 * Gets this score display object, used to print current score at playtime.
	 * @return scoreDisplay
	 */
	public ScoreDisplayObj getScoreDisplay() {
		return this.scoreDisplay;
	}
	
	/**
	 * Gets a reference to this object (GameEngine).
	 * @return this GameEngine
	 */
	public GameEngine get() {
		return this;
	}
	
	/**
     * Updates (increments) game time (in seconds).
     */
    private void incTime() {
        this.gameTime += this.deltaTime;
    }

    /**
     * Updates all AbstractGameObjects.
     */
    private void updateAllGameObjects() {
    	//for each --- update
        this.player.update();
        this.enemies.forEach(enemy -> enemy.update());
        this.powerups.forEach(powerup -> powerup.update());
    }

    /**
     * Removes all objects inside destroy queue and clears it.
     */
    private void removeObjectsInDestroyQueue() {
        //remove objects
        this.destroyQueue.forEach(obj -> {
        	if (this.enemies.contains(obj)) {
        		this.enemies.remove(obj);
        	} else if (this.powerups.contains(obj)) {
        		this.powerups.remove(obj);
        	}
        });
        //clear queue
        this.destroyQueue.clear();
    }
    
    /**
     * Puts thread to sleep for the remaining duration of the frame.
     * @param startTime
     * @param endTime
     */
    private void putThreadToSleep(final long startTime, final long endTime) {
    	try {
            Thread.sleep(TIME_CONST_60_HZ_MS - (endTime - startTime));
        } catch (IllegalArgumentException e1) { 
            e1.printStackTrace();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
    }
	
	/**
     * Checks if a collision with an enemy has occurred.
     * If true --> game over.
     * @return true if gameover, false otherwise
     */
	private boolean checkEnemyCollision() {
		for (final AbstractGameObject enemy: this.enemies) {
		    if (enemy.getCollider().checkCollision(
		    		(CircleCollider)this.player.getCollider())) {
		        return true;
		    }
		}
		return false;
	}
	
	/**
	 * Checks if a collision with a powerup has occurred.
	 * If true, applies powerup and destroys it.
	 */
	private void checkPowerupCollision() {
		this.powerups.forEach(powerup -> {
			this.applyPwrUp(powerup);
			this.destroy(powerup);
		});
	}
	
	/**
	 * Displays collisions and, in general, any new changes in the game scene.
	 */
	private void render() {
		//for each --- render
		final var renderList = new ArrayList<AbstractGameObject>();
        renderList.addAll(this.enemies);
        renderList.addAll(this.powerups);
        renderList.add(this.player);
        renderList.add(this.scoreDisplay);
        
        this.gameScene.render(renderList);
	}
}

/*
 * Aggiungi:
 * Calcolo punteggio (dal gameengine)
 * Salvataggio record punteggi di ogni singolo giocatore (il gameengine li calcola)
 * ScoreDisplay: score.update() ???
 * Metodo di Manu nello SpawnManager prima di fare la render()
 */
