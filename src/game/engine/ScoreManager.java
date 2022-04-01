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
    private String player;
    private List<Score> scoreList;
    

    /**
     * Creates & initializes this class.
     */
    public ScoreManager(final int score, final String player) {
    	this.scoreList = new LinkedList<>();
    	this.score = score;
    	this.player = player;
    }

    /**
     * Returns to menu.
     */
    public void menu() {

    }
}
/*
 * 
 */