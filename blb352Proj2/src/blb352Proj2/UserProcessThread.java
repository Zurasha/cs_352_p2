package blb352Proj2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class UserProcessThread extends Thread {
	private Thread t;
	private String threadName;
	private int userProcessNumber;
	
	public UserProcessThread(String name, int number) {
		threadName = name;
		userProcessNumber = number;
	}
	
	public void run() {
		try {
			// Open the file to get the information to be processed
			BufferedReader br = null;
	        try {
	            br = new BufferedReader(new FileReader("trace_" + userProcessNumber + ".txt"));
	            String line;
	            while ((line = br.readLine()) != null) {
	            	// For each simulate accessing address
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
	        
	        VMsim.finishedProcesses++;
		} catch (Exception e) {
			System.out.println("Thread " +  threadName + " interrupted.");
		}
	}
	
	public void start() {
		if (t == null) {
			t = new Thread (this, threadName);
			t.start();
		}
	}
}
