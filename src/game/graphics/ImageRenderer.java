package game.graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
/**
 * Renderer of an image
 */
public class ImageRenderer implements Renderer {

	private Image img;
	private double size;
	/**
	 * @param imgPath
	 * @param size
	 * @param rotation in degrees
	 * Creates a new Renderer with image of imgPath and in-game width of size rotated by rotation angle
	 */
	public ImageRenderer(final String imgPath, double size, double rotation) {
		this.img = new Image(imgPath, false);
		this.size = size;
		this.rotate(rotation);
	}
	
	@Override
	public void paint(GraphicsContext gc) {
		// TODO Auto-generated method stub
	}
	
	public void rotate(final double degrees) {
		
	}
}
