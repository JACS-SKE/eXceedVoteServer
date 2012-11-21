package jacs.database.exceed.dao;

import java.util.List;

import jacs.database.exceed.model.User;

public interface UserDAO {
	/**
	 * Find User in database by name.
	 * @param username : name of user.
	 * @return User.
	 */
	public User findUserByName(String username);
	/**
	 * Find User in database by name and password.
	 * @param username : name of user.
	 * @param password : password of user.
	 * @return User.
	 */
	public User findUserByNameAndPassword(String username,String password);
	/**
	 * Find all of User in database.
	 * @return List of User.
	 */
	public List<User> findAllUser();
	/**
	 * Login User .
	 * @param username : name of user when user login.
	 * @param password : password of user.
	 * @return message confirm login.
	 */
	public String loginUser(String username,String password);
	/**
	 * Registration User.
	 * @param username : name of user that each user want to regis. 
	 * @param password : password of user that each user want to regis.
	 * @return message confirm registration success or not.
	 */
	public String regisUser(String username,String password);
	/**
	 * Delete User.
	 * @param username : name of user that each user be delete.
	 * @param password : password of user that each user delete.
	 * @return message confirm delete user.
	 */
	public String deleteUser(String username,String password);
	/**
	 * Change Password 's User.
	 * @param user : User
	 * @param new_password : new password that user want to change.
	 * @return message confirm for change password.
	 */
	public String changePassword(User user,String new_password);
	/**
	 * Check match user in database.
	 * @param user 
	 * @return true if have user in database. otherwise false.
	 */
	public boolean checkMatchUser(User user);
}
