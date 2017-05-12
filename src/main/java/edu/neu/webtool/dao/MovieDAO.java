package edu.neu.webtool.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import edu.neu.webtool.exception.GenreException;
import edu.neu.webtool.exception.MovieException;
import edu.neu.webtool.pojo.Movie;

public class MovieDAO extends DAO {

	public Movie get(String title) throws MovieException {
		try {
			begin();
			Query q = getSession().createQuery("from Movie where title= :title");
			q.setString("title", title);
			Movie movie = (Movie) q.uniqueResult();
			commit();
			return movie;
		} catch (HibernateException e) {
			rollback();
			throw new MovieException("Could not obtain the movie named " + title + " " + e.getMessage());
		}
	}

	public Movie create(Movie movie) throws MovieException {
		try {
			begin();
			getSession().save(movie);
			commit();
			return movie;
		} catch (HibernateException e) {
			rollback();
			throw new MovieException("Exception while creating movie: " + e.getMessage());
		}
	}

	public boolean update(long movieID, String title, int year, String fileName, String actor, String actress,
			String director) throws MovieException {
		try {
			begin();
			Query q = getSession().createQuery(
					"update Movie m set m.title=:title, m.year = :year , m.actor = :actor, m.actress = :actress, m.director = :director where m.movieID=:movieID");
			System.out.println("---" + movieID);
			q.setParameter("title", title);
			q.setParameter("year", year);
			q.setParameter("actor", actor);
			q.setParameter("actress", actress);
			q.setParameter("director", director);
			q.setParameter("movieID", movieID);
			int temp = q.executeUpdate();
			if (temp != 0) {
				commit();
				return true;
			} else {
				System.out.println("Can not update!");
			}
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new MovieException("Exception while updating movie: " + e.getMessage());
		}
		return false;
	}

	public void delete(Movie movie) throws MovieException {
		try {
			begin();
			getSession().delete(movie);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new MovieException("Could not delete movie", e);
		}
	}

	public List<Movie> list() throws MovieException {
		try {
			begin();
			Query q = getSession().createQuery("from Movie");
			List<Movie> movies = q.list();
			commit();
			return movies;
		} catch (HibernateException e) {
			rollback();
			throw new MovieException("Could not List movie", e);
		}
	}
	
	public List<Movie> search(String title,String director, String actor, String actress) throws MovieException {
		try {
			begin();
			Criteria crit = getSession().createCriteria(Movie.class);
			Criterion titleC = Restrictions.like("title", "%"+title+"%");
			Criterion directorC = Restrictions.like("director", "%"+director+"%");
			Criterion actorC = Restrictions.like("actor", "%"+actor+"%");
			Criterion actressC = Restrictions.like("actress", "%"+actress+"%");
			Conjunction conjunction = Restrictions.conjunction();
			conjunction.add(titleC);
			conjunction.add(directorC);
			conjunction.add(actorC);
			conjunction.add(actressC);
			crit.add(conjunction);
			List results = crit.list();
//			Query q = getSession().createQuery("from Movie");
			List<Movie> movies = crit.list();
			commit();
			return movies;
		} catch (HibernateException e) {
			rollback();
			throw new MovieException("Could not List movie", e);
		}
	}

	public void update(Movie movie) throws MovieException  {
		// TODO Auto-generated method stub
		try {
			begin();
			getSession().update(movie);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new MovieException("Could not save the movie", e);
		}
	}

	public Movie getByID(long parseLong) throws MovieException {
		// TODO Auto-generated method stub
		try {
			begin();
			Query q = getSession().createQuery("from Movie where movieID= :movieid");
			q.setLong("movieid", parseLong);
			Movie movie = (Movie) q.uniqueResult();
			commit();
			return movie;
		} catch (HibernateException e) {
			rollback();
			throw new MovieException("Could not obtain the movie with id " + parseLong + " " + e.getMessage());
		}
	}

	public List<Movie> listRecent() throws MovieException {
		// TODO Auto-generated method stub
		try {
			begin();
			Criteria crit = getSession().createCriteria(Movie.class);
			crit.addOrder(Order.desc("year"));
			List<Movie> movies = crit.list();
			commit();
			return movies;
		} catch (HibernateException e) {
			rollback();
			throw new MovieException("Could not list movie with " + e.getMessage());
		}
	}
	
	public List<Movie> listPop() throws MovieException {
		// TODO Auto-generated method stub
		try {
			begin();
			Criteria crit = getSession().createCriteria(Movie.class);
			crit.addOrder(Order.desc("year"));
			List<Movie> movies = crit.list();
			commit();
			return movies;
		} catch (HibernateException e) {
			rollback();
			throw new MovieException("Could not list movie with " + e.getMessage());
		}
	}
}
