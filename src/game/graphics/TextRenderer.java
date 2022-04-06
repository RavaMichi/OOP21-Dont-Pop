package game.graphics;

import game.engine.GameApplication;
import game.model.AbstractGameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class TextRenderer implements Renderer {

	private static final String DEFAULT_FONT_NAME = "Impact";
	
	private Font font;
	private String text;
	private Color color;
	private AbstractGameObject parent;
	
	public TextRenderer(AbstractGameObject parent, String text, double size, Color color) {
		this.parent = parent;
		this.text = text;
		this.color = color;
		this.font = new Font(DEFAULT_FONT_NAME, GameApplication.convertToInt(size));
	}
	
	@Override
	public void paint(GraphicsContext gc) {
		// TODO Auto-generated method stub

	}

}
