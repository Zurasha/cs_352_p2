package blb352Proj2;

import java.util.ArrayList;
import java.util.LinkedList;

public class Memory {
	private int frameSize;
	private int frameCount;
	private LinkedList<Frame> LRUFrames;
	private ArrayList<Frame> frameList;
	
	/*
	 * Creates a "physical" memory object
	 */
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

	public int getFrameSize() {
		return frameSize;
	}

	public void setFrameSize(int frameSize) {
		this.frameSize = frameSize;
	}

	public int getFrameCount() {
		return frameCount;
	}

	public void setFrameCount(int frameCount) {
		this.frameCount = frameCount;
	}

	public LinkedList<Frame> getLRUFrames() {
		return LRUFrames;
	}

	public void setLRUFrames(LinkedList<Frame> lRUFrames) {
		LRUFrames = lRUFrames;
	}

	public ArrayList<Frame> getFrameList() {
		return frameList;
	}

	public void setFrameList(ArrayList<Frame> frameList) {
		this.frameList = frameList;
	}
}
