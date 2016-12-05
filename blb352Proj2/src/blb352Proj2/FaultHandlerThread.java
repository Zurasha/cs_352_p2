package blb352Proj2;

public class FaultHandlerThread extends Thread {
	private Thread t;
	private String threadName;
	public Boolean inUse;
	
	public FaultHandlerThread(String name) {
		threadName = name;
		inUse = false;
	}
	
	public void run() {
		try {
			// Needs to keep running till all user processes have been completed
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
		inUse = true;
		
		inUse = false;
	}

	public Boolean getInUse() {
		return inUse;
	}

	public void setInUse(Boolean inUse) {
		this.inUse = inUse;
	}
}
