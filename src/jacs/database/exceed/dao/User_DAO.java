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
	static {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		em = emf.createEntityManager();
	}
	private String saveUser(User user) {
		System.out.printf("Saving username : %s\n", user.getUsername());
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(user);
			tx.commit();
			System.out.printf("User saved. id = %d : Username = %s\n",user.getId(),user.getUsername());
			System.out.printf("Save User Complete !!! \n");
			return "REGIS_SUCCES" + ","+ user.getUsername() +","+user.getType();
		} catch (Exception ex) {
			System.out.println("Exception occurred!");
			//System.out.println( ex.getMessage() );
			// undo the transaction
			if (tx.isActive()) tx.rollback();
		}
		return "REGIS_FAILED"+","+user.getUsername()+","+user.getType();
	}
	private String findUserForLogin(String username,String password) {
		Query query =
			em.createQuery("SELECT user from User user WHERE user.username = :username and user.password = :password");
		query.setParameter("username", username);
		query.setParameter("password", password);
		List<User> result = query.getResultList();
		System.out.printf("----------\nFound %d match\nUsername : %s\nPassword : %s \n",result.size(), username,password);
		for(User user : result) System.out.println( user ); 
		System.out.println("----------");
		if(result.size() == 0) return "LOGIN_FAILED,"+username+","+password;
		else return "LOGIN_SUCCESS,"+result.get(0).getUsername()+","+result.get(0).getType();
	}
	private boolean findUserForRegistration(User user)	{
		Query query =
				em.createQuery("SELECT user from User user WHERE user.username = :username and user.password = :password");
			query.setParameter("username", user.getUsername());
			query.setParameter("password", user.getPassword());
			List<User> result = query.getResultList();
			System.out.printf("----------\nFound %d match\nUsername : %s\nPassword : %s \n",result.size(),user.getUsername(),user.getPassword());
			for(User user_match : result) System.out.println(user_match); 
			System.out.println("----------");
			if(result.size() == 0) return true;
			return false;
	}
	
	public String regisUser(String username, String password)	{
		System.out.println("Registration");
		System.out.printf("Username : %s\n",username);
		System.out.printf("Password : %s\n", password);
		User user = new User(username, password, "Guest");
		if(findUserForRegistration(user))	{
			String msg = saveUser(user);
			return msg;
		}
		return "REGIS_FAILED : "+user.getUsername()+" Already used.";
	}
	public String loginUser(String username, String password)	{
		String massage_Login = findUserForLogin(username,password);
		return massage_Login;
	}
	

}
