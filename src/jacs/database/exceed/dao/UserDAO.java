package jacs.database.exceed.dao;

import java.util.List;

import jacs.database.exceed.model.User;

public interface UserDAO {
	public List<User> findUser(User user);
	public List<User> findAllUser();
	public String login(User user);
	public String regisUser(User user);
	public boolean checkMatchUser(User user);
	public User findTestDao(User user);
	public String changePassword(User user,String new_password);
}
