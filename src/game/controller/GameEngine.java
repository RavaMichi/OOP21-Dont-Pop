package game.controller;

import java.util.ArrayList;
import java.util.List;

import game.model.AbstractGameObject;

/** 
 * GameEngine is the class that makes the game work.
*/
public class GameEngine {

//    private Player player;
    private List<AbstractGameObject> enemies;
    private List<AbstractGameObject> pwr;
//    private ScoreManager score;
    private static final int INITIAL_SIZE = 20;

//    SpawnManager spawnmanager;

    private static final long TIME_CONST_60_HZ_MS = 1000 / 60;
//    private static final long START_TIME = 0;

    /**
     * Game time, starting from 0.
     */
    private double time;

    /**
     * Creates a new GameEngine object and initializes its fields.
     */
    public GameEngine() {
        this.enemies = new ArrayList<>(INITIAL_SIZE);
        this.pwr = new ArrayList<>();   //default size: 10
    }

    /**
     * Updates (increments) game time.
     */
    private void incTime() {
        this.time += ((double) TIME_CONST_60_HZ_MS) / 1000;
    }

    /**
     * Starts the game loop (aka the engine).
     */
    public void startGameLoop() {
        while (true) {
            //interval between "frames"
            final long startTime = System.currentTimeMillis();

            //updates game time
            this.incTime();

            final long endTime = System.currentTimeMillis();

            try {
                Thread.sleep(TIME_CONST_60_HZ_MS - (endTime - startTime));
            } catch (Exception e) { }
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

    }

    /**
     * @return time in seconds
     */
    public double getTime() {
        return this.time;
    }

//    public static void main(String args[]) {
//        System.out.println("" + START_TIME);
//        System.out.println("" + this.getTime());
//    }
}
