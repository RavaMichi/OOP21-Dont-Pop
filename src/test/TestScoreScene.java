package test;


import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import game.engine.ScoreManager;
import game.util.ScoreCalc;
import game.util.Pair;

public class TestScoreScene extends Application {
	
	public class RankItem {
		private SimpleIntegerProperty rank;
		private SimpleStringProperty name;
		private SimpleIntegerProperty score;
		
		public RankItem(final int rank, final String name, final int score) {
			this.rank = new SimpleIntegerProperty(rank);
			this.name = new SimpleStringProperty(name);
			this.score = new SimpleIntegerProperty(score);
		}

		public int getRank() {
			return this.rank.get();
		}

		public String getName() {
			return this.name.get();
		}

		public int getScore() {
			return this.score.get();
		}
		
		
	}
	
	//table
	private TableView<RankItem> table = new TableView<>();
	//data to put in table
	private final ObservableList<Pair<String,Integer>> ranking;
	private final ObservableList<RankItem> data;
		
	private final ScoreManager scoreManager;
	private final ScoreCalc scoreCalc;
				
	/**
	 * Class constructor.
	 */
	public TestScoreScene(/*put score calc arg here after debugging*/) {
		this.scoreCalc = new ScoreCalc();
		this.scoreManager = new ScoreManager(this.scoreCalc);	//debug
		this.ranking = FXCollections.observableArrayList(this.scoreManager.getRanking());
		this.data = FXCollections.observableArrayList();
		
		//FIXME: numbers behave strangely on each run: last ones eventually get overwritten by first one
		//add ranking data to data
		for (var i: this.ranking) {
			final int index = this.ranking.indexOf(i);
			this.data.add(new RankItem(
						index + 1, 
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
		this.createTable(stage);
		
		//now make the table get its data from the score manager
	}

	/**
	 * Creates a table.
	 * @param stage
	 */
	@SuppressWarnings("unchecked")
	private void createTable(final Stage stage) {
		//TODO: application will pass you the screen size
		//get screen size and choose the smallest axis
		final Rectangle2D screenBounds = Screen.getPrimary().getBounds();
		final double screenSize = Math.min(screenBounds.getWidth(), screenBounds.getHeight());
		
		//create a non-resizable scene
		final Scene scene = new Scene(new Group());
		stage.setTitle("Score Ranking");
		stage.setWidth(screenSize);
		stage.setHeight(screenSize);
		stage.setResizable(false);

		//create label (vbox)
		final Label label = new Label("Leaderboard");
		label.setFont(new Font("Arial", 20));

		//make table editable
		this.table.setEditable(true);

		//create columns
		final TableColumn<RankItem, Integer> rankCol = new TableColumn<>("Rank");
		final TableColumn<RankItem, String> nameCol = new TableColumn<>("Name");
		final TableColumn<RankItem, Integer> scoreCol = new TableColumn<>("Score");
		
		//fetch data
		rankCol.setCellValueFactory(new PropertyValueFactory<>("rank"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));
		
		//set minimum width
		rankCol.setMinWidth(200);
		nameCol.setMinWidth(200);
		scoreCol.setMinWidth(200);
		
		//turn off data sorting
		rankCol.setSortable(false);
		nameCol.setSortable(false);
		scoreCol.setSortable(false);
		
		//add columns to table
//		this.table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		this.table.setItems(this.data);
		this.table.getColumns().addAll(rankCol, nameCol, scoreCol);
//		this.table.getItems().setAll(this.data);
		this.table.setMaxHeight(200);

//		for (int i=0; i<this.data.size(); i++) {
//			this.table.getItems().add(this.data.get(i));
//		}
		
		
		
		//create menu button
		final Button menuButton = new Button("Home");
		menuButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	/*TODO: Go to GameApplication menu*/
		    }
		});

		//add items to vertical box
		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(label, this.table, menuButton);

		//add vertical box to group
		((Group) scene.getRoot()).getChildren().addAll(vbox);

		//add scene to stage and make it visible (remove in final version)
		stage.setScene(scene);
		stage.show();
		
	}

}
