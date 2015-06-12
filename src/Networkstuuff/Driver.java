
public class Driver {

	public static void main(String[] args){
		Thread thread = new Thread(){
			public void run(){
				System.out.println("Hello");
			}
		};
		thread.start();
		System.out.println(thread.MAX_PRIORITY);
		System.out.println("oops");
	}
	
}
