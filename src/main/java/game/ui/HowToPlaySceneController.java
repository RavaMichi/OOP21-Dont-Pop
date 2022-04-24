package game.ui;

import game.engine.GameApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * The Class HowToPlaySceneController. A controller for HowToPlayScene about enemies and power up.
 */
public class HowToPlaySceneController {
	
	private final GameApplication application;
	
	@FXML private AnchorPane pane;
	@FXML private TextArea textarea;
	@FXML private TextArea textarea2;
	@FXML private TextArea textarea3;
	@FXML private TextArea textarea4;
	@FXML private TextArea textarea5;
	@FXML private Button goBackButton;
	@FXML private ImageView image;
	@FXML private ImageView image2;
	@FXML private ImageView image3;
	@FXML private ImageView image4;
	@FXML private ImageView image5;
	
	public HowToPlaySceneController(final GameApplication application) {
		this.application = application;
	}

	/**
	 * Go to the menu scene.
	 */
	public void menu() {
		this.application.menu();
	}
	
	
}
