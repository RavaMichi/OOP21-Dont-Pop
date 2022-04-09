package game.graphics;

import game.engine.GameApplication;
import game.model.AbstractGameObject;
import game.util.Point2D;
import javafx.application.Platform;
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
		PLAYER("/baloon.png"),
		BULLET("/bullet.png"),
		THORNBALL("/thornball.png");
		
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
	private Point2D offset;
	private double size;
	/**
	 * @param obj - the game object linked to this renderer
	 * @param sprite
	 * @param size
	 * @param rotation - in degrees
	 * @param offset - in world coordinates
	 * Creates a new Renderer with sprite and in-game width of size rotated by rotation angle.
	 * The image top-left corner will be painted at obj position traslated by offset
	 */
	public ImageRenderer(final AbstractGameObject obj, final Sprite sprite, double size, double rotation, Point2D offset) {
		this.obj = obj;
		setSprite(sprite);
		this.offset = offset;
		this.size = size;
		this.rotate(rotation, GameApplication.convertToInt(size));
	}
	/**
	 * @param newSprite
	 * Sets the current image to the newSprite image
	 */
	public void setSprite(Sprite newSprite) {
		this.baseSprite = newSprite;
		this.currentImg = newSprite.getImage();
	}
	
	@Override
	public void paint(GraphicsContext gc) {
		//incomplete
		int xPos = GameApplication.convertToInt(this.obj.getPosition().getX() + offset.getX());
		int yPos = GameApplication.convertToInt(this.obj.getPosition().getY() + offset.getY());
        gc.drawImage(currentImg, xPos, yPos, currentImg.getWidth(), currentImg.getHeight());
	}
	/**
	 * Sets the current rotation in degrees of this image
	 * @param degrees
	 */
	public void setRotation(final double degrees) {
		rotate(degrees, GameApplication.convertToInt(this.size));
	}
	/**
	 * @param degrees
	 * @param width - in pixels!
	 * Rotates this image by degrees angle and scales it to fit in a box of size width
	 */
	private void rotate(final double degrees, final double width) {
		//rotation is performed in the FX thread ( runLater() is used to achieve that )
		Platform.runLater(() -> {
			ImageView iv = new ImageView(this.baseSprite.getImage());
			iv.setFitWidth(width);
	        iv.setPreserveRatio(true);
	        iv.setSmooth(true);
	        iv.setCache(true);
			iv.setRotate(degrees);
			SnapshotParameters param = new SnapshotParameters();
			param.setFill(Color.TRANSPARENT);
			this.currentImg = iv.snapshot(param, null);
		});
	}
	/**
	 * 
	 * @return the attached game object
	 */
	protected AbstractGameObject getGameObject() {
		return this.obj;
	}
}
