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
		ServerSocket srvr=null;
		ServerSocket srt=null;
		for(int i =0;i<9;i++){
			states[i]=1;
		}
		
		try {
			//skm = new Socket("localhost", 1239);
			
			 srvr=new ServerSocket(1234);
			// srt=new ServerSocket(1236);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Didnt work");
		}
		while(true){
			Socket skl = null;
		//	Socket skn=null;
			
			try {
				skl = srvr.accept();
				//skn =srt.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			MiniServer mini = new MiniServer(skl);
		//	MiniServer mini2=new MiniServer(skn);
            mini.start();
           // mini2.start();
	}
		
	}

}
