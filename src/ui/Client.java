package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application{
	
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
		controllerA.setStage(primaryStage);
		FXMLLoader loader = new FXMLLoader(Client.class.getResource("VentanaA.fxml"));
		loader.setController(controllerA);
		Parent p = loader.load();
		
		Scene scene = new Scene(p);
		primaryStage.setScene(scene);
		primaryStage.show();
	
	}

}