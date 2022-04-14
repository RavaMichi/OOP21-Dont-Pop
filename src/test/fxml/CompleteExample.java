package test.fxml;

import game.util.ScoreCalc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import test.ScoreManager;

public class CompleteExample extends Application {

	private final ScoreManager scoreManager;
	private final ScoreCalc scoreCalc;
	
	private final ObservableList<Pair<String, Integer>> ranking;
	private final ObservableList<RankItem> yourScoreData;
	
	@Override
	public void start(Stage stage) throws Exception {
		VBox root = FXMLLoader.load(ClassLoader.getSystemResource("test/fxml/GUI.fxml"));
		
		Scene scene = new Scene(root, 500, 250);
		
		stage.setTitle("JavaFX - Complete Example");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
