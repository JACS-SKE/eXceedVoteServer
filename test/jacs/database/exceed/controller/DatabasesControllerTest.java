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
		Project_eXceedDAO p_dao = DaoFactory.getInstance().getProject_eXceedDAO();
		CriteriaDAO cri_dao = DaoFactory.getInstance().getCriteriaDAO();
		
		/*		
 		user_dao.regisUser("kimapiwat","1234");
		p_dao.saveProject("Hook Hook");
		p_dao.saveProject("Crysis");
		p_dao.saveProject("Durian");
		
		cri_dao.saveCriteria("Best Design");
		cri_dao.saveCriteria("Best Game");
		cri_dao.saveCriteria("Best Coding");
		cri_dao.saveCriteria("Best of All");
		*/
		
		DatabasesController controller_db = new DatabasesController();
		System.out.println(controller_db.init());
		//VOTE: criteria_id1,project_name1#POINT:project_id1,point1:project_id2,point2#username
		String s1 = "VOTE:6,Crysis#POINT:3,10:2,100#kimapiwat";
		System.out.println(controller_db.recieveMsg(s1));
	}

}
