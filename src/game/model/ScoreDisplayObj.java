package game.model;

import game.engine.GameEngine;
import game.graphics.*;
import game.util.Point2D;
import game.util.ScoreCalc;
import javafx.scene.paint.Color;

public class ScoreDisplayObj extends AbstractGameObject {
	
	private ScoreCalc calc;
	private String prefix = "";
	/*
	 * Requires position, direction and time to activation
	 */
	public ScoreDisplayObj(Point2D position, ObjectType type, GameEngine ge) {
		super(position, type, ge);
		this.calc = ge.getScoreCalc();
		
		var t = new TextRenderer(this, "1x Score: 0", 0.04, Color.WHITE, 0.0025, Color.BLACK);
		this.setRenderer(t);
		this.calc.onMultiplierStart(() -> {
			t.setFillColor(Color.GOLD);
			this.prefix = "2x ";
		});
		this.calc.onMultiplierEnd(() -> {
			t.setFillColor(Color.WHITE);
			this.prefix = "";
		});
	}

	@Override
	public void update() {
		var t = (TextRenderer)this.getRenderer();
		t.setText(this.prefix + calc.getScore());
	}
	
}
