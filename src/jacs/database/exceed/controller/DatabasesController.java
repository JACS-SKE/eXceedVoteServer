package jacs.database.exceed.controller;

import jacs.database.exceed.dao.User_DAO;

public class DatabasesController {

	
	public DatabasesController() {
		super();
	}
	
	public String recieveMsg(String msg)	{
		//pattern of msg : REGIS/LOGIN,username,password
		String[] temp_msg = msg.split(",");
		if (temp_msg.length == 3) {
			String type = temp_msg[0];
			String username = temp_msg[1];
			String password = temp_msg[2];
			if (type.toUpperCase().equals("REGIS")) {
				User_DAO userDao = new User_DAO();
				msg = userDao.regisUser(username, password);
			} else if (type.toUpperCase().equals("LOGIN")) {
				User_DAO userDao = new User_DAO();
				msg = userDao.loginUser(username, password);
			}
		}else {
			msg = "error";
		}
		return msg;
	}
}
