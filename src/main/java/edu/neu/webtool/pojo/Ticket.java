package edu.neu.webtool.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ticket_table")
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ticketID", unique = true, nullable = false)
	private long ticketID;

	@Column(name = "price")
	private int price;

	@Column(name = "seat")
	private int seat;

	@Column(name = "sold")
	private boolean sold;

	@ManyToOne
	@JoinColumn(name = "column_name_of_scheduleID")
	private Schedule schedule;

	public Ticket() {
	}

	public long getTicketID() {
		return ticketID;
	}

	public void setTicketID(long ticketID) {
		this.ticketID = ticketID;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public boolean isSold() {
		return sold;
	}

	public void setSold(boolean sold) {
		this.sold = sold;
	}

	@Override
	public String toString() {
		return "Ticket [" + ticketID + "]";
	}

}
