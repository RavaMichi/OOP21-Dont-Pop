package test.fxml.builder;

import game.engine.GameApplication;
import game.util.ScoreCalc;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import test.ScoreManager;

public class LaunchBuilder extends Application {
	
//	private final ScoreManager scoreManager;
//	private final ScoreCalc scoreCalc;
	
	

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/test/fxml/builder/sceneBuilder.fxml"));
		
		//controller created here
		BuilderController controller = new BuilderController();
		loader.setController(controller);
		
		//controller created in fxml
//		BuilderController controller = loader.getController();
		
		BorderPane root = loader.load();
		
//		BorderPane root = FXMLLoader.load(ClassLoader.getSystemResource("test/fxml/builder/sceneBuilder.fxml"));
		Scene scene = new Scene(root);
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
