package game.model;

import game.engine.GameEngine;
import game.graphics.*;
import game.util.Point2D;
import game.util.ScoreCalc;
import javafx.scene.paint.Color;

public class ScoreDisplayObj extends AbstractGameObject {
	
	private final ScoreCalc calc;
	private String prefix = "";
	/*
	 * Requires position, direction and time to activation
	 */
	public ScoreDisplayObj(final Point2D position, final ObjectType type, final GameEngine ge) {
		super(position, type, ge);
		this.calc = ge.getScoreCalc();
		
		final var t = new TextRenderer(this, "1x Score: 0", 0.04, Color.WHITE, 0.0025, Color.BLACK);
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
		final var t = (TextRenderer)this.getRenderer();
		t.setText(this.prefix + calc.getScore());
	}
	
}
