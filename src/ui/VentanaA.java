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
		ownAnswer = new Answer(nameAnswer.getText(), animalAnswer.getText(),locationAnswer.getText(), objectAnswer.getText());
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
					System.out.println("Leyendo " +save);
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
		case "Answer" : rivalAnswer = gson.fromJson(line, Answer.class);
		if(ownAnswer==null) {
			ownAnswer = new Answer(nameAnswer.getText(), animalAnswer.getText(),locationAnswer.getText(), objectAnswer.getText());
			String j = gson.toJson(ownAnswer);
			sendMessage(j);
			readMessage();
		}
			Platform.runLater(() -> {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("VentanaB.fxml"));
			loader.setController(vb);
			Parent p;
			try {
				p = (Parent) loader.load();
				Scene scene = new Scene(p);
				primaryStage.setScene(scene);
				calculateScore();
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

	public void calculateScore() {
		String[] answer = new String[2];
		int totalOpponent=0;
		int totalOwn=0;
		answer=calculateScore(ownAnswer.getAnimal(),rivalAnswer.getAnimal());
		vb.getOpponentAnimalResult().setText(rivalAnswer.getAnimal()+" "+answer[1]);
		vb.getOwnAnimalResult().setText(ownAnswer.getAnimal()+" "+answer[0]);
		totalOpponent+=Integer.valueOf(answer[1]);
		totalOwn+=Integer.valueOf(answer[0]);
		answer=calculateScore(ownAnswer.getCountry(),rivalAnswer.getCountry());
		vb.getOpponentLocationResult().setText(rivalAnswer.getCountry()+" "+answer[1]);
		vb.getOwnLocationResult().setText(ownAnswer.getCountry()+" "+answer[0]);
		totalOpponent+=Integer.valueOf(answer[1]);
		totalOwn+=Integer.valueOf(answer[0]);
		answer=calculateScore(ownAnswer.getName(),rivalAnswer.getName());
		vb.getOpponentNameResult().setText(rivalAnswer.getName()+" "+answer[1]);
		vb.getOwnNameResult().setText(ownAnswer.getName()+" "+answer[0]);
		totalOpponent+=Integer.valueOf(answer[1]);
		totalOwn+=Integer.valueOf(answer[0]);
		answer=calculateScore(ownAnswer.getThing(),rivalAnswer.getThing());
		vb.getOpponentObjectResult().setText(rivalAnswer.getThing()+" "+answer[1]);
		vb.getOwnObjectResult().setText(ownAnswer.getThing()+" "+answer[0]);
		totalOpponent+=Integer.valueOf(answer[1]);
		totalOwn+=Integer.valueOf(answer[0]);
		vb.setOpponentTotal(totalOpponent+"");
		vb.setOwnTotal(totalOwn+"");
	}
	
	private String[] calculateScore(String answer1, String answer2) {
		String[] answer = new String[2];
		if((answer1.equals("")||answer1.equals(" "))&&(answer2.equals("")||answer2.equals(" "))) {
			answer[0] = "0";
			answer[1]="0";
		}else if((answer1.equals("")||answer1.equals(" "))){
			answer[0]="0";
			answer[1]="100";
		}else if((answer2.equals("")||answer2.equals(" "))) {
			answer[0]= "100";
			answer[1]= "0";
		}else if(answer1.equalsIgnoreCase(answer2)) {
			answer[0]="50";
			answer[1]="50";
		}else {
			answer[0]="100";
			answer[1]="100";
		}
		return answer;
	}

}
