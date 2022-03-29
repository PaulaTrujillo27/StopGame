package TCP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.UUID;

import events.ReceiveMessage;
import events.SendMessage;

public class Session extends Thread implements ReceiveMessage,SendMessage{

	private Socket socket;	
	private BufferedReader reader;
	private BufferedWriter writer;
	

	public Session(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	

	@Override
	public void messageSend(String message) {
		new Thread(() -> {
			try {
				writer.write(message+"\n");
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
	
	}

	@Override
	public String messageReceive() {
		String message= "";
		try {
		message = reader.readLine();
		System.out.println(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return message;
	}
}
