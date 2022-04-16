package game.ui;

import game.engine.GameApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MenuSceneController {
	
	private GameApplication application;
	
	public MenuSceneController(final GameApplication application) {
		this.application = application;
	}

	@FXML private BorderPane borderPane;
	@FXML private Label title;
	@FXML private VBox gameControls;
	@FXML private HBox nameBox;
	@FXML private Label nameLabel;
	@FXML private TextField namePrompt;
	@FXML private VBox buttons;
	@FXML private Button playButton;
	@FXML private Button leaderboardButton;
	
	/**
	 * Play game.
	 */
	public void play() {
		this.application.game();
		this.application.setPlayerName(this.namePrompt.getText());
	}
	
	/**
	 * Show leaderboard.
	 * @throws Exception 
	 */
	public void leaderboard() throws Exception {
		this.application.viewScoreNoEdit();
	}
	
	//get player name box text and set it as player name in game application
}
