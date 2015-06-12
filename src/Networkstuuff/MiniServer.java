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
	String originaldata="nnnnnnnnyn";
	String outData="";
	String inputString=null;
	GameClient[] clients =new GameClient[2];
	private Socket skq;
	
	private BufferedReader in;
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
	public void addclients(){

	}
	public String getvalue(int g){
		return states[g];
	}
	public void run(){
		//Read input and process here
		int howmany=0;
		
			Socket skm;
			try {
				outData="";
				data="";
				
				
					skm = new Socket("10.0.1.39", 1239);
				
			
					skq = new Socket("10.0.1.39", 1257);
				
				// TODO Auto-generated catch block
				

				//  SocketAddress up=	srvr.getLocalSocketAddress();
				//System.out.println(up);
				//System.out.println("It connected");
             for(int b=0;b<2;b++){
            	
				BufferedReader in = new BufferedReader(
						new InputStreamReader (skl.getInputStream()));
            	 
            	
				PrintWriter out = new PrintWriter(skm.getOutputStream(),true);	
				PrintWriter out2 = new PrintWriter(skq.getOutputStream(),true);
				int count = 0;
				while ((inputString = in.readLine())!=null) {	
					System.out.println("LOOP"); 
					System.out.println(inputString);
					data+=inputString;
					if(inputString!=null){
						break;
					}
				}
				System.out.println("It recieved data");
				System.out.println(data);
				if(data.substring(0, 1)=="o"){
					data=data.substring(1);
					originaldata=data.substring(0,5)+originaldata.substring(5, 10);
				}
				else if(data.substring(0, 1)=="p"){
					
					originaldata=originaldata.substring(0, 5)+data.substring(5,10);
				}
				System.out.println("Saved states");

				
				outData=originaldata;
				System.out.println(outData);
				System.out.println("Repackaged");
				out.println(outData);
				out.flush();
				out2.println(outData);
				out2.flush();
				in.close();
				out.close();
				skl.close();
				skm.close();
				//srvr.close();
			}
			}
			catch(IOException r){

			}
			//implement your methods here
		}
	}



