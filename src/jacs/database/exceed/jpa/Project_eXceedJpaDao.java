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


public class Project_eXceedJpaDao implements Project_eXceedDAO {

private static final String PERSISTENCE_UNIT = "exceedvote";
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	static {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		em = emf.createEntityManager();
	}
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
	@Override
	public List<Project_eXceed> findAllProjects() {
		Query query = em.createQuery("SELECT project from Project_eXceed project");
		List<Project_eXceed> result = query.getResultList();
		// TODO Auto-generated method stub
		if(!(result.size()==0))	return result;
		return null;
	}
	@Override
	public boolean checkMatchProject(Project_eXceed project) {
		// TODO Auto-generated method stub
		if(!(project == null))	return true;
		return false;
	}
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
