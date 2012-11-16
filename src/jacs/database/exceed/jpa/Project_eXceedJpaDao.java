package jacs.database.exceed.jpa;

import jacs.database.exceed.model.Project_eXceed;
import jacs.database.exceed.model.User;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class Project_eXceedJpaDao {

private static final String PERSISTENCE_UNIT = "exceedvote";
	
	private static EntityManagerFactory emf;
	private static EntityManager em;
	static {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		em = emf.createEntityManager();
	}
	private static void saveProject(Project_eXceed project) {
		System.out.printf("Saving username : %s\n", project.getProject_Name());
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(project);
			tx.commit();
			System.out.printf("Project saved. id = %d : Project_name = %s",project.getProject_ID(),project.getProject_Name());
			System.out.printf("Save User Complete !!! \n");
		} catch (Exception ex) {
			System.out.println("Exception occurred!");
			//System.out.println( ex.getMessage() );
			// undo the transaction
			if (tx.isActive()) tx.rollback();
		}
	}
	public static void updateScore(String name)	{
		Project_eXceed project = em.find(Project_eXceed.class,FindProject(name));
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			project.setScores(project.getScores()+1);
			tx.commit();
			System.out.println(project);
		} catch (Exception ex) {
		}
	}
	public static void resetScore()	{
		
	}
	public  static Integer FindProject(String name) {
		Query query =
			em.createQuery("SELECT project from Project_eXceed project WHERE project.project_Name =:name");
		query.setParameter("name", name);
		List<Project_eXceed> result = query.getResultList();
		System.out.printf("----------\nFound %d match\nProject_Name : %s\n",
				result.size(),name);
		for(Project_eXceed project : result) System.out.println( project ); 
		System.out.println("----------");
		
		if(result.size() == 0) return null;
		else return result.get(0).getProject_ID();
	}
	public static void addProject(String name)	{
		Project_eXceed project = new Project_eXceed(name);
		saveProject(project);
	}
	
	
}
