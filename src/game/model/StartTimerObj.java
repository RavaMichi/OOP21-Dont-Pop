package game.model;

import game.engine.GameEngine;
import game.graphics.*;
import game.util.Point2D;
import javafx.scene.paint.Color;

public class StartTimerObj extends AbstractGameObject {
	
	private double timer;

	/**
	 * Builds a StartTimerObj (class constructor).
	 * @param position
	 * @param size
	 * @param type
	 * @param gameEngine
	 */
	public StartTimerObj(final Point2D position, final double size, final ObjectType type, final GameEngine gameEngine) {
		super(position, type, gameEngine);
		this.setRenderer((Renderer)new TextRenderer(this, "3", size, Color.web("#E60707")));
		timer = 3;
	}

	/**
	 * Makes timer count down second by second.
	 * When time's up, destroys this object (StartTimerObj).
	 */
	@Override
	public void update() {
		timer -= this.getGameEngine().getDeltaTime();
		if (timer >= 2) {
			((TextRenderer)this.getRenderer()).setText("3");
		} else if (timer >= 1) {
			((TextRenderer)this.getRenderer()).setText("2");
		} else if (timer >= 0) {
			((TextRenderer)this.getRenderer()).setText("1");
		} else if (timer >= -1) {
			((TextRenderer)this.getRenderer()).setText("GO!");
		} else {
			this.destroy();
		}
	}

}
