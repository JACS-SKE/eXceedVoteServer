package jacs.database.exceed.dao;

import jacs.database.exceed.model.Ballot;
import jacs.database.exceed.model.Project_eXceed;
import jacs.database.exceed.model.User;

import java.util.List;

public interface Project_eXceedDAO {
	public Project_eXceed findProjectByName(String name);
	public Project_eXceed findProjectByID(int id);
	public List<Project_eXceed> findAllProjects();
	public boolean checkMatchProject(Project_eXceed project);
	public String saveProject(String name);
	public String deleteProject(String name);
	public String updateBallot(Project_eXceed project,Ballot ballot);
	public String updateScore(Project_eXceed project,int score);
}
