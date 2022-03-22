package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;

public class VentanaA {

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

	@FXML
	void stopAction(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("ui/VentanaB.fxml"));
		
		Parent p;
		try {
			p=(Parent)loader.load();
			Scene scene = new Scene(p);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
		/*String line = messageTF.getText();
		if (writer != null) {
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
	}

	private BufferedWriter writer;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		new Thread(() -> {
			try {
				Socket socket = new Socket("127.0.0.1", 6000);

				writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				while (true) {
					String line = reader.readLine();
					
					//Run on UI Thread
					Platform.runLater(()->{
						outputTA.appendText(line);
					});
					
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();

	}*/

}
