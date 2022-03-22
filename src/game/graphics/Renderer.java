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
	/**
	 * @param worldPosition
	 * @return the screen position
	 * Converts the world position to screen position
	 */
	default int worldToPixel(double worldPosition) {
		return 0;//todo
	}
}
