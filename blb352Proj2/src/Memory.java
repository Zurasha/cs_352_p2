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
		frameList = new Frame[frameCount];
		
		for (int i = 0; i < frameCount; i++) {
			frameList[i] = new Frame(i, i*frameSize, (i+1)*frameSize - 1);
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
	
	/**
	 * Return 1 if it is found else -1
	 * @param page
	 * @return
	 */
	public int checkPageIfAvailable(int page) {
		for (int i = 0; i < frameCount; i++) {
			if (frameList[i].getPageNumber() == page) {
				frameList[i].setInserted(new Date());
				return i;
			}
		}
		return -1;
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