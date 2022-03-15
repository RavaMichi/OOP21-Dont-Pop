package game.controller;

import java.util.List;

import game.model.AbstractGameObject;

/* 
 * GameEngine is the class that makes the game work
*/

public class GameEngine {
	
	private Player player;
	private List<AbstractGameObject> enemies;
	private List<AbstractGameObject> pwr;
	private ScoreManager score;
	
	private final double TIME_CONST_60_HZ = (1/60);
	
	public void startGameLoop() {
		
	}
	
	public void instantiate(AbstractGameObject obj) {
		
	}
	
	public void destroy(AbstractGameObject obj) {
		
	}
	
	public void applyPwrUp(AbstractGameObject pwrup) {
		
	}
	
	//? maybe int is not the right return type
	public int getTime() {
		
		//! temporary return type
		return 0;
	}
}