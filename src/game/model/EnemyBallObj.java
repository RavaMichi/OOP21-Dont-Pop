package game.model;

import game.engine.GameEngine;
import game.graphics.*;
import game.collider.*;
import game.util.Point2D;

public class EnemyBallObj extends AbstractGameObject {
	
	private double rotation = 0;
	private double speed = 0.015;
	private double gravity = 0.0004;
	private Point2D velocity;
	
	public EnemyBallObj(Point2D position, ObjectType type, GameEngine ge) {
		super(position, type, ge);
		this.setRenderer((Renderer) new ImageRenderer(this, ImageRenderer.Sprite.THORNBALL, 0.1, 0));
		this.setCollider((Collider)new CircleCollider(this, 0.05, Point2D.of(0, 0)));

		//velocity calculation
		this.velocity = Point2D.of(ge.getPlayerPosition().getX() > position.getX() ? 1 : -1, -0.7);
		this.velocity.normalize();
		this.velocity.mul(speed);
	}

	@Override
	public void update() {
		rotation += 3;
		((ImageRenderer)this.getRenderer()).setRotation(rotation);
		this.getPosition().add(velocity);
		velocity.setY(velocity.getY() + gravity);
		if (this.getPosition().getX() >= 1.5 || this.getPosition().getY() >= 1.5 || this.getPosition().getX() <= -0.5 || this.getPosition().getY() <= -0.5) {
			this.destroy();
		}
		
	}

}