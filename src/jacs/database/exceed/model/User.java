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

/**
 * User is an entity (meaning that it can be saved to a database 
 * or other Persistent storage).
 * @author Apiwat Srisirisitthikul 5410546385
 *
 */
@Entity
@Table(name="people")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/**
	 * Name's user.
	 */
	private String username;
	/**
	 * Password's user.
	 */
	private String password;
	/** 
	 * Type's user such as Committer or student.
	 */
	private String type;
	/** User has a collection of hobbies. */
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private List<Ballot> ballot_box;
	
	public User()	{
	
	}
	public User(String username, String password,String type) {
		this.username = username;
		this.password = password;
		this.type = type;
		ballot_box = new ArrayList<Ballot>();
	}
	/**
	 * Add a Ballot that user voteBallot for each Project.
	 * @param ballot the Ballot object to add.
	 */
	public void addBallot(Ballot ballot)	{
		ballot.setUser(this);
		ballot_box.add(ballot);
	}
	public List<Ballot> getBallots()	{
		return ballot_box;
	}
	public Integer getId() {
		return id;
	}
	private void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	private void setType(String type) {
		this.type = type;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username = " + username + ", password = "
				+ password + ", type = " + type + "]";
	}
}
