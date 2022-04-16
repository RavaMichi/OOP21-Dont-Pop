package game.ui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/**
 * MenuScene displays the Main Menu GUI.
 */
public class MenuScene {
	
	private final Scene scene;
	
	/**
	 * Creates & initializes a MenuScene.
	 * @param screenSize
	 * @throws Exception (necessary to load files, esp. FXML)
	 */
	public MenuScene(final int screenSize) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/game/fxml/menuscene.fxml"));
		
		//controller created here
		MenuSceneController controller = new MenuSceneController();
		loader.setController(controller);
		
		//load GUI from FXML
		BorderPane root = loader.load();
		
		this.scene = new Scene(root, screenSize, screenSize);
		this.scene.getStylesheets().add(getClass().getResource("/game/css/menuscene-styles.css").toExternalForm());
	}
	
	/**
	 * Gets the Scene inside MenuScene
	 * @return this.scene
	 */
	public Scene getScene() {
		return this.scene;
	}
	
	/**
	 * Gets this object.
	 * @return MenuScene
	 */
	public MenuScene get() {
		return this;
	}
}
