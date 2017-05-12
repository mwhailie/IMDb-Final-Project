package edu.neu.webtool.dao;

import java.util.List;

import org.hibernate.Filter;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import edu.neu.webtool.exception.TheaterException;
import edu.neu.webtool.exception.UserException;

import edu.neu.webtool.pojo.Theater;

public class TheaterDAO extends DAO {
	public TheaterDAO() {
	}

	public Theater get(String theaterName) throws TheaterException {
		try {
			begin();
			Query q = getSession().createQuery("from Theater where theaterName= :theaterName");
			q.setString("theaterName", theaterName);
			Theater theater = (Theater) q.uniqueResult();
			commit();
			return theater;
		} catch (HibernateException e) {
			rollback();
			throw new TheaterException("Could not obtain the theater named " + theaterName + " " + e.getMessage());
		}
	}

	public Theater getByID(Long theaterID) throws TheaterException {
		try {
			begin();
			Query q = getSession().createQuery("from Theater where theaterID= :theaterID");
			q.setLong("theaterID", theaterID);
			Theater theater = (Theater) q.uniqueResult();
			commit();
			return theater;
		} catch (HibernateException e) {
			rollback();
			throw new TheaterException("Could not obtain the theater with id " + theaterID + " " + e.getMessage());
		}
	}

	public Theater addTheater(Theater t) throws TheaterException {
		try {
			begin();
			System.out.println("inside DAO");
			// Address address = new
			// Address(t.getAddress().getStreet(),t.getAddress().getCity(),t.getAddress().getState());
			Theater theater = new Theater(t.getTheaterName(), t.getStreet(), t.getCity(), t.getState());

			theater.setTheaterName(t.getTheaterName());
			// theater.setAddress(address);
			// address.setTheater(theater);
			getSession().save(theater);
			commit();
			return theater;

		} catch (HibernateException e) {
			rollback();
			throw new TheaterException("Exception while creating theater: " + e.getMessage());
		}
	}

	public void deleteTheater(Theater theater) throws TheaterException {
		try {
			begin();
			getSession().delete(theater);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new TheaterException("Could not delete theater", e);
		}
	}

	public void update(Theater theater) throws TheaterException {
		try {
			begin();
			getSession().update(theater);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new TheaterException("Could not save the theater", e);
		}
	}

	// public Theater updateTheater(Theater t, String street, String city,
	// String state)
	// throws TheaterException {
	// try {
	// begin();
	// System.out.println("inside DAO");
	// Address address = t.;
	// Theater theater = new Theater(t.getTheaterName());
	//
	// theater.setTheaterName(t.getTheaterName());
	// theater.setAddress(address);
	// address.setTheater(theater);
	// getSession().update(theater);
	// commit();
	// return theater;
	//
	// } catch (HibernateException e) {
	// rollback();
	// throw new TheaterException("Exception while creating theater: " +
	// e.getMessage());
	// }
	// }
	public List<Theater> list() throws TheaterException {

		try {
			begin();
			Query q = getSession().createQuery("from Theater");
			List<Theater> theaters = q.list();
			commit();
			return theaters;
		} catch (HibernateException e) {
			rollback();
			throw new TheaterException("Could not List theater", e);
		}

	}

	public void delete(Theater theater) throws TheaterException {
		// TODO Auto-generated method stub
		try {
			begin();
			getSession().delete(theater);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new TheaterException("Could not delete the theater", e);
		}
	}

	public List<Theater> search(String searchQuery) throws TheaterException {
		// TODO Auto-generated method stub
		try {
			begin();
			getSession();
			String query = "FROM Theater";
			Filter filter = getSession().enableFilter("hibFilter").setParameter("myParam", searchQuery);
			
			Query q = getSession().createQuery(query);
			// String searchType = request.getParameter("searchType");

			List<Theater> theaters = q.list();

			commit();
			return theaters;
		} catch (HibernateException e) {
			rollback();
			throw new TheaterException("Could not delete the theater", e);
		}
	}
}
