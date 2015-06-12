
public class NamedThread extends Thread {

	String n;
	
	public NamedThread(String name){
		n = name;
	}
	
	public void run(){
		System.out.println("Greetings from " + n);
	}
	
}
