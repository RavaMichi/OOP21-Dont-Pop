package game.graphics;

import java.util.List;

import game.model.AbstractGameObject;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
/**
 * Represents the Scene where to display the actual game objects.
 * It is managed by the GameEngine
 */
public class GameScene {
    
	private final Scene scene;
	private Canvas canvas;
	/**
	 * @param sizeX
	 * @param sizeY
	 * Creates a new GameScene of size (in pixels) sizeX*sizeY
	 */
	public GameScene(int sizeX, int sizeY) {
		Group gr = new Group();
		this.canvas = new Canvas(sizeX, sizeY);
		gr.getChildren().add(gr);
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
	 * @param objects - List of game objects
	 * Displays all the elements in objects which have a renderer
	 */
	public void render(List<AbstractGameObject> objects) {
		objects.stream().filter(o -> o.getRenderer() != null).forEach(o -> o.getRenderer().paint(getGraphics()));
	}
}
