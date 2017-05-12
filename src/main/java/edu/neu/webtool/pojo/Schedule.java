package edu.neu.webtool.pojo;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "schedule_table")
public class Schedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "scheduleID", unique = true, nullable = false)
	private long scheduleID;

	@Transient
	int inAuditorium;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "auditoriumID")
	private Auditorium auditorium;
	
	@Transient
	String onMovie;

	@ManyToOne
	@JoinColumn(name = "movieID")
	private Movie movie;

	@Column(name = "ticketprice")
	private int ticketprice;

	@Column(name = "date")
	private Date date;

	@Temporal(TemporalType.TIME)
	@Column(name = "startTime")
	private Date startTime;

//	@Column(name = "remain")
//	private int remain;
	
	@OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Ticket> tickets;

	public Schedule() {
	}

	public long getScheduleID() {
		return scheduleID;
	}

	public void setScheduleID(long scheduleID) {
		this.scheduleID = scheduleID;
	}

	public Auditorium getAuditorium() {
		return auditorium;
	}

	public void setAuditorium(Auditorium auditorium) {
		this.auditorium = auditorium;
	}

	public int getInAuditorium() {
		return inAuditorium;
	}

	public void setInAuditorium(int inAuditorium) {
		this.inAuditorium = inAuditorium;
	}

	public String getOnMovie() {
		return onMovie;
	}

	public void setOnMovie(String onMovie) {
		this.onMovie = onMovie;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public int getTicketprice() {
		return ticketprice;
	}

	public void setTicketprice(int ticketprice) {
		this.ticketprice = ticketprice;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	
	
//	public int getRemain() {
//		return remain;
//	}
//
//	public void setRemain(int remain) {
//		this.remain = remain;
//	}

	public Set<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}

	@Override
	public String toString() {
		return "Schedule [scheduleID=" + scheduleID + "]";
	}

}
