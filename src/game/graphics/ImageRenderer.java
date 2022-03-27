package game.graphics;

import game.model.AbstractGameObject;
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
	/**
	 * @param obj - the game object linked to this renderer
	 * @param sprite
	 * @param size
	 * @param rotation in degrees
	 * Creates a new Renderer with sprite and in-game width of size rotated by rotation angle
	 */
	public ImageRenderer(final AbstractGameObject obj, final Sprite sprite, double size, double rotation) {
		this.obj = obj;
		this.baseSprite = sprite;
		this.currentImg = sprite.getImage();
		this.rotate(rotation, this.worldToPixel(size));
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
}
