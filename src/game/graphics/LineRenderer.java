package game.graphics;

import game.model.AbstractGameObject;
import game.util.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
/**
 * Renderer of a line
 */
public class LineRenderer implements Renderer {

	private Point2D p1;
	private Point2D p2;
	private Color color;
	private double width;
	/**
	 * @param obj - the GameObject
	 * @param direction - in world coordinates
	 * @param color - the line color
	 * @param width - the line width
	 * Creates a new LineRenderer at obj position, pointing direction, with customizable color and width
	 */
	public LineRenderer(final AbstractGameObject obj, Point2D direction, Color color, double width) {
		double m = direction.getY()/direction.getX();
		Point2D p1 = getLimit(obj.getPosition(), m, 0);
		Point2D p2 = getLimit(obj.getPosition(), m, 1);
		
		this.p1 = p1;
		this.p2 = p2;
		this.color = color;
		this.width = width;
	}
	
	@Override
	public void paint(GraphicsContext gc) {
		gc.setLineWidth(this.width);
		gc.setStroke(color);
		gc.strokeLine(this.worldToPixel(p1.getX()), this.worldToPixel(p1.getY()), this.worldToPixel(p2.getX()), this.worldToPixel(p2.getY()));
	}
    /**
     * @param opacity
     * Changes the line color opacity
     */
	public void setOpacity(double opacity) {
		this.color = new Color(color.getRed(), color.getGreen(), color.getBlue(), opacity);
	}
	/**
	 * @param origin
	 * @param m
	 * @param xLimit
	 * @return the projection of the line y-y0=m(x-x0) on the xLimit border of the in-game world
	 */
	private Point2D getLimit(Point2D origin, double m, double xLimit) {
		double y = m*(xLimit - origin.getX()) + origin.getY();
		if (y > 1 || y < 0) {
			double newY = y>1 ? 1 : 0;
			double x = (newY - origin.getY() + m*origin.getX())/m;
			return Point2D.of(x, newY);
		} else {
			return Point2D.of(xLimit, y);
		}
	}
}
