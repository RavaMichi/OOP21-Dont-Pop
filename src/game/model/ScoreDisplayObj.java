package game.model;

import game.engine.GameEngine;
import game.graphics.*;
import game.util.Point2D;
import game.util.ScoreCalc;
import javafx.scene.paint.Color;

public class ScoreDisplayObj extends AbstractGameObject {
	
	private ScoreCalc calc;
	/*
	 * Requires position, direction and time to activation
	 */
	public ScoreDisplayObj(Point2D position, ObjectType type, GameEngine ge) {
		super(position, type, ge);
		this.calc = ge.getScoreCalc();
		this.setRenderer((Renderer)new TextRenderer(this, "1x Score: 0", 0.04, Color.WHITE, 0.0025, Color.BLACK));
	}

	@Override
	public void update() {
		var t = (TextRenderer)this.getRenderer();
		if (this.calc.getMultiplier() > 1) {
			t.setFillColor(Color.GOLD);
			t.setText(calc.getMultiplier() + "x " + calc.getScore());
		} else {
			t.setFillColor(Color.WHITE);
			t.setText("" + calc.getScore());
		}
	}
	
}
