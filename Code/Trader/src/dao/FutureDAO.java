package dao;

import java.util.List;

import entity.Future;

public interface FutureDAO {
	public List<Future> getAllFutures(int bid);
}
