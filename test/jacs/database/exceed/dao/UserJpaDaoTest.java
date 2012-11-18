package jacs.database.exceed.dao;

import java.util.Iterator;
import java.util.List;
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
			System.out.print("Registration(r) / Login(l) / ChangePassword(c) / clearDb(clear) : ");
			String mode = sc.nextLine();
			if (mode.equalsIgnoreCase("r")) {
				System.out.println("--------- Registration ---------");
				System.out.print("Username : ");
				id = sc.nextLine();
				System.out.print("Password : ");
				pass = sc.nextLine();
				System.out.println("Process : "+registration(id,pass));
			} else if (mode.equalsIgnoreCase("l")) {
				System.out.println("--------- Login ---------");
				//LOGIN_SUCCESS,name,type,point
				System.out.print("Username : ");
				id = sc.nextLine();
				System.out.print("Password : ");
				pass = sc.nextLine();
				User user = new User(id,pass,type);
				System.out.println("Process : "+login(id,pass));
			} else if ( mode.equalsIgnoreCase("clear"))	{
				List<User> result = user_dao.findAllUser();
				Iterator<User> it = result.iterator();
				while(it.hasNext())	{
					User user = it.next();
					System.out.println(user_dao.deleteUser(user.getUsername(), user.getPassword()));
				}
			}else
				break;
		}
	}
	public static String registration(String username,String password)	{
		return user_dao.regisUser(username,password);
	}
	public static String login(String username,String password)	{
		return user_dao.loginUser(username, password);
	}
}
