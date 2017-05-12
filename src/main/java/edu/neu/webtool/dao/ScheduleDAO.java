package edu.neu.webtool.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import edu.neu.webtool.exception.ScheduleException;
import edu.neu.webtool.pojo.Schedule;
import edu.neu.webtool.pojo.Theater;

public class ScheduleDAO extends DAO{
	public Schedule get(Long audiId) throws ScheduleException {
		try {
			begin();
			Query q = getSession().createQuery("from Schedule where ScheduleID= :audiId");
			q.setLong("audiId", audiId);
			Schedule Schedule = (Schedule) q.uniqueResult();
			commit();
			return Schedule;
		} catch (HibernateException e) {
			rollback();
			throw new ScheduleException("Could not obtain the named Schedule with id: " + audiId + " " + e.getMessage());
		}
	}

	public List<Schedule> list() throws ScheduleException {
		try {
			begin();
			Query q = getSession().createQuery("from Schedule");
			List<Schedule> list = q.list();
			commit();
			return list;
		} catch (HibernateException e) {
			rollback();
			throw new ScheduleException("Could not list the Schedules", e);
		}
	}

	public Schedule create(Schedule s) throws ScheduleException {
		try {
			begin();
			getSession().save(s);
			commit();
			return s;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create the Schedule", e);
			throw new ScheduleException("Exception while creating Schedule: " + e.getMessage());
		}
	}

	public void update(Schedule a) throws ScheduleException {
		try {
			begin();
			getSession().update(a);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new ScheduleException("Could not save the Schedule", e);
		}
	}

	public void update(Long ScheduleId, String ScheduleName, int seat) throws ScheduleException {
		try {
			begin();
			System.out.println("----"+ScheduleId);
			Query q = getSession().createQuery("update Schedule a set a.ScheduleName=:ScheduleName, a.seat=:seat where g.ScheduleId=:ScheduleId");
			q.setParameter("ScheduleName", ScheduleName);
			q.setParameter("seat", seat);
			q.setParameter("ScheduleId", ScheduleId);
			int temp = q.executeUpdate();
			if (temp != 0) {
				commit();
			} else {
				System.out.println("Can not update!");
			}
		} catch (HibernateException e) {
			rollback();
			throw new ScheduleException("Could not update the Schedule "+e.getMessage());
		}
	}

	public void delete(Schedule Schedule) throws ScheduleException {
		try {
			begin();
			getSession().delete(Schedule);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new ScheduleException("Could not delete the Schedule", e);
		}
	}

	public List<Schedule> getByTitleAndTheater(String title, Theater theater) throws ScheduleException {
		try {
			begin();
			Query q = getSession().createQuery("from Schedule");
			List<Schedule> list = q.list();
			commit();
			return list;
		} catch (HibernateException e) {
			rollback();
			throw new ScheduleException("Could not list the Schedules", e);
		}
	}
}
