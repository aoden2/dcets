package biz;

import dao.UserDao;
import dao.UserDaoImpl;
import entity.User;

public class UserBiz implements UserBizImpl {
	private UserDaoImpl uDao = new UserDao();

	public boolean checkLogin(String username, String password) {
		User u = uDao.listUser(username);
		if (u == null)
			return false;
		if (password.equals(u.getPassword()))
			return true;
		else
			return false;
	}
	
	public boolean hasUser(String username){
		User u = uDao.listUser(username);
		if (u == null)
			return false;
		else
			return true;
	}

	public void newUser(String username, String password) {
		User u = new User(username, password);
		uDao.addUser(u);
	}

}
