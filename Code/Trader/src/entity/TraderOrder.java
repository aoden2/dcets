package entity;

public class TraderOrder {

	private int fid;
	private int type;// 0 for buy&sell, 3 for only buy, -3 for only sell
	private String period;
	private String name;
	private String brokerBuy;
	private String brokerSell;
	private int qtyBuy;
	private int qtySell;
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

	public String getBrokerBuy() {
		return brokerBuy;
	}

	public void setBrokerBuy(String brokerBuy) {
		this.brokerBuy = brokerBuy;
	}

	public String getBrokerSell() {
		return brokerSell;
	}

	public void setBrokerSell(String brokerSell) {
		this.brokerSell = brokerSell;
	}

	public int getQtyBuy() {
		return qtyBuy;
	}

	public void setQtyBuy(int qtyBuy) {
		this.qtyBuy = qtyBuy;
	}

	public int getQtySell() {
		return qtySell;
	}

	public void setQtySell(int qtySell) {
		this.qtySell = qtySell;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TraderOrder(int fid, int type, String period, String name,
			String brokerBuy, String brokerSell, int qtyBuy, int qtySell,
			int bid, int ask, int last) {
		super();
		this.fid = fid;
		this.type = type;
		this.period = period;
		this.name = name;
		this.brokerBuy = brokerBuy;
		this.brokerSell = brokerSell;
		this.qtyBuy = qtyBuy;
		this.qtySell = qtySell;
		this.bid = bid;
		this.ask = ask;
		this.last = last;
	}

	public TraderOrder() {
		super();
	}

}
