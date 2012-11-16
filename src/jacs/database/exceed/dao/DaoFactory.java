package jacs.database.exceed.dao;

import jacs.database.exceed.jpa.JpaDaoFactory;
import jacs.database.exceed.jpa.UserJpaDao;
import jacs.util.PropertyManager;

public abstract class DaoFactory {
	private static DaoFactory factory;
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
	public abstract UserDAO getUserDAO();
	public abstract BallotDAO getBallotDAO();
}
