package game.engine;

import java.util.List;

import game.util.Leaderboard;
import game.util.ScoreCalc;
import game.util.Pair;

/**
 * ScoreManager calculates the current score.
 * Modifica i valori della tabella
 */
public class ScoreManager {
    
	private int score;
	private String playerName;
	private Leaderboard leaderboard;
	private GameApplication application;
	
    /**
     * Creates & initializes this class.
     * @param playerName
     * @param score
     * @param leaderboard
     * @param application
     */
    public ScoreManager(final String playerName, final int score, final Leaderboard leaderboard, final GameApplication application) {
    	this.score = score;
    	this.playerName = playerName;
    	this.leaderboard = leaderboard;
    	this.application = application;
    	
    	this.leaderboard.load();
    	this.leaderboard.addToRanking(this.playerName, this.score);
    	//save in file after adding current record
    	this.leaderboard.save();
    }
    
    /**
     * This constructor is useful when all you want to do is view current leaderboard, without editing it.
     * @param leaderboard
     * @param application
     */
    public ScoreManager(final Leaderboard leaderboard, final GameApplication application) {
    	this.score = 0;
    	this.playerName = "Viewing current leaderboard...";
    	this.leaderboard = leaderboard;
    	this.application = application;
    	
    	this.leaderboard.load();
    }

    /**
     * Gets a copy of the ranking list inside Leaderboard.
     * @return current ranking
     */
    public List<Pair<String,Integer>> getRanking() {
    	return this.leaderboard.getRanking();
    }
    
    public String getPlayerName() {
    	return this.playerName;
    }
    
    public int getScore() {
    	return this.score;
    }
    
    /**
     * Returns to menu.
     * @throws Exception 
     */
    public void menu() throws Exception {
    	//TODO: call menu method of application
    	this.application.menu();
    }
    
}
