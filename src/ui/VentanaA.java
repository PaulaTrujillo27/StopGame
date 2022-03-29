package ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.google.gson.Gson;

import events.SendMessage;
import javafx.application.Platform;
import javafx.event.ActionEvent;
/*import javafx.event.ActionEvent;*/
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Answer;
import model.Generic;
import model.Letter;

public class VentanaA{

	private BufferedWriter writer;
	private BufferedReader reader;
	private Answer ownAnswer;
	private Answer rivalAnswer;
	
	
	public BufferedWriter getWriter() {
		return writer;
	}

	public void setWriter(BufferedWriter writer) {
		this.writer = writer;
	}

	public BufferedReader getReader() {
		return reader;
	}

	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}

	@FXML
    private TextField animalAnswer;

    @FXML
    private TextField locationAnswer;

    @FXML
    private TextField nameAnswer;

    @FXML
    private TextField objectAnswer;

    @FXML
    private Button stopBtn;

    @FXML
    private Label title;
    
    private VentanaB vb;
    
    private Stage primaryStage;
    
    public VentanaA(VentanaB vb) {
    this.vb = vb;
    }
    
    public void setStage(Stage s) {
    	primaryStage = s;
    }
    
	@FXML
	private void stopAction(ActionEvent event) {
		if(nameAnswer.getText().isEmpty() ||animalAnswer.getText().isEmpty() || locationAnswer.getText().isEmpty() || objectAnswer.getText().isEmpty()) {
		
	}else {
		Gson gson = new Gson();
		Answer ownAnswer = new Answer(nameAnswer.getText(), animalAnswer.getText(),locationAnswer.getText(), objectAnswer.getText());
		String j = gson.toJson(ownAnswer);
		sendMessage(j);
		
	}
}
	
	public void sendMessage(String line) {
			new Thread(() -> {
				try {
					writer.write(line + "\n");
					writer.flush();
					System.out.println(line);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}).start();
		}
	
	public void readMessage() {
		new Thread(() -> {
			String save;
				try {
					save = reader.readLine();
					toDo(save);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}).start();
	}
	
	public void toDo(String line) {
		Gson gson = new Gson();
		Generic generic = gson.fromJson(line, Generic.class);
		switch(generic.type) {
		case "Letter": Letter letter = gson.fromJson(line, Letter.class);
		
		Platform.runLater(() -> {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("VentanaA.fxml"));
			loader.setController(this);
			Parent p;
			try {
				p = (Parent) loader.load();
				Scene scene = new Scene(p);
				Stage stage = new Stage();
				primaryStage = stage;
				primaryStage.setScene(scene);
				title.setText("Letra " +letter.getLetter());
				primaryStage.show();
				primaryStage.setResizable(false);
				readMessage();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		});	
		break;
		case "Answer" : Answer rivalAnswer = gson.fromJson(line, Answer.class);
		if(ownAnswer==null) {
			Answer ownAnswer = new Answer(nameAnswer.getText(), animalAnswer.getText(),locationAnswer.getText(), objectAnswer.getText());
			String j = gson.toJson(ownAnswer);
			sendMessage(j);
		}
			Platform.runLater(() -> {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("VentanaB.fxml"));
			loader.setController(vb);
			Parent p;
			try {
				p = (Parent) loader.load();
				Scene scene = new Scene(p);
				primaryStage.setScene(scene);
				vb.getOpponentAnimalResult().setText(rivalAnswer.getAnimal());
				vb.getOpponentLocationResult().setText(rivalAnswer.getCountry());
				vb.getOpponentNameResult().setText(rivalAnswer.getName());
				vb.getOpponentObjectResult().setText(rivalAnswer.getThing());
				vb.getOwnAnimalResult().setText(ownAnswer.getAnimal());
				vb.getOwnLocationResult().setText(ownAnswer.getCountry());
				vb.getOwnNameResult().setText(ownAnswer.getName());
				vb.getOwnObjectResult().setText(ownAnswer.getThing());
				primaryStage.show();
				primaryStage.setResizable(false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			});	
			break;
			
		
		}
	}


	public void initialize(URL arg0, ResourceBundle arg1) {
		
		

	}

	

}
