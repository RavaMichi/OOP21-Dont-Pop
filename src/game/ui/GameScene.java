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
	
	private Point2D mousePosition = new Point2D(0.5,0.5);
	private int size;
	
	/**
	 * @param sizeX
	 * @param sizeY
	 * Creates a new GameScene of size (in pixels) sizeX*sizeY
	 */
	public GameScene(final int size) {
		Group gr = new Group();
		this.activeCanvas = new Canvas(size, size);
		this.bufferCanvas = new Canvas(size, size);
		
		setTextSettings();
		
		//adds to the scene only the active canvas
		gr.getChildren().add(this.activeCanvas);
		
		//Updates the mouse position
		gr.setOnMouseMoved(event -> {
			mousePosition.setX(event.getSceneX()/size);
			mousePosition.setY(event.getSceneY()/size);
		});
		
		this.size = size;
		this.scene = new Scene(gr);
	}
	
	/**
	 * @return the scene to display
	 */
	public Scene getScene() {
		return this.scene;
	}
	
	/**
	 * @return the GraphicsContext where to draw the objects
	 */
	public GraphicsContext getGraphics() {
		//images are drawn into the buffer first, then displayed
		return this.bufferCanvas.getGraphicsContext2D();
	}
	
	/**
	 * @return the in-game coordinates of the mouse
	 */
	public Point2D getMouseWorldPosition() {
		return Point2D.copyOf(mousePosition); 
	}
	
	/**
	 * @param objects - List of game objects
	 * Displays all the elements in objects which have a renderer
	 */
	public void render(List<AbstractGameObject> objects) {
		clear();
		objects.stream().filter(o -> o.getRenderer() != null).forEach(o -> o.getRenderer().paint(getGraphics()));
		swapCanvas();
	}
	
	/**
	 * Swap the buffer canvas with the active one
	 */
	private void swapCanvas() {
		Canvas temp = this.bufferCanvas;
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
		getGraphics().clearRect(0, 0, this.size, this.size);
	}
}
