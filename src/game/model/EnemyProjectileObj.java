package game.model;

import game.engine.GameEngine;
import game.graphics.*;
import java.util.*;
import game.collider.*;
import game.util.Point2D;

public class EnemyProjectileObj extends AbstractGameObject {
	
	private static final double SIZE = 0.07;
	
	private Point2D velocity;
	private Set<Point2D> points = new HashSet<Point2D>();
	
	/*
	 * Requires initial position, direction and speed of projectile
	 */
	public EnemyProjectileObj(Point2D position, Point2D dir, float speed, ObjectType type, GameEngine ge) {
		super(position, type, ge);
		velocity = dir;
		velocity.normalize();
		velocity.mul(speed / 60);
		generatePoints();
		this.setCollider((Collider)new PointsCollider(this, points));
		double angle = (Math.atan(velocity.getY()/velocity.getX())) * (180/Math.PI);
		if (velocity.getX() < 0) {
			angle += 180;
		}
		this.setRenderer((Renderer)new ImageRenderer(this, ImageRenderer.Sprite.BULLET, SIZE, angle));
		// The formula is used to get degrees from a 2D vector using arctan of velocity, then converting the result from radians to degrees [* (180/Math.PI)]
	}
	
	/**
	 * Calculate the positions of the collion points of the bullet, appropriately rotated to match the bullet's trajectory
	 * 
	 * Involves some simple vector math
	 */
	private void generatePoints() {
		// POINT 1 = same direction as velocity, length 0.015
		Point2D p1 = Point2D.copyOf(velocity);
		p1.normalize();
		p1.mul(SIZE/2);
		
		// POINT 2 = sum of 2 vectors, one is the opposite of p1 and the other is the p2 offset, equal to p1/2 rotated by 90°
		Point2D p2 = Point2D.copyOf(p1);
		Point2D offset = Point2D.copyOf(p2);
		p2.mul(-1);
		offset.mul(0.5);
		offset.set(new Point2D(-offset.getY(), offset.getX()));
		p2.add(offset);
		
		// POINT 3 = sum of 2 vectors, one is the opposite of p1 and the other is the p3 offset, equal to p1/2 rotated by -90° ( = p2 offset * -1)
		Point2D p3 = Point2D.copyOf(offset);
		p3.mul(-1);
		offset.mul(-1);
		p3.add(offset);
		
		// ADD the points to set
		points.add(p1);
		points.add(p2);
		points.add(p3);
	}

	@Override
	public void update() {
		this.getPosition().add(velocity);
		if (this.getPosition().getX() >= 1.5 || this.getPosition().getY() >= 1.5 || this.getPosition().getX() <= -0.5 || this.getPosition().getY() <= -0.5) {
			this.destroy();
		}
	}
	
	

}
