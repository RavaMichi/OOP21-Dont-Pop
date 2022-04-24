package game.graphics;

import game.engine.GameApplication;
import game.model.AbstractGameObject;
import game.util.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Renderer of a line
 */
public class LineRenderer implements Renderer {

	private final Point2D p1;
	private final Point2D p2;
	private Color color;
	private final double width;
	
	/**
	 * Creates a new LineRenderer at obj position, pointing direction, with customizable color and width
	 * @param obj - the GameObject
	 * @param direction - in world coordinates
	 * @param color - the line color
	 * @param width - the line width
	 */
	public LineRenderer(final AbstractGameObject obj, final Point2D direction, final Color color, final double width) {
		final double m = direction.getY()/direction.getX();
		final Point2D p1 = getLimit(obj.getPosition(), m, 0);
		final Point2D p2 = getLimit(obj.getPosition(), m, 1);
		
		this.p1 = p1;
		this.p2 = p2;
		this.color = color;
		this.width = width;
	}
	
	/**
	 * Paints a line.
	 * @param GraphicsContext gc
	 */
	@Override
	public void paint(final GraphicsContext gc) {
		gc.setLineWidth(GameApplication.convertToInt(this.width));
		gc.setStroke(color);
		gc.strokeLine(GameApplication.convertToInt(p1.getX()), GameApplication.convertToInt(p1.getY()), GameApplication.convertToInt(p2.getX()), GameApplication.convertToInt(p2.getY()));
	}
	
    /**
     * Changes the line color opacity. It's a number between 0 and 1
     * @param opacity
     */
	public void setOpacity(double opacity) {
		opacity = Math.min(Math.max(opacity, 0), 1);
		this.color = new Color(color.getRed(), color.getGreen(), color.getBlue(), opacity);
	}
	
	/**
	 * Gets the projection of the line y-y0=m(x-x0) on the xLimit border of the in-game world
	 * @param origin
	 * @param m
	 * @param xLimit
	 * @return the projection
	 */
	private Point2D getLimit(final Point2D origin, final double m, final double xLimit) {
		final double y = m*(xLimit - origin.getX()) + origin.getY();
		if (y > 1 || y < 0) {
			final double newY = y>1 ? 1 : 0;
			final double x = (newY - origin.getY() + m*origin.getX())/m;
			return Point2D.of(x, newY);
		} else {
			return Point2D.of(xLimit, y);
		}
	}
}
