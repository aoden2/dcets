package dao;

import java.util.List;

import entity.User;

public interface UserDaoImpl {
	
	public List<User> listAllUser();
	
	public User listUser(String username);
	
	public void addUser(User user);

}
