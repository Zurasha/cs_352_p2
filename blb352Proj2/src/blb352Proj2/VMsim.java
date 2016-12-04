package blb352Proj2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class VMsim {
	public static Memory mainMemory;
	public static Map<String, Thread> threadMap = new ConcurrentHashMap<String, Thread>();
	public static int finishedProcesses = 0; // Counter to track if all  processes are ended
	public static int userProcessCount;
	// 0: page/frame size; 1: pages for the process; 2: number of frames in main memory; 3: user processes
	public static void main (String[] args) {
		int frameSize = Integer.parseInt(args[0]);
		int pageCount = Integer.parseInt(args[1]);
		int memoryFrameCount = Integer.parseInt(args[2]);
		userProcessCount = Integer.parseInt(args[3]);
		
		mainMemory = new Memory(frameSize, memoryFrameCount); // needs to be accessible from memory and fault threads
		
		// Build a map of threads to track and run all required threads including memory_manager, fault_handler, and user_process_i
		threadMap.putIfAbsent("memory_manager", new MemoryManagerThread("memory_manager"));
		threadMap.putIfAbsent("fault_handler", new FaultHandlerThread("fault_handler"));
		
		for (int i = 1; i <= userProcessCount; i++) {
			threadMap.putIfAbsent("user_process_" + i, new UserProcessThread("user_process_" + i, i));
		}
		
		Iterator<String> iter = threadMap.keySet().iterator();
		
		// Start each thread
		while (iter.hasNext()) {
			String key = iter.next();
			threadMap.get(key).start();
		}
		
		while (finishedProcesses < userProcessCount + 2) {
			// Prevent the thread from closing until all processes are complete
		}
	}
}