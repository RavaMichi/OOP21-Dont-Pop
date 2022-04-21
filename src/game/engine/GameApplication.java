package game.engine;

import game.ui.GameScene;
import game.ui.MenuScene;
import game.ui.ScoreScene;
import game.util.Leaderboard;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class GameApplication extends Application {
	
	private static final String SAVE_PATH = "Dont-Pop/res/.save";
	
	//guardare dove lo devo prendere
	private String playerName;
	private Leaderboard leaderboard;
	@SuppressWarnings("unused")
	private ScoreManager scoremanager;
	public final static int screenSize;	// usare la percentuale dello schermo non valori da 0 a 1: 0.n * size; dove n è
										// la percentuale dello schermo. ergo ho i valori da 0 a 1
	private Stage primaryStage;
	/**
	 * Static initializer: gets screen size.
	 */
	static {
		Rectangle2D screenBounds = Screen.getPrimary().getBounds();
		screenSize = (int) (0.95 * Math.min(screenBounds.getWidth(), screenBounds.getHeight()));
	}
	
	/**
	 * Class constructor.
	 */
	public GameApplication() {
		this.playerName = "";
	}
	
	/**
	 * Gets the player's name
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
		return num / screenSize;	// ritorna la posizione percentuale rispetto allo schermo. esempio gli passo 350 e la risoluzione è 700 lui mi tira fuori 0.5 
									// (50% della lunghezza dello schermo)
	}

	/**
	 * Convert percentage size to pixel size.
	 * @param num
	 * @return pixel size
	 */
	public static int convertToInt(final double num) {
		return (int) (num * screenSize);
	}

	/**
	 * Start GUI, then launch the menu.
	 * @throws Exception 
	 */
	public void start(final Stage primaryStage) throws Exception { // public void start(final Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.leaderboard = new Leaderboard(SAVE_PATH);
		this.primaryStage.setWidth(screenSize);
		this.primaryStage.setHeight(screenSize);
		this.primaryStage.setResizable(false); //ScoreCalc scolreCalc= new ScoreCalc(); ScoreManager scoremanager = new
		this.menu();//ScoreManager(this.scolreCalc);// prende score e il player dal game engine
		
		this.primaryStage.setOnCloseRequest(e -> this.exit());
		
		this.primaryStage.show();
	}

	/**
	 * Launch menu GUI.
	 * @throws Exception 
	 */
	public void menu() {
		try {
			MenuScene menuscene = new MenuScene(this, screenSize);
			this.switchScene(menuscene.getScene());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Launch game GUI (run the game).
	 */
	public void game() {
		GameScene gamescene = new GameScene(screenSize);
		GameEngine gameEngine = new GameEngine(this, gamescene);
		this.switchScene(gamescene.getScene());
		new Thread(gameEngine).start();

	}

	/**
	 * Launch score GUI (view score ranking).
	 * @param score
	 * @throws Exception 
	 */
	public void score(final int score){
		ScoreManager scoremanager = new ScoreManager(this.playerName, score, this.leaderboard, this);// prende score e il nome player e leaderboard dal game engine e aggiungere al costruttpre il nome del player
		try {
			ScoreScene scoreScene = new ScoreScene(scoremanager, this, screenSize);
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
		ScoreManager scoreManager = new ScoreManager(this.leaderboard, this);
		ScoreScene scoreScene = new ScoreScene(scoreManager, this, screenSize);
		
		this.switchScene(scoreScene.getScene());
	}

	/**
	 * Set JavaFX Thread scene to the scene passed as argument.
	 * @param scene
	 */
	void switchScene(Scene scene) {
		Platform.runLater(() -> {
			this.primaryStage.setScene(scene);
			this.primaryStage.setWidth(screenSize);		//DO NOT TOUCH: doesn't work without width and height
			this.primaryStage.setHeight(screenSize);
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
