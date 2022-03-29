package model;

import java.io.IOException;
import java.util.Random;

import com.google.gson.Gson;

import TCP.Session;
import events.ReceiveMessage;
import events.SendMessage;

public class Match{
	
	private Session player1;
	private Session player2;
	private SendMessage sender1;
	private SendMessage sender2;
	private ReceiveMessage receiver1;
	private ReceiveMessage receiver2;
	private Thread ventanaA;
	private Thread ventanaB;
	private String player1Message;
	private String player2Message;
	
	public Match(Session player1, Session player2) {
		this.player1 = player1;
		this.player2 = player2;
		sender1=player1;
		receiver1=player1;
		sender2=player2;
		receiver2=player2;
		sendLetter();
	}

	public void sendLetter() {

	        Random random = new Random();
	        char randomChar = (char) (random.nextInt(26) + 'a');
	        Gson gson = new Gson();
	        Letter letter = new Letter(Character.toString(randomChar));
	        String j = gson.toJson(letter);
	        sender1.messageSend(j);
			sender2.messageSend(j);
			playGame();
	    }
	
	public void playGame(){
		ventanaA = new Thread() {
            public void run() {
                synchronized(this) {
                    String a = receiver1.messageReceive();
                    if(a.contains("Result")) {
                    	ventanaB.interrupt();
                    }
                    
                    sender2.messageSend(a);
                    String b = receiver2.messageReceive();
                    sender1.messageSend(b);
                }
            }
        };


        ventanaB = new Thread() {
            public void run() {
                synchronized(this) {
                    String a = receiver2.messageReceive();
                    if(a.contains("Result")) {
                    	ventanaA.interrupt();
                    }
                    
                    sender1.messageSend(a);
                    String b = receiver1.messageReceive();
                    sender2.messageSend(b);
                }
            }
        };
        ventanaA.start();
        ventanaB.start();
    }

	
	
	
	
	
	}
