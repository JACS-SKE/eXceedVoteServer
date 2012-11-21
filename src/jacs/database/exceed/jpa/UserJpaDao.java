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
	/**
	 * This is the Persistence Unit Name in ther persistence.xml file.
	 */
	private static final String PERSISTENCE_UNIT = "exceedvote";
	/** 
	 * EntityManagerFactory is how you access JPA services.
	 */
	private static EntityManagerFactory emf;
	/**
	 * A scanner for getting input from the console.
	 */
	private static EntityManager em;
	// create the entity manager factory only one time.
	static {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		em = emf.createEntityManager();
	}
	
	/**
	 * Registration User.
	 * @param username : name of user that each user want to regis. 
	 * @param password : password of user that each user want to regis.
	 * @return message confirm registration success or not.
	 */
	public  String regisUser(String username,String password) {
		System.out.printf("Saving username : %s\n", username);
		EntityTransaction tx = em.getTransaction();
		try {
			if(!checkMatchUser(findUserByName(username)))	{
				User user = new User(username, password, "student");
				tx.begin();
				em.persist(user);
				tx.commit();
				System.out.printf("User saved. id = %d : Username = %s\n",user.getId(),user.getUsername());
				System.out.printf("Save User Complete !!! \n");
				return "REGIS_SUCCESS" + ","+ user.getUsername() +","+user.getType();
			}
		} catch (Exception ex) {
			System.out.println("Exception occurred!");
			if (tx.isActive()) tx.rollback();
		}
		return "REGIS_FAILED : "+username+" - Already used.";
	}
	/**
	 * Delete User.
	 * @param username : name of user that each user be delete.
	 * @param password : password of user that each user delete.
	 * @return message confirm delete user.
	 */
	public String deleteUser(String username,String password)	{
		EntityTransaction tx = em.getTransaction();
		try	{
			if(checkMatchUser(findUserByName(username)))	{
				User user = findUserByName(username);
				tx.begin();
				em.remove(user);
				tx.commit();
				System.out.printf("Remove. id = %d : Username = %s\n", user.getId(),user.getUsername());
				return "REMOVE_SUCCESS";
			}
		}catch(Exception ex)	{
			System.out.println("Exception occurred!");
			if(tx.isActive()) tx.rollback();
		}
		return "REMOVE_FAILED";
	}
	/**
	 * Login User .
	 * @param username : name of user when user login.
	 * @param password : password of user.
	 * @return message confirm login.
	 */
	public  String loginUser(String username,String password) {
		User user = findUserByNameAndPassword(username, password);
		String msg = "";
		if(user == null) return "LOGIN_FAILED";
		if(!checkMatchUser(user)) return "LOGIN_FAILED";
		else{
			if(user.getType().equalsIgnoreCase("committee"))	{
				msg = "LOGIN_SUCCESS,"+user.getUsername()+","+user.getType()+",10";
			}else	{
				msg = "LOGIN_SUCCESS,"+user.getUsername()+","+user.getType()+",5";
			}
		}
		return msg;
	}
	/**
	 * Change Password 's User.
	 * @param user : User
	 * @param new_password : new password that user want to change.
	 * @return message confirm for change password.
	 */
	public String changePassword(User user,String new_password)	{
		System.out.printf("change password user : %s\nOld password : %s\nNew password : %s\n",user.getUsername(),user.getPassword(),new_password);
		EntityTransaction tx = em.getTransaction();
		String msg = "null";
		try{
			tx.begin();
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
	/**
	 * Check match user in database.
	 * @param user 
	 * @return true if have user in database. otherwise false.
	 */
	public boolean checkMatchUser(User user)	{
		if( user == null)	return false;
		return true;
	}
	/**
	 * Find User in database by name.
	 * @param username : name of user.
	 * @return User.
	 */
	public User findUserByName(String username)	{
		Query query =
				em.createQuery("SELECT user from User user WHERE user.username = :username");
		query.setParameter("username", username);
		List<User> result = query.getResultList();
		if(!(result.size() == 0))	return result.get(0);
		return null;
	}
	/**
	 * Find User in database by name and password.
	 * @param username : name of user.
	 * @param password : password of user.
	 * @return User.
	 */
	public User findUserByNameAndPassword(String username,String password)	{
		Query q = 
			em.createQuery("SELECT user from User user WHERE user.username = :username and user.password =:password");
		q.setParameter("username",username);
		q.setParameter("password", password);
		List<User> result = q.getResultList();
		if(!(result.size() ==0))	return result.get(0);
		return null;
	}
	
	/**
	 * Find all of User in database.
	 * @return List of User.
	 */
	public List<User> findAllUser()	{
		Query query = em.createQuery("SELECT user from User user ORDER by user.type");
		List<User> result = query.getResultList();
		return result;
	}
	

}
