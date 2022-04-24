package game.model;

import game.engine.GameEngine;
import game.graphics.*;
import game.collider.*;
import game.util.Point2D;
import javafx.scene.paint.Color;

/**
 * This class models an explosion.
 */
public class EnemyBombObj extends AbstractGameObject {
	
	private double timer;	//0
	private Boolean hasExploded = false;
	
	public EnemyBombObj(final Point2D position, final float timeToActivation, final ObjectType type, final GameEngine ge) {
		super(position, type, ge);
		this.setRenderer((Renderer) new CircleRenderer(this, 0.25, Color.color(1, 0, 0, 0.5)));
		this.timer = timeToActivation;
	}

	@Override
	public void update() {
		this.timer -= this.getGameEngine().getDeltaTime();
		if (this.timer <= 0) {
			explode();
		}
		if (this.timer <= -0.25) {
			this.destroy();
		}

	}

	private void explode() {
		if (!this.hasExploded) {
			this.hasExploded = true;
			this.setCollider((Collider) new CircleCollider(this, 0.25, Point2D.of(0, 0)));
			((CircleRenderer) this.getRenderer()).setOpacity(1);
		}
	}

}
