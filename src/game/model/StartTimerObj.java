package game.model;

import game.engine.GameEngine;
import game.graphics.*;
import game.util.Point2D;
import javafx.scene.paint.Color;

public class StartTimerObj extends AbstractGameObject {
	
	private double timer;

	public StartTimerObj(Point2D position, double size, ObjectType type, GameEngine gameEngine) {
		super(position, type, gameEngine);
		this.setRenderer((Renderer)new TextRenderer(this, " 3", size, Color.DARKRED));
		timer = 3;
//		System.out.println("Created Timer!");
	}

	@Override
	public void update() {
		timer -= this.getGameEngine().getDeltaTime();
		if (timer >= 2) {
			((TextRenderer)this.getRenderer()).setText(" 3");
		} else if (timer >= 1) {
			((TextRenderer)this.getRenderer()).setText(" 2");
		} else if (timer >= 0) {
			((TextRenderer)this.getRenderer()).setText(" 1");
		} else if (timer >= -1) {
			((TextRenderer)this.getRenderer()).setText("GO!");
		} else {
			this.destroy();
		}
	}

}
