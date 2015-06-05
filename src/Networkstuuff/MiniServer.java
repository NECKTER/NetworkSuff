package Networkstuuff;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MiniServer extends Thread{
	   private Socket skl = null;
	   String [] states=new String [10];
	   String data="";
		Boolean hj;
		Socket skm;
		ServerSocket srvr=null;
		String outData="";
		String inputString=null;
		
	    public MiniServer(Socket socket) {
	    	
	        super("MiniServer");
	        this.skl = socket;
	       // for(int i=0;i<10;)
	        for(int i=0;i<10;i++){
				states[i]="n";
			}
	    }
	    public void togglevalue(int where){
			if(states[where]=="n"){
				states[where]="y";
			}
			else if(states[where]=="y"){
				states[where]="n";
			}
		}
	    public String getvalue(int g){
	    	return states[g];
	    }
	    public void run(){
	            //Read input and process here
	    	
                Socket skm;
				try {
					outData="";
					data="";
					skm = new Socket("localhost", 1239);
			
					// TODO Auto-generated catch block
					
				
			//  SocketAddress up=	srvr.getLocalSocketAddress();
			    //System.out.println(up);
				//System.out.println("It connected");
			
				BufferedReader in = new BufferedReader(
			            new InputStreamReader (skl.getInputStream()));
				PrintWriter out = new PrintWriter(skm.getOutputStream(),true);	
				
				while ((inputString = in.readLine())!=null) {System.out.println("LOOP"); System.out.println(inputString);data=inputString;if(inputString!=null){break;}}
				System.out.println("It recieved data");
		    	System.out.println(data);
				for(int i =0;i<5;i++){
					states[i]=data.trim().substring(i,i+1);
				}
				System.out.println("Saved states");
				
				for (int l=0;l<10;l++){
				 outData=outData+states[l];
				}
				data=outData;
				System.out.println(data);
				System.out.println("Repackaged");
				
				out.println(outData);
		        out.flush();
				in.close();
				out.close();
				skl.close();
				skm.close();
				//srvr.close();
	    }
				catch(IOException e){
					
				}
	            //implement your methods here
	    }
	}

