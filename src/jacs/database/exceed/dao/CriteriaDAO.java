package jacs.database.exceed.dao;

import java.util.List;

import jacs.database.exceed.model.Criteria;

public interface CriteriaDAO {
	/**
	 * Find the criteria by criteria's name.
	 * @param name : name of criteria.
	 * @return object of criteria.
	 */
	public Criteria findCriteriaByName(String name);
	/**
	 * Find the criteria by criteria's id.
	 * @param id : id of criteria.
	 * @return object of criteria.
	 */
	public Criteria findCriteriaByID(int id);
	/**
	 * Find All the criteria in database.
	 * @return List of Criteria in database.
	 */
	public List<Criteria> findAllCriteria();
	/**
	 * Check match Criteria in database has or not have.
	 * @param criteria : object of criteria.
	 * @return  true : if database has criteria. Otherwise is null.
	 */
	public boolean checkMatchCriteria(Criteria criteria);
	/**
	 * Save Criteria in database 
	 * @param name : name of criteria that want to save in database.
	 * @return message when can save or not.
	 */
	public String saveCriteria(String name);
	/**
	 * Delete Criteria in database.
	 * @param name : name of criteria that want to delete.
	 * @return message when can delete or not.
	 */
	public String deleteCriteria(String name);
}
