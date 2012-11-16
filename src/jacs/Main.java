package jacs;

import java.util.List;
import java.util.Scanner;

import jacs.database.exceed.dao.BallotDAO;
import jacs.database.exceed.dao.DaoFactory;
import jacs.database.exceed.dao.UserDAO;
import jacs.database.exceed.model.Ballot;
import jacs.database.exceed.model.User;


public class Main {
	public static Scanner sc = new Scanner(System.in);
	public static BallotDAO bdao;
	public static UserDAO dao;
	public static void main(String[] args) {
		//new Server();
		//testUserDao();
		testBallotDao();
	}
	public static void testBallotDao()	{
		bdao= DaoFactory.getInstance().getBallotDAO();
		dao = DaoFactory.getInstance().getUserDAO();
		String msg;
		//registration
		User user = new User(sc.nextLine(),sc.nextLine(),"Student");
		
		Ballot b = new Ballot("Bad Coding");
		
		user.addBallot(b);
		msg = dao.regisUser(user);
		System.out.println(msg);
		System.out.println(b.getUser());
		
		 System.out.println(msg);
		
		
	}
	public static void testUserDao()	{
		
		dao = DaoFactory.getInstance().getUserDAO();
		
		
		
		//Test regis User
		User user = new User("Guysssss fuckkkk","054451652","Admin");
		String s = dao.regisUser(user);
		System.out.println(s);
		
		
		//Test find User
		User x = dao.findTestDao(user);
		if(x != null) System.out.println("Found "+x);
		
		//check same User
		if(x.equals(user)) System.out.println("SAME USER");
		else System.out.println("NOT SAME USER");
		
		//Test find All User
		List<User> people = dao.findAllUser();
		for (User u : people) {
			System.out.println(u);
	 	}
		
		
	}

}
