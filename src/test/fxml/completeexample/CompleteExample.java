package test.fxml.completeexample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CompleteExample extends Application {

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
