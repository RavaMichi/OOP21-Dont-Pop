package game.ui;

import java.util.Random;

import game.engine.GameApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
	public void goToTheMenu() {

		this.application.menu();
	}
	
	
	/**
	 * Starts game when user presses ENTER key.
	 * @param keyPressed (starts game if ENTER)
	 */
	public void onEnter(final KeyEvent keyPressed) {
		if(keyPressed.getCode() == KeyCode.ENTER) {
			this.goToTheMenu();
		}
	}
	
	
}
