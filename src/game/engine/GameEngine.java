package game.engine;

import java.util.ArrayList;
import java.util.List;
import game.util.Point2D;
import game.model.*;

/** 
 * GameEngine is the class that makes the game work.
*/
public class GameEngine {
//or, extends Runnable - then call it from a thread
    /**
     * Game time, starting from 0.
     */
    private double gameTime;
    private PlayerObj player;
    private List<AbstractGameObject> enemies;
    private List<AbstractGameObject> pwr;
    private ScoreManager scoreManager;
    private SpawnManager spawnmanager;
    private ScoreCalc scoreCalc;

    private static final int INITIAL_SIZE = 50;
    private static final int MULTIPLIER_TIME = 5;       //five seconds of multiplier
    private static final long TIME_CONST_60_HZ_MS = 1000 / 60;

    private boolean hasShield = false;
    private boolean hasMultiplier = false;
    private double multiplierTime; //mette il tempo in secondi della durata del multiplier (time goes down over time)

    //Duration of a frame
    private double deltaTime;

    /**
     * Manages final score, that will be displayed both during gameplay and after gameover.
     * Differs from ScoreManager, which manages GUI-related aspects of score displaying
     */
    private class ScoreCalc {
        private int score;
        private int multiplier;

        /**
         * Creates class and sets multiplier to 1 by default.
         */
        ScoreCalc() {
            this.multiplier = 1;
        }

        /**
         * Gets current score.
         * @return current score
         */
        public int getScore() {
            return this.score;
        }

        /**
         * Increments score by 1.
         */
        public void incScore() {
            this.score++;
        }

        /**
         * Increments score by <delta>.
         * @param delta
         */
        public void incScore(final int delta) {
            this.score += delta;
        }

        /**
         * Gets current value of multiplier.
         * @return multiplier
         */
        public int getMultiplier() {
            return this.multiplier;
        }

        /**
         * Sets multiplier to any value.
         * @param multiplier
         */
        public void setMultiplier(final int multiplier) {
            this.multiplier = multiplier;
        }

        /**
         * Resets multiplier back to 1.
         */
        public void resetMultiplier() {
            this.multiplier = 1;
        }
    }

    /**
     * Creates a new GameEngine object and initializes its fields.
     */
    public GameEngine() {
        this.enemies = new ArrayList<>(INITIAL_SIZE);
        this.pwr = new ArrayList<>();   //default size: 10
        this.scoreCalc = new ScoreCalc();
    }

    /**
     * Updates (increments) game time (in seconds).
     */
    private void incTime() {
        final double deltaTime = ((double) TIME_CONST_60_HZ_MS) / 1000;
        this.gameTime += deltaTime;

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
                if (enemy.getCollider().checkCollision((CircleCollider)this.player.getCollider())) {
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

            //end of frame
            final long endFrame = System.currentTimeMillis();

            this.deltaTime = this.deltaTime(endFrame, startTime) / 1000;
        }
    }

    /**
     * Calculates the time that passes between two frames, given that frames will not always be computed in the same time.
     * @param startFrame frame time
     * @param endFrame frame time
     * @return time difference between two frames
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
}
