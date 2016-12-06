import java.util.Date;

public class Memory {
	private int frameSize;
	private int frameCount;
	private Frame[] frameList;
	
	/*
	 * Creates a "physical" memory object
	 */
	public Memory(int size, int count) {
		frameSize = size;
		frameCount = count;
		
		for (int i = 0; i < frameCount; i++) {
			Frame frame = new Frame(i, i*frameSize, (i+1)*frameSize - 1);
			frameList[i] = frame;
		}
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
	
	public boolean checkPageIfAvailable(int index) {
		for (int i = 0; i < frameCount; i++) {
			if (frameList[i].getPageNumber() == index) {
				frameList[i].setInserted(new Date());
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns true if the frame replaced was free else false if none free
	 * @param page
	 * @return boolean
	 */
	public SwapFrameValue FrameReplace(int page) {
		int index = findLRUFrame();
		SwapFrameValue value = new SwapFrameValue(index, false);
		if (frameList[index].getPageNumber() < 0) {
			value.setWasFree(true);
		}
		frameList[index].setInserted(new Date());
		frameList[index].setPageNumber(page);
		return value;
	}
	
	/**
	 * Finds the least resently used frame and returns the index
	 * @return int
	 */
	private int findLRUFrame() {
		int replaceIndex = 0;
		Date LRU = new Date();
		for (int i = 0; i < frameCount; i++) {
			if (frameList[i].getInserted().compareTo(LRU) < 0) {
				LRU = frameList[i].getInserted();
				replaceIndex = i;
			}
		}
		return replaceIndex;
	}
}