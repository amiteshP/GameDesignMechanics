package ThreadLib;

public class ThreadaholicsAnonymous {

	public static void main(String args[]) {
	new Thread() {
		public void run() {
			System.out.println("Hey Jude: Welcome to the realm of the mad god!");
			System.out.println(Thread.currentThread().getName());
		}
	}.start();
	}
}
