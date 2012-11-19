package jacs.database.exceed.controller;

import jacs.database.exceed.dao.CriteriaDAO;
import jacs.database.exceed.dao.DaoFactory;
import jacs.database.exceed.dao.Project_eXceedDAO;
import jacs.database.exceed.dao.UserDAO;
import jacs.database.exceed.model.Criteria;

public class DatabasesControllerTest {

		
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		UserDAO user_dao = DaoFactory.getInstance().getUserDAO();
		Project_eXceedDAO project_dao = DaoFactory.getInstance().getProject_eXceedDAO();
		CriteriaDAO criteria_dao = DaoFactory.getInstance().getCriteriaDAO();
		
		DatabasesController controller_db = new DatabasesController();
		System.out.println(controller_db.init());
		//VOTE: criteria_id1,project_name1#POINT:project_id1,point1:project_id2,point2#username
		
		String s1 = "VOTE:1,Assasin creed:2,JACS:3,Call of Duty 4:4,Assasin creed:5,FULL MOON:6,JACS#POINT:1,0:2,3:3,3:4,2:5,3:6,3#kimapiwat";
		System.out.println(controller_db.recieveMsg(s1));
	}

}
