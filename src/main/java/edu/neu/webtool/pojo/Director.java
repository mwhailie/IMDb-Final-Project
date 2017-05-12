package edu.neu.webtool.pojo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "director_table")
@PrimaryKeyJoinColumn(name = "personID")
public class Director extends Person{
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "director")
	private Set<Movie> movies;

	public Set<Movie> getMovieNames() {
		return movies;
	}

	public void setMovieNames(Set<Movie> movies) {
		this.movies = movies;
	}

	
}
