package Networkstuuff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;

import Mainpackage.Panel;

public class GameServer extends Thread {
	private Socket skl = null;
	String[] states = new String[10];
	String data = "";
	Boolean hj;
	Socket skm;
	ServerSocket srvr = null;
	String originaldata = "nnnnnnnnyn";
	String outData = "";
	String inputString = null;
	ServerSocket swag;

	private Socket skq;

	private BufferedReader in;

	public GameServer() {


		// for(int i=0;i<10;)
	}

	public void togglevalue(int where) {
		if (originaldata.substring(where, where + 1) == "y") {
			originaldata = originaldata.substring(0, where) + "n" + originaldata.substring(where + 1);
		} else {
			originaldata = originaldata.substring(0, where) + "y" + originaldata.substring(where + 1);
		}
	}

	public String getvalue(int g) {
		return originaldata.substring(g, g + 1);
	}

	public void run() {
		//Read input and process here

		int howmany=0;
		
			
			try {
				outData="";
				data="";
				swag = new ServerSocket(1340);
				System.out.println("Oh hi Jessy");
				this.skl = swag.accept();
				skm = new Socket("10.0.1.39", 1233);
				
				
				
			
					
				
				// TODO Auto-generated catch block
				

				//  SocketAddress up=	srvr.getLocalSocketAddress();
				//System.out.println(up);
				//System.out.println("It connected");
             
            	
				BufferedReader in = new BufferedReader(
						new InputStreamReader (skl.getInputStream()));
            	 
            	
				PrintWriter out = new PrintWriter(skm.getOutputStream(),true);	
				
				int count = 0;
				while ((inputString = in.readLine())!=null) {	
					System.out.println("LOOP"); 
					System.out.println(inputString);
					data+=inputString;
					if(inputString!=null){
						break;
					}

			}
			System.out.println("It recieved data");
			System.out.println(data);

			originaldata = data.substring(0, 5) + originaldata.substring(5, 10);

			System.out.println("Saved states");

			outData = originaldata;
			System.out.println(outData);
			System.out.println("Repackaged");
			out.println(outData);
			out.flush();

			in.close();
			out.close();
			skl.close();
			skm.close();
			//srvr.close();
		}

		catch (IOException r) {

		}
		//implement your methods here
	}
}
	
