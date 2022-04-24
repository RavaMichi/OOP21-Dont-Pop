package game.engine;

import game.ui.GameScene;
import game.ui.HowToPlayScene;
import game.ui.MenuScene;
import game.ui.ScoreScene;
import game.util.Leaderboard;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;

/**
 * This class runs the entire game, switching scenes when needed.
 */
public class GameApplication extends Application {
	
	private static final String SAVE_PATH = ".save";
	
	//guardare dove lo devo prendere
	private String playerName;
	private Leaderboard leaderboard;
	@SuppressWarnings("unused")
	private ScoreManager scoremanager;
	public final static int SCREEN_SIZE;	// usare la percentuale dello schermo non valori da 0 a 1: 0.n * size; dove n è
										// la percentuale dello schermo. ergo ho i valori da 0 a 1
	private Stage primaryStage;
	
	/**
	 * Static initializer: gets screen size.
	 */
	static {
		final Rectangle2D screenBounds = Screen.getPrimary().getBounds();
		SCREEN_SIZE = (int) (0.95 * Math.min(screenBounds.getWidth(), screenBounds.getHeight()));
	}
	
	/**
	 * Class constructor.
	 */
	public GameApplication() {
		this.playerName = "";
	}
	
	/**
	 * Gets the player's name.
	 * @return playerName
	 */
	public String getPlayerName() {
		return this.playerName;
	}

	/**
	 * Set player name to the String passed as argument.
	 * @param playerName
	 */
	public void setPlayerName(final String playerName) {
		this.playerName = playerName;
	}
	
	
	/**
	 * Convert pizel size to percentage size.
	 * @param num
	 * @return percentage size
	 */
	public static double convertToDouble(final int num) {	// deve essere compreso tra 0 e n e lo devo trasformare in 0-1
		return num / SCREEN_SIZE;	// ritorna la posizione percentuale rispetto allo schermo. esempio gli passo 350 e la risoluzione è 700 lui mi tira fuori 0.5 
									// (50% della lunghezza dello schermo)
	}
	
	
	/**
	 * Launch howTPlay GUI (run the game).
	 */
	public void howToPlay() {
		try {
			final HowToPlayScene howtoplayscene = new HowToPlayScene(this, SCREEN_SIZE);
			this.switchScene(howtoplayscene.getScene());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Convert percentage size to pixel size.
	 * @param num
	 * @return pixel size
	 */
	public static int convertToInt(final double num) {
		return (int) (num * SCREEN_SIZE);
	}

	/**
	 * Start GUI, then launch the menu.
	 * @throws Exception 
	 */
	public void start(final Stage primaryStage) throws Exception { // public void start(final Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.leaderboard = new Leaderboard(SAVE_PATH);
		this.primaryStage.setWidth(SCREEN_SIZE);
		this.primaryStage.setHeight(SCREEN_SIZE);
		this.primaryStage.setResizable(false); 			//ScoreCalc scolreCalc= new ScoreCalc(); ScoreManager scoremanager = new ScoreManager(this.scolreCalc);
		this.menu();									// prende score e il player dal game engine
		
		this.primaryStage.setOnCloseRequest(e -> this.exit());
		
		this.primaryStage.show();
	}

	/**
	 * Launch menu GUI.
	 * @throws Exception 
	 */
	public void menu() {
		try {
			final MenuScene menuscene = new MenuScene(this, SCREEN_SIZE);
			this.switchScene(menuscene.getScene());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Launch game GUI (run the game).
	 */
	public void game() {
		final GameScene gamescene = new GameScene(SCREEN_SIZE);
		final GameEngine gameEngine = new GameEngine(this, gamescene);
		this.switchScene(gamescene.getScene());
		new Thread(gameEngine).start();

	}

	/**
	 * Launch score GUI (view score ranking).
	 * @param score
	 * @throws Exception 
	 */
	public void score(final int score){
		final ScoreManager scoremanager = new ScoreManager(this.playerName, score, this.leaderboard, this);// prende score e il nome player e leaderboard dal game engine e aggiungere al costruttpre il nome del player
		try {
			final ScoreScene scoreScene = new ScoreScene(scoremanager, this, SCREEN_SIZE);
			this.switchScene(scoreScene.getScene());
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * Launch score GUI without editing score list.
	 * Useful when viewing leaderboard before playing the game.
	 * @throws Exception 
	 */
	public void viewScoreNoEdit() throws Exception {
		final ScoreManager scoreManager = new ScoreManager(this.leaderboard, this);
		final ScoreScene scoreScene = new ScoreScene(scoreManager, this, SCREEN_SIZE);
		
		this.switchScene(scoreScene.getScene());
	}

	/**
	 * Set JavaFX Thread scene to the scene passed as argument.
	 * @param scene
	 */
	void switchScene(final Scene scene) {
		Platform.runLater(() -> {
			this.primaryStage.setScene(scene);
			this.primaryStage.setWidth(SCREEN_SIZE);		//DO NOT TOUCH: doesn't work without width and height
			this.primaryStage.setHeight(SCREEN_SIZE);
			this.primaryStage.show();
		});
	}
	
	/**
	 * Main function: launch the program.
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Exit function: exit from the program.
	 * @param args
	 */
	private void exit() {
		System.exit(0);
	}
	
}
