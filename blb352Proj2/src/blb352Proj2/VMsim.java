package blb352Proj2;

public class VMsim {
	// 0 : page/frame size; 1: pages for the process; 2: number of frames in main memory; 3: user processes
	public static void main (String[] args) {
		int frameSize = Integer.parseInt(args[0]);
		int pageCount = Integer.parseInt(args[1]);
		int memoryFrameCount = Integer.parseInt(args[2]);
		int userProcessCount = Integer.parseInt(args[3]);
	}
}