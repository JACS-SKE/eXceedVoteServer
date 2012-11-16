package jacs.database.exceed.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Ballot
 *
 */
@Entity
@Table(name="Ballots")
public class Ballot implements Serializable {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	private int project_id;
	private String criteria;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	
	private static final long serialVersionUID = 1L;

	public Ballot() {
		super();
	}
	
	public Ballot(String criteria)	{
		this.criteria  = criteria;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((criteria == null) ? 0 : criteria.hashCode());
		result = prime * result + id;
		result = prime * result + project_id;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Ballot))
			return false;
		Ballot other = (Ballot) obj;
		if (criteria == null) {
			if (other.criteria != null)
				return false;
		} else if (!criteria.equals(other.criteria))
			return false;
		if (id != other.id)
			return false;
		if (project_id != other.project_id)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Ballot [id=" + id + ", project_id=" + project_id
				+ ", criteria=" + criteria +": Vote by"+ user.getUsername() + "]";
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

	public String getCriteria() {
		return criteria;
	}

	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}
}
