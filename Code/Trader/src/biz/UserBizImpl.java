package biz;

public interface UserBizImpl {
	public boolean checkLogin(String username, String password);
	
	public boolean hasUser(String username);
	
	public void newUser(String username, String password);

}
