package test.fxml.experimental;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Controller extends Application{
	
	public void start(Stage stage) throws Exception {
		VBox root = FXMLLoader.load(ClassLoader.getSystemResource("test/fxml/experimental/sample.fxml"));
		
//		FXMLLoader loader = new FXMLLoader();
//		loader.setController(this);
		
		Scene scene = new Scene(root, 500, 250);

		stage.setTitle("JavaFX - Complete Example");
		stage.setScene(scene);
		stage.show();
	}
	
	
}
