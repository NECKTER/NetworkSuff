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

public class GameServ {
	
	public static int[] clientsvalues=new int[10];
	Panel l;
	
	
	public static void main(String[] args){
		 int [] states=new int [10];
		String inputString = "";
		String data = "";
		String info = "1000000001";
		String infoOut="";
		Socket skt = null;
		Socket skj = null;
		ServerSocket srv = null;
		try {
			srv = new ServerSocket(1239);
			System.out.println("New server serversocket");
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		while(true){
			System.out.println("Looping server");
			data="";
			inputString="";
		try {
			skj=srv.accept();
			skt=new Socket("LocalHost",1124);
			BufferedReader in = new BufferedReader(new
		    InputStreamReader(skj.getInputStream()));
			PrintWriter pw = new PrintWriter(skt.getOutputStream(),true);//, true);
			 
			 
		         System.out.print("Message sent");
                 
				while ((inputString = in.readLine())!=null) {System.out.println("LOOP"); System.out.println(inputString); data+=(inputString);}
		         System.out.println("Recieved data");
		        // Read one line and output it
		         System.out.println(data);
               for(int i=0;i<10;i++){
              	clientsvalues[i]=data.trim().charAt(i); 
      }
		         System.out.println("Unpackaged");
		      
    for(int i =0; i<9;i++){
          infoOut=infoOut+clientsvalues[i];
	         }
    pw.println(info);
	 pw.flush();
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

