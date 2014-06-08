package entity;

public class TraderOrder {
	
	private int fid;
	private int type;
	private String period;
	private String broker;
	private int qty;
	private int bid;
	private int ask;
	private int last;
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getBroker() {
		return broker;
	}
	public void setBroker(String broker) {
		this.broker = broker;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public int getAsk() {
		return ask;
	}
	public void setAsk(int ask) {
		this.ask = ask;
	}
	public int getLast() {
		return last;
	}
	public void setLast(int last) {
		this.last = last;
	}
	public TraderOrder(int fid, int type, String period, String broker,
			int qty, int bid, int ask, int last) {
		super();
		this.fid = fid;
		this.type = type;
		this.period = period;
		this.broker = broker;
		this.qty = qty;
		this.bid = bid;
		this.ask = ask;
		this.last = last;
	}
	public TraderOrder() {
		super();
	}

}
