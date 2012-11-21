package jacs.database.exceed.model;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Criteria
 * Criteria is an entity (meaning that it can be saved to a dabase
 * or other Persistent storage).
 * @author Apiwat Srisirisitthikul 5410546385
 */
@Entity
public class Criteria implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	/**
	 * Criterai's name.
	 */
	private String name;
	private static final long serialVersionUID = 1L;

	public Criteria() {
		this("");
	}   
	public Criteria(String name)	{
		this.name= (name!=null)? name : "";
	}
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Criteria [id=" + id + ", name=" + name + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Criteria))
			return false;
		Criteria other = (Criteria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
   
}
