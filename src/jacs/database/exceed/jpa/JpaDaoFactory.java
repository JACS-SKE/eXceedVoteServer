package jacs.database.exceed.jpa;

import jacs.database.exceed.dao.DaoFactory;
import jacs.database.exceed.dao.UserDAO;


public class JpaDaoFactory extends DaoFactory{
	protected UserDAO userJpaDao;
	public JpaDaoFactory()	{
	}
	@Override
	public UserDAO getUserDAO() {
		if(userJpaDao == null)	{
			userJpaDao = new User_JPA();
		}
		return userJpaDao;
	}
	

}
