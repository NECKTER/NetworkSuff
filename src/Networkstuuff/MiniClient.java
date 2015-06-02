package Networkstuuff;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MiniClient extends Thread{
	   private Socket skl = null;
	   int [] states=new int [10];
	   String data="";
		Boolean hj;
		Socket skm;
		ServerSocket srvr=null;
		String outData="";
		String inputString=null;
	    public MiniClient(Socket socket) {

	        super("MiniClient");
	        this.skl = socket;

	    }

	    public void run(){
	            //Read input and process here
                Socket skm;
				try {
				
				
	    }
				catch(IOException e){
					
				}
	            //implement your methods here
	    }
	}

