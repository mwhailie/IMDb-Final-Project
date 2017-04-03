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
            Query q=getSession().createQuery("from Genre where title= :title");
            q.setString("title",title);
            Genre genre=(Genre)q.uniqueResult();
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
            Genre cat = new Genre(title);
            getSession().save(cat);
            commit();
            return cat;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create the genre", e);
            throw new GenreException("Exception while creating genre: " + e.getMessage());
        }
    }

    public void update(Genre genre) throws GenreException {
        try {
            begin();
            getSession().update(genre);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new GenreException("Could not save the genre", e);
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
