package test;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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

public class TestScoreScene extends javafx.application.Application {
	
	private class RankItem {
		private int rank;
		private String name;
		private int score;
		
		public RankItem(final ObservableList<Pair<String,Integer>> ranking, final Pair<String,Integer> item) {
			this.rank = ranking.indexOf(item);
			this.name = item.get1();
			this.score = item.get2();
		}

		public int getRank() {
			return rank;
		}

		public String getName() {
			return name;
		}

		public int getScore() {
			return score;
		}
		
		
	}
	
	//table
	private TableView<RankItem> table = new TableView<>();
	//data to put in table
	////private final ObservableList<Integer> data = FXCollections.observableArrayList(1,2,3);
	private ObservableList<Pair<String,Integer>> ranking;
//	private ObservableList<Pair<Integer, Pair<String,Integer>>> data;
	private ObservableList<RankItem> data = FXCollections.observableArrayList();
		
	private final ScoreManager scoreManager;
	private final ScoreCalc scoreCalc;
				
	/**
	 * Class constructor.
	 */
	public TestScoreScene(/*put score calc arg here after debugging*/) {
		this.scoreCalc = new ScoreCalc();
		this.scoreManager = new ScoreManager(this.scoreCalc);	//debug
		this.ranking = FXCollections.observableArrayList(this.scoreManager.getRanking());
//		for (var i: this.ranking) {
//			int index = this.ranking.indexOf(i);
//			this.data.set(index, new Pair<>(index, this.ranking.get(index)));
//		}
		for (var i: this.ranking) {
			this.data.add(new RankItem(this.ranking, i));
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
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

		//rank column
		final TableColumn rankCol = new TableColumn("Rank");
		rankCol.setMinWidth(200);
		rankCol.setCellValueFactory(new PropertyValueFactory<>("Rank"));

		//name column
		final TableColumn nameCol = new TableColumn("Name");
		nameCol.setMinWidth(200);
		nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));

		//score column
		final TableColumn scoreCol = new TableColumn("Score");
		scoreCol.setMinWidth(200);
		scoreCol.setCellValueFactory(new PropertyValueFactory<>("Score"));

		//add columns to table
		this.table.setItems(this.data);
		this.table.getColumns().addAll(rankCol, nameCol, scoreCol);
//		for (int i=0; i<this.data.size(); i++) {
//			this.table.getItems().add(this.data.get(i));
//		}
		
				

		//add items to vertical box
		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(label, this.table);

		//add vertical box to group
		((Group) scene.getRoot()).getChildren().addAll(vbox);

		//add scene to stage and make it visible (remove in final version)
		stage.setScene(scene);
		stage.show();
		
	}

}
