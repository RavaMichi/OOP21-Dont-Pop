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
    
	private Leaderboard leaderboard;
	
    /**
     * Creates & initializes this class.
     */
    public ScoreManager(final Leaderboard leaderboard) {
    	
    	this.leaderboard = leaderboard;
    	this.leaderboard.load();
    	
    	//this.leaderboard.addToRanking("gigiabaffa", 5);
    	//this.leaderboard.addToRanking("hehehe", 10);
    	
    	//add five entries to leaderboard (test)
//    	this.leaderboard.addToRanking("I'm a pro player", 1000);
//    	this.leaderboard.addToRanking("Gettin' second here", 500);
//    	this.leaderboard.addToRanking("Yeeeet I'm third", 250);
//    	this.leaderboard.addToRanking("At least I'm not last", 100);
//    	this.leaderboard.addToRanking("Noob here :(", 50);
    	
    	//save in file after adding current record
    	this.leaderboard.save();
    }

    /**
     * Gets a copy of the ranking list inside Leaderboard.
     * @return current ranking
     */
    public List<Pair<String,Integer>> getRanking() {
    	return this.leaderboard.getRanking();
    }
    
    /**
     * Returns to menu.
     */
    public void menu() {
    	//TODO: call menu method of application
    	//ScoreScene will call this method
    }
    
}
