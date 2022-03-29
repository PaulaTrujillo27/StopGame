package model;

import java.io.IOException;
import java.util.Random;

import com.google.gson.Gson;

import TCP.Session;
import events.ReceiveMessage;
import events.SendMessage;

public class Match implements SendMessage{
	
	private Session player1;
	private Session player2;
	private SendMessage sender1;
	private SendMessage sender2;
	private ReceiveMessage receiver1;
	private ReceiveMessage receiver2;
	private Thread ventanaA;
	private Thread ventanaB;
	private String player1Message;
	private String plater2Message;
	
	public Match(Session player1, Session player2) {
		this.player1 = player1;
		this.player2 = player2;
		player1.setSend(this);
		player2.setSend(this);
		sendLetter();
	}

	public void sendLetter() {

	        Random random = new Random();
	        char randomChar = (char) (random.nextInt(26) + 'a');
	        Gson gson = new Gson();
	        Letter letter = new Letter(Character.toString(randomChar));
	        String j = gson.toJson(letter);
	        try {
				player1.sendMessage(j);
		        player2.sendMessage(j);
		        playGame();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	
	public void playGame() {
		ventanaA = new Thread() {
			
			public void run() {
			
				synchronized(this) {
					String a = player1.readMessage();
					System.out.println(a);
					//System.out.println("Este mensaje deberia activar el if " + a);
					if(a.contains("Answer")) {
						//System.out.println("Entra en el if");
						ventanaB.interrupt();
						//bWaiting.stop();
						System.out.println("Este es el estado del hilo B " + ventanaB.isInterrupted());

					}
		
		System.out.println("Envio mensaje 1");
		
					try {
						player2.sendMessage(a);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		
		
		//new Thread(() -> {
			
					String b = player2.readMessage();
			
			//System.out.println("Envio mensaje 2");
					try {
						player1.sendMessage(b);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			//sesionB.sendMessage("");
			
			//}).start();
		
			
		//System.out.println("En este sitio esta esperando la respuesta de la pantalla final");	
		//
		//
					/*
					new Thread(() -> {
					
						sesionA.readMessage();	
						
						
					}).start();	
					
					*/
					new Thread(() -> {
					
						player2.readMessage();
						
					}).start();	
					
				}
			}
		};
		
		
		ventanaB = new Thread() {
			
			public void run() {
			
				synchronized(this) {
				
				String b = player2.readMessage();
			
				//System.out.println("Este mensaje deberia activar el if " + b);

				if(b.contains("Answer")) {
					//System.out.println("Entra en el if");
					
					ventanaA.interrupt();
					//aWaiting.stop();
					//aWaiting.stop();
					System.out.println("Este es el estado del hilo A " + ventanaA.isInterrupted());
					
				}
		
		//System.out.println("Envio mensaje 1");
		
				try {
					player1.sendMessage(b);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		
		//new Thread(() -> {
		
				String a = player1.readMessage();
			
			//System.out.println("Envio mensaje 2");
				try {
					player2.sendMessage(a);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			//sesionB.sendMessage("");
			
			//}).start();
		
			
		//System.out.println("En este sitio esta esperando la respuesta de la pantalla final");	
		//sesionA.readMessage();
		//sesionB.readMessage();
				
				
				new Thread(() -> {
					
					player1.readMessage();	
					
					
				}).start();	
				
				/*
				new Thread(() -> {
				
					sesionB.readMessage();
					
				}).start();	
				*/
				}
				
			}
		};
		ventanaA.start();
		ventanaB.start();
 		
	}

	@Override
	public String messageSend(String message, Session session) {
		return message;
	}
	
	
	
	
	}
