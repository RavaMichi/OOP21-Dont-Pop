package test;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import game.util.ScoreCalc;
import game.util.Pair;
import game.util.RankItem;

public class TestScoreSceneWithoutFXML extends Application {

	private final ScoreManager scoreManager;
	private final ScoreCalc scoreCalc;
	
	//tables
	private TableView<RankItem> leaderboardTable = new TableView<>();
	private TableView<RankItem> yourScoreTable = new TableView<>();
	//data to put in tables
	private final ObservableList<Pair<String,Integer>> ranking;
	private final ObservableList<RankItem> leaderboardData;
	private final ObservableList<RankItem> yourScoreData = 
			FXCollections.observableArrayList(new RankItem("", "Stocazzo", 3));
		
	/**
	 * Class constructor.
	 */
	public TestScoreSceneWithoutFXML(/*put score calc arg here after debugging*/) {
		this.scoreCalc = new ScoreCalc();
		this.scoreManager = new ScoreManager(this.scoreCalc);	//debug
		this.ranking = FXCollections.observableArrayList(this.scoreManager.getRanking());
		this.leaderboardData = FXCollections.observableArrayList();
		
		//add ranking data to data
		for (var i: this.ranking) {
			final int index = this.ranking.indexOf(i);
			this.leaderboardData.add(new RankItem(
						Integer.toString(index + 1),
						i.get1(), 
						i.get2()));
		}
	}
	
	/**
	 * Starts this class (debug).
	 * Remove this in non-test version.
	 */
	@Override
	public void start(final Stage stage) throws Exception {
		//application will pass you the screen size
		//get screen size and choose the smallest axis
		final Rectangle2D screenBounds = Screen.getPrimary().getBounds();
		final double screenSize = Math.min(screenBounds.getWidth(), screenBounds.getHeight());
		
		//create a non-resizable scene
//		final Scene scene = new Scene(new Group());
		stage.setTitle("Score Ranking");
		stage.setWidth(screenSize);
		stage.setHeight(screenSize);
		stage.setResizable(false);
		
		

		
		//create leaderboard label (vbox)
		final Label leaderboardLabel = new Label("Leaderboard");
		leaderboardLabel.setId("leaderboard-label");
//		leaderboardLabel.setFont(new Font("Arial", 20));
		//make table editable
		this.leaderboardTable.setEditable(true);
		this.leaderboardTable.setId("leaderboard-table");
		//create columns
		final TableColumn<RankItem, Integer> rankCol = new TableColumn<>("Rank");
		final TableColumn<RankItem, String> nameCol = new TableColumn<>("Name");
		final TableColumn<RankItem, Integer> scoreCol = new TableColumn<>("Score");
		//fetch data
		rankCol.setCellValueFactory(new PropertyValueFactory<>("rank"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));
		
		rankCol.setId("rank-col");
		nameCol.setId("name-col");
		scoreCol.setId("score-col");
		
		//turn off data sorting
		rankCol.setSortable(false);
		nameCol.setSortable(false);
		scoreCol.setSortable(false);
		//add columns to table
		this.leaderboardTable.setItems(this.leaderboardData);
		this.leaderboardTable.getColumns().addAll(rankCol, nameCol, scoreCol);
		//set minimum width & table bounds
		//TODO: set these values with CSS only
//		rankCol.setMinWidth(60);
//		nameCol.setMinWidth(200);
//		scoreCol.setMinWidth(200);
//		this.leaderboardTable.setMaxHeight(148);
//		this.leaderboardTable.setMaxWidth(462);
		
		//create your rank label (vbox)
		final Label yourScoreLabel = new Label("Your Score");
		yourScoreLabel.setId("your-score-label");
		yourScoreLabel.setFont(new Font("Verdana", 20));
		//make table editable
		this.yourScoreTable.setEditable(true);
		this.yourScoreTable.setId("your-score-table");
		//create columns
		final TableColumn<RankItem, Integer> yourRankCol = new TableColumn<>("Rank");
		final TableColumn<RankItem, String> yourNameCol = new TableColumn<>("Name");
		final TableColumn<RankItem, Integer> yourScoreCol = new TableColumn<>("Score");
		//fetch data
		yourRankCol.setCellValueFactory(new PropertyValueFactory<>("rank"));
		yourNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		yourScoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));
		
		yourRankCol.setId("rank-col");
		yourNameCol.setId("name-col");
		yourScoreCol.setId("score-col");
		
		//turn off data sorting
		yourRankCol.setSortable(false);
		yourNameCol.setSortable(false);
		yourScoreCol.setSortable(false);
		//add columns to table
		this.yourScoreTable.setItems(this.yourScoreData);
		this.yourScoreTable.getColumns().addAll(yourRankCol, yourNameCol, yourScoreCol);
		//set minimum width & table bounds
		//TODO: set these values with CSS only
//		yourRankCol.setMinWidth(60);
//		yourNameCol.setMinWidth(200);
//		yourScoreCol.setMinWidth(200);
//		this.yourScoreTable.setMaxHeight(52);
//		this.yourScoreTable.setMaxWidth(462);
		
		//create menu button
		final Button menuButton = new Button("Home");
		menuButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {
		    	/*Go to GameApplication menu*/
		    }
		});

		//add items to vertical box
		final VBox vbox = new VBox();
		vbox.setId("vbox");
//		vbox.setSpacing(5);
//		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(yourScoreLabel, this.yourScoreTable, leaderboardLabel, this.leaderboardTable, menuButton);
		
		final BorderPane root = new BorderPane(vbox);
//		BorderPane.setAlignment(vbox, Pos.CENTER);
		vbox.setAlignment(Pos.TOP_CENTER);
		
		final Scene scene = new Scene(root);
		
//		//add vertical box to group
//		((Group) scene.getRoot()).getChildren().addAll(root);
		
		//setting css style sheet
		String stylesheet = getClass().getResource("/game/ui/scorescene_styles.css").toExternalForm();
		scene.getStylesheets().add(stylesheet);

		//add scene to stage and make it visible (remove in final version)
		stage.setScene(scene);
		stage.show();
	}
	
	/*
	 * Metti il punteggio corrente sopra alla tabella (a new table above this table?)
	 * Evidenzia la riga del punteggio corrente
	 * Centra il bottone home
	 */
}
