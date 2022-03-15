package game.controller;

import java.util.ArrayList;
import java.util.List;

import game.model.AbstractGameObject;

/* 
 * GameEngine is the class that makes the game work
*/

public class GameEngine {
	
//	private Player player;
	private List<AbstractGameObject> enemies;
	private List<AbstractGameObject> pwr;
//	private ScoreManager score;
	
//	SpawnManager spawnmanager;
	
	public GameEngine() {
		this.enemies = new ArrayList<>(20);
		this.pwr = new ArrayList<>();	//default size: 10
	}
	
	private static final long TIME_CONST_60_HZ_MS = (1000/60);
	//private static final long START_TIME = 0;
	//game time
	private double time = 0;
	
	/*
	 * updates (increments) game time
	 */
	private void incTime() {
		this.time += ((double) TIME_CONST_60_HZ_MS)/1000;
	}
	
	public void startGameLoop() {
		while(true) {
			//interval between "frames"
			long start_time = System.currentTimeMillis();
			
			//updates game time
			this.incTime();
			
			long end_time = System.currentTimeMillis();
			
			try {
				Thread.sleep(TIME_CONST_60_HZ_MS-(end_time - start_time));
			} catch (Exception e) {}
		}
	}
	
	public void instantiate(AbstractGameObject obj) {
		if(obj.getType().isEnemy()) {
			this.enemies.add(obj);
		} else if(obj.getType().isPowerUp()) {
			this.pwr.add(obj);
		}
		//player does not get instantiated
	}
	
	public void destroy(AbstractGameObject obj) {
		if(this.enemies.contains(obj)) {
			this.enemies.remove(obj);
		} else if(this.pwr.contains(obj)) {
			this.pwr.remove(obj);
		}
	}
	
	public void applyPwrUp(AbstractGameObject pwrup) {
		
	}
	
	/*
	 * @return time in seconds
	 */
	public double getTime() {
		return this.time;
	}
	
//	public static void main(String args[]) {
//		System.out.println("" + START_TIME);
//		System.out.println("" + this.getTime());
//	}
}