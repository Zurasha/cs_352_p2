public class FaultHandlerThread extends Thread {
	private Thread t;
	private String threadName;
	public Memory mainMemory;
	
	public FaultHandlerThread(String name) {
		threadName = name;
		mainMemory = VMsim.mainMemory;
	}
	
	public void run() {
        return;
	}
	
	public void start() {
		if (t == null) {
			t = new Thread (this, threadName);
			t.start();
		}
	}
	
	/**
	 * Handles faults and calls frame replacement to make sure that frames are being updated with the correct pages
	 * @param address
	 * @param processName
	 */
	public void handle(Address address, String processName) {
		String message = "";
		System.out.println(processName + " accesses address " + address.getAddress() + " (page number = " + address.getPage() + ", page offset = " + address.getOffset() + ") not in main memory."); // Not found in main memory
		
		synchronized (mainMemory) {
			SwapFrameValue swapValue = mainMemory.FrameReplace(address.getPage());
			
			if (swapValue.isWasFree()) {
				message = processName + " finds a free frame in main memory (frame number = " + swapValue.getFrameIndex() + ")."; // Add free frame replace message
			} else {
				message = processName + " replaces a frame (frame number = " + swapValue.getFrameIndex() + ") from the main memory. "; // Add no free replace message
			}
			System.out.println(message);
			simulateSwap(processName, swapValue.getFrameIndex(), address.getPage());
			System.out.println(processName + " accesses address " + address.getAddress() + " (page number = " + address.getPage() + ", page offset = " + address.getOffset() + ") in main memory (frame number = " + swapValue.getFrameIndex() + ")"); // Access the 
		}
	}
	
	/**
	 * Simulates a swap including sleeping for a moment
	 * @param processName
	 * @param frameIndex
	 * @param page
	 */
	private void simulateSwap(String processName, int frameIndex, int page) {
		try {
			System.out.println(processName + " issues an I/O operation to swap in demanded page (page number = " + page + ").");
			t.sleep(1000);
			System.out.println(processName + " demanded page (page number = " + page + ") has been swapped in main memory (frame number = " + frameIndex + ").");
		} catch (InterruptedException e) {
			return;
		}
	}
}
