package game.ui;

import game.engine.GameApplication;
import game.engine.ScoreManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Scale;

/**
 * ScoreScene displays a GUI showing your current score in a beautiful table.
 */
public class ScoreScene {
	
	private final Scene scene;
	private final static int DEFAULT_SIZE = 300;
	
	/**
	 * Creates & initializes a ScoreScene.
	 * @param scoreManager
	 * @param screenSize
	 * @throws Exception (necessary to load files, esp. FXML)
	 */
	public ScoreScene(final ScoreManager scoreManager, final GameApplication application, final int screenSize) throws Exception {
		final FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/scorescene.fxml"));
		
		//controller created here
		final ScoreSceneController controller = new ScoreSceneController(scoreManager, application, screenSize);
		loader.setController(controller);
		
		//controller created in fxml
//		BuilderController controller = loader.getController();
		
		//load GUI from FXML
		final AnchorPane root = loader.load();

		final double scaleFactor = (double)screenSize / DEFAULT_SIZE;
        final Scale scaleTransformation = new Scale(scaleFactor, scaleFactor, 0, 0);
        root.getTransforms().add(scaleTransformation);
		
		this.scene = new Scene(root, screenSize, screenSize);
		this.scene.getStylesheets().add(getClass().getResource("/css/scorescene-styles.css").toExternalForm());
	}
	
	/**
     * Gets the Scene inside ScoreScene.
     * @return this.scene
     */
	public Scene getScene() {
		return this.scene;
	}
	
	/**
	 * Gets this object.
	 * @return ScoreScene
	 */
	public ScoreScene get() {
		return this;
	}
}
