package blb352Proj2;

public class Frame {
	private int id;
	private int byteStart;
	private int byteEnd;
	private boolean inUse;
	
	public Frame(int number, int start, int end) {
		id = number;
		byteStart = start;
		byteEnd = end;
		inUse = false;
	}

	public int getByteStart() {
		return byteStart;
	}

	public void setByteStart(int byteStart) {
		this.byteStart = byteStart;
	}

	public int getByteEnd() {
		return byteEnd;
	}

	public void setByteEnd(int byteEnd) {
		this.byteEnd = byteEnd;
	}

	public boolean isInUse() {
		return inUse;
	}

	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
