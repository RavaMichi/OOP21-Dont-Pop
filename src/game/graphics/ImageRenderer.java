package game.graphics;

import game.model.AbstractGameObject;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
/**
 * Renderer of an image
 */
public class ImageRenderer implements Renderer {

	private Image img;
	private String imgPath;
	private final AbstractGameObject obj;
	private double size;
	/**
	 * @param imgPath
	 * @param size
	 * @param rotation in degrees
	 * Creates a new Renderer with image of imgPath and in-game width of size rotated by rotation angle
	 */
	public ImageRenderer(final AbstractGameObject obj, final String imgPath, double size, double rotation) {
		this.obj = obj;
		this.rotate(rotation);
		this.size = size;
	}
	
	@Override
	public void paint(GraphicsContext gc) {
        gc.drawImage(img, size, size);
	}
	/**
	 * @param degrees
	 * Rotates this image by degrees angle
	 */
	public void rotate(final double degrees) {
		ImageView iv = new ImageView(new Image(imgPath));
		iv.setRotate(degrees);
		SnapshotParameters param = new SnapshotParameters();
		param.setFill(Color.TRANSPARENT);
		this.img = iv.snapshot(param, null);
	}
}
