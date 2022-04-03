package game.ui;

import game.engine.ScoreManager;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * ScoreScene displays a GUI showing your current score.
 * Mostra la tabella
 */
public class ScoreScene {

    //private final Scene scene;
    private ScoreManager scoreManager;
    private final Scene scene;

    /**
     * Creates & initializes this class.
     */
    public ScoreScene(final ScoreManager scoreManager) {
    	this.scoreManager = scoreManager;
    	//passagli come argomento qualcosa, cerca su Google
    	this.scene = new Scene(table);
    }

    /*
     * Find a Table template to display scores
     */
    private TableView table = new TableView();
    
    public void start(Stage stage) {
    	Scene scene = new Scene(new Group());
    	stage.setTitle("Table View Sample");
    	stage.setWidth(300);
    	stage.setHeight(500);
    	
    	final Label label = new Label("Leaderboard");
    	label.setFont(new Font("Arial", 20));
    	
    	table.setEditable(true);
    	
    	TableColumn rankCol = new TableColumn("Rank");
    	TableColumn nameCol = new TableColumn("Name");
    	TableColumn scoreCol = new TableColumn("Score");
    	
    	table.getColumns().addAll(rankCol, nameCol, scoreCol);
    	
    	final VBox vbox = new VBox();
    	vbox.setSpacing(5);
    	vbox.setPadding(new Insets(10, 0, 0, 10));
    	vbox.getChildren().addAll(label, table);
    	
    	((Group) scene.getRoot()).getChildren().addAll(vbox);
    	
    	stage.setScene(scene);
    	stage.show();
    }
    
    /**
     * @return this ScoreScene
     * Gets this ScoreScene
     */
    public Scene get() {
        return this.scene;
    }

}
