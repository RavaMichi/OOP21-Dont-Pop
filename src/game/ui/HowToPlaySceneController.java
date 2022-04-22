package game.ui;

import game.engine.GameApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * The Class HowToPlaySceneController. A controller for HowToPlayScene about enemies and power up
 */
public class HowToPlaySceneController {
	
	private GameApplication application;
	
	public HowToPlaySceneController(final GameApplication application) {
		this.application = application;
	}

	@FXML private AnchorPane pane;
	@FXML private TextArea textarea;
	@FXML private TextArea textarea2;
	@FXML private TextArea textarea3;
	@FXML private TextArea textarea4;
	@FXML private Button goBackButton;

	
	/**
	 * Go to the menu scene
	 */
	public void menu() {

		this.application.menu();
	}
	
	
}
