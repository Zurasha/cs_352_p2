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
        // Adds to the numbers of completed processes
        VMsim.finishedProcesses++;
	}
	
	public void start() {
		if (t == null) {
			t = new Thread (this, threadName);
			t.start();
		}
	}
	
	public void handleAddress(int address) {
		int index = address/VMsim.mainMemory.getFrameSize();
		int offset = address % VMsim.mainMemory.getFrameSize();
		
		if (VMsim.mainMemory.isFrameInUse(index)) {
			
		} else {
			FaultHandlerThread faultHan = (FaultHandlerThread) VMsim.threadMap.get("fault_handler");
			faultHan.handle(index, offset);
		}
	}
}
