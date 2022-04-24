package test.fxml.completeexample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import game.util.RankItem;

/**
 * Test class.
 */
public class UIController {
//	@FXML
//	private Label lbl;
//	
//	@FXML
//	private Button btn;
//	
//	@FXML
//	public void btnOnClickHandler() {
//		lbl.setText("Hello, World!");
//	}
	
	@FXML
	private Label yourScoreLabel;
	@FXML
	private TableView<RankItem> yourScoreTable;
	
	@FXML
	private TableColumn<RankItem, String> yourRankCol;
	@FXML
	private TableColumn<RankItem, String> yourNameCol;
	@FXML
	private TableColumn<RankItem, Integer> yourScoreCol;
	
	@FXML
	private Label leaderboardLabel;
//	@FXML
//	private TableView<RankItem> leaderboardTable;
//	
//	@FXML
//	private TableColumn<RankItem, String> rankCol;
//	@FXML
//	private TableColumn<RankItem, String> nameCol;
//	@FXML
//	private TableColumn<RankItem, Integer> scoreCol;
	
	@FXML
	private Button menuButton;
	
	@FXML
	public void buttonClicked() {
		menuButton.setText("Yamete Kudasai");
	}
}
