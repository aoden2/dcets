package dao;

import java.util.List;
import entity.BrokerInfo;
public interface BrokerDao {
	public List<BrokerInfo> getAllBrokers();
	public int addBroker(String name, String ip, int port, String pass);
}
