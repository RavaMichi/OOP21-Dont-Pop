package game.graphics;

import game.model.ScoreManager;
import javafx.scene.Scene;

/**
 * ScoreScene displays a GUI showing your current score.
 * Mostra la tabella
 */
public class ScoreScene {

    private final Scene scene;
    private ScoreManager scoreManager;

    /**
     * Creates & initializes this class.
     */
    public ScoreScene(final ScoreManager scoreManager) {
    	this.scoreManager = scoreManager;
    }

    /*
     * Find a Table template to display scores
     */
    
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
