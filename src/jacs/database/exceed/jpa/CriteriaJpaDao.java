package jacs.database.exceed.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import jacs.database.exceed.dao.CriteriaDAO;
import jacs.database.exceed.model.Criteria;

/**
 * Using DAO to manager information about Criteria in Database.
 * @author GoDParTicle
 *
 */
public class CriteriaJpaDao implements CriteriaDAO {
	/**
	 * This is the Persstence Unit Name in the persistence.xml file.
	 * The name here must EXACTLY MATCH the name in persistence.xml
	 */
	private static final String PERSISTENCE_UNIT = "exceedvote";
	/**
	 * EntityManagerFactory is how you access JPA services.
	 */
	private static EntityManagerFactory emf;
	/**
	 * EntityManager doesthe actual work.
	 */
	private static EntityManager em;
	//create the entity manager factory only one time.
	static {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		em = emf.createEntityManager();
	}
	/**
	 * Find the criteria by criteria's name.
	 * @param name : name of criteria.
	 * @return object of criteria.
	 */
	@Override
	public Criteria findCriteriaByName(String name) {
		// TODO Auto-generated method stub
		Query q = em.createQuery("SELECT c FROM Criteria c WHERE c.name LIKE :name");
		q.setParameter("name","%"+name+"%");
		List<Criteria> result = q.getResultList();
		System.out.printf("----------\nFound %d matches\nCriteria Name : %s\n",result.size(),name);
		
		if(!(result.size()==0)) return result.get(0);
		else return null;
	}
	/**
	 * Check match Criteria in database has or not have.
	 * @param criteria : object of criteria.
	 * @return  true : if database has criteria. Otherwise is null.
	 */
	@Override
	public boolean checkMatchCriteria(Criteria criteria) {
		// TODO Auto-generated method stub
		if(criteria == null)	return false;
		return true;
	}
	/**
	 * Save Criteria in database 
	 * @param name : name of criteria that want to save in database.
	 * @return message when can save or not.
	 */
	@Override
	public String saveCriteria(String name) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try {
			if(!checkMatchCriteria(findCriteriaByName(name)))	{
				tx.begin();
				Criteria c = new Criteria(name);
				em.persist(c);
				tx.commit();
				return "ADD_CRITERIA_SUCCESSED : "+c.getName();
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Exception occurred!");
			if(tx.isActive()) tx.rollback();
		}
		return "ADD_CRITERIA_FAILED";
	}
	/**
	 * Delete Criteria in database.
	 * @param name : name of criteria that want to delete.
	 * @return message when can delete or not.
	 */
	@Override
	public String deleteCriteria(String name) {
		// TODO Auto-generated method stub
		EntityTransaction tx = em.getTransaction();
		try	{
			if(checkMatchCriteria(findCriteriaByName(name)))	{
				Criteria c = findCriteriaByName(name);
				tx.begin();
				em.remove(c);
				tx.commit();
				System.out.printf("Remove. id = %d : Criteria = %s\n", c.getId(),c.getName());
				return "REMOVE_SUCCESS";
			}
		}catch(Exception ex)	{
			System.err.println("Exception occurred!");
			if(tx.isActive()) tx.rollback();
		}
		return "REMOVE_FAILED";
	}
	/**
	 * Find All the criteria in database.
	 * @return List of Criteria in database.
	 */
	@Override
	public List<Criteria> findAllCriteria() {
		
		Query q = em.createQuery("SELECT c FROM Criteria c");
		List<Criteria> result = q.getResultList();
		System.out.printf("found %d matches\n",result.size());
		return result;
	}
	/**
	 * Find the criteria by criteria's id.
	 * @param id : id of criteria.
	 * @return object of criteria.
	 */
	@Override
	public Criteria findCriteriaByID(int id) {
		// TODO Auto-generated method stub
		Query q = em.createQuery("SELECT c FROM Criteria c WHERE c.id =:id");
		q.setParameter("id",id);
		List<Criteria> result = q.getResultList();
		System.out.printf("----------\nFound %d matches\nCriteria ID : %d\n",result.size(),id);	
		if(!(result.size()==0)) return result.get(0);
		else return null;
	}
	

}
