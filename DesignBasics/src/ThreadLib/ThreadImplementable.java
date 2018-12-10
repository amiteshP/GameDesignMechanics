package ThreadLib;

public class ThreadImplementable implements Runnable{

	public ThreadImplementable() {
		Thread newThread = new Thread(this);
		newThread.start();
	}
	
	@Override
	public void run() {
		System.out.println("Hey Jude: Welcome to the realm of the mad god.!");
		
	}
	
	public static void main(String args[]) {
		ThreadImplementable th = new ThreadImplementable();
	}

}
