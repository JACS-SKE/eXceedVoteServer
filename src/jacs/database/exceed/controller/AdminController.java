package jacs.database.exceed.controller;

import java.util.List;

public class AdminController {

	/**
	 * @param args
	 */
	private List<String>	 project_list;
	private List<String> criteria_list;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public boolean addProject(String project_name)	{	
		return project_list.add(project_name);
	}
	public boolean addCriteria (String criteria_name){	
		return criteria_list.add(criteria_name);
	}
	

}
