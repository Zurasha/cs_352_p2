package blb352Proj2;

public class MemoryManagerThread extends Thread {
	private Thread t;
	private String threadName;
	
	public MemoryManagerThread(String name) {
		threadName = name;
	}
	
	public void run() {
		try {
			// If the page is in the main memory output message, access mainMemory Vmsim.mainMemory
			while (VMsim.finishedProcesses < VMsim.userProcessCount) {
				
			}
		} catch (Exception e) {
			System.out.println("Thread " +  threadName + " interrupted.");
		}
        VMsim.finishedProcesses++;
	}
	
	public void start() {
		if (t == null) {
			t = new Thread (this, threadName);
			t.start();
		}
	}
}
