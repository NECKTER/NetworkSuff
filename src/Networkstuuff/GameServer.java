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
		String data="";
		Boolean hj;
		Socket skm;
		
		String outData="";
		String inputString=null;
		for(int i =0;i<9;i++){
			states[i]=1;
		}
		while(true){
		try {
			skm = new Socket("localhost", 1239);
			
			ServerSocket srvr=new ServerSocket(1234);
			Socket skl=srvr.accept();
		//	skt = new Socket("localhost", 1234);
		//  SocketAddress up=	srvr.getLocalSocketAddress();
		    //System.out.println(up);
			System.out.println("It connected");
		
			BufferedReader in = new BufferedReader(
		            new InputStreamReader (skl.getInputStream()));
			PrintWriter out = new PrintWriter(skm.getOutputStream(),true);	
			
			while ((inputString = in.readLine())!=null) {System.out.println("LOOP"); System.out.println(inputString); data+=(inputString);}
			System.out.println("It recieved data");
	    	System.out.println(data);
			for(int i =0;i<10;i++){
				states[i]=data.trim().charAt(i);
			}
			System.out.println("Saved states");
			
			for (int l=0;l<8;l++){
			 outData=outData+states[l];
			}
			
			System.out.println("Repackaged");
			
			out.println(outData);
	        out.flush();
			in.close();
			out.close();
			skl.close();
			skm.close();
			srvr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Didnt work");
		}
	}
	}

}

