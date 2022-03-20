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
	 * @param p1
	 * @param p2
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
		// TODO Auto-generated method stub
	}

}
