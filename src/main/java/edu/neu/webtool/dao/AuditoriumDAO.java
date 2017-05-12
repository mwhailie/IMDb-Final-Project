package edu.neu.webtool.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import edu.neu.webtool.exception.AuditoriumException;
import edu.neu.webtool.pojo.Auditorium;
import edu.neu.webtool.pojo.Movie;

public class AuditoriumDAO extends DAO {

	public Auditorium get(Long audiId) throws AuditoriumException {
		try {
			begin();
			Query q = getSession().createQuery("from Auditorium where auditoriumID= :audiId");
			q.setLong("audiId", audiId);
			Auditorium auditorium = (Auditorium) q.uniqueResult();
			commit();
			return auditorium;
		} catch (HibernateException e) {
			rollback();
			throw new AuditoriumException("Could not obtain the named auditorium with id: " + audiId + " " + e.getMessage());
		}
	}

	public List<Auditorium> list() throws AuditoriumException {
		try {
			begin();
			Query q = getSession().createQuery("from Auditorium");
			List<Auditorium> list = q.list();
			commit();
			return list;
		} catch (HibernateException e) {
			rollback();
			throw new AuditoriumException("Could not list the auditoriums", e);
		}
	}

	public Auditorium create(Auditorium a) throws AuditoriumException {
		try {
			begin();
			getSession().save(a);
			commit();
			return a;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create the auditorium", e);
			throw new AuditoriumException("Exception while creating auditorium: " + e.getMessage());
		}
	}

	public void update(Auditorium a) throws AuditoriumException {
		try {
			begin();
			getSession().update(a);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new AuditoriumException("Could not save the auditorium", e);
		}
	}

	public void update(Long auditoriumId, String auditoriumName, int seat) throws AuditoriumException {
		try {
			begin();
			System.out.println("----"+auditoriumId);
			Query q = getSession().createQuery("update Auditorium a set a.auditoriumName=:auditoriumName, a.seat=:seat where g.auditoriumId=:auditoriumId");
			q.setParameter("auditoriumName", auditoriumName);
			q.setParameter("seat", seat);
			q.setParameter("auditoriumId", auditoriumId);
			int temp = q.executeUpdate();
			if (temp != 0) {
				commit();
			} else {
				System.out.println("Can not update!");
			}
		} catch (HibernateException e) {
			rollback();
			throw new AuditoriumException("Could not update the auditorium "+e.getMessage());
		}
	}

	public void delete(Auditorium auditorium) throws AuditoriumException {
		try {
			begin();
			getSession().delete(auditorium);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new AuditoriumException("Could not delete the auditorium", e);
		}
	}
}
