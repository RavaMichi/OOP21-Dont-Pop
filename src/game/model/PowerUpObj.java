package game.model;

import game.model.GameEngine;
import game.util.Point2D;

class PowerUpObj extends AbstractGameObject {

	public PowerUpObj(Point2D position, ObjectType type, GameEngine gameEngine) {
		super(position, type, gameEngine);
	}

	@Override
	public void update() {
		return; // da finire
	}

}
