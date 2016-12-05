package blb352Proj2;

public class MemoryManagerThread extends Thread {
	private Thread t;
	private String threadName;
	
	public MemoryManagerThread(String name) {
		threadName = name;
	}
	
	public void run() {
		try {
			// Needs to keep running till all user processes have been completed
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
	/**
	 * Handle the address and output or call the fault handler as needed
	 * @param address
	 * @param processId
	 */
	public void handleAddress(int address, int processId) {
		int index = address/VMsim.mainMemory.getFrameSize();
		int offset = address % VMsim.mainMemory.getFrameSize();
		
		if (VMsim.mainMemory.isFrameInUse(index)) {
			System.out.println("" + processId);
		} else {
			FaultHandlerThread faultHan = (FaultHandlerThread) VMsim.threadMap.get("fault_handler");
			// Delay until the faultHandler is ready to handle another thread
			while (faultHan.getInUse()) {}
			faultHan.handle(index, offset);
		}
	}
}
