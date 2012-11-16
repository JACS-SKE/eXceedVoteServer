package jacs.database.exceed.dao;

import java.util.Scanner;

import jacs.database.exceed.model.User;


public class UserJpaDaoTest {
	
	private static UserDAO user_dao;
	public static Scanner sc = new Scanner(System.in);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		user_dao = DaoFactory.getInstance().getUserDAO();
		User user = new User("sagittarius", "1234","student");
		//System.out.print(registration(user));
		//System.out.print("New Password : ");
		//System.out.println(changePassword(user, sc.nextLine()));
		User x = findUser(user);
		System.out.println(x.getId());
	}
	public static User findUser(User user)	{
		return user_dao.findTestDao(user);
	}
	public static String registration(User user)	{
		return user_dao.regisUser(user);
	}
	public static String changePassword(User user,String new_password)	{
		return user_dao.changePassword(user , new_password);
	}

}
