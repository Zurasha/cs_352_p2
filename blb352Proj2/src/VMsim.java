import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * To run use the 'make' command followed by 'java VMsim args1 args2 args3 args4'
 * @author Bryce Beougher
 *
 */
public class VMsim {
	public static Memory mainMemory;
	public static Map<String, Thread> threadMap = new ConcurrentHashMap<String, Thread>();
	public static int pageCount; // Max pages a process can use
	public static int userProcessCount;
	
	// 0: page/frame size; 1: pages for the process; 2: number of frames in main memory; 3: user processes
	public static void main (String[] args) {
		int frameSize = Integer.parseInt(args[0]);
		pageCount = Integer.parseInt(args[1]);
		int memoryFrameCount = Integer.parseInt(args[2]);
		userProcessCount = Integer.parseInt(args[3]);
		
		mainMemory = new Memory(frameSize, memoryFrameCount);
		
		// Build a map of threads to track and run all required threads including memory_manager, fault_handler, and user_process_i
		threadMap.putIfAbsent("memory_manager", new MemoryManagerThread("memory_manager"));
		threadMap.putIfAbsent("fault_handler", new FaultHandlerThread("fault_handler"));
		
		String path = ""; // Was just in case the path to the file needed to be added at some point
		// Handles retrieving all addresses and handing them off to the appropriate threads
		for (int i = 0; i < userProcessCount; i++) {
			ArrayList<Address> addresses = new ArrayList<Address>();
			BufferedReader br = null;
	        try {
	            br = new BufferedReader(new FileReader(path + "trace_" + (i+1) + ".txt"));
	            String line;
	            while ((line = br.readLine()) != null) {
	            	if (line.length() > 0) {
	            		int address = Integer.parseInt(line);
	            		int page = address / frameSize;
	        			int offset = address % frameSize;
	            		addresses.add(new Address(address, page, offset));
	            	}
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (br != null) {
	                    br.close();
	                }
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
	        // Skips a process if nothing is required
	        if (addresses.size() > 0) {
	        	threadMap.putIfAbsent("process_" + (i+1), new UserProcessThread("[Process " + (i+1) + "]", (i+1), addresses));
	        }
		}
		
		// Start each user process
		Iterator<String> iter = threadMap.keySet().iterator();
		
		// Start each thread
		while (iter.hasNext()) {
			String key = iter.next();
			threadMap.get(key).start();
		}
	}
}