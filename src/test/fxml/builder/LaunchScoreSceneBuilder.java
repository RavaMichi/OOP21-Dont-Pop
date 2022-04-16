package test.fxml.builder;

import game.engine.GameApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LaunchScoreSceneBuilder extends Application {
	
//	private final ScoreManager scoreManager;
//	private final ScoreCalc scoreCalc;
	
	

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/game/fxml/scorescene.fxml"));
		
		//controller created here
		ScoreSceneController controller = new ScoreSceneController();
		loader.setController(controller);
		
		//controller created in fxml
//		BuilderController controller = loader.getController();
		
		BorderPane root = loader.load();

		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/game/css/scorescene-styles.css").toExternalForm());
		stage.setTitle("Score Ranking");
		stage.setWidth(GameApplication.screenSize * 0.90);
		stage.setHeight(GameApplication.screenSize * 0.90);
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
