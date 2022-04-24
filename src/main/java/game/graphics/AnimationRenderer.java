package game.graphics;

import java.util.List;

import game.engine.GameEngine;
import javafx.scene.canvas.GraphicsContext;

/**
 * Renderer of an animation - a sequence of image renderers.
 */
public class AnimationRenderer implements Renderer {

	private final List<ImageRenderer> frames;
	private double animationTime;
	private boolean loop;
	private final GameEngine ge;
	
	private double timer;	//0
	private int index;		//0
	
	/**
	 * Creates a new Renderer representing an sequence of images.
	 * @param images to display in order
	 * @param speed - frames per seconds
	 * @param loop - enables the looping of the animation
	 */
	public AnimationRenderer(final List<ImageRenderer> images, final double speed, final boolean loop) {
		if (images == null || images.isEmpty()) {
			throw new IllegalArgumentException("images can't be null or empty.");
		}
		this.frames = images;
		this.setSpeed(speed);
		this.enableLoop(loop);
		this.ge = this.frames.get(0).getGameObject().getGameEngine();
	}
	
	/**
	 * Renders an animation.
	 * @param gc
	 */
	@Override
	public void paint(final GraphicsContext gc) {
		final var imgR = this.frames.get(index);
		if (imgR != null) {
			imgR.paint(gc);
		}
		this.incIndex();
	}
	
	/**
	 * Used to update the sequence.
	 */
	private void incIndex() {
		this.timer += this.ge.getDeltaTime();
		//index increment condition
		if (this.timer >= this.animationTime) {
			this.timer -= this.animationTime;

			this.index++;
			if (this.index >= this.frames.size()) {
				this.index = this.loop ? 0 : this.frames.size() - 1;
			}
		}
	}
	
	/**
	 * Sets the speed of the animation.
	 * @param speed
	 */
	public void setSpeed(final double speed) {
		this.animationTime = 1 / speed;
	}
	
	/**
	 * Enables/disables the looping of the animation.
	 * @param loop
	 */
	public void enableLoop(final boolean loop) {
		this.loop = loop;
	}
	
	/**
	 * Sets the current frame to index frame.
	 * @param index - must be within range, or it will be ignored
	 */
	public void goToFrame(final int index) {
		if (index >= 0 && index < this.frames.size()) {
			this.index = index;
			this.timer = 0;
		}
	}
}
