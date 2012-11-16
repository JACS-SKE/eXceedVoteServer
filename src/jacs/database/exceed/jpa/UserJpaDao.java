package jacs.database.exceed.jpa;

import jacs.database.exceed.dao.UserDAO;
import jacs.database.exceed.model.User;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.Query;

/**
 * @author GoDParTicle
 *
 */
public class UserJpaDao implements UserDAO{
	private static final String PERSISTENCE_UNIT = "exceedvote";
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	static {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		em = emf.createEntityManager();
	}
	
	public  String regisUser(User user) {
		System.out.printf("Saving username : %s\n", user.getUsername());
		EntityTransaction tx = em.getTransaction();
		try {
			//if(!checkMatchUser(user))	{
				tx.begin();
				em.persist(user);
				tx.commit();
				System.out.printf("User saved. id = %d : Username = %s\n",user.getId(),user.getUsername());
				System.out.printf("Save User Complete !!! \n");
				return "REGIS_SUCCES" + ","+ user.getUsername() +","+user.getType();
			//}
		} catch (Exception ex) {
			System.out.println("Exception occurred!");
			if (tx.isActive()) tx.rollback();
		}
		return "REGIS_FAILED"+","+user.getUsername()+","+user.getType();
	}
	
	public String changePassword(User user,String new_password)	{
		System.out.printf("change password user : %s\nOld password : %s\nNew password : %s\n",user.getUsername(),user.getPassword(),new_password);
		EntityTransaction tx = em.getTransaction();
		PersistenceUnitUtil util = emf.getPersistenceUnitUtil();
		String msg = "null";
		try{
			tx.begin();
			//User u = (User)findUser(user);
			System.out.println(user.getId());
			User u = em.find(User.class,(int)user.getId());
			u.setPassword(new_password);
			em.merge(u);
			tx.commit();
			msg = "CHANGE_PASSWORD_SUCCESSED,"+u.getUsername()+","+u.getPassword();
		}catch(Exception ex)	{
			System.err.println("Exception occured!");
			if(tx.isActive()) tx.rollback();
		}finally	{
			em.close();
		}
		
		if(msg.equalsIgnoreCase("null"))
			return "CHANGE_PASSWORD_FAILED,"+user.getUsername()+","+user.getPassword();
		else 
			return msg;
	}
	
	public  String login(User user) {

		if(!checkMatchUser(user)) return "LOGIN_FAILED,"+user.getUsername()+","+user.getPassword();
		else return "LOGIN_SUCCESS,"+user.getUsername()+","+user.getType();
	}
	
	
	public boolean checkMatchUser(User user)	{
		List<User> result = findUser(user);
		System.out.printf("----------\nFound %d match\nUsername : %s\nPassword : %s \n",result.size(),user.getUsername(),user.getPassword());	
		if(result.size() == 1) return true;
		else return false;
	}
	
	
	
	public List<User> findUser(User user)	{
		Query query =
				em.createQuery("SELECT user from User user WHERE user.username = :username and user.password = :password");
		query.setParameter("username", user.getUsername());
		query.setParameter("password", user.getPassword());
		List<User> result = query.getResultList();
		return result;
	}
	public User findTestDao(User user)	{
		Query query =
				em.createQuery("SELECT user from User user WHERE user.username = :username and user.password = :password");
		query.setParameter("username", user.getUsername());
		query.setParameter("password", user.getPassword());
		User x = (User) query.getSingleResult();
		return x;
	}
	
	public List<User> findAllUser()	{
		Query query = em.createQuery("SELECT user from User user ORDER by user.type");
		List<User> result = query.getResultList();
		return result;
	}
	

}
