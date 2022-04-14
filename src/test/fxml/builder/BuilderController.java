package test.fxml.builder;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class BuilderController {
	
	public BuilderController() {
		
	}
	
	@FXML
	private VBox layout;
	@FXML
	private VBox yourScore;
	@FXML
	private Label yourScoreLabel;
	@FXML
	private TableView yourScoreTable;
	@FXML
	private TableColumn rankCol;
	@FXML
	private TableColumn nameCol;
	@FXML
	private TableColumn scoreCol;
	@FXML
	private VBox leaderboard;
	@FXML
	private Label leaderboardLabel;
	@FXML
	private TableView leaderboardTable;
	@FXML
	private VBox menu;
	@FXML
	private Button menuButton;
}