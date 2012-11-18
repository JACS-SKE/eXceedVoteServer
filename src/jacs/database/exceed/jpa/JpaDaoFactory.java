package jacs.database.exceed.jpa;

import jacs.database.exceed.dao.BallotDAO;
import jacs.database.exceed.dao.CriteriaDAO;
import jacs.database.exceed.dao.DaoFactory;
import jacs.database.exceed.dao.Project_eXceedDAO;
import jacs.database.exceed.dao.UserDAO;


public class JpaDaoFactory extends DaoFactory{
	protected UserDAO userJpaDao;
	protected CriteriaDAO criteriaJpaDao;
	protected Project_eXceedDAO projectJpaDao;
	public JpaDaoFactory()	{
	}
	@Override
	public UserDAO getUserDAO() {
		if(userJpaDao == null)	{
			userJpaDao = new UserJpaDao();
		}
		return userJpaDao;
	}
	@Override
	public BallotDAO getBallotDAO() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CriteriaDAO getCriteriaDAO() {
		// TODO Auto-generated method stub
		if(criteriaJpaDao==null)	{
			criteriaJpaDao = new CriteriaJpaDao();
		}
		return criteriaJpaDao;
	}
	@Override
	public Project_eXceedDAO getProject_eXceedDAO() {
		// TODO Auto-generated method stub
		if(projectJpaDao == null)	{
			projectJpaDao = new Project_eXceedJpaDao();
		}
		return projectJpaDao;
	}
	

}
