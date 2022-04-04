package game.util;

/**
 * Manages final score, that will be displayed both during gameplay and after gameover.
 * Differs from ScoreManager, which manages GUI-related aspects of score displaying
 */
public class ScoreCalc {
	private double score;
    private double multiplier;
    private final static double SCORE_PER_FRAME = 0.25;

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
    public double getScore() {
        return this.score;
    }

    /**
     * Increments score every 4 frames, giving >=15 points per second.
     */
    public void incScore() {
    	this.score += this.getMultiplier() * SCORE_PER_FRAME;
    }

    /**
     * Increments score by an arbitrary amount.
     * Can choose to ignore multiplier (default: apply current multiplier).
     * @param delta
     */
    public void incScore(final double delta, final boolean ignoreMultiplier) {
    	if (ignoreMultiplier) {
    		this.score += delta;
    	} else {
    		this.score += delta * this.getMultiplier();
    	}
    }

    /**
     * Gets current value of multiplier.
     * @return multiplier
     */
    public double getMultiplier() {
        return this.multiplier;
    }
    
    /**
     * Sets multiplier to any value.
     * @param multiplier
     */
    public void setMultiplier(final double multiplier) {
        this.multiplier = multiplier;
    }

    /**
     * Resets multiplier back to 1.
     */
    public void resetMultiplier() {
        this.multiplier = 1;
    }
}
