
public class swapFrameValue {
	private int frameIndex;
	private boolean wasFree;
	
	public swapFrameValue(int index, boolean status) {
		frameIndex = index;
		wasFree = status;
	}
	public int getFrameIndex() {
		return frameIndex;
	}
	public void setFrameIndex(int frameIndex) {
		this.frameIndex = frameIndex;
	}
	public boolean isWasFree() {
		return wasFree;
	}
	public void setWasFree(boolean wasFree) {
		this.wasFree = wasFree;
	}
}
