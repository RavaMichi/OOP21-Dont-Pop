package game.model;

import game.collider.CircleCollider;
import game.engine.GameEngine;
import game.graphics.*;
import game.util.Point2D;

public class PlayerObj extends AbstractGameObject {

	private Point2D movement;
	private GameEngine gameEngine;
	private double rotation = 0; // In degrees
	private static double radius = 0.037;
	private static double size = 0.075;
	
	private static double speed = 0.075;

	public PlayerObj(Point2D position, ObjectType type, GameEngine ge) {
		super(position, type, ge);
		gameEngine = ge;
		this.setCollider(new CircleCollider(this, radius, Point2D.of(0, -size / 3)));
		this.setRenderer(new ImageRenderer(this, ImageRenderer.Sprite.PLAYER, size, 0));
	}

	/**
	 * Change the player's speed
	 * @param newSpeed
	 */
	public void setSpeed(double newSpeed) {
		speed = newSpeed;
	}
	
	/**
	 * Kills the player and ends the game
	 */
	public void die() {
		//this.setRenderer(new AnimationRenderer(null, 0, false)); animation renderer è un mistero
		this.getGameEngine().endGame();
	}
	
	@Override
	public void update() {
		movement = gameEngine.getMousePosition();
		movement.sub(this.getPosition());
		if(movement.getMagnitude() <= speed) {
			this.getPosition().add(movement);
		} else {
			movement.normalize();
			movement.mul(speed);
			this.getPosition().add(movement);
		}
		if (movement.getX() < 0) {
			rotation = -3;
		} else if (movement.getX() == 0) {
			rotation = 0;
		} else {
			rotation = 3;
		}
		((ImageRenderer)this.getRenderer()).setRotation(rotation);
	}
}
