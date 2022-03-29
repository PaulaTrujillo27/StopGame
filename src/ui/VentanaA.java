package ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class VentanaA {

	private BufferedWriter writer;
	private BufferedReader reader;
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
	void stopAction(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("VentanaB.fxml"));
		loader.setController(vb);
		Parent p = (Parent) loader.load();
		Scene scene = new Scene(p);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
		
		
	}
	
	public void sendMessage(String line) {
			new Thread(() -> {
				try {
					writer.write(line + "\n");
					writer.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}).start();
		}
	


	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
			Socket socket = new Socket("127.0.0.1", 6000);

			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Thread(() -> {
			
		}).start();

	}

}
