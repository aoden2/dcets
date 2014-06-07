package entity;

public class OriginOrder {
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public OriginOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OriginOrder(int id, int fid, int bid, int quantity, int price,
			int status) {
		super();
		this.id = id;
		this.fid = fid;
		this.bid = bid;
		this.quantity = quantity;
		this.price = price;
		this.status = status;
	}
	private int id;
	private int fid;
	private int bid;
	private int quantity;
	private int price;
	private int status;
}
