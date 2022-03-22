package ui;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class VentanaA implements Initializable {

	private TextField animalAnswer;

    @FXML
    private TextField locationAnswer;

    @FXML
    private TextField nameAnswer;

    @FXML
    private TextField objectAnswer;

    @FXML
  /*  private Button stopBtn{
    	String line = nameAnswer.getText();
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
	}*/

	private BufferedWriter writer;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	}