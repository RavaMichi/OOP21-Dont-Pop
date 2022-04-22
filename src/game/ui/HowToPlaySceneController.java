package game.ui;

import game.engine.GameApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

/**
 * The Class HowToPlaySceneController. A controller for HowToPlayScene about enemies and power up
 */
public class HowToPlaySceneController {
	
	private GameApplication application;
	
	public HowToPlaySceneController(final GameApplication application) {
		this.application = application;
	}

	@FXML private Pane pane;
	@FXML private TextArea textarea;
	@FXML private Button goBackButton;

	
	/**
	 * Go to the menu scene
	 */
	public void menu() {

		this.application.menu();
	}
	
	
}
