package game.model;

import java.util.ArrayList;
import java.util.List;
import game.collider.CircleCollider;
import game.engine.GameEngine;
import game.engine.AudioManager.Sound;
import game.graphics.*;
import game.util.Point2D;

public class PlayerObj extends AbstractGameObject {

	private Point2D movement;
	private double rotation = 0; // In degrees
	private static double radius = 0.037;
	private static double size = 0.075;
	private List<ImageRenderer> animationFrames;
	private double deathTimer = 1.2;
	
	private boolean isDead = false;
	private static double speed = 0.075;

	public PlayerObj(Point2D position, ObjectType type, GameEngine ge) {
		super(position, type, ge);
		this.setCollider(new CircleCollider(this, radius, Point2D.of(0, -size / 3)));
		this.setRenderer(new ImageRenderer(this, ImageRenderer.Sprite.PLAYER, size, this.rotation));
		
		//calculates the proportional size of the player animation images
		double size2 = size * ImageRenderer.Sprite.POP_ANIMATION_1.getImage().getWidth() / ImageRenderer.Sprite.PLAYER.getImage().getWidth();
		this.animationFrames = new ArrayList<>();
		this.animationFrames.add(new ImageRenderer(this, ImageRenderer.Sprite.POP_ANIMATION_1, size2, 0));
		this.animationFrames.add(new ImageRenderer(this, ImageRenderer.Sprite.POP_ANIMATION_2, size2, 0));
		this.animationFrames.add(new ImageRenderer(this, ImageRenderer.Sprite.POP_ANIMATION_3, size2, 0));
		this.animationFrames.add(new ImageRenderer(this, ImageRenderer.Sprite.POP_ANIMATION_4, size2, 0));

	}

	/**
	 * Change the player's speed
	 * @param newSpeed
	 */
	public void setSpeed(double newSpeed) {
		speed = newSpeed;
	}
	
	/**
	 * Kills the player, and sets the death animation
	 */
	public void die() {
		if (!this.isDead) {
			this.getGameEngine().play(Sound.POP, 0.8);
			this.setRenderer(new AnimationRenderer(this.animationFrames, 20, false));
			this.isDead = true;
		}
	}
	/**
	 * Updates the death status, after the animation it ends the game
	 */
	public void updateDeath() {
		this.deathTimer -= this.getGameEngine().getDeltaTime();
		if (deathTimer <= 0) {
			this.getGameEngine().endGame();
		}
	}
	
	@Override
	public void update() {
		if (this.isDead) {
			updateDeath();
			return;
		}
		movement = this.getGameEngine().getMousePosition();
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
	
	public void setBaloonImage() {
		if (!this.isDead) {
			((ImageRenderer)this.getRenderer()).setSprite(ImageRenderer.Sprite.PLAYER);
		}
	}

	public void setGoldenBaloonImage() {
		if (!this.isDead) {
			((ImageRenderer)this.getRenderer()).setSprite(ImageRenderer.Sprite.GOLDEN_PLAYER);
		}	
	}

	public void setShieldImage() {
		if (!this.isDead) {
			((ImageRenderer)this.getRenderer()).setSprite(ImageRenderer.Sprite.SHIELD_PLAYER);
		}
	}
	
	public boolean isDead() {
		return this.isDead;
	}
}
