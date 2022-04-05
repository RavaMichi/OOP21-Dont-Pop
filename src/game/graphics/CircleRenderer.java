package game.graphics;

import game.model.AbstractGameObject;
import game.util.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
/**
 * Renderer of a circle
 */
public class CircleRenderer implements Renderer {

	private AbstractGameObject parent;
	private double radius;
	private Color color;
	/**
	 * @param parent - the GameObject
	 * @param radius - the circle radius
	 * @param color - the circle color
	 * Creates a new Renderer at parent position, representing a circle of radius radius and color color
	 */
	public CircleRenderer(final AbstractGameObject parent, double radius, Color color) {
		this.parent = parent;
		this.radius = radius;
		this.color = color;
	}
	
	@Override
	public void paint(GraphicsContext gc) {
		// TODO Auto-generated method stub

	}

}
