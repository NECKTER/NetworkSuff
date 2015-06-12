package Networkstuuff;

import java.net.*;
import java.io.*;
 
public class GameServerthread extends Thread {
    private Socket socket = null;
 int m=0;
    public GameServerthread(Socket socket) {
        super("KKMultiServerThread");
        this.socket = socket;
    }
     
    public void run() {
 
        try (
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(
                    socket.getInputStream()));
        ) {
            String inputLine, outputLine = null,originaldata="nnnnnnnnnn",data="";
            if(m==0){
            	 out.println(originaldata);
            }
            else{
            out.println(outputLine);
            }
            while ((inputLine = in.readLine()) != null) {
             data=inputLine;
            	if(inputLine.substring(0, 1)=="o"){
					data=data.substring(1);
					originaldata=data.substring(0,5)+originaldata.substring(5, 10);
				}
				else if(data.substring(0, 1)=="p"){
					
					originaldata=originaldata.substring(0, 5)+data.substring(5,10);
				}
            	outputLine=originaldata;
                out.println(outputLine);
                if (outputLine.equals("Bye"))
                    break;
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
