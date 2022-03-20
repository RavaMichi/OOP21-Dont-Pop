package game.graphics;

import javafx.scene.canvas.GraphicsContext;
/**
 * Graphic component of the game object. Can draw images or lines, depending on implementation
 */
public interface Renderer {
	/**
	 * @param The GraphicsContext instance where to draw
	 * Draws on gc this rendering information
	 */
	void paint(GraphicsContext gc);
	
}
