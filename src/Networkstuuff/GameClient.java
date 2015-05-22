package Networkstuuff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import Mainpackage.Panel;

public class GameClient {
	public static int[] clientsvalues=new int[9];
	Panel l;
	
	public static void main(String[] args){
		String info;
		
		 Socket skt;
		try {
			
			skt = new Socket("localhost", 1234);
			BufferedReader in = new BufferedReader(new
		            InputStreamReader(skt.getInputStream()));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(skt.getOutputStream()));//, true);
			
		         System.out.print("Received string: '");
                 
		         while (!in.ready()) {}
		        // Read one line and output it
                for(int i=0;i<9;i++){
                	clientsvalues[i]=in.readLine().trim().charAt(i); 
                }
		         
		         in.close();
		         
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
         
	
	
	}
	
}

