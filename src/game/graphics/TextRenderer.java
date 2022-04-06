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
	private Color color;
	private AbstractGameObject parent;
	/**
	 * Creates a new Renderer which displays a text at parent's position
	 * @param parent - the parent gameObject
	 * @param text
	 * @param size - in-game size of the text
	 * @param color of the text
	 */
	public TextRenderer(AbstractGameObject parent, String text, double size, Color color) {
		this.parent = parent;
		setText(text);
		setColor(color);
		this.font = new Font(DEFAULT_FONT_NAME, GameApplication.convertToInt(size));
	}
	
	@Override
	public void paint(GraphicsContext gc) {
		gc.setFill(this.color);
		gc.setFont(this.font);
		gc.fillText(this.text,
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
	public void setColor(Color color) {
		this.color = color;
	}

}
