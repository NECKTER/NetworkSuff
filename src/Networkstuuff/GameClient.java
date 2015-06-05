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
	
	public static String[] clientsvalues=new String[10];
	Panel l;
	 public void togglevalue(int where){
			if(clientsvalues[where]=="n"){
				clientsvalues[where]="y";
			}
			else if(clientsvalues[where]=="y"){
				clientsvalues[where]="n";
			}
		}
	 public String getvalue(int l){
		 return clientsvalues[l];
	 }
	public GameClient(){
		
		for(int i=0;i<10;i++){
			clientsvalues[i]=("n");
		}
	}
	
	public static void main(String[] args){
		 String [] states=new String [10];
		String inputString = "";
		String data = "";
		String info = "nnnnnnnnnn";
		String infoOut="";
		Socket skt;
		Socket skj;
		while(true){
			infoOut="";
		try {
			ServerSocket srv=new ServerSocket(1239);
			skt = new Socket("localhost", 1234);
			skj=srv.accept();
			BufferedReader in = new BufferedReader(new
		    InputStreamReader(skj.getInputStream()));
			PrintWriter pw = new PrintWriter(skt.getOutputStream(),true);//, true);
			System.out.println(info);
			 pw.println(info);
			 pw.flush();
			 
		         System.out.print("Message sent");
                 
				while ((inputString = in.readLine())!=null) {System.out.println("LOOP"); System.out.println(inputString); info=(inputString);}
		         System.out.println("Recieved data");
		        // Read one line and output it
		         System.out.println(info);
		         
               for(int i=5;i<10;i++){
              	clientsvalues[i]=info.trim().substring(i,i+1); 
      }
		         System.out.println("Unpackaged");
		      
               for(int i =0; i<10;i++){
                infoOut=infoOut+clientsvalues[i];
	         }
                info=infoOut;
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

