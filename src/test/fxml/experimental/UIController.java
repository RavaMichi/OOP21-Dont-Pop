package test.fxml.experimental;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Test class.
 */
public class UIController {
	@FXML
	public Label lbl;
	@FXML
	public Button btn;
	@FXML
	public void btnOnClickHandler() {
		lbl.setText("Hello, World!");
	}
}