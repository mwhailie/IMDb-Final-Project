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
@Table(name="order_table")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderID", unique = true, nullable = false)
	private long orderID;	
	
	@ManyToOne
	@JoinColumn(name = "personID")
	private User user;
	
	@Transient
	int placedBy;
	
	@OneToMany
	private Set<Ticket> tickets=new HashSet<Ticket>();
	
	@Column(name="totalPrice")
	private int total;

//	@Column(name="number")
//	private int number;
	
	public Order() {
	}

	public Order(User user, int totalprice) {
		// TODO Auto-generated constructor stub
		this.user = user;
		this.total = totalprice;
	}

	public long getOrderID() {
		return orderID;
	}

	public void setOrderID(long orderID) {
		this.orderID = orderID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getPlacedBy() {
		return placedBy;
	}

	public void setPlacedBy(int placedBy) {
		this.placedBy = placedBy;
	}

	public Set<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Order [" + orderID + "]";
	}
	
	
}
