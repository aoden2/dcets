package dao;

import java.util.List;

import entity.Future;

public interface FutureDao {
	public void refreshFutures();
	
	public List<Future> getAllFutures(int bid);
	
	public List<String> getAllFutureName();
	
	public int getFutureByNamePeriod(String name, String period);
	
	public List<Integer> getFutureByName(String name);
	
	public Future getFutureById(int id);
}
