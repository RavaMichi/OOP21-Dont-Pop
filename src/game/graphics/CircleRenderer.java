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
	/**
     * @param opacity
     * Changes the circle color opacity. It's a number between 0 and 1
     */
	public void setOpacity(double opacity) {
		opacity = Math.min(Math.max(opacity, 0), 1);
		this.color = new Color(color.getRed(), color.getGreen(), color.getBlue(), opacity);
	}
	
	@Override
	public void paint(GraphicsContext gc) {
		gc.setFill(color);
		gc.fillOval(this.parent.getPosition().getX() - this.radius, this.parent.getPosition().getY() - this.radius, this.radius*2, this.radius*2);
	}

}
