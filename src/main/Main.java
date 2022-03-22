package main;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main {

	public static void main(String[] args){
		new Main();
		

	}
	public Main() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ui/VentanaA.fxml"));
		try {
			Parent p;
			p = (Parent) loader.load();
			Scene scene = new Scene(p);
			Stage stage = new Stage();
			
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
