import java.util.Date;

public class Frame {
	private int id;
	private int byteStart;
	private int byteEnd;
	private int pageNumber;
	private Date inserted;
	private boolean inUse;
	
	public Frame(int number, int start, int end) {
		id = number;
		byteStart = start;
		byteEnd = end;
		pageNumber = -1;
		inserted = new Date();
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

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Date getInserted() {
		return inserted;
	}

	public void setInserted(Date inserted) {
		this.inserted = inserted;
	}
}