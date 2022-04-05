package game.model;

import game.engine.GameEngine;
import game.util.Point2D;

public class EnemyLineObj extends AbstractGameObject {
	
	Point2D point;
	Point2D direction;
	float timer;

	/*
	 * Requires position, direction and time to activation
	 */
	public EnemyLineObj(Point2D position, Point2D dir, float timeToActivation, ObjectType type, GameEngine ge) {
		super(position, type, ge);
		point = position;
		direction = dir;
		timer = timeToActivation;
	}

	@Override
	public void update() {
		// fa lampeggiare la linea e poi attiva il collider
		
	}
	
}
