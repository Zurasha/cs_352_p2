


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class UserProcessThread extends Thread {
	private Thread t;
	private String threadName;
	private int userProcessNumber;
	private ArrayList<Address> addressList;
	
	public UserProcessThread(String name, int number, ArrayList<Address> addresses) {
		threadName = name;
		userProcessNumber = number;
		addressList = addresses;
	}
	
	/**
	 * Goes through its list of addresses and call teh memory manager to handle checking for hits or to call fault handler
	 */
	public void run() {
		for (int i = 0; i < addressList.size(); i++) {
			MemoryManagerThread memMan = (MemoryManagerThread) VMsim.threadMap.get("memory_manager");
    		if (!memMan.handleAddress(addressList.get(i), threadName)) {
    			System.out.println(threadName + " Attempted to access a page greater than max pages allowed");
    			break;
    		}
		}
		return;
	}
	
	public void start() {
		if (t == null) {
			t = new Thread (this, threadName);
			t.start();
		}
	}
}
