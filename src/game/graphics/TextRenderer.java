package game.graphics;

import game.engine.GameApplication;
import game.model.AbstractGameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
/**
 * Class representing a text.
 */
public class TextRenderer implements Renderer {

	private static final String DEFAULT_FONT_NAME = "Impact";
	
	private Font font;
	private String text;
	private double borderSize;
	private Color fillColor;
	private Color strokeColor;
	private AbstractGameObject parent;
	private final boolean onlyFill;
	/**
	 * Creates a new Renderer which displays a text at parent's position
	 * @param parent - the parent gameObject
	 * @param text
	 * @param size - in-game size of the text
	 * @param color of the text
	 * @param border size - the width of the border
	 * @param border color of the text
	 */
	public TextRenderer(AbstractGameObject parent, String text, double size, Color fillColor, final double borderSize, Color borderColor) {
		this.parent = parent;
		setText(text);
		setFillColor(fillColor);
		setBorderColor(borderColor);
		this.font = new Font(DEFAULT_FONT_NAME, GameApplication.convertToInt(size));
		this.borderSize = GameApplication.convertToInt(borderSize);
		this.onlyFill = borderColor.equals(Color.TRANSPARENT);
	}
	/**
	 * Creates a new Renderer which displays a text at parent's position (without outline)
	 * @param parent - the parent gameObject
	 * @param text
	 * @param size - in-game size of the text
	 * @param color of the text
	 */
	public TextRenderer(AbstractGameObject parent, String text, double size, Color fillColor) {
		this(parent, text, size, fillColor, 0, Color.TRANSPARENT);
	}
	
	@Override
	public void paint(GraphicsContext gc) {
		gc.setFont(this.font);
		gc.setFill(this.fillColor);
		gc.fillText(this.text,
				GameApplication.convertToInt(this.parent.getPosition().getX()),
				GameApplication.convertToInt(this.parent.getPosition().getY()));
		if (onlyFill) return;
		gc.setLineWidth(this.borderSize);
		gc.setStroke(this.strokeColor);
		gc.strokeText(this.text,
				GameApplication.convertToInt(this.parent.getPosition().getX()),
				GameApplication.convertToInt(this.parent.getPosition().getY()));
	}
	/**
	 * Sets the text.
	 * @param txt
	 */
	public void setText(String txt) {
		this.text = txt;
	}
	/**
	 * Sets the color of the text.
	 * @param color
	 */
	public void setFillColor(Color color) {
		this.fillColor = color;
	}

	/**
	 * Sets the border color of the text.
	 * @param color
	 */
	public void setBorderColor(Color color) {
		this.strokeColor = color;
	}
}
