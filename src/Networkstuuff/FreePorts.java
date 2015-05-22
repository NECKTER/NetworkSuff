package Networkstuuff;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class FreePorts {

	ArrayList<Integer> ports = new ArrayList<>();

	public FreePorts() {
		// TODO Auto-generated constructor stub
		test();
	}

	private void test() {
		// TODO Auto-generated method stub
		for (int i = 0; i <= 65535; i++) {
			try {
				ServerSocket s = new ServerSocket(i);
			} catch (IOException e) {
				ports.add(i);
			}
		}
		System.out.print(ports);
	}

	public ArrayList<Integer> usedPorts() {
		return ports;
	}
}
