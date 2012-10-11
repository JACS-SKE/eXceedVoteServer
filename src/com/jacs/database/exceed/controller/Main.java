package com.jacs.database.exceed.controller;

import com.jacs.database.exceed.dao.Project_eXceed_DAO;
import com.jacs.database.exceed.dao.User_DAO;
import com.jacs.database.exceed.model.Project_eXceed;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User_DAO userDao = new User_DAO();
		Project_eXceed_DAO projectDao = new Project_eXceed_DAO();
		userDao.loginUser("Kanisorn", "qwerty240635");
		projectDao.updateScore("I Love You");
	}

}
