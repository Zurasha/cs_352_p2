package blb352Proj2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class VMsim {
	
	// 0: page/frame size; 1: pages for the process; 2: number of frames in main memory; 3: user processes
	public static void main (String[] args) {
		int frameSize = Integer.parseInt(args[0]);
		int pageCount = Integer.parseInt(args[1]);
		int memoryFrameCount = Integer.parseInt(args[2]);
		int userProcessCount = Integer.parseInt(args[3]);
		
		// Build a map of threads to track and run all required threads including memory_manager, fault_handler, and user_process_i
		Map<String, Thread> threadMap = new ConcurrentHashMap<String, Thread>();
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
	}
}