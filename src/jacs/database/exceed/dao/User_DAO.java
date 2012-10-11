package jacs.database.exceed.dao;

import jacs.database.exceed.model.User;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class User_DAO {
	private static final String PERSISTENCE_UNIT = "exceedvote";
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static Scanner sc = new Scanner(System.in);
	static {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		em = emf.createEntityManager();
	}
	private static void saveUser(User user) {
		System.out.printf("Saving username : %s\n", user.getUsername());
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(user);
			tx.commit();
			System.out.printf("User saved. id = %d : Username = %s\n",user.getId(),user.getUsername());
			System.out.printf("Save User Complete !!! \n");
		} catch (Exception ex) {
			System.out.println("Exception occurred!");
			//System.out.println( ex.getMessage() );
			// undo the transaction
			if (tx.isActive()) tx.rollback();
		}
	}
	private static String FindUser(String username,String password) {
		Query query =
			em.createQuery("SELECT user from User user WHERE user.username = :username and user.password = :password");
		query.setParameter("username", username);
		query.setParameter("password", password);
		List<User> result = query.getResultList();
		System.out.printf("----------\nFound %d match\nUsername : %s\nPassword : %s \n",result.size(), username,password);
		for(User user : result) System.out.println( user ); 
		System.out.println("----------");
		if(result.size() == 0) return "LOGIN_FAILED";
		else return "LOGIN_SUCCESS,"+result.get(0).getType();
	}
	
	public static void regisUser(String username, String password)	{
		User user = new User(username, password, "Guest");
		saveUser(user);
	}
	public static String loginUser(String username, String password)	{
		String massage_Login = FindUser(username,password);
		return massage_Login;
	}
	

}
