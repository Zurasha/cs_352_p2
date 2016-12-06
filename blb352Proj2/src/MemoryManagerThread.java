

public class MemoryManagerThread extends Thread {
	private Thread t;
	private String threadName;
	private Memory mainMemory;
	
	public MemoryManagerThread(String name) {
		threadName = name;
		mainMemory = VMsim.mainMemory;
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
	 * @param processName
	 */
	public boolean handleAddress(int address, String processName) {
		int page = 0, offset = 0;
		boolean pageHit = false;
		synchronized (mainMemory) {
			page = address / mainMemory.getFrameSize();
			offset = address % mainMemory.getFrameSize();
			
			// Check if address divided by page/frame size is > page/frame size if so break
			if (page > VMsim.pageCount) {
				return false;
			}
			
			pageHit = mainMemory.checkPageIfAvailable(page);
		}
		// Check if the address hits
		if (pageHit) {
			System.out.println(processName + " ");
		} else {
			FaultHandlerThread faultHandler = (FaultHandlerThread) VMsim.threadMap.get("fault_handler");
			faultHandler.handle(address, processName);
		}
		return true;
	}
}
