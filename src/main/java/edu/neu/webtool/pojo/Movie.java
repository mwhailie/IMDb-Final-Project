package edu.neu.webtool.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="movie_table")
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="movieID", unique = true, nullable = false)
	private long movieID;
	
	@Column(name="title")
	private String title;
	
	@Column(name="year")
    private int year;
	
	@ManyToMany(mappedBy="movies")
	private Set<Genre> genres = new HashSet<Genre>();
	
//    private Actor actor;
//    private Director director;


	
    public Movie(String title, int year, Genre genre) {
        this.title = title;
        this.year = year;
        //this.genres.add(genre);
    }
    
//    public Movie( String title, Actor actor, Director director, Genre genre, int year) {
//    	this.title = title;
//    	this.actor = actor;
//    	this.director = director;
//    	this.genre = genre;
//    	this.year = year;    
//    }
    
    public Movie() {
    }

	public long getMovieID() {
		return movieID;
	}

	public void setMovieID(long movieID) {
		this.movieID = movieID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Set<Genre> getGenres() {
		return genres;
	}

	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}

}
