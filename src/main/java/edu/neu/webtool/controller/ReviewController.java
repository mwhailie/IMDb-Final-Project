package edu.neu.webtool.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



import edu.neu.webtool.dao.MovieDAO;
import edu.neu.webtool.dao.ReviewDAO;
import edu.neu.webtool.dao.UserDAO;
import edu.neu.webtool.exception.ReviewException;
import edu.neu.webtool.pojo.Movie;
import edu.neu.webtool.pojo.Review;
import edu.neu.webtool.pojo.User;

@Controller
@RequestMapping("/review/*")
public class ReviewController {
	@Autowired
	@Qualifier("reviewDao")
	ReviewDAO reviewDao;
	@Autowired
	@Qualifier("movieDao")
	MovieDAO movieDao;
	
	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;
	
//	@RequestMapping(value="/review/add", method = RequestMethod.GET)
//	public ModelAndView initializeForm(HttpServletRequest request) throws Exception {			
//		String title = request.getParameter("title");
//		Movie movie=movieDao.get(title);
//		Review review = new Review();
//		review.setMovie(movie);
//		return new ModelAndView("movie-detail","review",review);
//	}
	
	@RequestMapping(value = "/review/add", method = RequestMethod.POST)
	public ModelAndView addReview(@ModelAttribute("review") Review review, BindingResult result,HttpServletRequest request) throws Exception {		
		try {			

			System.out.println(request.getParameter("title"));
			User user = (User)request.getSession().getAttribute("user");
			review.setPostByUser(user);//transient	
			Movie movie = movieDao.get(request.getParameter("title"));
			review.setMovie(movie);//transient			
			review = reviewDao.create(review);	
			//movie.getReviews().add(review);//getReview():nullpointerexception
			 ModelAndView mv =new ModelAndView();
			 mv.setViewName("movie-detail");
			 mv.addObject("movie",movie);
			 Set<Review> r = movie.getReviews();
			 mv.addObject("movie",movie);
			 mv.addObject("reviews",r);
			return mv;			
		} catch (ReviewException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage",  e.getMessage());
		}		
	}
}
