package Networkstuuff;

import java.io.IOException;
import java.net.ServerSocket;

public class GameServerretry {
    public static void main(String[] args) throws IOException {
 
   // if (args.length != 1) {
     //   System.err.println("Usage: java KKMultiServer <port number>");
      //  System.exit(1);
   // }
 
       // int portNumber = Integer.parseInt(args[0]);
        boolean listening = true;
         
        try (ServerSocket serverSocket = new ServerSocket(1240)) { 
            while (listening) {
                new GameServerthread(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + 1240);
            System.exit(-1);
        }
    }
}

