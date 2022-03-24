package game.graphics;

import java.util.List;

import game.model.AbstractGameObject;
import game.util.Point2D;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
/**
 * Represents the Scene where to display the actual game objects.
 * It is managed by the GameEngine
 */
public class GameScene {
    
	private final Scene scene;
	private Canvas canvas;
	private Point2D mousePosition = new Point2D(0.5,0.5);
	/**
	 * @param sizeX
	 * @param sizeY
	 * Creates a new GameScene of size (in pixels) sizeX*sizeY
	 */
	public GameScene(int size) {
		Group gr = new Group();
		this.canvas = new Canvas(size, size);
		gr.getChildren().add(this.canvas);
		
		//Updates the mouse position
		gr.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				mousePosition.setX(event.getSceneX()/size);
				mousePosition.setY(event.getSceneY()/size);
			}
		});
		
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
		return this.canvas.getGraphicsContext2D();
	}
	/**
	 * @return the in-game coordinates of the mouse
	 */
	public Point2D getMouseWorldPosition() {
		return this.mousePosition; 
	}
	/**
	 * @param objects - List of game objects
	 * Displays all the elements in objects which have a renderer
	 */
	public void render(List<AbstractGameObject> objects) {
		objects.stream().filter(o -> o.getRenderer() != null).forEach(o -> o.getRenderer().paint(getGraphics()));
	}
}
