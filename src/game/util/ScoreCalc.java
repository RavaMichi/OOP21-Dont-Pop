package game.util;

/**
 * Manages final score, that will be displayed both during gameplay and after gameover.
 * Differs from ScoreManager, which manages GUI-related aspects of score displaying
 */
public class ScoreCalc {
	private int score;
    private int multiplier;
    private double multiplierTime;
    private int frameCounter;
    private final static double MULTIPLIER_TIME = 5;	//five seconds of multiplier
    private final static int FRAMES_PER_SCORE = 4;
    private final static int MULTIPLIER_2X = 2;
    
    private boolean hasMultiplier;
    

    /**
     * Creates class and sets multiplier to 1 by default.
     */
    public ScoreCalc() {
        this.multiplier = 1;
    }

    /**
     * Gets current score.
     * @return score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Increments score every 4 frames, giving >=15 points per second.
     */
    public void incScore() {
    	this.incFrameCounter();
    	if (this.getFrameCounter() % FRAMES_PER_SCORE == 0) {
    		//this.score += (1 * this.getMultiplier())
    		this.score += this.getMultiplier();
    		this.resetFrameCounter();
    	}
    }
	
    /**
     * Increments score by an arbitrary amount.
     * Can choose to ignore multiplier (default: apply current multiplier).
     * @param delta
     */
    public void incScore(final int delta, final boolean ignoreMultiplier) {
    	if (ignoreMultiplier) {
    		this.score += delta;
    	} else {
    		this.score += delta * this.getMultiplier();
    	}
    }

    /**
     * Manages multiplier time, decreasing it until it reaches 0.
     */
    private void manageMultiplierTime(final double deltaTime) {
	    //decrease multiplier time
        if (this.getMultiplierTime() > 0) {
            this.decMultiplierTime(deltaTime);
        } else if (this.hasMultiplier) {
            //multiplier expired: restoring normal settings
            this.hasMultiplier = false;
            this.scoreCalc.resetMultiplier();
            //TODO: add score multiplier manager
        }
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
        this.multiplierTime = MULTIPLIER_TIME;
    }
    
    /**
     * Sets multiplier to default value (2x).
     * Useful when you don't want to specify the value of the multiplier.
     */
    public void setMultiplier() {
    	this.setMultiplier(MULTIPLIER_2X);
    }

    /**
     * Resets multiplier back to 1.
     */
    public void resetMultiplier() {
        this.multiplier = 1;
    }
    
    /**
     * Gets remaining multiplier time.
     * @return multiplierTime
     */
    public double getMultiplierTime() {
    	return this.multiplierTime;
    }
    
    /**
     * Decreases multiplier time by an arbitrary amount.
     * @param decrement
     */
    public void decMultiplierTime(final double decrement) {
    	this.multiplierTime -= decrement;
    }
    
    /**
     * Gets current frame counter.
     * @return frameCounter
     */
    private int getFrameCounter() {
    	return this.frameCounter;
    }
    
    /**
     * Increments frame counter by 1.
     */
    private void incFrameCounter() {
    	this.frameCounter++;
    }
    
    /**
     * Resets frame counter to 0.
     */
    private void resetFrameCounter() {
    	this.frameCounter = 0;
    }
}
