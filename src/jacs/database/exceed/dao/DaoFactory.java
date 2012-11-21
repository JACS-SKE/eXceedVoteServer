package jacs.database.exceed.dao;

import jacs.database.exceed.jpa.JpaDaoFactory;
import jacs.database.exceed.jpa.UserJpaDao;
import jacs.util.PropertyManager;
/**
 * 
 * @author GoDParTicle
 *
 */
public abstract class DaoFactory {
	private static DaoFactory factory;
	/**
	 * Get real Instance Object.
	 * @return Real Object.
	 */
	public static DaoFactory getInstance()	{
		if(factory == null) {
			String classname = PropertyManager.getProperty("persistence.factory");
			if (classname != null) try {
				factory = (DaoFactory) Class.forName(classname).newInstance();
			} catch( Exception e) {
				//TODO log this exception with Log4J
				System.err.println( e );
			}
			// fall back to a default
			if (factory == null) factory = new JpaDaoFactory();
		}
		return factory;
	}
	/** 
	 * Get UserDAO.
	 * @return UserDAO.
	 */
	public abstract UserDAO getUserDAO();
	/**
	 * Get BallotDAO.
	 * @return BallotDAO.
	 */
	public abstract BallotDAO getBallotDAO();
	/**
	 * Get CriteriaDAO.
	 * @return CriteriaDAO.
	 */
	public abstract CriteriaDAO getCriteriaDAO();
	/**
	 * Get Project_eXceedDAO
	 * @return Project_eXceedDAO
	 */
	public abstract Project_eXceedDAO getProject_eXceedDAO();
}
