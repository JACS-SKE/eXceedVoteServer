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
		String id;
		String pass;
		String type = "student";
		user_dao = DaoFactory.getInstance().getUserDAO();
		while (true) {
			System.out.print("Registration(r) / Login(l) / ChangePassword(c) : ");
			String mode = sc.nextLine();
			if (mode.equalsIgnoreCase("r")) {
				System.out.println("--------- Registration ---------");
				System.out.print("Username : ");
				id = sc.nextLine();
				System.out.print("Password : ");
				pass = sc.nextLine();
				User user = new User(id,pass,type);
				System.out.println("Process : "+registration(user));
			} else if (mode.equalsIgnoreCase("l")) {
				System.out.println("--------- Login ---------");
				System.out.print("Username : ");
				id = sc.nextLine();
				System.out.print("Password : ");
				pass = sc.nextLine();
				User user = new User(id,pass,type);
				System.out.println("Process : "+user_dao.login(user));
			} else
				break;
		}
	}
	public static User findUser(User user)	{
		return user_dao.findTestDao(user);
	}
	public static String registration(User user)	{
		return user_dao.regisUser(user);
	}
	public static String changePassword(User user,String new_password)	{
		User x = findUser(user);
		return user_dao.changePassword(x , sc.nextLine());
	}
	

}
