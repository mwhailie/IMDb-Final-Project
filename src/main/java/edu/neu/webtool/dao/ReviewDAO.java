package edu.neu.webtool.dao;

import org.hibernate.HibernateException;

import edu.neu.webtool.exception.ReviewException;
import edu.neu.webtool.pojo.Movie;
import edu.neu.webtool.pojo.Review;



public class ReviewDAO extends DAO{
	public Review create(Review review) throws ReviewException {
        try {
            begin();  
            System.out.println(review.getMovie());
            getSession().save(review);     
            commit();
            return review;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create review", e);
            throw new ReviewException("Exception while creating review: " + e.getMessage());
        }
    }
}
