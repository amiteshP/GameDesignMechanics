package ThreadLib;

public class ThreadExtensible extends Thread{
	
	@Override
	public void run() {
		System.out.println("hey Jude!, welcome to the realm of the mad god.");
		System.out.println(Thread.currentThread().getName());
	}
	
	public static void main(String args[]) {
		Thread firstThread = new ThreadExtensible();
		firstThread.start();
		Thread secondThread = new ThreadExtensible();
		secondThread.start();
	}
	
}
