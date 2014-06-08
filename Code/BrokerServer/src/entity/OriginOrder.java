/*
 * Author   : Zhou Cheng
 * Date     : 2014-6-8
 * Project  : BrokerServer
 * Filename : OriginOrder.java
 * 
 * All rights reserved.
 */
package entity;

import java.util.Date;

public class OriginOrder {
	private int id;
	private int fid; // future id
	private int tid; // trader id
	private int quantity; // 总量
	private int cumQtyl; // 成交量
	private int leavesqty; // 剩下未成交量
	private int price; // 可以接受的报价
	private Date date; // 接收时间
	private int status;

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

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getCumQtyl() {
		return cumQtyl;
	}

	public void setCumQtyl(int cumQtyl) {
		this.cumQtyl = cumQtyl;
	}

	public int getLeavesqty() {
		return leavesqty;
	}

	public void setLeavesqty(int leavesqty) {
		this.leavesqty = leavesqty;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
