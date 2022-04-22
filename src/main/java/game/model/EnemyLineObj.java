package game.model;

import game.collider.*;
import game.engine.GameEngine;
import game.graphics.*;
import game.util.Point2D;
import javafx.scene.paint.Color;

public class EnemyLineObj extends AbstractGameObject {
	
	private double timer = 0;
	private Boolean hasActivated = false;
	private Point2D direction;

	/*
	 * Requires position, direction and time to activation
	 */
	public EnemyLineObj(Point2D position, Point2D dir, float timeToActivation, ObjectType type, GameEngine ge) {
		super(position, type, ge);
		timer = timeToActivation;
		direction = dir;
		this.setRenderer((Renderer)new LineRenderer(this, dir, Color.color(1, 0, 0, 0.5), 0.01));
	}

	@Override
	public void update() {
		timer -= this.getGameEngine().getDeltaTime();
		if (timer <= 0) {
			activate();
		}
		if (timer <= -1) {
			this.destroy();
		}
		
	}

	private void activate() {
		if (!hasActivated) {
			hasActivated = true;
			this.setCollider((Collider)new RayCollider(this, direction));
			this.setRenderer((Renderer)new LineRenderer(this, direction, Color.color(1, 0, 0, 1), 0.02));
		}
		
	}
	
}
