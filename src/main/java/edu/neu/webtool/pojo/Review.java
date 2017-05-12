package edu.neu.webtool.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;



@Entity
@Table(name="review_table")
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="reviewID", unique = true, nullable = false)
	private long reviewID;
	
	@ManyToOne
	@JoinColumn(name = "personID")
	private User postByUser;
	
	@Transient
	int postedBy;
	
	@Transient
	int onMovie;
	
	@ManyToOne
	@JoinColumn(name = "movieID")
	private Movie movie;
	
	@Column(name="star")
	private int star;
	
	@Column(name="comment")
	private String comment;
	
	public Review() {
	}
	
	public Review(User postByUser, Movie movie, int star, String comment) {
		this.postByUser = postByUser;
		this.movie = movie;
		this.star = star;
		this.comment = comment;
	}



	public long getReviewID() {
		return reviewID;
	}



	public void setReviewID(long reviewID) {
		this.reviewID = reviewID;
	}

	public User getPostByUser() {
		return postByUser;
	}

	public void setPostByUser(User postByUser) {
		this.postByUser = postByUser;
	}

	
	
	public Movie getMovie() {
		return movie;
	}

	

	public int getOnMovie() {
		return onMovie;
	}

	public void setOnMovie(int onMovie) {
		this.onMovie = onMovie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}



	public int getStar() {
		return star;
	}



	public void setStar(int star) {
		this.star = star;
	}



	public String getComment() {
		return comment;
	}



	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(int postedBy) {
		this.postedBy = postedBy;
	}


	
}
