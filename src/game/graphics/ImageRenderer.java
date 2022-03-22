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
	private double imgRatio = 1;
	/**
	 * @param obj - the game object linked to this renderer
	 * @param imgPath
	 * @param size
	 * @param rotation in degrees
	 * Creates a new Renderer with image of imgPath and in-game width of size rotated by rotation angle
	 */
	public ImageRenderer(final AbstractGameObject obj, final String imgPath, double size, double rotation) {
		this.obj = obj;
		this.size = size;
		this.imgPath = imgPath;
		this.rotate(rotation);
	}
	
	@Override
	public void paint(GraphicsContext gc) {
		//incomplete
		int xPos = this.worldToPixel(this.obj.getPosition().getX());
		int yPos = this.worldToPixel(this.obj.getPosition().getY());
        gc.drawImage(img, xPos, yPos, img.getWidth(), img.getHeight());
	}
	/**
	 * @param degrees
	 * Rotates this image by degrees angle
	 */
	public void rotate(final double degrees) {
		ImageView iv = new ImageView(new Image(imgPath, size, size, true, false));
		iv.setRotate(degrees);
		SnapshotParameters param = new SnapshotParameters();
		param.setFill(Color.TRANSPARENT);
		this.img = iv.snapshot(param, null);
	}
}
