package game.model;

import game.controller.GameEngine;
import game.util.Point2D;

public class PlayerObj extends AbstractGameObject {

	Point2D playerPosition;
	GameEngine gameEngine;
	
	public PlayerObj(Point2D position, ObjectType type, GameEngine ge) {
		// INIZIALIZZA VARIABILI PLAYER
		super(position, type, ge);
		playerPosition = position;
		gameEngine = ge;
	}

	@Override
	void update() {
		// gameEngine.getMousePosition
		
	}
}
