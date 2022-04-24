package test.renderer;

import java.util.List;

import game.engine.GameApplication;
import game.graphics.CircleRenderer;
import game.graphics.LineRenderer;
import game.graphics.TextRenderer;
import game.model.AbstractGameObject;
import game.model.AbstractGameObject.ObjectType;
import game.ui.GameScene;
import game.util.Point2D;
import game.util.RandomInt;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
 * This class is used to test the renderers and the game scene.
 * Its execution is manual: click on the screen and the display will change randomically
 */
public class TestMain extends Application {

	GameScene gameScene;
	//create an object at the center of the screen
	AbstractGameObject obj = new AbstractGameObject(Point2D.of(0.5, 0.5), ObjectType.PLAYER, null) {
		public void update() { }
	};;
	RandomInt rand = new RandomInt();
	
	//creates a black circle with diameter of screensize
	CircleRenderer circleR = new CircleRenderer(obj, 0.5, Color.BLACK);
	//Creates an aqua line horizontal
	LineRenderer lineR = new LineRenderer(obj, Point2D.of(1, 0), Color.AQUA, 0.05);
	//Creates a big text at the center, yellow with black outline
	TextRenderer textR = new TextRenderer(obj, "Prova", 0.5, Color.YELLOW, 0.02, Color.BLACK);
	
	@Override
	public void start(Stage stage) {
		//creates a Game Scene of size 800*800px
		gameScene = new GameScene(GameApplication.convertToInt(1));
		
		stage.setScene(gameScene.getScene());
		
		stage.getScene().setOnMouseClicked(e -> {
			switch (rand.getRandomInt(0, 2)) {
			case 0:
				testCircleRenderer();
				break;
			case 1:
				testLineRenderer();
				break;
			case 2:
				testTextRenderer();
				break;
			}
			System.out.println("clicked at: " + this.gameScene.getMouseWorldPosition());
			});
		
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	/**
	 * Tests the Circle Renderer
	 */
	private void testCircleRenderer() {

		obj.setRenderer(circleR);
		
		//change the opacity
		circleR.setOpacity(0.8);
		//set the radius to 1/4 screen size
		circleR.setRadius(0.25);
		
		gameScene.render(List.of(obj));
	}
	/**
	 * Tests the Line Renderer
	 */
	private void testLineRenderer() {

		obj.setRenderer(lineR);
		
		//change the opacity
		lineR.setOpacity(0.8);
		
		gameScene.render(List.of(obj));
	}
	/**
	 * Tests the Text Renderer
	 */
	private void testTextRenderer() {

		obj.setRenderer(textR);
		
		//change the color of the text
		textR.setFillColor(Color.DARKRED);
		//chage the text
		textR.setText("ABC");
		
		gameScene.render(List.of(obj));
	}
}
