package game.ui;

import game.engine.GameApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Scale;


/**
 * The Class HowToPlayScene.
 */
public class HowToPlayScene {

	private static final int DEFAULT_SIZE = 650; //when scaling factor is 1, screen size = default size.
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
		final FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/howtoplayscene.fxml"));

		// controller created here
		final HowToPlaySceneController controller = new HowToPlaySceneController(application);
		loader.setController(controller);

		// load GUI from FXML
		final AnchorPane root = loader.load();
		
		//scaling
		final double scaleFactor = (double)screenSize / DEFAULT_SIZE;
		final Scale scaleTransformation = new Scale(scaleFactor, scaleFactor, 0, 0);
		root.getTransforms().add(scaleTransformation);

		this.scene = new Scene(root, screenSize, screenSize);
		this.scene.getStylesheets().add(getClass().getResource("/css/howtoplayscene-styles.css").toExternalForm());
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
