package game.engine;

import java.io.IOException;

//import java.awt.Dimension;
//import java.awt.Toolkit;

import game.ui.GameScene;
import game.ui.MenuScene;
import game.ui.ScoreScene;
import game.util.Leaderboard;
import game.util.ScoreCalc;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class GameApplication extends Application {
	
	private static final String SAVE_PATH = ".save";
	
	//guardare dove lo devo prendere
	String playerName;
	private Leaderboard leaderboard;
	private ScoreManager scoremanager;
	public final static int screenSize;	// usare la percentuale dello schermo non valori da 0 a 1: 0.n * size; dove n è
										// la percentuale dello schermo. ergo ho i valori da 0 a 1
	private Stage primaryStage;
//	ScoreCalc scoreCalc = new ScoreCalc();

	/**
	 * Static initializer: gets screen size.
	 */
	static {
		Rectangle2D screenBounds = Screen.getPrimary().getBounds();
		screenSize = (int) Math.min(screenBounds.getWidth(), screenBounds.getHeight());
	}

	/**
	 * Set player name to the String passed as argument.
	 * @param playerName
	 */
	public void setPlayerName(final String playerName) {
		this.playerName = playerName;
	}
	
	// prende un int che va da zero a 1
	/**
	 * Convert pizel size to percentage size.
	 * @param num
	 * @return percentage size
	 */
	public static double convertToDouble(final int num) {	// deve essere compreso tra 0 e n e lo devo trasformare in 0-1
		return num / screenSize;	// ritorna la posizione percentuale rispetto allo schermo. esempio gli passo 350 e la risoluzione è 700 lui mi tira fuori 0.5 
									// (50% della lunghezza dello schermo)
	}

	// fa l'opposto di quello sopra
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
	 */
	public void start(final Stage primaryStage) { // public void start(final Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.leaderboard = new Leaderboard(SAVE_PATH);
		primaryStage.setWidth(screenSize);
		primaryStage.setHeight(screenSize);
		primaryStage.setResizable(false); //ScoreCalc scolreCalc= new ScoreCalc(); ScoreManager scoremanager = new
		this.game();//ScoreManager(this.scolreCalc);// prende score e il player dal game engine
		
		this.primaryStage.setOnCloseRequest(e -> exit());
		
		this.primaryStage.show();
	}

	/**
	 * Launch menu GUI.
	 * @throws Exception 
	 */
	public void menu() throws Exception {
		//menumanager non servirà a un cazzo, poi andrà tolto
		MenuScene menuscene = new MenuScene(screenSize);	//manca la classe, aspetto per modificare il cosrtuttore
		this.setSceneM(menuscene.getScene());					//manca la classe, aspett il nome del metodo
	}

	/**
	 * Launch game GUI (run the game).
	 */
	public void game() {
		GameScene gamescene = new GameScene(screenSize);
		GameEngine gameEngine = new GameEngine(this, gamescene);
		this.setSceneM(gamescene.getScene());
		new Thread(gameEngine).start();

	}

	/**
	 * Launch score GUI (view score ranking).
	 * @param score
	 * @throws Exception 
	 */
	public void score(final int score) throws Exception {
		ScoreManager scoremanager = new ScoreManager(this.playerName, score, this.leaderboard, this);// prende score e il nome player e leaderboard dal game engine e aggiungere al costruttpre il nome del player
		ScoreScene scoreScene = new ScoreScene(this.scoremanager, screenSize);//aggiungere il manager
	
		this.setSceneM(scoreScene.getScene());
	}

	// SETTA LA SCENA CHE GLI PASSO AL THREAD DEL JAVA FX
	/**
	 * Set JavaFX Thread scene to the scene passed as argument.
	 * @param scene
	 */
	void setSceneM(Scene scene) {
		Platform.runLater(() -> {
			primaryStage.setScene(scene);
			primaryStage.show();
		});
	}
	
	/**
	 * Main function: launch the program.
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	private void exit() {
		System.exit(0);
	}
	
}