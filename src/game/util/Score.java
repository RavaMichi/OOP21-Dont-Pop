package game.util;

/**
 * Manages final score, that will be displayed both during gameplay and after gameover.
 * Differs from ScoreManager, which manages GUI-related aspects of score displaying
 */
public class Score {
	private int score;
    private int multiplier;
    private int frameCounter;
    private final static int FRAMES_PER_SCORE = 4;

    /**
     * Creates class and sets multiplier to 1 by default.
     */
    public Score() {
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
     * Increments score every 4 frames, giving >=15 points per second.
     */
    public void incScore() {
    	this.incFrameCounter();
    	if (this.getFrameCounter() % FRAMES_PER_SCORE == 0) {
    		//delta = 1 * multiplier --> hence why I left multiplier only
            this.score += multiplier;
            this.resetFrameCounter();
    	}
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
