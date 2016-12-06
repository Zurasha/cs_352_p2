public class FaultHandlerThread extends Thread {
	private Thread t;
	private String threadName;
	public Memory mainMemory;
	
	public FaultHandlerThread(String name) {
		threadName = name;
		mainMemory = VMsim.mainMemory;
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
	
	public void handle(int page, int offset, String processName) {
		String message = "";
		System.out.println(processName + " accesses address x (page number = p, page offset = d) not in main memory."); // Not found in main memory
		
		synchronized (mainMemory) {
			swapFrameValue swapValue = mainMemory.FrameReplace(page);
			
			if (swapValue.isWasFree()) {
				message = processName + " finds a free frame in main memory (frame number = f)."; // Add free frame replace message
			} else {
				message = processName + " replaces a frame (frame number = f) from the main memory. "; // Add no free replace message
			}
			System.out.println(message);
			simulateSwap(processName, swapValue.getFrameIndex(), page);
			System.out.println(processName + " accesses address x (page number = p, page offset =d) in main memory (frame number = f)"); // Access the 
		}
	}
	
	private void simulateSwap(String processName, int frameIndex, int page) {
		try {
			System.out.println(processName + " issues an I/O operation to swap in demanded page (page number = p).");
			t.sleep(1000);
			System.out.println(processName + " demanded page (page number =p) has been swapped in main memory (frame number = f).");
		} catch (InterruptedException e) {
			return;
		}
	}
}
