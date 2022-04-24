package game.ui;

import java.util.List;

import game.model.AbstractGameObject;
import game.util.Point2D;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.TextAlignment;

/**
 * Represents the Scene where to display the actual game objects.
 * It is managed by the GameEngine
 */
public class GameScene {
    
	private final Scene scene;
	
	private Canvas activeCanvas;
	private Canvas bufferCanvas;
	
	private final Point2D mousePosition = new Point2D(0.5, 0.5);
	private final int size;
	
	/**
	 * Creates a new GameScene of size (in pixels) size*size
	 * @param size
	 */
	public GameScene(final int size) {
		final Group gr = new Group();
		this.activeCanvas = new Canvas(size, size);
		this.bufferCanvas = new Canvas(size, size);
		
		this.setTextSettings();
		
		//adds to the scene only the active canvas
		gr.getChildren().add(this.activeCanvas);
		
		//Updates the mouse position
		gr.setOnMouseMoved(event -> {
			this.mousePosition.setX(event.getSceneX() / size);
			this.mousePosition.setY(event.getSceneY() / size);
		});
		
		this.size = size;
		this.scene = new Scene(gr);
		
		//initialize the canvases
		this.render(List.of());
	}
	
	/**
	 * @return the scene to display
	 */
	public Scene getScene() {
		return this.scene;
	}
	
	/**
	 * Gets the current buffer where to draw the objects
	 * @return the GraphicsContext buffer
	 */
	public GraphicsContext getGraphics() {
		//images are drawn into the buffer first, then displayed
		return this.bufferCanvas.getGraphicsContext2D();
	}
	
	/**
	 * Gets the in-game coordinates of the mouse
	 * @return the mouse position
	 */
	public Point2D getMouseWorldPosition() {
		return Point2D.copyOf(this.mousePosition); 
	}
	
	/**
	 * Displays all the elements in objects which have a renderer
	 * @param objects - List of game objects
	 */
	public void render(final List<AbstractGameObject> objects) {
		this.clear();
		objects.stream().filter(o -> o.getRenderer() != null).forEach(o -> o.getRenderer().paint(getGraphics()));
		this.swapCanvas();
	}
	
	/**
	 * Swap the buffer canvas with the active one
	 */
	private void swapCanvas() {
		final Canvas temp = this.bufferCanvas;
		this.bufferCanvas = this.activeCanvas;
		this.activeCanvas = temp;
	}
	
	/**
	 * Sets the settings for text rendering.
	 * Makes the text centered at creation.
	 */
	private void setTextSettings() {
		this.activeCanvas.getGraphicsContext2D().setTextBaseline(VPos.CENTER);
		this.bufferCanvas.getGraphicsContext2D().setTextBaseline(VPos.CENTER);
		this.activeCanvas.getGraphicsContext2D().setTextAlign(TextAlignment.CENTER);
		this.bufferCanvas.getGraphicsContext2D().setTextAlign(TextAlignment.CENTER);
	}
	
	/**
	 * Clears the buffer canvas
	 */
	public void clear() {
		this.getGraphics().clearRect(0, 0, this.size, this.size);
	}
}
