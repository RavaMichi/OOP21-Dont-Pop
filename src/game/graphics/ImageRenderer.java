package game.graphics;

import application.ImageRenderer.Sprite;
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

	/**
	 * Enumerator representing a sprite. Each sprite has its own preallocated image.
	 * The use of sprites instead of new images is recommended in order to boost rendering performance. 
	 */
	public static enum Sprite {
		//...sprites to add...//
		PLAYER("path/to/player/img");
		
		private final Image img;
		
		private Sprite(final String path) {
			System.out.println("Loading sprite '" + path + "'...");
			this.img = new Image(path);
			System.out.println("Done");
		}
		
		public Image getImage() {
			return this.img;
		}
	}
	
	private Image currentImg;
	private Sprite baseSprite;
	private final AbstractGameObject obj;
	private double size;
	/**
	 * @param obj - the game object linked to this renderer
	 * @param sprite
	 * @param size
	 * @param rotation in degrees
	 * Creates a new Renderer with sprite and in-game width of size rotated by rotation angle
	 */
	public ImageRenderer(final AbstractGameObject obj, final Sprite sprite, double size, double rotation) {
		this.obj = obj;
		this.size = size;
		this.baseSprite = sprite;
		this.currentImg = sprite.getImage();
		this.rotate(rotation);
	}
	
	@Override
	public void paint(GraphicsContext gc) {
		//incomplete
		int xPos = this.worldToPixel(this.obj.getPosition().getX());
		int yPos = this.worldToPixel(this.obj.getPosition().getY());
        gc.drawImage(currentImg, xPos, yPos, currentImg.getWidth(), currentImg.getHeight());
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
		this.currentImg = iv.snapshot(param, null);
	}
}
