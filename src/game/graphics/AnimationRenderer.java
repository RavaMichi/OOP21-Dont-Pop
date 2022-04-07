package game.graphics;

import java.util.List;

import game.engine.GameEngine;
import javafx.scene.canvas.GraphicsContext;
/**
 * Renderer of an animation - a sequence of image renderers
 */
public class AnimationRenderer implements Renderer {

	private final List<ImageRenderer> frames;
	private double animationTime;
	private boolean loop;
	private final GameEngine ge;
	
	private double timer = 0;
	private int index = 0;
	/**
	 * Creates a new Renderer representing an sequence of images.
	 * @param images to display in order
	 * @param speed - frames per seconds
	 * @param loop - enables the looping of the animation
	 */
	public AnimationRenderer(final List<ImageRenderer> images, double speed, boolean loop) {
		if (images == null || images.size() == 0) throw new IllegalArgumentException("images can't be null or empty.");
		this.frames = images;
		this.animationTime = 1/speed;
		this.loop = loop;
		this.ge = images.get(0).getGameObject().getGameEngine();
	}
	
	@Override
	public void paint(GraphicsContext gc) {
		frames.get(index).paint(gc);
		incIndex();
	}
	/**
	 * Used to update the sequence
	 */
	private void incIndex() {
		if (this.index < this.frames.size()) {
			//update timer
			this.timer += ge.getDeltaTime();
			//index increment condition
			if (this.timer >= this.animationTime) {
				this.timer -= this.animationTime;
				
				this.index++;
				if (this.loop && this.index > this.frames.size()) {
					//loop
					this.index = 0;
				}
			}
		}
	}

}
