package jacs.database.exceed.dao;

import jacs.database.exceed.model.Criteria;
import jacs.database.exceed.model.Project_eXceed;
import jacs.database.exceed.model.User;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Project_eXceedJpaDaoTest {

	public static final String ADD = "ap";
	public static final String FIND = "f";
	public static final String REMOVE = "rp";
	public static final String FIND_ALL = "fa";
	public static Project_eXceedDAO p_dao;
	public static Scanner sc = new Scanner(System.in);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		p_dao = DaoFactory.getInstance().getProject_eXceedDAO();
		while (true) {
			System.out
					.print("Add Project(ap) / Find Project(f) / Remove Project(rp) / Find All Project(fa) / clearDB (clear) : ");
			String mode = sc.nextLine();

			if (mode.equalsIgnoreCase(ADD)) {
				System.out.print("Add Project : ");
				String name = sc.nextLine();
				System.out.println(p_dao.saveProject(name));
			} else if (mode.equalsIgnoreCase(FIND)) {
				System.out.print("Find Project : ");
				String name = sc.nextLine();
				System.out.println(p_dao.findProjectByName(name));
			} else if (mode.equalsIgnoreCase(REMOVE)) {
				System.out.print("Remove Project : ");
				String name = sc.nextLine();
				System.out.println(p_dao.deleteProject(name));
			} else if (mode.equalsIgnoreCase(FIND_ALL)) {
				List<Project_eXceed> result = p_dao.findAllProjects();
				//System.out.println(result.size());
				for(int i = 0;i<result.size(); i++) {
					System.out.println(result.get(i));
				}
			} else if ( mode.equalsIgnoreCase("clear"))	{
				List<Project_eXceed> result = p_dao.findAllProjects();
				Iterator<Project_eXceed> it = result.iterator();
				while(it.hasNext())	{
					Project_eXceed p  = it.next();
					System.out.println(p_dao.deleteProject(p.getProject_Name()));
				}
			}else
				break;
		}
	}
	
	
}
