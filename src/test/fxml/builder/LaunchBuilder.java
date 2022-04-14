package test.fxml.builder;

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
		
		BuilderController controller = new BuilderController();
		loader.setController(controller);
		
//		BuilderController controller = loader.getController();
		BorderPane root = loader.load();
		
//		BorderPane root = FXMLLoader.load(ClassLoader.getSystemResource("test/fxml/builder/sceneBuilder.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
