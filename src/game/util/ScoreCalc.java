package game.util;

/**
 * Manages final score, that will be displayed both during gameplay and after gameover.
 * Differs from ScoreManager, which manages GUI-related aspects of score displaying
 */
public class ScoreCalc {
	private int score;
    private boolean calcStatus;
    private int multiplier;
    private double multiplierTime;
    private double frameCounter;
    private final static double MULTIPLIER_TIME = 5;	//five seconds of multiplier
    private final static int POINTS_PER_SECOND= 15;
    private final static double SECONDS_PER_POINT = 1 / (double)POINTS_PER_SECOND;
    private final static int MULTIPLIER_2X = 2;
    
    private boolean hasMultiplier;
    

    /**
     * Creates class and sets multiplier to 1 by default.
     */
    public ScoreCalc() {
        this.multiplier = 1;
        setCalcStatus(false);
    }

    /**
     * Gets current score.
     * @return score
     */
    public int getScore() {
        return this.score;
    }
    
    /**
     * Gets current calc status: if true, the score shall be calculated.
     * @return calcStatus
     */
    public boolean getCalcStatus() {
    	return this.calcStatus;
    }
    
    /**
     * Sets whether ScoreCalc shall calculate the score (true) or not (false)
     * @param status
     */
    public void setCalcStatus(final boolean status) {
    	this.calcStatus = status;
    }
	
    /**
     * Increments score by an arbitrary amount.
     * Can choose to ignore multiplier (default: apply current multiplier).
     * @param delta
     */
    public void incScore(final int deltaScore) {
        this.score += deltaScore * this.getMultiplier();
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
            this.resetMultiplier();
            //TODO: add score multiplier manager
        }
    }

    /**
     * Increments score by 1 every 4 frames, giving >= 15 points per second.
     *
     */
	public void calculateScore(final double deltaTime) {
		//only calculates score if calcStatus is true
		if (this.getCalcStatus()) {
			this.frameCounter += deltaTime;
	    	if (this.getFrameCounter() >= SECONDS_PER_POINT) {
	    		this.incScore(1);
	    		this.resetFrameCounter();
	    	}
	        this.manageMultiplierTime(deltaTime);
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
    private double getFrameCounter() {
    	return this.frameCounter;
    }
    
    /**
     * Resets frame counter.
     */
    private void resetFrameCounter() {
    	this.frameCounter -= SECONDS_PER_POINT;
    }
}
