package edu.neu.webtool.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import edu.neu.webtool.exception.OrderException;
import edu.neu.webtool.pojo.Order;
import edu.neu.webtool.pojo.User;


public class OrderDAO extends DAO{
	public Order get(Long orderID) throws OrderException {
		try {
			begin();
			Query q = getSession().createQuery("from Order where OrderID= :orderID");
			q.setLong("orderID", orderID);
			Order Order = (Order) q.uniqueResult();
			commit();
			return Order;
		} catch (HibernateException e) {
			rollback();
			throw new OrderException("Could not obtain the named Order with id: " + orderID + " " + e.getMessage());
		}
	}

	public List<Order> list() throws OrderException {
		try {
			begin();
			Query q = getSession().createQuery("from Order");
			List<Order> list = q.list();
			commit();
			return list;
		} catch (HibernateException e) {
			rollback();
			throw new OrderException("Could not list the Orders", e);
		}
	}

	public Order create(Order s) throws OrderException {
		try {
			begin();
			getSession().save(s);
			commit();
			return s;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create the Order", e);
			throw new OrderException("Exception while creating Order: " + e.getMessage());
		}
	}

	public void update(Order a) throws OrderException {
		try {
			begin();
			getSession().update(a);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new OrderException("Could not save the Order", e);
		}
	}



	public void delete(Order Order) throws OrderException {
		try {
			begin();
			getSession().delete(Order);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new OrderException("Could not delete the Order", e);
		}
	}
	
	public Order placeOrder(User user, int totalprice)  throws OrderException {
		try {
			begin();
			Order order = new Order(user, totalprice);
			getSession().save(order);
			commit();
			return order;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create the Order", e);
			throw new OrderException("Exception while creating Order: " + e.getMessage());
		}
	}
	
}
