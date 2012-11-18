package jacs.database.exceed.dao;

import java.util.List;

import jacs.database.exceed.model.Criteria;

public interface CriteriaDAO {
	public Criteria findCriteriaByName(String name);
	public Criteria findCriteriaByID(int id);
	public List<Criteria> findAllCriteria();
	public boolean checkMatchCriteria(Criteria criteria);
	public String saveCriteria(String name);
	public String deleteCriteria(String name);
}
