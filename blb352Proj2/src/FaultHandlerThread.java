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
	
	public void handle(int address, String processName) {
		int page = 0, offset = 0;
		String message = "";
		System.out.println(); // Not found in main memory
		
		synchronized (mainMemory) {
			page = address / mainMemory.getFrameSize();
			offset = address % mainMemory.getFrameSize();
			swapFrameValue swapValue = mainMemory.FrameReplace(page);
			
			if (swapValue.isWasFree()) {
				message = ""; // Add free frame replace message
			} else {
				message = ""; // Add no free replace message
			}
			simulateSwap(processName, swapValue.getFrameIndex(), page);
		}
	}
	
	private void simulateSwap(String processName, int frameIndex, int page) {
		try {
			System.out.println();
			t.sleep(1000);
			System.out.println();
		} catch (InterruptedException e) {
			return;
		}
	}
}
