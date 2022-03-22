package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class VentanaB {

	 @FXML
	    private Button finishBtn;

	    @FXML
	    private Label opponentAnimalResult;

	    @FXML
	    private Label opponentLocationResult;

	    @FXML
	    private Label opponentNameResult;

	    @FXML
	    private Label opponentObjectResult;

	    @FXML
	    private Label ownAnimalResult;

	    @FXML
	    private Label ownLocationResult;

	    @FXML
	    private Label ownNameResult;

	    @FXML
	    private Label ownObjectResult;
	
	    @FXML
	    void finishAction(ActionEvent event) {
	    	Node source = (Node) event.getSource();
	    	Stage stage = (Stage) source.getScene().getWindow();
	    	stage.close();
	    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
