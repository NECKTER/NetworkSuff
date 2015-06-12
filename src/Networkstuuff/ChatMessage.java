package Networkstuuff;

import java.io.*;

public class ChatMessage implements Serializable {

	protected static final long serialVersionUID = 1112122200L;

	// The different types of message sent by the Client
	// WHOISIN to receive the list of the users connected
	// MESSAGE an ordinary message
	// LOGOUT to disconnect from the Server

	
	private int type;
	private String message;

	// constructor
	ChatMessage(String message) {
	
		this.message = message;
	}

	// getters
	

	String getMessage() {
		return message;
	}
}