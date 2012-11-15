package jacs.database.exceed.dao;

import jacs.database.exceed.jpa.JpaDaoFactory;
import jacs.database.exceed.jpa.User_JPA;

public abstract class DaoFactory {
	private static DaoFactory factory;
	public static DaoFactory getInstance()	{
		if(factory == null) {
			factory = new JpaDaoFactory();
		}
		return factory;
	}
	public abstract UserDAO getUserDAO();
}
