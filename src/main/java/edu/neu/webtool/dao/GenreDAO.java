package edu.neu.webtool.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import edu.neu.webtool.exception.GenreException;
import edu.neu.webtool.pojo.Genre;

public class GenreDAO extends DAO {

	public Genre get(String title) throws GenreException {
		try {
			begin();
			Query q = getSession().createQuery("from Genre where title= :title");
			q.setString("title", title);
			Genre genre = (Genre) q.uniqueResult();
			commit();
			return genre;
		} catch (HibernateException e) {
			rollback();
			throw new GenreException("Could not obtain the named genre " + title + " " + e.getMessage());
		}
	}

	public List<Genre> list() throws GenreException {
		try {
			begin();
			Query q = getSession().createQuery("from Genre");
			List<Genre> list = q.list();
			commit();
			return list;
		} catch (HibernateException e) {
			rollback();
			throw new GenreException("Could not list the genres", e);
		}
	}

	public Genre create(String title) throws GenreException {
		try {
			begin();
			Genre g = new Genre(title);
			getSession().save(g);
			commit();
			return g;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create the genre", e);
			throw new GenreException("Exception while creating genre: " + e.getMessage());
		}
	}

	public void update(Genre g) throws GenreException {
		try {
			begin();
			getSession().update(g);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new GenreException("Could not save the genre", e);
		}
	}

	public void update(Long genreId, String title) throws GenreException {
		try {
			begin();
			System.out.println("----"+genreId);
			Query q = getSession().createQuery("update Genre g set g.title=:title where g.genreId=:genreId");
			q.setParameter("title", title);
			q.setParameter("genreId", genreId);
			int temp = q.executeUpdate();
			if (temp != 0) {
				commit();
			} else {
				System.out.println("Can not update!");
			}
		} catch (HibernateException e) {
			rollback();
			throw new GenreException("Could not update the genre "+e.getMessage());
		}
	}

	public void delete(Genre genre) throws GenreException {
		try {
			begin();
			getSession().delete(genre);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new GenreException("Could not delete the genre", e);
		}
	}
}
