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
			while (VMsim.finishedProcesses < VMsim.userProcessCount) {
				
			}
		} catch (Exception e) {
			System.out.println("Thread " +  threadName + " interrupted.");
		}
        // Adds to the numbers of completed processes
        VMsim.finishedProcesses++;
	}
	
	public void start() {
		if (t == null) {
			t = new Thread (this, threadName);
			t.start();
		}
	}
	
	public void handle(int index, int offset) {
		
	}
}
