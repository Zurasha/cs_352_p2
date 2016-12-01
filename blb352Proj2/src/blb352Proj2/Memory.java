package blb352Proj2;

import java.util.ArrayList;
import java.util.LinkedList;

public class Memory {
	private int frameSize;
	private int frameCount;
	private LinkedList<Frame> LRUFrames;
	private ArrayList<Frame> frameList;
	
	public Memory(int size, int count) {
		frameSize = size;
		frameCount = count;
		
		for (int i = 0; i < frameCount; i++) {
			Frame frame = new Frame(i, i*frameSize, (i+1)*frameSize - 1);
			LRUFrames.add(frame);
			frameList.add(frame);
		}
	}
	
	public boolean isFrameInUse(int address) {
		int index = address/frameSize;
		return frameList.get(index).isInUse();
	}
}
