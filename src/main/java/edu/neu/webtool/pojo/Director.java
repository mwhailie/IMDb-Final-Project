package edu.neu.webtool.pojo;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "director_table")
@PrimaryKeyJoinColumn(name = "personID")
public class Director extends Person{
	
	
	private Set<String> movies;
}
