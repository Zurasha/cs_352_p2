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
	
	public boolean isFrameInUse(int index) {
		return frameList[index].isInUse();
	}
	
	private void FrameReplace(int page) {
		int index = findLRUFrame();
		frameList[index].setInserted(new Date());
		frameList[index].setPageNumber(page);
	}
	
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