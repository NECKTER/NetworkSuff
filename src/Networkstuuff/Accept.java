package Networkstuuff;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Accept extends Thread {
	private Socket sock = null;
	private ServerSocket servSock = null;

	public Accept() {
		// TODO Auto-generated constructor stub
	}

	public void update(ServerSocket ss, Socket s) {
		// TODO Auto-generated method stub
		sock = s;
		servSock = ss;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			sock = servSock.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Socket getSock() {
		return sock;
	}
}
