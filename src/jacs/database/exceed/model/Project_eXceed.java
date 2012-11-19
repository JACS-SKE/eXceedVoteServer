package jacs.database.exceed.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="ProjectList")
public class Project_eXceed implements Serializable{
	
	private static final long serialVersionUID = 1L; 
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String project_Name;
	private int score;
	@OneToMany(mappedBy="project",cascade=CascadeType.ALL)
	private List<Ballot> ballot_box;
	
	public Project_eXceed()	{
		
	}
	public Project_eXceed(String project_Name) {
		super();
		this.project_Name = project_Name;
		this.score = 0;
		ballot_box = new ArrayList<Ballot>();
	}

	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public void addBallot(Ballot ballot)	{
		ballot.setProject(this);
		ballot_box.add(ballot);
	}
	public List<Ballot> getBallots()	{
		return ballot_box;
	}
	public int getProject_ID() {
		return id;
	}

	private void setProject_ID(int id) {
		this.id = id;
	}

	public String getProject_Name() {
		return project_Name;
	}

	public void setProject_Name(String project_Name) {
		this.project_Name = project_Name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result
				+ ((project_Name == null) ? 0 : project_Name.hashCode());
		result = prime * result + score;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Project_eXceed))
			return false;
		Project_eXceed other = (Project_eXceed) obj;
		if (id != other.id)
			return false;
		if (project_Name == null) {
			if (other.project_Name != null)
				return false;
		} else if (!project_Name.equals(other.project_Name))
			return false;
		if (score != other.score)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Project_eXceed [id=" + id + ", project_Name=" + project_Name
				+ ", score=" + score + "]";
	}


	
}
