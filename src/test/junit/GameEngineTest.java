package test.junit;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import game.engine.GameApplication;
import game.engine.GameEngine;
import game.model.AbstractGameObject;
import game.model.PlayerObj;
import game.model.ScoreDisplayObj;
import game.ui.GameScene;
import game.util.Point2D;
import game.util.ScoreCalc;

public class GameEngineTest {

	private GameEngine gameEngine;
	
	@org.junit.Test
	public void testPlayerCreation() {
		this.gameEngine = new GameEngine(null, null);
		final PlayerObj player = new PlayerObj(new Point2D(0.5, 0.5), AbstractGameObject.ObjectType.PLAYER, null);
		assertEquals(player, player);
		
	}
	
	@Test
	public void testPlayerPosition() {
		this.gameEngine = new GameEngine(null, null);
		final Point2D mousePosition = new Point2D(0.5, 0.5);
		assertEquals(mousePosition, this.gameEngine.getPlayerPosition());
	}
	
	@Test
	public void testScoreCalc() {
		this.gameEngine = new GameEngine(null, null);
		ScoreCalc scoreCalc = new ScoreCalc();
		assertEquals(scoreCalc, this.gameEngine.getScoreCalc());
	}
	
	@Test
	public void testScoreDisplay() {
		this.gameEngine = new GameEngine(null, null);
		ScoreDisplayObj scoreDisplay = new ScoreDisplayObj(new Point2D(0.5, 0.03), AbstractGameObject.ObjectType.SCORE, null);
		assertEquals(scoreDisplay, this.gameEngine.getScoreDisplay());
	}
	
	@Test
	public void testGameEngineCreation() {
		this.gameEngine = new GameEngine(null, null);
		assertEquals(this.gameEngine, this.gameEngine.get());
	}
}
