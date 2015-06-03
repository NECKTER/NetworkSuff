package Networkstuuff;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MiniServer extends Thread{
	   private Socket skl = null;
	   int [] states=new int [10];
	   String data="";
		Boolean hj;
		Socket skm;
		ServerSocket srvr=null;
		String outData="";
		String inputString=null;
	    public MiniServer(Socket socket) {

	        super("MiniServer");
	        this.skl = socket;

	    }

	    public void run(){
	            //Read input and process here
                Socket skm;
                outData="";
                data="";
				try {
					
					skm = new Socket("localhost", 1231);
			
					// TODO Auto-generated catch block
					
				
			//  SocketAddress up=	srvr.getLocalSocketAddress();
			    //System.out.println(up);
				//System.out.println("It connected");
			
				BufferedReader in = new BufferedReader(
			            new InputStreamReader (skl.getInputStream()));
				PrintWriter out = new PrintWriter(skm.getOutputStream(),true);	
				out.println(outData);
		        out.flush();
				while ((inputString = in.readLine())!=null) {System.out.println("LOOP"); System.out.println(inputString);data+=inputString; if(inputString!=null){break;}}
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
	    }
				catch(IOException e){
					
				}
	            //implement your methods here
	    }
	}

