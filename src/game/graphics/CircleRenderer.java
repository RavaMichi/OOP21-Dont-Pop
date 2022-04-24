package game.graphics;

import game.engine.GameApplication;
import game.model.AbstractGameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Renderer of a circle
 */
public class CircleRenderer implements Renderer {

	private final AbstractGameObject parent;
	private double radius;
	private Color color;
	
	/**
	 * Creates a new Renderer at parent position, representing a circle of radius radius and color color
	 * @param parent - the GameObject
	 * @param radius - the circle radius
	 * @param color - the circle color
	 */
	public CircleRenderer(final AbstractGameObject parent, final double radius, final Color color) {
		this.parent = parent;
		setRadius(radius);
		this.color = color;
	}
	
	/**
	 * Changes the circle color opacity. It's a number between 0 and 1
     * @param opacity
     */
	public void setOpacity(double opacity) {
		opacity = Math.min(Math.max(opacity, 0), 1);
		this.color = new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), opacity);
	}
	
	/**
	 * Set the radius of the circle
	 * @param radius
	 */
	public void setRadius(final double radius) {
		this.radius = GameApplication.convertToInt(radius);
	}
	
	/**
	 * Paints a circle.
	 * @param GraphicsContext gc
	 */
	@Override
	public void paint(final GraphicsContext gc) {
		gc.setFill(color);
		gc.fillOval(GameApplication.convertToInt(this.parent.getPosition().getX()) - this.radius,
				GameApplication.convertToInt(this.parent.getPosition().getY()) - this.radius,
				this.radius * 2,
				this.radius * 2);
	}

}
