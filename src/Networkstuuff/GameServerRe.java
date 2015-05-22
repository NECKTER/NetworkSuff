package Networkstuuff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Mainpackage.Panel;
public class GameServerRe {

	public GameServerRe() {
		// TODO Auto-generated constructor stub
		
	}

	public static void main(String[] args){
		int [] states=new int [8];
		String data="Swag city";
		Boolean hj;
		while(true){
		try {
			ServerSocket srvr=new ServerSocket(1234);
			Socket skt=srvr.accept();
			System.out.println("It connected");
			BufferedReader in = new BufferedReader(
		            new InputStreamReader (skt.getInputStream()));
			while (!in.ready()) {}
			
			for(int i =0;i<8;i++){
				states[i]=in.readLine().trim().charAt(i);
			}
			PrintWriter out = new PrintWriter(skt.getOutputStream(),true);	
			for (int l=0;l<8;l++){
			 data=data+states[l];
			}
			out.write(data);;
	        
			in.close();
			out.close();
			skt.close();
			srvr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Didnt work");
		}
	}
	}

}
