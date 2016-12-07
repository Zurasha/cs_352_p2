/**
 * Class to store the address and it's parts
 * @author Bryce
 *
 */
public class Address {
	private int address;
	private int page;
	private int offset;
	
	public Address(int address, int page, int offset) {
		this.address = address;
		this.page = page;
		this.offset = offset;
	}
	
	public int getAddress() {
		return address;
	}
	public void setAddress(int address) {
		this.address = address;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
}
