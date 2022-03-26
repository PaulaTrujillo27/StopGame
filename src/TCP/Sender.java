package TCP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Sender {

	private static Socket socket;
	private static Scanner scanner;
	private static BufferedWriter bwriter;
	public static void main(String[] args) {
		// El papel del cliente es enviar una solicitud
		
		scanner = new Scanner(System.in);
		while(true) {
			String line = scanner.nextLine();
			try {
				socket = new Socket("127.0.0.1",6000);
				bwriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				new BufferedReader(new InputStreamReader(socket.getInputStream()));
				bwriter.write(line+"\n");
				bwriter.flush();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
			