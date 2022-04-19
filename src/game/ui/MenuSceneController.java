package game.ui;

import java.util.Random;

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
	 * Sets some properties dependent on Java variables (not accessible from neither FXML nor CSS).
	 */
	public void initialize() {
		String playerName = this.application.getPlayerName();
		if (!playerName.isEmpty()) {
			//remembers current name
			this.namePrompt.setText(playerName);
		} else {
			//generate random name
			playerName = this.generateRandomName();
			this.application.setPlayerName(playerName);
//			this.namePrompt.setText(playerName);
		}
	}
	
	/**
	 * Play game.
	 * If namePrompt TextField is empty, the game will use a default nickname.
	 */
	public void play() {
		String playerName = this.namePrompt.getText();
		if (playerName.startsWith(" ")) {
			//do nothing when player name starts with a whitespace
			//color name box of red
		} else if (!playerName.isEmpty()) {
			this.application.setPlayerName(playerName);
		}
		
		this.application.game();
	}
	
	/**
	 * Show leaderboard.
	 * @throws Exception 
	 */
	public void leaderboard() throws Exception {
		this.application.viewScoreNoEdit();
	}
	
	//get player name box text and set it as player name in game application
	
	private String generateRandomName() {
		String playerName = "Player";
		Random rand = new Random();
		int upperBound = 10;
		for(int i=0; i<3; i++) {
			playerName += rand.nextInt(upperBound);
		}
		
		return playerName;
	}
}
