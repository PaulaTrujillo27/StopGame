package ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import events.ReceiveMessage;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application{
	
	private BufferedWriter writer;
	private BufferedReader reader;
	private ReceiveMessage receive;
	public ReceiveMessage getReceive() {
		return receive;
	}

	public void setReceive(ReceiveMessage receive) {
		this.receive = receive;
	}

	private VentanaA controllerA;
	private VentanaB controllerB;

	public Client() {
		controllerB = new VentanaB();
		controllerA = new VentanaA(controllerB);
	
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			Socket socket = new Socket("127.0.0.1", 6000);

			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	
			controllerA.setStage(primaryStage);
			controllerA.setWriter(writer);
			controllerA.setReader(reader);
			controllerA.readMessage();
			/*FXMLLoader loader = new FXMLLoader(Client.class.getResource("VentanaA.fxml"));
			loader.setController(controllerA);
			Parent p = loader.load();
			
			Scene scene = new Scene(p);
			primaryStage.setScene(scene);
			primaryStage.show();
			*/
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Thread(() -> {
			
		}).start();	
	
	}
	
	
	
	
	
	
}	
	
