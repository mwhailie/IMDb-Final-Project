package edu.neu.webtool.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="genre_table")
public class Genre {
		@Id 
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="genreID", unique = true, nullable = false)
	    private long genreId;
		
		@Column(name="title", unique=true, nullable = false)
	    private String title;
	    
		@ManyToMany
	    @JoinTable (
	       name="genre_movie_table",
	       joinColumns = {@JoinColumn(name="genreID", nullable = false, updatable = false)},
	       inverseJoinColumns = {@JoinColumn(name="movieID" )}
	    )
		private Set<Movie> movies = new HashSet<Movie>();

	    public Genre(String title) {
	        this.title = title;
	    }

	    public Genre() {
	    }
	    


		public long getGenreId() {
			return genreId;
		}

		public void setGenreId(long genreId) {
			this.genreId = genreId;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public Set<Movie> getMovies() {
			return movies;
		}

		public void setMovies(Set<Movie> movies) {
			this.movies = movies;
		}

		@Override 
		public String toString(){
			return title;
		}
}
