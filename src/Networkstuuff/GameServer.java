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
static MiniServer mini;
private static ServerSocket sr;
private static ServerSocket srvr;
private static MiniServer mini2;
	public GameServer() {
		ServerSocket srvr=null;
		boolean iscon=false;
		ServerSocket srt=null;
		
		try {
			//skm = new Socket("localhost", 1239);
			
			 srvr=new ServerSocket(1240);
			
			//srt=new ServerSocket(1236);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Didnt work");
		}
		while(true){
			Socket skl = null;
		     Socket skn=null;
			
			try {
				skl = srvr.accept();
				
				
				iscon=true;
				skn =srt.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			 mini = new MiniServer(skl);
			//mini2=new MiniServer(skn);
		//	mini2.start();
	//	MiniServer mini2=new MiniServer(skn);
            mini.start();
        //   mini2.start();
	}
		
	}
	
		
	

	public static void main(String[] args){
		
		
}
	}
