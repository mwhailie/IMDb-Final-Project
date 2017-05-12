package edu.neu.webtool.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import edu.neu.webtool.exception.TicketException;
import edu.neu.webtool.pojo.Ticket;

public class TicketDAO  extends DAO{
	public Ticket get(Long ticketId) throws TicketException {
		try {
			begin();
			Query q = getSession().createQuery("from Ticket where TicketID= :ticketId");
			q.setLong("audiId", ticketId);
			Ticket Ticket = (Ticket) q.uniqueResult();
			commit();
			return Ticket;
		} catch (HibernateException e) {
			rollback();
			throw new TicketException("Could not obtain the named Ticket with id: " + ticketId + " " + e.getMessage());
		}
	}

	public List<Ticket> list() throws TicketException {
		try {
			begin();
			Query q = getSession().createQuery("from Ticket");
			List<Ticket> list = q.list();
			commit();
			return list;
		} catch (HibernateException e) {
			rollback();
			throw new TicketException("Could not list the Tickets", e);
		}
	}

	public Ticket create(Ticket s) throws TicketException {
		try {
			begin();
			getSession().save(s);
			commit();
			return s;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create the Ticket", e);
			throw new TicketException("Exception while creating Ticket: " + e.getMessage());
		}
	}

	public void update(Ticket a) throws TicketException {
		try {
			begin();
			getSession().update(a);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new TicketException("Could not save the Ticket", e);
		}
	}

	public void update(Long TicketId,  int seat) throws TicketException {
		try {
			begin();
			System.out.println("----"+TicketId);
			Query q = getSession().createQuery("update Ticket a set a.seat=:seat where g.TicketId=:TicketId");
			q.setParameter("seat", seat);
			q.setParameter("TicketId", TicketId);
			int temp = q.executeUpdate();
			if (temp != 0) {
				commit();
			} else {
				System.out.println("Can not update!");
			}
		} catch (HibernateException e) {
			rollback();
			throw new TicketException("Could not update the Ticket "+e.getMessage());
		}
	}

	public void delete(Ticket ticket) throws TicketException {
		try {
			begin();
			getSession().delete(ticket);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new TicketException("Could not delete the Ticket", e);
		}
	}

	public boolean buyTickets(Long TicketId) throws TicketException{
		// TODO Auto-generated method stub
		try {
			begin();
			Query q = getSession().createQuery("update Ticket a set a.sold=true where g.TicketId=:TicketId");
			q.setParameter("TicketId", TicketId);
			int temp = q.executeUpdate();
			if (temp != 0) {
				commit();
				return true;
			} else {
				System.out.println("Can not update!");
				return false;
			}
			
		} catch (HibernateException e) {
			rollback();
			throw new TicketException("Could not buy the Ticket", e);

		}
	}

	public Ticket getTicketBySchedule(Long scheduleID, int seat) throws TicketException {
		// TODO Auto-generated method stub
		try {
			begin();
			Query q = getSession().createQuery("from Ticket where schedule= :scheduleID AND seat=:seat");
			q.setLong("scheduleID", scheduleID);
			q.setInteger("seat", seat);
//			Ticket Ticket = (Ticket) crit.uniqueResult();
			Ticket ticket = (Ticket) q.uniqueResult();
			//List<Ticket> ticket = q.list();
			commit();
			return ticket;
		} catch (HibernateException e) {
			rollback();
			throw new TicketException("Could not obtain the named Ticket with scheduleID: " + scheduleID + " and seat: " + seat+" "+ e.getMessage());
		}
	}

	public List<Ticket> listByOrder(Long orderID)throws TicketException {
		try {
			begin();

			List<Ticket> list = list();
			commit();
			return list;
		} catch (HibernateException e) {
			rollback();
			throw new TicketException("Could not list the Tickets", e);
		}
	
	}
}
