package jacs;

import java.util.List;

import jacs.database.exceed.dao.DaoFactory;
import jacs.database.exceed.dao.UserDAO;
import jacs.database.exceed.model.User;


public class Main {

	public static void main(String[] args) {
		//new Server();
		testUserDao();
		
	}
	public static void testUserDao()	{
		
		DaoFactory factory = DaoFactory.getInstance();
		UserDAO dao = factory.getUserDAO();
		
		
		//Test regis User
		User kim = new User("kimapiwatTestDao","054451652","Admin");
		//String s = dao.regisUser(kim);
		//System.out.println(s);
		
		//Test find User
		User x = dao.findTestDao(kim);
		if(x != null) System.out.println("Found "+x);
		
		
		if(x.equals(kim)) System.out.println("Hello world!!!!!!!");
		
		//Test find All User
		//List<User> people = dao.findAllUser();
		//for (User user : people) {
		//	System.out.println(user);
	 	//}
		
		
	}

}
