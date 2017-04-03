package edu.neu.webtool.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.neu.webtool.dao.GenreDAO;
import edu.neu.webtool.dao.MovieDAO;
import edu.neu.webtool.exception.MovieException;
import edu.neu.webtool.pojo.Genre;
import edu.neu.webtool.pojo.Movie;



@Controller
@RequestMapping("/movie/*")
public class MovieController {
	@Autowired
	@Qualifier("movieDao")
	MovieDAO movieDao;
	
	@Autowired
	@Qualifier("genreDao")
	GenreDAO genreDAO;
	
	
	@RequestMapping(value = "/movie/add", method = RequestMethod.POST)
	public ModelAndView addGenre(@ModelAttribute("movie") Movie movie, BindingResult result) throws Exception {
		try {			
			movie = movieDao.create(movie);	
            
            for(Genre g: movie.getGenres()){
            	g = genreDAO.get(g.getTitle());
            	g.getMovies().add(movie);
            	genreDAO.update(g); //to maintain many to many relationship
            }			
			return new ModelAndView("movie-success", "movie", movie);
			
		} catch (MovieException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}		
	}
	
	@RequestMapping(value = "/movie/list", method = RequestMethod.GET)
	public ModelAndView addGenre(HttpServletRequest request) throws Exception {

		try {						
			List<Movie> movies = movieDao.list();
			return new ModelAndView("movie-list", "movies", movies);			
		} catch (MovieException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
		
		
	}

	@RequestMapping(value="/movie/add", method = RequestMethod.GET)
	public ModelAndView initializeForm(HttpServletRequest request) throws Exception {		
		ModelAndView mv = new ModelAndView();
		mv.addObject("genres", genreDAO.list());			
		mv.addObject("movie", new Movie());
		mv.setViewName("movie-form");
		return mv;
	}
}
