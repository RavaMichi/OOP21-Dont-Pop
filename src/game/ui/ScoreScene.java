package game.ui;

import game.engine.ScoreManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/**
 * ScoreScene displays a GUI showing your current score in a beautiful table.
 */
public class ScoreScene {
	
	private final Scene scene;
	
	/**
	 * Creates & initializes a ScoreScene.
	 * @param scoreManager
	 * @param screenSize
	 * @throws Exception (necessary to load files, esp. FXML)
	 */
	public ScoreScene(final ScoreManager scoreManager, final int screenSize) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/game/fxml/scorescene.fxml"));
		
		//controller created here
		ScoreSceneController controller = new ScoreSceneController(scoreManager, screenSize);
		loader.setController(controller);
		
		//controller created in fxml
//		BuilderController controller = loader.getController();
		
		//load GUI from FXML
		BorderPane root = loader.load();

		this.scene = new Scene(root, screenSize, screenSize);
		this.scene.getStylesheets().add(getClass().getResource("/game/css/scorescene-styles.css").toExternalForm());
//		stage.setTitle("Score Ranking");
//		stage.setWidth(GameApplication.screenSize * 0.90);
//		stage.setHeight(GameApplication.screenSize * 0.90);
//		stage.setResizable(false);
//		stage.setScene(scene);
//		stage.show();
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
