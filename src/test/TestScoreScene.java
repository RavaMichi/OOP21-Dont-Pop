package test;

import game.util.Pair;
import game.util.ScoreCalc;
import game.util.Leaderboard;

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


import game.util.Leaderboard;
import game.engine.ScoreManager;

public class TestScoreScene extends javafx.application.Application {

	//table
	private TableView table = new TableView();
	
	//data to put in table
	private final ObservableList<Integer> data = FXCollections.observableArrayList();
	
	//size of the square filling the screen
	private double screenSize;
	
	@Override
	public void start(Stage stage) throws Exception {
		
		//get screen size and choose the smallest axis
		Rectangle2D screenBounds = Screen.getPrimary().getBounds();
		this.screenSize = Math.min(screenBounds.getWidth(), screenBounds.getHeight());
		System.out.println(screenBounds);
		
		//create a non-resizable scene
		Scene scene = new Scene(new Group());
		stage.setTitle("Score Ranking");
		stage.setWidth(this.screenSize);
		stage.setHeight(this.screenSize);
		stage.setResizable(false);

		//create label
		final Label label = new Label("Leaderboard");
		label.setFont(new Font("Arial", 20));

		//make table editable
		this.table.setEditable(true);

		//rank column
		TableColumn rankCol = new TableColumn("Rank");
		rankCol.setMinWidth(200);
		rankCol.setCellValueFactory(new PropertyValueFactory<Integer, String>("rank"));

		//name column
		TableColumn nameCol = new TableColumn("Name");
		nameCol.setMinWidth(200);
		nameCol.setCellValueFactory(new PropertyValueFactory<Integer, String>("name"));

		//score column
		TableColumn scoreCol = new TableColumn("Score");
		scoreCol.setMinWidth(200);
		scoreCol.setCellValueFactory(new PropertyValueFactory<Integer, String>("score"));

		//add columns to table
		this.table.setItems(this.data);
		this.table.getColumns().addAll(rankCol, nameCol, scoreCol);

		//add items to vertical box
		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(label, this.table);

		//add vertical box to group
		((Group) scene.getRoot()).getChildren().addAll(vbox);

		//add scene to stage and make it visible
		stage.setScene(scene);
		stage.show();
		
	}

}
