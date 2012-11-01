package jacs.database.exceed.controller;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatabasesController controller_db = new DatabasesController();
		String msg = controller_db.recieveMsg("REGIS,kimapiwat,1234");
		System.out.println(msg);
	}

}
