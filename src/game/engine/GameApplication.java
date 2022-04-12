package game.engine;
import java.awt.Dimension;

import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;

import game.ui.GameScene;
import game.ui.ScoreScene;
import game.util.Leaderboard;
import game.util.ScoreCalc;
import javafx.application.Application;
import javafx.stage.Stage;
import test.TestScoreSceneLegacy;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class GameApplication extends Application {
	
	//guardare dove lo devo prendere
	final String playerName;
	private final Leaderboard leaderboard;
	private final ScoreManager scoremanager;
	public final static int size;// usare la percentuale dello schermo non valori da 0 a 1: 0.n * size; dove n è
									// la percentuale dello schermo. ergo ho i valori da 0 a 1
	public int score = 0;
	private Stage primaryStage2 = new Stage();
		ScoreCalc scoreCalc = new ScoreCalc();
		
		
	static {
		Rectangle2D screenBounds = Screen.getPrimary().getBounds();
		size = (int) Math.min(screenBounds.getWidth(), screenBounds.getHeight());
	}

	
	public void setPlayerName(final String playerName) {
		this.playerName=playerName;
	}
	
	// prende un int che va da zero a 1
	static double convertToDouble(final int num) {// deve essere compreso tra 0 e n e lo devo trasformare in 0-1
		double tmp = num / size;// ritorna la posizione percentuale rispetto allo scermo. esempio gli passo 350 e la risoluzione è 700 lui mi tira fuori 0.5 (50% della lunghezza dello
		// schermo )
		return tmp;
	}

	// fa l'opposto di quello sopra
	static int convertToInt(final double num) {
		int tmp = (int) num * size;
		return tmp;
	}

	public void startGameApplication() { // public void start(final Stage primaryStage) {
		primaryStage2.setWidth(size);
		primaryStage2.setHeight(size);
		primaryStage2.setResizable(false); //ScoreCalc scolreCalc= new ScoreCalc(); ScoreManager scoremanager = new
		 this.menu();//ScoreManager(this.scolreCalc);// prende score e il player dal game engine
			
	}

	public void menu() {
		leaderboard=new Leaderboard(this.playerName);
		MenuManager menumanager = new MenuManager();		//manca la classe, aspetto per modificare il cosrtuttore
		MenuScene menuscene = new MenuScene(menumanager);	//manca la classe, aspetto per modificare il cosrtuttore
		this.setSceneM(menuscene.get());					//manca la classe, aspett il nome del metodo
	}


	public void game() {
		GameScene gamescene = new GameScene(size);
		GameEngine gameEngine = new GameEngine(this, gamescene , scoreCalc);
		gameEngine.run();
		this.setSceneM(gamescene.getScene());

	}

	public void score(/*int punteggio da mettere e creare lo scalc fuori*/) {
	
		ScoreManager scoremanager = new ScoreManager(leaderboard);// prende score e il nome  player e leaderboard dal game engine e aggiungere al costruttpre il nome del player
		ScoreScene scoreScene = new ScoreScene(this,this.primaryStage2,this.scoremanager, this.scoreCalc);//aggiungere il manager
	
		this.setSceneM(scoreScene.getScene());

	}

	// SETTA LA SCENA CHE GLI PASSO AL THREAD DEL JAVA FX
	void setSceneM(Scene scene) {
		Platform.runLater(() -> {
			primaryStage2.setscene(scene);
			primaryStage2.show();
		});
	}
	
	public  void main(String[] args) {
		startGameApplication();
	}

}