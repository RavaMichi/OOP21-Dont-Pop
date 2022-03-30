package game.model;

import game.controller.GameEngine;
import game.util.Point2D;

public class EnemyBombObj extends AbstractGameObject {
	
	Point2D point;
	float timer;
	
	/*
	 * Requires position, direction and time to activation
	 */
	public EnemyBombObj(Point2D position, float timeToActivation, ObjectType type, GameEngine ge) {
		super(position, type, ge);
		point = position;
		timer = timeToActivation;
	}

	@Override
	void update() {
		// genera UI esplosione, dopo *timer* secondi attiva collider
		
	}

}
