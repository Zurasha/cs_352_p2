

public class MemoryManagerThread extends Thread {
	private Thread t;
	private String threadName;
	private Memory mainMemory;
	
	public MemoryManagerThread(String name) {
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
	 * Handle the address and output or call the fault handler as needed
	 * @param address
	 * @param processName
	 */
	public boolean handleAddress(Address address, String processName) {
		int pageHit = 0;
		synchronized (mainMemory) {
			// Check if address divided by page/frame size is > page/frame size if so break
			if (address.getPage() > VMsim.pageCount) {
				System.out.println(address.getPage() + "HERE");
				return false;
			}
			pageHit = mainMemory.checkPageIfAvailable(address.getPage());
		}
		// Check if the address hits
		if (pageHit > 0) {
			System.out.println(processName + " accesses address " + address.getAddress() + " (page number = " + address.getPage() + ", page offset=" + address.getOffset() + ") in main memory (frame number = " + pageHit + "). ");
		} else {
			FaultHandlerThread faultHandler = (FaultHandlerThread) VMsim.threadMap.get("fault_handler");
			faultHandler.handle(address, processName);
		}
		return true;
	}
}
