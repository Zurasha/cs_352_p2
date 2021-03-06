/**
 * A class used to pass the required data between certain functions to make for easier/cleaner code
 * @author Bryce
 *
 */
public class SwapFrameValue {
	private int frameIndex;
	private boolean wasFree;
	
	public SwapFrameValue(int index, boolean status) {
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
