package de.hatoma.exman.model.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.hibernate.annotations.NaturalId;

/**
 * The "User" entity
 * @author tobias
 *
 */
@Entity
@Table(name="User")
public class User implements Serializable{
	/** The serial version UID. */
	private static final long serialVersionUID = 1L;
	private long id;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	/* BEGIN OF CLASS */
	
	
	private String username;
	private String fullname;
	
	@NaturalId
	@Column(name = "USERNAME", nullable = false)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name="FULLNAME", nullable=false)
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

}
