package game.graphics;

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
	 * @param p1 - in world coordinates
	 * @param p2 - in world coordinates
	 * @param color
	 * @param width
	 * Creates a new Renderer by passing a start point, an end point, a color and the line width
	 */
	public LineRenderer(Point2D p1, Point2D p2, Color color, double width) {
		this.p1 = p1;
		this.p2 = p2;
		this.color = color;
		this.width = width;
	}
	
	@Override
	public void paint(GraphicsContext gc) {
		gc.setLineWidth(this.width);
		gc.strokeLine(this.worldToPixel(p1.getX()), this.worldToPixel(p1.getY()), this.worldToPixel(p2.getX()), this.worldToPixel(p2.getY()));
	}
    /**
     * @param opacity
     * Changes the line color opacity
     */
	public void setOpacity(double opacity) {
		this.color = new Color(color.getRed(), color.getGreen(), color.getBlue(), opacity);
	}
}
