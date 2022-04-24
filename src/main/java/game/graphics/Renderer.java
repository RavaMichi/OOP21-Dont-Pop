package game.graphics;

import javafx.scene.canvas.GraphicsContext;

/**
 * Graphic component of the game object. Can draw images or lines, depending on implementation
 */
public interface Renderer {
	
	/**
	 * Draws on gc this rendering information.
	 * @param gc - The GraphicsContext instance where to draw
	 */
	void paint(GraphicsContext gc);
}
