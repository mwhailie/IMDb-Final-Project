package edu.neu.webtool.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;



@Entity
@Table(name = "theater_table")
@FilterDef(name="hibFilter",parameters=@ParamDef( name="myParam", type="string" ) )
@Filter(name = "hibFilter",	condition=":myParam = city"	)
public class Theater {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "theaterID", unique = true, nullable = false)
	private long theaterID;

	@Column(name = "theaterName")
	private String theaterName;

	@Column(name = "street")
	private String street;

	@Column(name = "city")
	private String city;
	
	@Column(name = "state")
	private String state;
	
	@OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
	private Set<Auditorium> auditoriums=new HashSet<Auditorium>();

	@Column(name = "audiNum")
	private int audiNum=0;

	public Theater(String theaterName, String street, String city, String state) {
		this.theaterName = theaterName;
		this.city = city;
		this.state = state;
		this.street = street;
	}

	public Theater() {
	}

	public long getTheaterID() {
		return theaterID;
	}

	public void setTheaterID(long theaterID) {
		this.theaterID = theaterID;
	}

	public String getTheaterName() {
		return theaterName;
	}

	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}



	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Set<Auditorium> getAuditoriums() {
		return auditoriums;
	}

	public void setAuditoriums(Set<Auditorium> auditoriums) {
		this.auditoriums = auditoriums;
	}

	public int getAudiNum() {
		return audiNum;
	}

	public void setAudiNum(int audiNum) {
		this.audiNum = audiNum;
	}

	@Override
	public String toString() {
		return "Theater [" + theaterName + "]";
	}

}
