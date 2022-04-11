package game.model;

import game.engine.GameEngine;
import game.graphics.*;
import game.util.Point2D;
import javafx.scene.paint.Color;

public class ScoreDisplayObj extends AbstractGameObject {
	
	private int mult = 1;

	/*
	 * Requires position, direction and time to activation
	 */
	public ScoreDisplayObj(Point2D position, ObjectType type, GameEngine ge) {
		super(position, type, ge);
		this.setRenderer((Renderer)new TextRenderer(this, "1x Score: 0", 0.1, Color.DARKBLUE));
	}

	@Override
	public void update() {
		// animazione ?
	}

	public void updateScore(int score) {
		((TextRenderer)this.getRenderer()).setText(mult + "x Score: " + score);
		
	}
	
	public void updateMultiplier(int score, int m) {
		mult = m;
		updateScore(score);
	}
	
}
