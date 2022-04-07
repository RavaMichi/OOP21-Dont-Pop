package game.graphics;

import java.util.List;

import game.engine.GameEngine;
import javafx.scene.canvas.GraphicsContext;

public class AnimationRenderer implements Renderer {

	private final List<ImageRenderer> frames;
	private double animationTime;
	private boolean loop;
	private final GameEngine ge;
	
	public AnimationRenderer(final List<ImageRenderer> images, double speed, boolean loop) {
		if (images == null || images.size() == 0) throw new IllegalArgumentException("images can't be null or empty.");
		this.frames = images;
		this.animationTime = 1/speed;
		this.loop = loop;
		this.ge = images.get(0).getGameObject().getGameEngine();
	}
	
	@Override
	public void paint(GraphicsContext gc) {
		// TODO Auto-generated method stub

	}

}
