package trash;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import game.engine.ScoreManager;
import game.util.Pair;
import game.util.RankItem;

/**
 * ScoreScene displays a GUI showing your current score in a beautiful table.
 */
public class ScoreSceneLegacy {

    private final ScoreManager scoreManager;
//    private final ScoreCalc scoreCalc;
    private final Scene scene;
    
    //tables
    private TableView<RankItem> leaderboardTable = new TableView<>();
	private TableView<RankItem> yourScoreTable = new TableView<>();
	//data to put in tables
	private final ObservableList<Pair<String,Integer>> ranking;
	private final ObservableList<RankItem> leaderboardData;
	private final ObservableList<RankItem> yourScoreData;

    /**
     * Creates & initializes this class.
     * Then, Creates a ScoreScene.
     */
    @SuppressWarnings("unchecked")
	public ScoreSceneLegacy(final ScoreManager scoreManager, final int screenSize) {
    	this.scene = new Scene(new Group());
    	this.scoreManager = scoreManager;
    	this.ranking = FXCollections.observableArrayList(this.scoreManager.getRanking());
    	this.leaderboardData = FXCollections.observableArrayList();
    	this.yourScoreData = FXCollections.observableArrayList(
    			new RankItem("", 
    					this.scoreManager.getPlayerName(), 
    					this.scoreManager.getScore()));
    	
    	//add ranking data to leaderboardData
		for (var i: this.ranking) {
			final int index = this.ranking.indexOf(i);
			this.leaderboardData.add(new RankItem(
						Integer.toString(index + 1),
						i.get1(), 
						i.get2()));
		}
    	
		String stylesheet = getClass().getResource("/game/ui/scorescene_styles.css").toExternalForm();
		scene.getStylesheets().add(stylesheet);
		
		//LEADERBOARD
		//create label (vbox)
		final Label leaderboardLabel = new Label("Leaderboard");
		leaderboardLabel.setFont(new Font("Arial", 20));
		//make table editable
		this.leaderboardTable.setEditable(true);
		//create columns
		final TableColumn<RankItem, Integer> rankCol = new TableColumn<>("Rank");
		final TableColumn<RankItem, String> nameCol = new TableColumn<>("Name");
		final TableColumn<RankItem, Integer> scoreCol = new TableColumn<>("Score");
		//fetch data
		rankCol.setCellValueFactory(new PropertyValueFactory<>("rank"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));
		//turn off data sorting
		rankCol.setSortable(false);
		nameCol.setSortable(false);
		scoreCol.setSortable(false);
		//add columns to table
		this.leaderboardTable.setItems(this.leaderboardData);
		this.leaderboardTable.getColumns().addAll(rankCol, nameCol, scoreCol);
		//set minimum width & table bounds
		//TODO: set these values with CSS only
		rankCol.setMinWidth(60);
		nameCol.setMinWidth(200);
		scoreCol.setMinWidth(200);
		this.leaderboardTable.setMaxHeight(148);
		this.leaderboardTable.setMaxWidth(462);
		
		//YOUR RANK
		//create your rank label (vbox)
		final Label yourScoreLabel = new Label("Your Score");
		yourScoreLabel.setFont(new Font("Verdana", 20));
		//make table editable
		this.yourScoreTable.setEditable(true);
		//create columns
		final TableColumn<RankItem, Integer> yourRankCol = new TableColumn<>("Rank");
		final TableColumn<RankItem, String> yourNameCol = new TableColumn<>("Name");
		final TableColumn<RankItem, Integer> yourScoreCol = new TableColumn<>("Score");
		//fetch data
		yourRankCol.setCellValueFactory(new PropertyValueFactory<>("rank"));
		yourNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		yourScoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));
		//turn off data sorting
		yourRankCol.setSortable(false);
		yourNameCol.setSortable(false);
		yourScoreCol.setSortable(false);
		//add columns to table
		this.yourScoreTable.setItems(this.yourScoreData);
		this.yourScoreTable.getColumns().addAll(yourRankCol, yourNameCol, yourScoreCol);
		//set minimum width & table bounds
		//TODO: set these values with CSS only
		yourRankCol.setMinWidth(60);
		yourNameCol.setMinWidth(200);
		yourScoreCol.setMinWidth(200);
		this.yourScoreTable.setMaxHeight(52);
		this.yourScoreTable.setMaxWidth(462);
		
		//create menu button
		final Button menuButton = new Button("Home");
		menuButton.setOnAction(new EventHandler<ActionEvent>() {
			private ScoreManager scoreManager;
			@Override
			public void handle(ActionEvent e) {
		    	//Go to GameApplication menu
				this.scoreManager.menu();
			}
			
			//initialize scoreManager in this class
			private EventHandler<ActionEvent> init(ScoreManager scoreManager) {
				this.scoreManager = scoreManager;
				return this;
			}
			
		}.init(this.scoreManager));
		
		//add items to vertical box
		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(yourScoreLabel, this.yourScoreTable, leaderboardLabel, this.leaderboardTable, menuButton);
		//add vertical box to group
		((Group) this.scene.getRoot()).getChildren().addAll(vbox);
    }
    
    /**
     * Gets the Scene inside ScoreScene.
     * @return this.scene
     */
    public Scene getScene() {
        return this.scene;
    }
    
    /**
     * Gets this class.
     * @return ScoreScene
     */
    public ScoreSceneLegacy get() {
    	return this;
    }

}
