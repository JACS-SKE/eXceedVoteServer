package jacs.database.exceed.dao;

public class DaoFactoryTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DaoFactory dao = DaoFactory.getInstance();
		System.out.println(dao.getClass().getName());
	}

}
