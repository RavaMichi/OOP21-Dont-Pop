package game.model;

import game.collider.*;
import game.engine.GameEngine;
import game.graphics.*;
import game.util.Point2D;
import javafx.scene.paint.Color;

/**
 * This class models a laser.
 */
public class EnemyLineObj extends AbstractGameObject {
	
	private double timer;	//0
	private Boolean hasActivated = false;
	private final Point2D direction;

	/*
	 * Requires position, direction and time to activation
	 */
	public EnemyLineObj(final Point2D position, final Point2D dir, final float timeToActivation, final ObjectType type, final GameEngine ge) {
		super(position, type, ge);
		this.timer = timeToActivation;
		this.direction = dir;
		this.setRenderer((Renderer)new LineRenderer(this, dir, Color.color(1, 0, 0, 0.5), 0.01));
	}

	/**
	 * Updates laser status, activating or destroying it.
	 */
	@Override
	public void update() {
		this.timer -= this.getGameEngine().getDeltaTime();
		if (this.timer <= 0.15) {
			this.activate();
		}
		if (this.timer <= -0.65) {
			this.destroy();
		}

	}

	private void activate() {
		if (!this.hasActivated) {
			this.hasActivated = true;
			this.setCollider((Collider)new RayCollider(this, this.direction));
			this.setRenderer((Renderer)new LineRenderer(this, this.direction, Color.color(1, 0, 0, 1), 0.02));
		}

	}
	
}
