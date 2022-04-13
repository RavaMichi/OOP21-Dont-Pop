package test.junit;
import static org.junit.Assert.*;

import game.collider.*;
import game.model.AbstractGameObject;
import game.util.Point2D;

public class Test {
	
	private AbstractGameObject player;
	
	private void initPlayer() {
		//creates a game object at the center
		player = new AbstractGameObject(Point2D.of(0.5, 0.5), null, null) {
			@Override
			public void update() {
				//moves horizontally
				this.getPosition().add(Point2D.of(0.1, 0));
			}
			
		};
		//circle of radius 0.2
		player.setCollider(new CircleCollider(player, 0.1));
	}
	
	@org.junit.Test
	public void testCircleCollider() {
		initPlayer();
		var circle = new AbstractGameObject(Point2D.of(1, 0.5), null, null) {
			@Override
			public void update() {
			}
		};
		circle.setCollider(new CircleCollider(circle, 0.05));
		
		//they don't touch
		assertFalse(circle.getCollider().checkCollision((CircleCollider)player.getCollider()));
		player.update();
		player.update();
		assertFalse(circle.getCollider().checkCollision((CircleCollider)player.getCollider()));
		player.update();
		player.update();
		//they touch
		assertTrue(circle.getCollider().checkCollision((CircleCollider)player.getCollider()));
	}
	
	@org.junit.Test
	public void testRayCollider() {
		initPlayer();
		var ray = new AbstractGameObject(Point2D.of(0, 0), null, null) {
			@Override
			public void update() {
			}
		};
		//laser is a diagonal
		ray.setCollider(new RayCollider(ray, Point2D.of(1, 1)));
		
		//they touch
		assertTrue(ray.getCollider().checkCollision((CircleCollider)player.getCollider()));
		player.update();
		assertTrue(ray.getCollider().checkCollision((CircleCollider)player.getCollider()));
		player.update();
		//they don't touch
		assertFalse(ray.getCollider().checkCollision((CircleCollider)player.getCollider()));
	}
}
