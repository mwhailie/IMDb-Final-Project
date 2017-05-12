package edu.neu.webtool.pojo;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "actor_table")
@PrimaryKeyJoinColumn(name = "personID")
public class Actor {
	
	@Column(name = "gender")
	boolean gender;
	@Column(name = "birthday")
	Date birthday;
	
	private Set<String> movieNames;
	
	public Actor() {
	}

	public Actor(boolean gender, Date birthday) {
		super();
		this.gender = gender;
		this.birthday = birthday;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Set<String> getMovieNames() {
		return movieNames;
	}

	public void setMovieNames(Set<String> movieNames) {
		this.movieNames = movieNames;
	}
	
	
}
