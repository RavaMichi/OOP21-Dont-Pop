package game.model;

import game.collider.*;
import game.engine.GameEngine;
import game.graphics.*;
import game.util.Point2D;

public class PowerUpObj extends AbstractGameObject {

	private double timer = 0;
	
	public PowerUpObj(Point2D position, ObjectType type, GameEngine gameEngine) {
		super(position, type, gameEngine);
		if (type == AbstractGameObject.ObjectType.PWRUP_SHIELD) {
			this.setRenderer((Renderer) new ImageRenderer(this, ImageRenderer.Sprite.PWRUP_SHIELD, 0.1, 0));
		} else if (type == AbstractGameObject.ObjectType.PWRUP_MULTIPLIER) {
			this.setRenderer((Renderer) new ImageRenderer(this, ImageRenderer.Sprite.PWRUP_MULTIPLIER, 0.1, 0));
		} else if (type == AbstractGameObject.ObjectType.PWRUP_SWEEPER) {
			this.setRenderer((Renderer) new ImageRenderer(this, ImageRenderer.Sprite.PWRUP_SWEEPER, 0.1, 0));
		}
		this.setCollider((Collider)new CircleCollider(this, 0.1));
	}

	@Override
	public void update() {
		timer += this.getGameEngine().getDeltaTime();
		if (timer >= 5) {
			this.destroy();
		}
	}

}
