package test.fxml.builder;

import game.engine.GameApplication;
import game.util.Pair;
import game.util.RankItem;
import game.util.ScoreCalc;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import test.ScoreManager;

public class ScoreSceneController {
	
	private final ScoreManager scoreManager;
	private final ScoreCalc scoreCalc;
	
	private final ObservableList<Pair<String, Integer>> ranking;
	private final ObservableList<RankItem> leaderboardData;
	private final ObservableList<RankItem> yourScoreData = 
			FXCollections.observableArrayList(new RankItem("", "Stocazzo", 3));
	
	public ScoreSceneController() {
		this.scoreCalc = new ScoreCalc();
		this.scoreManager = new ScoreManager(this.scoreCalc);
		this.ranking = FXCollections.observableArrayList(this.scoreManager.getRanking());
		this.leaderboardData = FXCollections.observableArrayList();
		
		for(var i: this.ranking) {
			final int index = this.ranking.indexOf(i);
			this.leaderboardData.add(new RankItem(
					Integer.toString(index + 1),
					i.get1(),
					i.get2()));
		}
	}
	
	@FXML private VBox layout;
	@FXML private VBox yourScore;
	@FXML private Label yourScoreLabel;
	@FXML private TableView<RankItem> yourScoreTable;		//your score table
	@FXML private TableColumn<RankItem, String> yourRankCol;
	@FXML private TableColumn<RankItem, String> yourNameCol;
	@FXML private TableColumn<RankItem, Integer> yourScoreCol;
	@FXML private VBox leaderboard;
	@FXML private Label leaderboardLabel;
	@FXML private TableView<RankItem> leaderboardTable;		//leaderboard table
	@FXML private TableColumn<RankItem, String> rankCol;
	@FXML private TableColumn<RankItem, String> nameCol;
	@FXML private TableColumn<RankItem, Integer> scoreCol;
	@FXML private VBox menu;
	@FXML private Button menuButton;
	
	public void initialize() {
		this.yourScoreTable.setItems(this.yourScoreData);
		this.leaderboardTable.setItems(this.leaderboardData);
		
		//setting tables % width
		this.yourScoreTable.setMaxWidth(GameApplication.convertToInt(0.85));
		this.leaderboardTable.setMaxWidth(GameApplication.convertToInt(0.85));
		
		//setting columns % width
		this.yourRankCol.setPrefWidth(GameApplication.convertToInt(0.1));
		this.yourNameCol.setPrefWidth(GameApplication.convertToInt(0.449));
		this.yourScoreCol.setPrefWidth(GameApplication.convertToInt(0.3));
		this.rankCol.setPrefWidth(GameApplication.convertToInt(0.1));
		this.nameCol.setPrefWidth(GameApplication.convertToInt(0.449));
		this.scoreCol.setPrefWidth(GameApplication.convertToInt(0.3));
		
		//setting columns % height
		this.yourScoreTable.setFixedCellSize(GameApplication.convertToInt(0.03));
		this.leaderboardTable.setFixedCellSize(GameApplication.convertToInt(0.03));
		
	}
	
	public void menu() {
		this.menuButton.setText("Yamete Kudasai! ^_^");
	}
	
}