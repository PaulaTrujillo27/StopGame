package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.VentanaA;

public class Main extends Application{
	private VentanaA va;
	public static void main(String[] args){
		launch(args);
		

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/VentanaA.fxml"));
		va = new VentanaA(primaryStage);
		loader.setController(va);
		Parent p = (Parent) loader.load();
		Scene scene = new Scene(p);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);
		
	}
	

}
