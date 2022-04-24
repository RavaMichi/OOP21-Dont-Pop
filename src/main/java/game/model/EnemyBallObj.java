package game.model;

import game.engine.GameEngine;
import game.graphics.*;
import game.collider.*;
import game.util.Point2D;

/**
 * This class models a thornball.
 */
public class EnemyBallObj extends AbstractGameObject {
	
	private double rotation;	//0
	private final double speed = 0.015;
	private final double gravity = 0.0004;
	private final Point2D velocity;
	
	/**
	 * Class constructor.
	 * @param position
	 * @param type
	 * @param ge
	 */
	public EnemyBallObj(final Point2D position, final ObjectType type, final GameEngine ge) {
		super(position, type, ge);
		this.setRenderer((Renderer) new ImageRenderer(this, ImageRenderer.Sprite.THORNBALL, 0.1, 0));
		this.setCollider((Collider) new CircleCollider(this, 0.05, Point2D.of(0, 0)));

		//velocity calculation
		this.velocity = Point2D.of(ge.getPlayerPosition().getX() > position.getX() ? 1 : -1, -0.7);
		this.velocity.normalize();
		this.velocity.mul(this.speed);
	}

	/**
	 * Updates thornball speed and position.
	 */
	@Override
	public void update() {
		this.rotation += 3;
		((ImageRenderer) this.getRenderer()).setRotation(this.rotation);
		this.getPosition().add(this.velocity);
		this.velocity.setY(this.velocity.getY() + this.gravity);
		if (this.getPosition().getX() >= 1.5 || this.getPosition().getY() >= 1.5 || this.getPosition().getX() <= -0.5 || this.getPosition().getY() <= -0.5) {
			this.destroy();
		}

	}

}
