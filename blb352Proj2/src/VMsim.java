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
	public static int finishedProcesses = 0; // Counter to track if all  processes are ended
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
		
		ArrayList<ArrayList<Integer>> addressList = new ArrayList<ArrayList<Integer>>(); // Store addresses for threads to process
		
		for (int i = 1; i <= userProcessCount; i++) {
			addressList.add(new ArrayList<Integer>());
			BufferedReader br = null;
	        try {
	            br = new BufferedReader(new FileReader("./trace_" + i + ".txt"));
	            String line;
	            while ((line = br.readLine()) != null) {
	            	if (line.length() > 0) {
	            		addressList.get(i).add(Integer.parseInt(line));
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
		}
		
		for (int i = 1; i <= userProcessCount; i++) {
			threadMap.putIfAbsent("process_" + i, new UserProcessThread("[Process " + i + "]", i, addressList.get(i)));
		}
		// Start each user process
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