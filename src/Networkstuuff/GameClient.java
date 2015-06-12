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
import java.util.jar.JarInputStream;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Mainpackage.Panel;

public class GameClient extends Thread {
	private String[] states = new String[10];
	private String inputString = "";
	private String data = "";
	private String infoOut = "";
	private Socket skt;
	private Socket skj;
	private int m = 0;
	String info = "nnnnmnnnnm";
	private String Ip;

	private static int playernumber = 2;
	private static ServerSocket srv = null;

	private static Socket skm;

	public void setvalue(int m, char k) {
		info = info.substring(0, m) + k + info.substring(m + 1);
	}

	public void togglevalue(int where) {
		if (info.substring(where, where + 1).equals("y")) {
			info = info.substring(0, where) + "n" + info.substring(where + 1);
		} else {
			info = info.substring(0, where) + "y" + info.substring(where + 1);
		}
	}

	public String getvalue(int l) {
		return (info.substring(l, l + 1));
	}

	public GameClient(String s) {
		Ip = s;
		try {

			srv = new ServerSocket(1233);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//playernumber=j;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		while (true) {
			infoOut = "";
			try {

				//	skt = new Socket(InetAddress.getByName("2620:9b::1915:3aa7"), 1240);
				skm = new Socket(Ip, 1236);

				PrintWriter pw = new PrintWriter(skm.getOutputStream(), true);//, true);

				if (m < 1) {

					pw.println(info);
					m++;
				} else {
					pw.println(info);

				}

				pw.flush();
				skj = srv.accept();
				BufferedReader in = new BufferedReader(new InputStreamReader(skj.getInputStream()));

				while ((inputString = in.readLine()) != null) {
					data = inputString;
				}
				// Read one line and output it
				if (data == null) {
					System.out.println("data is NULL");
				}

				info = data.substring(0, 5) + info.substring(5, 10);


				//  System.out.println(info);

				in.close();
				pw.close();
				//skm.close();
				//skt.close();

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
