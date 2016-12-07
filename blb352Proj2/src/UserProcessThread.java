


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class UserProcessThread extends Thread {
	private Thread t;
	private String threadName;
	private int userProcessNumber;
	private ArrayList<Integer> addressList;
	
	public UserProcessThread(String name, int number, ArrayList<Integer> addresses) {
		threadName = name;
		userProcessNumber = number;
		addressList = addresses;
	}
	
	public void run() {
		for (int i = 0; i < addressList.size(); i++) {
			MemoryManagerThread memMan = (MemoryManagerThread) VMsim.threadMap.get("memory_manager");
    		if (memMan.handleAddress(addressList.get(i).intValue(), threadName)) {
    			System.out.println(threadName + " Attempted to access a page greater than max pages allowed");
    			break;
    		}
		}
		VMsim.finishedProcesses++;
	}
	
	public void start() {
		if (t == null) {
			t = new Thread (this, threadName);
			t.start();
		}
	}
}
