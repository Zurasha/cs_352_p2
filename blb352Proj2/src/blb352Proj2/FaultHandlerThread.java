package blb352Proj2;

public class FaultHandlerThread extends Thread {
	private Thread t;
	private String threadName;
	public Boolean active;
	
	public FaultHandlerThread(String name) {
		threadName = name;
		active = false;
	}
	
	public void run() {
		try {
			// Handle a fault
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
