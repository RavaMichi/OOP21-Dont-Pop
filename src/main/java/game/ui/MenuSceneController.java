package game.ui;

import java.util.Random;

import game.engine.GameApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 * Test class.
 */
public class MenuSceneController {
	
	private final GameApplication application;
	
	@FXML private AnchorPane anchorPane;
	@FXML private Label title;
	@FXML private Label nameLabel;
	@FXML private TextField namePrompt;
	@FXML private Button playButton;
	@FXML private Button leaderboardButton;
	@FXML private Button howtoplayButton;
	
	public MenuSceneController(final GameApplication application) {
		this.application = application;
	}

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
			this.namePrompt.setText(playerName);
		}
	}
	
	/**
	 * Play game.
	 * If namePrompt TextField is empty, the game will use a default nickname.
	 */
	public void play() {
		final String playerName = this.namePrompt.getText();
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
		final String playerName = this.namePrompt.getText();
		if (playerName.startsWith(" ")) {
			//do nothing when player name starts with a whitespace
		} else if (!playerName.isEmpty()) {
			this.application.setPlayerName(playerName);
		}
		this.application.viewScoreNoEdit();
	}
	
	public void howToPlay() {
		this.application.howToPlay();
	}
	
	/**
	 * Starts game when user presses ENTER key.
	 * @param keyPressed (starts game if ENTER)
	 */
	public void onEnter(final KeyEvent keyPressed) {
		if(keyPressed.getCode() == KeyCode.ENTER) {
			this.play();
		}
	}
	
	//get player name box text and set it as player name in game application
	
	private String generateRandomName() {
		String playerName = "Player";
		final Random rand = new Random();
		final int upperBound = 10;
		for(int i = 0; i < 3; i++) {
			playerName += rand.nextInt(upperBound);
		}

		return playerName;
	}
}
