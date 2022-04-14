package test;

	import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
	import javafx.event.ActionEvent;
	import javafx.event.EventHandler;
	import javafx.fxml.FXML;
	import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
	import javafx.geometry.Rectangle2D;
	import javafx.scene.Group;
	import javafx.scene.Parent;
	import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
	import javafx.stage.Screen;
	import javafx.stage.Stage;
	
	import game.util.ScoreCalc;
	
	import java.net.URL;
	import java.util.Locale;
	import java.util.ResourceBundle;
	
	import game.util.Pair;
	import game.util.RankItem;

public class TestScoreScene extends Application {

	private final ScoreManager scoreManager;
	private final ScoreCalc scoreCalc;
	
	//tables
//	private TableView<RankItem> leaderboardTable = new TableView<>();
//	private TableView<RankItem> yourScoreTable = new TableView<>();
	//data to put in tables
	private final ObservableList<Pair<String,Integer>> ranking;
	private final ObservableList<RankItem> leaderboardData;
	private final ObservableList<RankItem> yourScoreData;
		
	@FXML
	private Label yourScoreLabel;
	@FXML
	private Label leaderboardLabel;
	
	@FXML
	private TableView<RankItem> yourScoreTable;
	@FXML
	private TableView<RankItem> leaderboardTable;
	
	@FXML
	private TableColumn<RankItem, String> yourRankCol;
	@FXML
	private TableColumn<RankItem, String> yourNameCol;
	@FXML
	private TableColumn<RankItem, Integer> yourScoreCol;
	@FXML
	private TableColumn<RankItem, String> rankCol;
	@FXML
	private TableColumn<RankItem, String> nameCol;
	@FXML
	private TableColumn<RankItem, Integer> scoreCol;

	
	/**
	 * Class constructor.
	 */
	public TestScoreScene(/*put score calc arg here after debugging*/) {
		this.scoreCalc = new ScoreCalc();
		this.scoreManager = new ScoreManager(this.scoreCalc);	//debug
		this.ranking = FXCollections.observableArrayList(this.scoreManager.getRanking());
		this.leaderboardData = FXCollections.observableArrayList();
		this.yourScoreData = FXCollections.observableArrayList(new RankItem("NaN", "Stocazzo", 3));
		
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
	 * When home button is clicked, launches the menu GUI.
	 */
	@FXML
	private void buttonClicked() {
		/*ScoreManager.menu()*/
		System.out.println("Oh yes daddy, click me again! ^_^");	//clearly for debug purposes
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
//		FXMLLoader loader = new FXMLLoader();
//		URL xmlURL = getClass().getResource("/home/francesco/OOP-Project/Dont-Pop/src/test/ScoreSceneFXML.fxml");
//		loader.setLocation(xmlURL);
//		loader.setController(this);		//this class also acts as FXML controller
//		final Parent root = loader.load();
		
		
		
//		FXMLLoader loader = new FXMLLoader(TestScoreScene.class.getResource("/home/francesco/OOP-Project/Dont-Pop/src/test/ScoreSceneFXML.fxml"));
//		VBox root = (VBox) loader.load();
//		final Scene scene = new Scene(root);
		
//		FXMLLoader loader = new FXMLLoader();
//		URL fxmllocation = getClass().getResource("/fxml/ScoreSceneFXML.fxml");
//		loader.setLocation(fxmllocation);
//		loader.setController(this);
//		loader.setResources(ResourceBundle.getBundle("i18n/Text", new Locale("en", "US")));
//		Group root = loader.<Group>load();
//		Scene scene = new Scene(root);
		
		Parent root = FXMLLoader.load(getClass().getResource("ScoreSceneFXML.fxml"));
		
		
		
		stage.setTitle("Score Ranking");
//		stage.setWidth(screenSize);
//		stage.setHeight(screenSize);
		stage.setResizable(false);
//		stage.setScene(scene);
		stage.setScene(new Scene(root, screenSize, screenSize));
		stage.show();
		
		//----------------------------------------//

		//create leaderboard label (vbox)
//		leaderboardLabel.setFont(new Font("Arial", 20));
		//make table editable
		this.leaderboardTable.setEditable(true);

		//turn off data sorting
		rankCol.setSortable(false);
		nameCol.setSortable(false);
		scoreCol.setSortable(false);
		//add columns to table
		this.leaderboardTable.setItems(this.leaderboardData);
		this.leaderboardTable.setMaxHeight(148);
		this.leaderboardTable.setMaxWidth(462);
		
		//create your rank label (vbox)
//		yourScoreLabel.setFont(new Font("Verdana", 20));
		//make table editable
		this.yourScoreTable.setEditable(true);

		//turn off data sorting
		yourRankCol.setSortable(false);
		yourNameCol.setSortable(false);
		yourScoreCol.setSortable(false);
		//add columns to table
		this.yourScoreTable.setItems(this.yourScoreData);
		//set minimum width & table bounds
		this.yourScoreTable.setMaxHeight(52);
		this.yourScoreTable.setMaxWidth(462);
		
	}
	
	/*
	 * Metti il punteggio corrente sopra alla tabella (a new table above this table?)
	 * Evidenzia la riga del punteggio corrente
	 * Centra il bottone home
	 */
}
