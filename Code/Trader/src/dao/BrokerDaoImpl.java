package dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import util.JDBCUtil;
import entity.BrokerInfo;

public class BrokerDaoImpl implements BrokerDao{	
		public List<BrokerInfo> getAllBrokers(){
			List<BrokerInfo> brokers = new ArrayList<BrokerInfo>();
			return brokers;
		}
		public int addBroker(String name, String ip, int port){
			int id = 0;
			return id;
		}
}
