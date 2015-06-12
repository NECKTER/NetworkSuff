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
	String info = "nnnnnnnnnn";

	

	private static int playernumber = 2;
	private static ServerSocket srv = null;

	private static Socket skm;

	public void togglevalue(int where) {
		if (info.substring(where, where + 1).equals("y")) {
			info = info.substring(0, where) + "n" + info.substring(where + 1);
		} else {
			info = info.substring(0, where) + "y" + info.substring(where + 1);
		}
	}

	public String getvalue(int l) {
		return (info.substring(l, l+ 1));
	}

	public GameClient() {
		try {
			
			srv = new ServerSocket(1233);
			
			System.out.println("MAKES NEW SERVER socket");
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
			System.out.println("Starts inside while");
			try {

				//	skt = new Socket(InetAddress.getByName("2620:9b::1915:3aa7"), 1240);
				 skm = new Socket("10.140.106.107", 1236);
			
				System.out.println("it accepted");
				
				
				
				System.out.println("it accepted");
				PrintWriter pw = new PrintWriter(skm.getOutputStream(), true);//, true);

				if (m < 1) {

					pw.println(info);
					System.out.println(info);
					m++;
				} else {
					pw.println(info);

				}

				pw.flush();
				skj=srv.accept();				
				BufferedReader in = new BufferedReader(new InputStreamReader(skj.getInputStream()));
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

				info = data.substring(0, 5)+info.substring(5, 10);
       
				System.out.println("Unpackaged");

				//  System.out.println(info);
				System.out.println("Repackedged");

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
