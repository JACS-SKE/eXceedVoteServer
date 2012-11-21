package jacs.database.exceed.dao;

import jacs.database.exceed.model.Ballot;
import jacs.database.exceed.model.Project_eXceed;
import jacs.database.exceed.model.User;

import java.util.List;
/**
 * Project_eXceedDAO : Manager information of each Project_eXceedDAO.
 * @author GoDParTicle
 *
 */
public interface Project_eXceedDAO {
	/**
	 * Find Project in Database by name.
	 * @param name : name of Project.
	 * @return Object of Project_eXceed.
	 */
	public Project_eXceed findProjectByName(String name);
	/** 
	 * Find Project in Database by id.
	 * @param id : id of Project.
	 * @return Object of Project_eXceed.
	 */
	public Project_eXceed findProjectByID(int id);
	/**
	 * Find All of Project in Database.
	 * @return List of Project.
	 */
	public List<Project_eXceed> findAllProjects();
	/**
	 * Check : In the Databse have project or not.
	 * @param project that you want to check.
	 * @return  true if match. otherwise false.
	 */
	public boolean checkMatchProject(Project_eXceed project);
	/**
	 * Save Project into Database.
	 * @param name of Project that you want to save.
	 * @return message for confirm that saved.
	 */
	public String saveProject(String name);
	/** 
	 * Delete Project in Database.
	 * @param name of Project that you want to delete.
	 * @return message for confirm that deleted.
	 */
	public String deleteProject(String name);
	/**
	 * Update Ballot into database went Voter,Committee or User give Ballot to each Project.
	 * @param project : each Project that Voter give ballot .
	 * @param ballot : Ballot contains name of Criteria that vote by Voter.
	 * @return message for confirm  Vote for Ballot.
	 */
	public String updateBallot(Project_eXceed project,Ballot ballot);
	/**
	 * Update Score into database went Voter give score to each Project.
	 * @param project : each Project that Voter give Score.
	 * @param score : score that gave by Boter.
	 * @return message for confirm Vote for score.
	 */
	public String updateScore(Project_eXceed project,int score);
}
