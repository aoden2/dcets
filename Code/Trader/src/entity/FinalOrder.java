package entity;

public class FinalOrder {
	private int id;
	private int oid;
	private int quantity;
	private int price;
	private int status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
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
	public FinalOrder(int id, int oid, int quantity, int price, int status) {
		super();
		this.id = id;
		this.oid = oid;
		this.quantity = quantity;
		this.price = price;
		this.status = status;
	}
	public FinalOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

}
