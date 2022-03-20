package game.model;

import game.controller.GameEngine;
import game.util.Point2D;

class EnemyProjectileObj extends AbstractGameObject {

	Point2D currentPosition;
	Point2D direction;
	float velocity;
	
	/*
	 * Requires initial position, direction and speed of projectile
	 */
	public EnemyProjectileObj(Point2D position, Point2D dir, float v, ObjectType type, GameEngine ge) {
		super(position, type, ge);
		currentPosition = position;
		direction = dir;
		velocity = v;
	}
	
	@Override
	void update() {
		// va dritto nella sua direzione
		
	}
	
	

}