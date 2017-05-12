package edu.neu.webtool.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
@Table(name = "movie_table")
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movieID", unique = true, nullable = false)
	private long movieID;

	@Column(name = "title")
	private String title;

	@Column(name = "year")
	private int year;

	@Column(name = "director")
	private String director;

	@Column(name = "actor")
	private String actor;

	@Column(name = "actress")
	private String actress;

	@Column(name = "time")
	private int time;
	
	@Transient
	private CommonsMultipartFile poster; // for DataBinder to bind <input
											// type="file".../>
											// will not be mapped for Hibernate
											// as
											// we store the file in the
											// FileSystem
											// file will be placed into this
											// field
											// by DataBinder
											// file is in the memory. needs to
											// be
											// transferred to the FileSystem
											// using
											// java.io.file
	@Column(name = "filename")
	private String filename;

	@ManyToMany(mappedBy = "movies")
	private Set<Genre> genres = new HashSet<Genre>();

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Review> reviews= new HashSet<Review>();
	
	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Schedule> schedules= new HashSet<Schedule>();

	public Movie(Movie m) {
		this.title = m.title;
		this.year = m.year;
		this.actor = m.actor;
		this.actress = m.actress;
		this.director = m.director;
	}

	public Movie(String title, int year, Set<Genre> genres) {
		this.title = title;
		this.year = year;
		// this.genres.add(genre);
	}

	// public Movie( String title, Actor actor, Director director, Genre genre,
	// int year) {
	// this.title = title;
	// this.actor = actor;
	// this.director = director;
	// this.genre = genre;
	// this.year = year;
	// }

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

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getActress() {
		return actress;
	}

	public void setActress(String actress) {
		this.actress = actress;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	// for Hibernate
	public CommonsMultipartFile getPoster() {
		return poster;
	}

	public void setPoster(CommonsMultipartFile poster) {
		this.poster = poster;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Set<Genre> getGenres() {
		return genres;
	}

	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}

	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}
	
	

	public Set<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(Set<Schedule> schedules) {
		this.schedules = schedules;
	}

	@Override
	public String toString() {
		return title;
	}
}
