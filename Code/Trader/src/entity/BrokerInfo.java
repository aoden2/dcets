package entity;

public class BrokerInfo {
	private int id;
	private String name;
	private String ip;
	private int port;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public BrokerInfo(int id, String name, String ip, int port) {
		super();
		this.id = id;
		this.name = name;
		this.ip = ip;
		this.port = port;
	}
	public BrokerInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

}
