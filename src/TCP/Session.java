package TCP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.UUID;

import events.SendMessage;

public class Session extends Thread {

	private Socket socket;
	private String id;
	
	private BufferedReader reader;
	private BufferedWriter writer;
	private String m= " ";
	private SendMessage send;
	
	public SendMessage getSend() {
		return send;
	}

	public void setSend(SendMessage send) {
		this.send = send;
	}

	private OnMessageListener listener;

	public Session(Socket socket) {
		this.socket = socket;
		id = UUID.randomUUID().toString();

	}

	@Override
	public void run() {
		try {
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			while(true) {
				String line = reader.readLine();
				System.out.println(line);
				listener.onMessage(line);
			}
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	
	
	public void sendMessage(String msg) throws IOException {
		new Thread(() -> {
			try {
				writer.write(msg+"\n");
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();	

	}
	
	
	public String readMessage() {
		try {
			String message= " ";
		message = reader.readLine();
		m= send.messageSend(message, this);		
		System.out.println(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return m;
	}
	
	public void setListener(OnMessageListener listener) {
		this.listener = listener;
	}
	
	public interface OnMessageListener{
		void onMessage(String line);
	}
}
