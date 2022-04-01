package game.engine;

import java.util.LinkedList;
import java.util.List;

import game.ui.ScoreScene;
import game.util.Score;

/**
 * ScoreManager calculates the current score.
 * Modifica i valori della tabella
 */
public class ScoreManager {
	
    private int score;
    private List<Score> scoreList;

    /**
     * Creates & initializes this class.
     */
    public ScoreManager() {
    	this.scoreList = new LinkedList<>();
    }

    /**
     * TODO: WRITE A JAVADOC FOR THIS CLASS.
     */
    public void menu() {

    }
}
/*
 * 
 */