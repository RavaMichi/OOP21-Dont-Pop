package game.graphics;

import game.engine.GameApplication;
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
		PLAYER("/baloon.png"),
		BULLET("/bullet.png"),
		THORNBALL("/thornball.png"),
		GOLDEN_PLAYER("/golden_baloon.png"),
		SHIELD_PLAYER("/shield_baloon.png"),
		POP_ANIMATION_1("/pop_1.png"),
		POP_ANIMATION_2("/pop_2.png"),
		POP_ANIMATION_3("/pop_3.png"),
		POP_ANIMATION_4("/pop_4.png"),
		//PNGs TO BE CREATED YET
		PWRUP_SHIELD("/shield.png"),
		PWRUP_MULTIPLIER("/multiplier.png"),
		PWRUP_SWEEPER("/sweeper.png");
		
		
		private final Image img;
		
		private Sprite(final String path) {
//			System.out.println("Loading sprite '" + path + "'...");
			this.img = new Image(path);
//			System.out.println("Done");
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
	 * Creates a new Renderer with sprite and in-game width of size rotated by rotation angle.
	 * The image will be centered at obj position.
	 * @param obj - the game object linked to this renderer
	 * @param sprite
	 * @param size
	 * @param rotation - in degrees
	 */
	public ImageRenderer(final AbstractGameObject obj, final Sprite sprite, double size, double rotation) {
		this.obj = obj;
		setSprite(sprite);
		this.size = size;
		this.rotate(rotation, GameApplication.convertToInt(size));
	}
	
	/**
	 * Sets the current image to the newSprite image
	 * @param newSprite
	 */
	public void setSprite(Sprite newSprite) {
		this.baseSprite = newSprite;
		this.currentImg = newSprite.getImage();
	}
	
	/**
	 * Paints an image on the screen.
	 * @param GraphicsContext gc
	 */
	@Override
	public void paint(GraphicsContext gc) {
		//incomplete
		double xPos = GameApplication.convertToInt(this.obj.getPosition().getX()) - this.currentImg.getWidth()/2;
		double yPos = GameApplication.convertToInt(this.obj.getPosition().getY()) - this.currentImg.getHeight()/2;
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
	 * Rotates this image by degrees angle and scales it to fit in a box of size width
	 * @param degrees
	 * @param width - in pixels!
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
	 * Gets the attached game object (the one to be rendered).
	 * @return the attached game object
	 */
	protected AbstractGameObject getGameObject() {
		return this.obj;
	}
}
