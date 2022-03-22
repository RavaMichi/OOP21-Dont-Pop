package game.graphics;

/**
 * ScoreScene displays a GUI showing your current score.
 */
public class ScoreScene {

    private ScoreManager scoreManager;
    
    /**
     * Creates & initializes this class.
     */
    public ScoreScene() {
        this.scoreManager = new ScoreManager();
    }
    
    /**
     * @return this ScoreScene
     * Gets this ScoreScene
     */
    public ScoreScene get() {
        //DETAILS NEED TO GET DEFINED BETTER
        //DO I NEED A NEW SCENE CLASS?
        return this;
    }

}
