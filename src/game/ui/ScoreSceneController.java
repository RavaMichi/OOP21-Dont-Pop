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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * This class is the FXML controller of ScoreScene class.
 */
public class ScoreSceneController {
	
	private final ScoreManager scoreManager;
	private final GameApplication application;
	private final int screenSize;
	private final ObservableList<Pair<String, Integer>> ranking;
	private final ObservableList<RankItem> leaderboardData;
	private final ObservableList<RankItem> yourScoreData;
	private final static int DEFAULT_SIZE = 300;
	
	//These fields are automatically injected by the FXML loader.
	@FXML private AnchorPane anchorPane;
	@FXML private Label yourScoreLabel;
	@FXML private TableView<RankItem> yourScoreTable;		//your score table
	@FXML private TableColumn<RankItem, String> yourRankCol;
	@FXML private TableColumn<RankItem, String> yourNameCol;
	@FXML private TableColumn<RankItem, Integer> yourScoreCol;
	@FXML private Label leaderboardLabel;
	@FXML private TableView<RankItem> leaderboardTable;		//leaderboard table
	@FXML private TableColumn<RankItem, String> rankCol;
	@FXML private TableColumn<RankItem, String> nameCol;
	@FXML private TableColumn<RankItem, Integer> scoreCol;
	@FXML private Button playButton;
	@FXML private Button menuButton;
	
	/**
	 * Initializes non-fxml fields of this class.
	 * Then, returns control to ScoreScene which will build the layout as per the FXML file.
	 * @param scoreManager
	 * @param screenSize
	 */
	public ScoreSceneController(final ScoreManager scoreManager, final GameApplication application, final int screenSize) {
		this.scoreManager = scoreManager;
		this.application = application;
		this.screenSize = screenSize;
		this.ranking = FXCollections.observableArrayList(this.scoreManager.getRanking());
		this.leaderboardData = FXCollections.observableArrayList();
		this.yourScoreData = FXCollections.observableArrayList(
				new RankItem(
						this.scoreManager.getRank(), 
						this.scoreManager.getPlayerName(), 
						this.scoreManager.getScore()));

		for(final var i: this.ranking) {
			final int index = this.ranking.indexOf(i);
			this.leaderboardData.add(new RankItem(
					Integer.toString(index + 1),
					i.get1(),
					i.get2()));
		}
	}
	
	/**
	 * Sets some properties dependent on Java variables (not accessible from neither FXML nor CSS).
	 */
	public void initialize() {
		this.yourScoreTable.setItems(this.yourScoreData);
		this.leaderboardTable.setItems(this.leaderboardData);
		
		//setting tables % width
		final double tableWidth = DEFAULT_SIZE * 0.85;
		this.yourScoreTable.setMaxWidth(tableWidth);
		this.leaderboardTable.setMaxWidth(tableWidth);
		
		//setting columns % width
		this.yourRankCol.setPrefWidth(tableWidth * 0.191);
		this.yourNameCol.setPrefWidth(tableWidth * 0.515);
		this.yourScoreCol.setPrefWidth(tableWidth * 0.230);
		this.rankCol.setPrefWidth(tableWidth * 0.191);
		this.nameCol.setPrefWidth(tableWidth * 0.515);
		this.scoreCol.setPrefWidth(tableWidth * 0.230);
		//setting columns % height
//		this.yourScoreTable.setFixedCellSize(this.screenSize * 0.03);
//		this.leaderboardTable.setFixedCellSize(this.screenSize * 0.03);
		
		//doesn't show your current score if player views leaderboard from main menu
		if (this.scoreManager.isReadOnly()) {
			this.yourScoreLabel.setVisible(false);
			this.yourScoreTable.setVisible(false);
			this.playButton.setText("Play");
		} else {
			this.yourScoreTable.setSelectionModel(null);
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
	
	/**
	 * Play game (again).
	 * Calls the relative GameApplication function.
	 */
	public void play() {
		this.application.game();
	}
	
}