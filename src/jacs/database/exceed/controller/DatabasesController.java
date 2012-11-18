package jacs.database.exceed.controller;

import java.util.List;

import jacs.database.exceed.dao.CriteriaDAO;
import jacs.database.exceed.dao.DaoFactory;
import jacs.database.exceed.dao.Project_eXceedDAO;
import jacs.database.exceed.dao.UserDAO;
import jacs.database.exceed.model.Ballot;
import jacs.database.exceed.model.Criteria;
import jacs.database.exceed.model.Project_eXceed;
import jacs.database.exceed.model.User;


public class DatabasesController {
	private static final String INIT = "INIT";
	private static final String REGIS = "REGIS";
	private static final String LOGIN = "LOGIN";
	private static final String VOTE  = "VOTE";
	private UserDAO user_dao;
	private Project_eXceedDAO project_dao;
	private CriteriaDAO criteria_dao;

	public DatabasesController() {
		super();
		user_dao = DaoFactory.getInstance().getUserDAO();
		project_dao = DaoFactory.getInstance().getProject_eXceedDAO();
		criteria_dao = DaoFactory.getInstance().getCriteriaDAO();
		
	}
	
	public String recieveMsg(String msg)	{
		if(msg.contains(LOGIN) || msg.contains(REGIS))	{
			String[] authentication = msg.split(",");
			if(authentication[0].equalsIgnoreCase(LOGIN))	{
				return authentication(authentication[1], authentication[2]);
			}else	{
				return registration(authentication[1], authentication[2]);
			}
		}else if(msg.contains(VOTE))	{
			//VOTE  : criteria_id1,project_name1
			//#
			//POINT : project_id1,point1  :  project_id2,point2
			//#
			Criteria c;
			Project_eXceed p;
			User user;
			Ballot ballot;
			//username
			String[] temp_msg = msg.split("#");
			String username = temp_msg[2];
			
			//User who vote ---- VOTE  : criteria_id1,project_name1-----------------------------------
			user = user_dao.findUserByName(username);
			// Vote for Ballot ------------------------------------- 
			String[] vote = getVoteDescription(temp_msg[0]);
			c = criteria_dao.findCriteriaByID(Integer.parseInt(vote[0]));
			p = project_dao.findProjectByName(vote[1]);
			ballot = new Ballot(c.getName());
			ballot.setUser(user);
			user.addBallot(ballot);
			System.out.println(project_dao.updateBallot(p, ballot));
			//----------------------------------------------------------
			String[] point = temp_msg[1].split(":");
			// Vote for Point -- POINT : project_id1,point1  :  project_id2,point2 ------------------------------------------
			for(int i =1; i<point.length; i++)	{
				//POINT : project_id1,point1  :  project_id2,point2
				String[] vote_for_point = point[i].split(",");
				System.out.println(vote_for_point[0]);
				System.out.println(vote_for_point[1]);
				System.out.println(
						project_dao.updateScore(
								project_dao.findProjectByID(
										Integer.parseInt(vote_for_point[0])), Integer.parseInt(vote_for_point[1])));
						
			}
			
			
		}
		return "VOTE_COMPELTE";
	}
	public String[] getVoteDescription(String voteForBallot)	{
		//separate VOTE  and  criteria_id1,project_name1
		String[] temp_vote1 = voteForBallot.split(":");
		//separate criteria_id and project_name
		String[] temp_vote2 = temp_vote1[1].split(",");
		return temp_vote2;
	}
	public String init()	{
		
		user_dao.regisUser("kimapiwat","1234");
		project_dao.saveProject("Hook Hook");
		project_dao.saveProject("Crysis");
		project_dao.saveProject("Durian");
		
		criteria_dao.saveCriteria("Best Design");
		criteria_dao.saveCriteria("Best Game");
		criteria_dao.saveCriteria("Best Coding");
		criteria_dao.saveCriteria("Best of All");
		
		
		//INIT : id1,name1 : id2,name2 : id3,name3 
		// #
		//CAT : cat_id1,best coding : cat_id2,best GUI : cat_id3,best of all
		List<Project_eXceed> projects_list = project_dao.findAllProjects();
		List<Criteria> criteria_list = criteria_dao.findAllCriteria();
		if (!(projects_list.size() == 0 && criteria_list.size() == 0)) {
			StringBuilder sb = new StringBuilder("");
			sb.append("INIT");
			for (Project_eXceed p : projects_list) {
				sb.append(":" + p.getProject_ID() + "," + p.getProject_Name());
			}
			sb.append("#");
			sb.append("CRI");
			for (Criteria c : criteria_list) {
				sb.append(":" + c.getId() + "," + c.getName());
			}
			return sb.toString();
		}
		return "INIT_FAILED";
	}
	public String registration(String username, String password)	{	
		return user_dao.regisUser(username, password);
	}
	public String authentication(String username,String password)	{
		return user_dao.loginUser(username,password);
	}
	public String vote()	{
		return null;
	}
}
