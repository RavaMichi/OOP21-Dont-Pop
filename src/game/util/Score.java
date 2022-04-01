package game.util;

/**
 * Manages final score, that will be displayed both during gameplay and after gameover.
 * Differs from ScoreManager, which manages GUI-related aspects of score displaying
 */
public class Score {
	private int score;
    private int multiplier;

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
