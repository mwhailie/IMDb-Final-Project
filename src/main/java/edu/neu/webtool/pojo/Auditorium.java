package edu.neu.webtool.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "auditorium_table")
public class Auditorium {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "auditoriumID", unique = true, nullable = false)
	private long auditoriumID;

	@Column(name = "auditoriumName")
	private String auditoriumName;

	@Transient
	int inTheater;
	
	@ManyToOne
	@JoinColumn(name = "theaterID")
	private Theater theater;

	@Column(name = "seat")
	private int seat;

//	@Column(name = "remain")
//	private int remain;

	@OneToMany(mappedBy = "auditorium", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Schedule> movieSchedule =new HashSet<Schedule>() ;

	public long getAuditoriumID() {
		return auditoriumID;
	}

	public void setAuditoriumID(long auditoriumID) {
		this.auditoriumID = auditoriumID;
	}

	public Auditorium() {
	}

	public String getAuditoriumName() {
		return auditoriumName;
	}

	public void setAuditoriumName(String auditoriumName) {
		this.auditoriumName = auditoriumName;
	}

	public Theater getTheater() {
		return theater;
	}

	public void setTheater(Theater theater) {
		this.theater = theater;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}
	
//	public int getRemain() {
//		return remain;
//	}
//
//	public void setRemain(int remain) {
//		this.remain = remain;
//	}

	public Set<Schedule> getMovieSchedule() {
		return movieSchedule;
	}

	public void setMovieSchedule(Set<Schedule> movieSchedule) {
		this.movieSchedule = movieSchedule;
	}
	
	

	public int getInTheater() {
		return inTheater;
	}

	public void setInTheater(int inTheater) {
		this.inTheater = inTheater;
	}

	@Override
	public String toString() {
		return "Auditorium [" + auditoriumName + "]";
	}

}
