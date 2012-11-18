package jacs.database.exceed.dao;

import java.util.List;

import jacs.database.exceed.model.User;

public interface UserDAO {
	public User findUserByName(String username);
	public List<User> findAllUser();
	public String loginUser(String username,String password);
	public String regisUser(String username,String password);
	public String deleteUser(String username,String password);
	public String changePassword(User user,String new_password);
	
}
