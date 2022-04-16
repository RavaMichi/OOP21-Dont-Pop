package game.model;

import game.engine.GameEngine;
import game.graphics.*;
import game.collider.*;
import game.util.Point2D;
import javafx.scene.paint.Color;

public class EnemyBombObj extends AbstractGameObject {
	
	private double timer = 0;
	private Boolean hasExploded = false;
	
	public EnemyBombObj(Point2D position, float timeToActivation, ObjectType type, GameEngine ge) {
		super(position, type, ge);
		this.setRenderer((Renderer) new CircleRenderer(this, 0.25, Color.color(1, 0, 0, 0.7)));
	}

	@Override
	public void update() {
		timer -= this.getGameEngine().getDeltaTime();
		if (timer <= 0) {
			explode();
		}
		if (timer <= -0.25) {
			this.destroy();
		}
		
	}

	private void explode() {
		if (!hasExploded) {
			hasExploded = true;
			this.setCollider((Collider)new CircleCollider(this, 0.25, Point2D.of(0, 0)));
			((CircleRenderer) this.getRenderer()).setOpacity(1);
		}
	}

}
