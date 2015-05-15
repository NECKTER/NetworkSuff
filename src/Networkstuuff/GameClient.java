package Networkstuuff;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import Mainpackage.Panel;
import NetworkSuff;

public class GameClient extends Thread {
private InetAddress ipAddress;
private DatagramSocket socket;
private Panel panel;
public GameClient(Panel pan,String ipAdress){
	this.panel=pan;
	try {
		this.socket=new DatagramSocket();
		this.ipAddress=InetAddress.getByName(ipAdress);
	} catch (SocketException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}
public void run(){
	while(true){
		byte[]data =new byte[1024];//can change size of package 
		DatagramPacket packet = new DatagramPacket(data,data.length);
		try {
			socket.receive(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Server>"+new String(packet.getData()));
	}
}
public void sendData(byte[]data){
	DatagramPacket packet=new DatagramPacket(data,data.length,ipAddress,1331);//change port, above 1024
	try {
		socket.send(packet);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
