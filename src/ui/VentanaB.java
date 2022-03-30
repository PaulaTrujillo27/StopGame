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
	    private Label opponentTotal;

	    @FXML
	    private Label ownTotal;
	
	    @FXML
	    private Label opponentAnimalResult;

	    public Button getFinishBtn() {
			return finishBtn;
		}

		public void setFinishBtn(Button finishBtn) {
			this.finishBtn = finishBtn;
		}

		public Label getOpponentAnimalResult() {
			return opponentAnimalResult;
		}

		public void setOpponentAnimalResult(Label opponentAnimalResult) {
			this.opponentAnimalResult = opponentAnimalResult;
		}

		public Label getOpponentLocationResult() {
			return opponentLocationResult;
		}

		public void setOpponentLocationResult(Label opponentLocationResult) {
			this.opponentLocationResult = opponentLocationResult;
		}

		public Label getOpponentNameResult() {
			return opponentNameResult;
		}

		public void setOpponentNameResult(Label opponentNameResult) {
			this.opponentNameResult = opponentNameResult;
		}

		public Label getOpponentObjectResult() {
			return opponentObjectResult;
		}

		public void setOpponentObjectResult(Label opponentObjectResult) {
			this.opponentObjectResult = opponentObjectResult;
		}

		public Label getOwnAnimalResult() {
			return ownAnimalResult;
		}

		public void setOwnAnimalResult(Label ownAnimalResult) {
			this.ownAnimalResult = ownAnimalResult;
		}

		public Label getOwnLocationResult() {
			return ownLocationResult;
		}

		public void setOwnLocationResult(Label ownLocationResult) {
			this.ownLocationResult = ownLocationResult;
		}

		public Label getOwnNameResult() {
			return ownNameResult;
		}

		public void setOwnNameResult(Label ownNameResult) {
			this.ownNameResult = ownNameResult;
		}

		public Label getOwnObjectResult() {
			return ownObjectResult;
		}

		public void setOwnObjectResult(Label ownObjectResult) {
			this.ownObjectResult = ownObjectResult;
		}

		
	    @FXML
	    void finishAction(ActionEvent event) {
	    	Node source = (Node) event.getSource();
	    	Stage stage = (Stage) source.getScene().getWindow();
	    	stage.close();
	    }

		
		public void setOpponentTotal(String opponentTotal) {
			this.opponentTotal.setText(opponentTotal);
		}

		public void setOwnTotal(String ownTotal) {
			this.ownTotal.setText(ownTotal);
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
