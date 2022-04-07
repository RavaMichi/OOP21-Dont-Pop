package game.model;

import game.collider.CircleCollider;
import game.engine.GameEngine;
import game.graphics.ImageRenderer;
import game.util.Point2D;

public class PlayerObj extends AbstractGameObject {

	private Point2D movement;
	private GameEngine gameEngine;
	private static double radius = 0.1;
	private static double size = 1;
	private static Point2D offset = new Point2D(0.1, 0.1);
	
	private static double speed = 1;

	public PlayerObj(Point2D position, ObjectType type, GameEngine ge) {
		super(position, type, ge);
		gameEngine = ge;
		this.setCollider(new CircleCollider(this, radius));
		this.setRenderer(new ImageRenderer(this, ImageRenderer.Sprite.PLAYER, size, 0, offset));
	}

	/**
	 * Change the player's speed
	 * @param newSpeed
	 */
	public void setSpeed(double newSpeed) {
		speed = newSpeed;
	}
	
	@Override
	public void update() {
		movement = gameEngine.getMousePosition();
		movement.sub(this.getPosition());
		if(movement.getMagnitude() <= speed) {
			this.getPosition().set(movement);
		} else {
			movement.normalize();
			movement.mul(speed);
			this.getPosition().add(movement);
		}
		if (movement.getX() > 0) {
			// rotation = 10°
		} else if (movement.getX() == 0) {
			// rotation = 0°
		} else {
			// https://www.youtube.com/watch?v=2BrIPhuUxBQ
		}
	}
}
