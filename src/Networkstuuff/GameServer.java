package Networkstuuff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;

import Mainpackage.Panel;
public class GameServer {

	public GameServer() {
		// TODO Auto-generated constructor stub
		
	}

	public static void main(String[] args){
		int [] states=new int [10];
		 Socket skl = null;
		   
		   String data="";
			Boolean hj;
			Socket skm;
			Socket skn;
			String outData="";
			String inputString=null;
		ServerSocket srvr=null;
		ServerSocket srt=null;
		for(int i =0;i<9;i++){
			states[i]=1;
		}
		
		try {
			//skm = new Socket("localhost", 1239);
			
			 srvr=new ServerSocket(1234);
			 srt=new ServerSocket(1236);
			 skl = srvr.accept();
		     skn =srt.accept();
				
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Didnt work");
		}
		
		while(true){
			
			try {
				
				skm = new Socket("localhost", 1238);
				outData="";
				data="";
						// TODO Auto-generated catch block
				while(true){	
					
				//  SocketAddress up=	srvr.getLocalSocketAddress();
				    //System.out.println(up);
					//System.out.println("It connected");
					BufferedReader in = new BufferedReader(
				    new InputStreamReader (skl.getInputStream()));
					PrintWriter out = new PrintWriter(skm.getOutputStream(),true);	
					
					while ((inputString = in.readLine())!=null) {System.out.println("LOOP"); System.out.println(inputString);data+=inputString; if(inputString!=null){break;}}
					System.out.println("It recieved data");
			    	System.out.println(data);
					for(int i =0;i<9;i++){
						states[i]=(int)data.trim().charAt(i);
					}
					System.out.println("Saved states");
					
					for (int l=0;l<9;l++){
					 outData=outData+states[l];
					}
					
					System.out.println("Repackaged");
					
					out.println(outData);
			        out.flush();
					in.close();
					out.close();
					skl.close();
					skm.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}	
	}
	
}


