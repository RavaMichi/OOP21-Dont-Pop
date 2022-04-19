package test.fxml.scorescene;
	
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Runs test class of ScoreScene.
 */
public class LaunchScoreScene extends Application {
//	@Override
//	public void start(Stage primaryStage) {
//		try {
//			BorderPane root = new BorderPane();
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public static void main(String[] args) {
//		launch(args);
//	}
	
	public static void main(String[] args) {
		launch(TestScoreSceneWithoutFXML.class, args);
	}

	/**
	 * Entry point of JavaFX application.
	 */
	@Override
	public void start(Stage stage) throws Exception {
		
	}
}
