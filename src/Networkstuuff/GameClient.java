package Networkstuuff;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

import Mainpackage.Panel;

public class GameClient extends Thread {
	private String[] states = new String[10];
	private String inputString = "";
	private String data = "";
	private String infoOut = "";
	private Socket skt;
	private Socket skj;
	private int m = 0;

	static String info = "nynnynnynn";

	public static String[] clientsvalues = new String[10];
	Panel l;
	private static int playernumber = 2;
	private static ServerSocket srv = null;

	private static Socket skm;

	public void togglevalue(int where) {
		if (clientsvalues[where] == "n") {
			clientsvalues[where] = "y";
		} else
			if (clientsvalues[where] == "y") {
				clientsvalues[where] = "n";
			}
	}

	public String getvalue(int l) {
		return clientsvalues[l];
	}

	public GameClient(int j) {
		//playernumber=j;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			infoOut = "";
			try {
				if (playernumber == 1) {
					srv = new ServerSocket(1239);
				} else {
					//srv = new ServerSocket(1257);
					srv = new ServerSocket(1239);
				}
				skt = new Socket("10.0.1.39", 1240);
				// skm = new Socket("10.0.1.39", 1236);
				skj = srv.accept();
				BufferedReader in = new BufferedReader(new InputStreamReader(skj.getInputStream()));
				PrintWriter pw = new PrintWriter(skt.getOutputStream(), true);//, true);

				if (m < 1) {
					if (playernumber == 2) {
						pw.println("o" + info);
					} else {
						pw.println("p" + info);
					}
					System.out.println(info);
					m++;
				} else {
					if (playernumber == 2) {
						pw.println("o" + data);
					} else {
						pw.println("p" + data);
					}

				}

				pw.flush();

				System.out.print("Message sent");

				while ((inputString = in.readLine()) != null) {
					System.out.println("LOOP");
					System.out.println(inputString);
					data = inputString;
				}
				System.out.println("Recieved data");
				// Read one line and output it
				System.out.println(data);
				if (data == null) {
					System.out.println("data is NULL");
				}

				if (playernumber == 1) {
					info = info.substring(0, 5) + data.substring(5, 10);
				}

				else
					if (playernumber == 2) {

						info = data.substring(0, 5) + info.substring(5, 10);
					}
				System.out.println("Unpackaged");

				data = info;
				//  System.out.println(info);
				System.out.println("Repackedged");

				in.close();
				pw.close();
				srv.close();
				skt.close();

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
