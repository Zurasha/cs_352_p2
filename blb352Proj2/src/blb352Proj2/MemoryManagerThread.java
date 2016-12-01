package blb352Proj2;

public class MemoryManagerThread extends Thread {
	private Thread t;
	private String threadName;
	
	public MemoryManagerThread(String name) {
		threadName = name;
	}
	
	public void run() {
		try {
			// Open the file to get the information to be processed
		} catch (Exception e) {
			System.out.println("Thread " +  threadName + " interrupted.");
		}
	}
	
	public void start() {
		if (t == null) {
			t = new Thread (this, threadName);
			t.start();
		}
	}
}
