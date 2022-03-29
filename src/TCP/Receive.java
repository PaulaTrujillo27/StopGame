package TCP;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import model.Match;

public class Receive implements Session.OnMessageListener{

	public static void main(String[] args) throws IOException {
		Receive receive = Receive.getInstace();
		receive.StartServer();
	}

	private ArrayList<Session> sessions;
	private static Receive instance;

	private Receive() throws IOException {
	}
	
	public void StartServer() {
		sessions = new ArrayList<>();
		ServerSocket server;
		try {
			server = new ServerSocket(6000);
			while (true) {
				System.out.println("Esperando cliente...");
				Socket socket = server.accept();
				System.out.println("Nuevo cliente conectado!");
				System.out.println("Entró en el puerto: " + socket.getPort());
				Session session = new Session(socket);
				session.setListener(this);
				session.start();
				sessions.add(session);
				
				if(sessions.size()%2==0) {
					new Thread(() -> {
						Match match = new Match(sessions.get(sessions.size()-2),sessions.get(sessions.size()-1));
					}).start();	
				
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static Receive getInstace() {
		if(instance==null) {

			try {
				instance = new Receive();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return instance;
	}
	

	@Override
	public void onMessage(String line) {
		// TODO Auto-generated method stub
		
	}
}