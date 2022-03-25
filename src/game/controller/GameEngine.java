package game.controller;

import java.util.ArrayList;
import java.util.List;

//consider breaking it down into more specific classes instead of bulk importing them
import game.model.*;
import game.util.Point2D;


/** 
 * GameEngine is the class that makes the game work.
*/
public class GameEngine extends Thread {
//or, extends Runnable - then call it from a thread
    /**
     * Game time, starting from 0.
     */
    private double time;
    private Player player;
    private List<AbstractGameObject> enemies;
    private List<AbstractGameObject> pwr;
    private ScoreManager score;
    private SpawnManager spawnmanager;

    private static final int INITIAL_SIZE = 50;
    private static final int MULTIPLIER_TIME = 5;       //five seconds of multiplier
    private static final long TIME_CONST_60_HZ_MS = 1000 / 60;

    private boolean hasShield = false;
    private boolean hasMultiplier = false;
    private double multiplierTime; //mette il tempo in secondi della durata del multiplier (time goes down over time)

    /**
     * Manages final score, that will be displayed both during gameplay and after gameover.
     * Differs from ScoreManager, which manages GUI-related aspects of score displaying
     */
    private class ScoreManagement {
        
    }

//    private static final long START_TIME = 0;

    /**
     * Creates a new GameEngine object and initializes its fields.
     */
    public GameEngine() {
        this.enemies = new ArrayList<>(INITIAL_SIZE);
        this.pwr = new ArrayList<>();   //default size: 10
    }

    /**
     * Updates (increments) game time (in seconds).
     */
    private void incTime() {
        final double deltaTime = ((double) TIME_CONST_60_HZ_MS) / 1000;
        this.time += deltaTime;

        if (this.multiplierTime > 0) {
            //decrements multiplier time, so as to make it come to an end
            this.multiplierTime -= deltaTime; 
        } else {
            //multiplier powerup has expired: reverting settings back to normal
            if (this.hasMultiplier) {
                this.hasMultiplier = false;
                //TODO: add score multiplier manager
            }
        }
    }

    /**
     * Starts the game loop (aka the engine).
     */
    public void startGameLoop() {
        boolean gameOver = false;
        while (true) {
            //interval between "frames"
            final long startTime = System.currentTimeMillis();

            //updates game time
            this.incTime();

            //advance spawnManager
            this.spawnmanager.advance();
            //update all AbstractGameObjects (for each --- update)
            this.player.update();
            for (AbstractGameObject enemy: this.enemies) {
                enemy.update();
            }
            for (AbstractGameObject powerup: this.pwr) {
                powerup.update();
            }
            //collision control (Controllo collisioni separate)
                //1. ENEMIES -- if true, game over (prints score and gets back to menu)
            for (AbstractGameObject enemy: this.enemies) {
                if (enemy.getCollider().checkCollision(this.player)) {
                    gameOver = true;
                    break;
                }
            }

            //game over: breaking loop
            if (gameOver) {
                //TODO: print score
                //TODO: go back to menu
                //maybe you should put some thought into this break
                break;
            }
                //2. POWERUPS -- if true, look out object type (enum) and apply effect
            //display collisions: for each --- render
            for (AbstractGameObject powerup: this.pwr) {
                //TODO: check object type and apply effect
                //TODO: display collisions (for each - render)
            }

            final long endTime = System.currentTimeMillis();

            try {
                Thread.sleep(TIME_CONST_60_HZ_MS - (endTime - startTime));
            } catch (IllegalArgumentException e1) { 
                e1.printStackTrace();
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        }
    }

    /**
     * Creates a game object.
     * @param obj
     */
    public void instantiate(final AbstractGameObject obj) {
        if (obj.getType().isEnemy()) {
            this.enemies.add(obj);
        } else if (obj.getType().isPowerUp()) {
            this.pwr.add(obj);
        }
        //player does not get instantiated
    }

    /**
     * Destroys a game object.
     * @param obj
     */
    public void destroy(final AbstractGameObject obj) {
        if (this.enemies.contains(obj)) {
            this.enemies.remove(obj);
        } else if (this.pwr.contains(obj)) {
            this.pwr.remove(obj);
        }
    }

    /**
     * Applies a powerup.
     * @param pwrup
     */
    public void applyPwrUp(final AbstractGameObject pwrup) {
        switch (pwrup.getType()) {
            case PWRUP_SHIELD:
                this.hasShield = true;
                break;
            case PWRUP_MULTIPLIER:
                this.hasMultiplier = true;
                //five seconds of multiplier
                this.multiplierTime = MULTIPLIER_TIME;
                break;
            case PWRUP_SWEEPER: 
                this.enemies.clear();
                break;
            default:
                //does nothing
        }
    }

    /**
     * @return time in seconds
     */
    public double getTime() {
        return this.time;
    }

    /**
     * @return object player
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * @return player position
     */
    public Point2D getPlayerPosition() {
        return this.player.getPosition();
    }

//    public static void main(String args[]) {
//        System.out.println("" + START_TIME);
//        System.out.println("" + this.getTime());
//    }
}
