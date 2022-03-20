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
	 * Creates a new Renderer with image of imgPath and in-game width of size
	 */
	public ImageRenderer(final String imgPath, double size) {
		this.img = new Image(imgPath, false);
		this.size = size;
	}
	
	@Override
	public void paint(GraphicsContext gc) {
		// TODO Auto-generated method stub
	}
	
	public void rotate() {
		
	}
}
