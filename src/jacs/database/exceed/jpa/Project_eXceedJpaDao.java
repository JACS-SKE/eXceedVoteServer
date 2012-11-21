package jacs.database.exceed.jpa;

import jacs.database.exceed.dao.Project_eXceedDAO;
import jacs.database.exceed.model.Ballot;
import jacs.database.exceed.model.Project_eXceed;
import jacs.database.exceed.model.User;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Using JPA Dao to manager information of Project in database.
 * @author Apiwat Srisirisitthikul 5410546385
 *
 */
public class Project_eXceedJpaDao implements Project_eXceedDAO {
	/**
	 * This is the Persistence Unit Name in persistence.xml file.
	 */
	private static final String PERSISTENCE_UNIT = "exceedvote";
	/**
	 * EntityManagerFactory is how you access JPA services.
	 */
	private static EntityManagerFactory emf;
	/**
	 * EntityManager does the actual work.
	 */
	private static EntityManager em;
	//create the entity manager factory only one time.
	static {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		em = emf.createEntityManager();
	}
	/**
	 * Find Project in Database by name.
	 * @param name : name of Project.
	 * @return Object of Project_eXceed.
	 */
	@Override
	public Project_eXceed findProjectByName(String name) {
		// TODO Auto-generated method stub
		Query query =
				em.createQuery("SELECT project from Project_eXceed project WHERE project.project_Name =:name");
		query.setParameter("name", name);
		List<Project_eXceed> result = query.getResultList();
		System.out.printf("----------\nFound %d match\nProject_Name : %s\n",result.size(),name);
		if(!(result.size()==0))	return result.get(0);
		return null;
	}
	/**
	 * Find All of Project in Database.
	 * @return List of Project.
	 */
	@Override
	public List<Project_eXceed> findAllProjects() {
		Query query = em.createQuery("SELECT project from Project_eXceed project");
		List<Project_eXceed> result = query.getResultList();
		// TODO Auto-generated method stub
		if(!(result.size()==0))	return result;
		return null;
	}
	/**
	 * Check : In the Databse have project or not.
	 * @param project that you want to check.
	 * @return  true if match. otherwise false.
	 */
	@Override
	public boolean checkMatchProject(Project_eXceed project) {
		// TODO Auto-generated method stub
		if(!(project == null))	return true;
		return false;
	}
	/**
	 * Save Project into Database.
	 * @param name of Project that you want to save.
	 * @return message for confirm that saved.
	 */
	@Override
	public String saveProject(String name) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			if(!checkMatchProject(findProjectByName(name)))	{
				tx.begin();
				Project_eXceed p = new Project_eXceed(name);
				em.persist(p);
				tx.commit();
				return "SAVE_PROJECT_SUCCESS";
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Exception occurred!");
			if(tx.isActive()) tx.rollback();
		}
		return "SAVE_PROJECT_FAILED";
	}
	/** 
	 * Delete Project in Database.
	 * @param name of Project that you want to delete.
	 * @return message for confirm that deleted.
	 */
	@Override
	public String deleteProject(String name) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			if(checkMatchProject(findProjectByName(name))){
				tx.begin();
				Project_eXceed p = findProjectByName(name);
				em.remove(p);
				tx.commit();
				return "REMOVE_PROJECT_SUCCESS";
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Exception occurred!");
			if(tx.isActive()) tx.rollback();
		}
		return null;
	}
	/** 
	 * Find Project in Database by id.
	 * @param id : id of Project.
	 * @return Object of Project_eXceed.
	 */
	@Override
	public Project_eXceed findProjectByID(int id) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("SELECT p FROM Project_eXceed p WHERE p.id =:id");
		query.setParameter("id",id);
		List<Project_eXceed> result = query.getResultList();
		System.out.printf("----------\nFound %d match\nProject Id : %d\n",result.size(),id);
			
		if( !(result.size()==0)) return result.get(0);
		return null;
	}
	/**
	 * Update Ballot into database went Voter,Committee or User give Ballot to each Project.
	 * @param project : each Project that Voter give ballot .
	 * @param ballot : Ballot contains name of Criteria that vote by Voter.
	 * @return message for confirm  Vote for Ballot.
	 */
	@Override
	public String updateBallot(Project_eXceed project,Ballot ballot) {
		// TODO Auto-generated method stub
		//System.out.printf("change password user : %s\nOld password : %s\nNew password : %s\n",user.getUsername(),user.getPassword(),new_password);
		EntityTransaction tx = em.getTransaction();
		String msg = "null";
		try{
			tx.begin();
			Project_eXceed p = em.find(Project_eXceed.class,(int)project.getProject_ID());
			p.addBallot(ballot);
			em.merge(p);
			tx.commit();
			msg = "UPDATE_BALLOT_SUCCESS";
		}catch(Exception ex)	{
			System.err.println("Exception occured!");
			if(tx.isActive()) tx.rollback();
		}finally	{
			//em.close();
		}
		
		if(msg.equalsIgnoreCase("null"))
			return "UPDATE_BALLOT_FAILED";
		else 
			return msg;
	}
	/**
	 * Update Score into database went Voter give score to each Project.
	 * @param project : each Project that Voter give Score.
	 * @param score : score that gave by Boter.
	 * @return message for confirm Vote for score.
	 */
	@Override
	public String updateScore(Project_eXceed project, int score) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		String msg = "null";
		try{
			tx.begin();
			Project_eXceed p = em.find(Project_eXceed.class,(int)project.getProject_ID());
			p.setScore(p.getScore()+score);
			em.merge(p);
			tx.commit();
			msg = "UPDATE_SCORE_SUCCESS";
		}catch(Exception ex)	{
			System.err.println("Exception occured!");
			if(tx.isActive()) tx.rollback();
		}finally	{
			//em.close();
		}
		if(msg.equalsIgnoreCase("null"))
			return "UPDATE_SCORE_FAILED";
		else 
			return msg;
	}
	
	
}
