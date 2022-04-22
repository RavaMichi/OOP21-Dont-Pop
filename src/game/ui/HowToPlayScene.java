package game.ui;

import game.engine.GameApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


/**
 * The Class HowToPlayScene.
 */
public class HowToPlayScene {

	/** The scene. */
	private final Scene scene;

	/**
	 * Instantiates a new how to play scene.
	 *
	 * @param application the application
	 * @param screenSize the screen size
	 * @throws Exception the exception
	 */
	public HowToPlayScene(final GameApplication application, final int screenSize) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("src/game/fxml/howtoplay.fxml"));

		// controller created here
		HowToPlaySceneController controller = new HowToPlaySceneController(application);
		loader.setController(controller);

		// load GUI from FXML
		AnchorPane root = (AnchorPane) loader.load();

		this.scene = new Scene(root, screenSize, screenSize);
	}

	/**
	 * Gets the scene.
	 *
	 * @return the scene
	 */
	public Scene getScene() {
		return this.scene;
	}

	/**
	 * Gets the.
	 *
	 * @return the how to play scene
	 */
	public HowToPlayScene get() {
		return this;
	}
}
