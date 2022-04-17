package game.ui;

import game.engine.GameApplication;
import game.engine.ScoreManager;
import game.util.Pair;
import game.util.RankItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

/**
 * This class is the FXML controller of ScoreScene class.
 */
public class ScoreSceneController {
	
	private final ScoreManager scoreManager;
	private final int screenSize;
	private final ObservableList<Pair<String, Integer>> ranking;
	private final ObservableList<RankItem> leaderboardData;
	private final ObservableList<RankItem> yourScoreData;
	
	/**
	 * Initializes non-fxml fields of this class.
	 * Then, returns control to ScoreScene which will build the layout as per the FXML file.
	 * @param scoreManager
	 * @param screenSize
	 */
	public ScoreSceneController(final ScoreManager scoreManager, final int screenSize) {
		this.scoreManager = scoreManager;
		this.screenSize = screenSize;
		this.ranking = FXCollections.observableArrayList(this.scoreManager.getRanking());
		this.leaderboardData = FXCollections.observableArrayList();
		this.yourScoreData = FXCollections.observableArrayList(
				new RankItem("", 
						this.scoreManager.getPlayerName(), 
						this.scoreManager.getScore()));

		for(var i: this.ranking) {
			final int index = this.ranking.indexOf(i);
			this.leaderboardData.add(new RankItem(
					Integer.toString(index + 1),
					i.get1(),
					i.get2()));
		}
	}
	
	//These fields are automatically injected by the FXML loader.
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
	
	/**
	 * Sets some properties dependent on Java variables (not accessible from neither FXML nor CSS).
	 */
	public void initialize() {
		this.yourScoreTable.setItems(this.yourScoreData);
		this.leaderboardTable.setItems(this.leaderboardData);
		
		//setting tables % width
		this.yourScoreTable.setMaxWidth(this.screenSize * 0.85);
		this.leaderboardTable.setMaxWidth(this.screenSize * 0.85);
		
		//setting columns % width
		this.yourRankCol.setPrefWidth(this.screenSize * 0.1);
		this.yourNameCol.setPrefWidth(this.screenSize * 0.449);
		this.yourScoreCol.setPrefWidth(this.screenSize * 0.3);
		this.rankCol.setPrefWidth(this.screenSize * 0.1);
		this.nameCol.setPrefWidth(this.screenSize * 0.449);
		this.scoreCol.setPrefWidth(this.screenSize * 0.3);
		
		//setting columns % height
		this.yourScoreTable.setFixedCellSize(this.screenSize * 0.03);
		this.leaderboardTable.setFixedCellSize(this.screenSize * 0.03);
		
		//doesn't show your current score if player views leaderboard from main menu
		if (this.scoreManager.isReadOnly()) {
			this.yourScore.setVisible(false);
		}
	}
	
	/**
	 * Go back to main menu.
	 * Calls the relative ScoreManager function.
	 * @throws Exception 
	 */
	public void menu() throws Exception {
		this.scoreManager.menu();
	}
	
}