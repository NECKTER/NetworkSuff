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

public class GameClient {
	
	public static int[] clientsvalues=new int[10];
	Panel l;
	static int thisclientport=1234;
	GameClient(int port){
		this.thisclientport=port;
	}
	
	
	public static void main(String[] args){
		 int [] states=new int [10];
		String inputString = "";
		String data = "";
		String info = "1000010001";
		String infoOut="";
		Socket skt;
		Socket skj;
		while(true){
			infoOut="";
			inputString="";
		try {
			ServerSocket srv=new ServerSocket(1238);
			skj=srv.accept();
			skt = new Socket("localhost", thisclientport);
			BufferedReader in = new BufferedReader(new
		    InputStreamReader(skj.getInputStream()));
			PrintWriter pw = new PrintWriter(skt.getOutputStream(),true);//, true);
			 pw.println(info);
			 pw.flush();
			 
		         System.out.print("Message sent");
                 
				while ((inputString = in.readLine())!=null) {System.out.println("LOOP"); System.out.println(inputString); data=(inputString);if(inputString!=null){break;}}
		         System.out.println("Recieved data");
		        // Read one line and output it
		        // System.out.println(data);
               for(int i=0;i<9;i++){
            	   if(data.trim().charAt(i)==49){
              	clientsvalues[i]=1; 
            	   }
            	   else{
            			clientsvalues[i]=0;  
            	   }
      }
		         System.out.println("Unpackaged");
		      
    for(int i =0; i<9;i++){
          infoOut=infoOut+clientsvalues[i];
	         }
    System.out.println(infoOut);
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

