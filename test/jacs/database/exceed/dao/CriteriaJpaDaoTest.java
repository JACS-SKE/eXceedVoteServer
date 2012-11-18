package jacs.database.exceed.dao;

import jacs.database.exceed.model.Criteria;
import jacs.database.exceed.model.User;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class CriteriaJpaDaoTest {
	
	
	public static final String ADD = "ac";
	public static final String FIND = "f";
	public static final String REMOVE = "rc";
	public static final String FIND_ALL = "fa";
	public static CriteriaDAO cri_dao;
	/**
	 * @param args
	 */
	public static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		cri_dao = DaoFactory.getInstance().getCriteriaDAO();
		while (true) {
			System.out
					.print("Add Criteria(ac) / Find Criteria(f) / Remove Criteria(rc) / Find All Criteria(fa) / clearDB(clear) : ");
			String mode = sc.nextLine();

			if (mode.equalsIgnoreCase(ADD)) {
				System.out.print("Add Criteria : ");
				String name = sc.nextLine();
				System.out.println(cri_dao.saveCriteria(name));
			} else if (mode.equalsIgnoreCase(FIND)) {
				System.out.print("Find Criteria : ");
				String name = sc.nextLine();
				System.out.println(cri_dao.saveCriteria(name));
			} else if (mode.equalsIgnoreCase(REMOVE)) {
				System.out.print("Remove Criteria : ");
				String name = sc.nextLine();
				System.out.println(cri_dao.deleteCriteria(name));
			} else if (mode.equalsIgnoreCase(FIND_ALL)) {
				List<Criteria> result = cri_dao.findAllCriteria();
				//System.out.println(result.size());
				for(int i = 0;i<result.size(); i++) {
					System.out.println(result.get(i));
				}
			}else if ( mode.equalsIgnoreCase("clear"))	{
				List<Criteria> result = cri_dao.findAllCriteria();
				Iterator<Criteria> it = result.iterator();
				while(it.hasNext())	{
					Criteria c = it.next();
					System.out.println(cri_dao.deleteCriteria(c.getName()));
				}
			} else
				break;
		}
	}

}
