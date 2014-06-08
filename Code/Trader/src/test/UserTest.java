package test;

import biz.UserBiz;
import biz.UserBizImpl;

public class UserTest {

	public static void main(String[] args) {
		UserBizImpl uBiz = new UserBiz();
		uBiz.newUser("zc", "aaa");

	}

}
