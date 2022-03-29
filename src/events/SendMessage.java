package events;

import TCP.Session;

public interface SendMessage {

	public String messageSend(String message, Session session);
}
