package edu.neu.webtool.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import edu.neu.webtool.dao.GenreDAO;
import edu.neu.webtool.dao.MovieDAO;
import edu.neu.webtool.exception.MovieException;
import edu.neu.webtool.pojo.Genre;
import edu.neu.webtool.pojo.Movie;
import edu.neu.webtool.pojo.Review;
import edu.neu.webtool.validator.MovieValidator;

@Controller
@RequestMapping("/movie/*")
public class MovieController {
	@Autowired
	@Qualifier("movieDao")
	MovieDAO movieDao;

	@Autowired
	@Qualifier("genreDao")
	GenreDAO genreDao;

	@Autowired
	@Qualifier("movieValidator")
	MovieValidator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@Autowired
	ServletContext servletContext;

	@RequestMapping(value = "/movie/add", method = RequestMethod.GET)
	public ModelAndView initializeForm(ModelMap model) throws Exception {
		Movie movie = new Movie();
		ModelAndView mv = new ModelAndView();
		mv.addObject("genres", genreDao.list());
		mv.addObject("movie", movie);
		mv.setViewName("movie-form");
		// model.addAttribute("movie", movie);
		// model.addAttribute("genres", genreDao.list());
		return mv;
	}

	@RequestMapping(value = "/movie/add", method = RequestMethod.POST)
	protected ModelAndView addMovie(@ModelAttribute("movie") Movie movie, BindingResult result) throws Exception {

		validator.validate(movie, result);
		if (result.hasErrors()) {
			return new ModelAndView("error", "errorMessage", result);
		}
		try {
			if (movie.getFilename().trim() != "" || movie.getFilename() != null) {
				File directory;
				String check = File.separator; // linux.
				String path = null;
				if (check.equalsIgnoreCase("\\")) {
					path = servletContext.getRealPath("").replace("build\\", ""); 
				}
				if (check.equalsIgnoreCase("/")) {
					path = servletContext.getRealPath("").replace("build/", "");
					path += "/"; // Mac systems.
				}
				directory = new File(path + "\\" + movie.getFilename());
				boolean temp = directory.exists();
				if (!temp) {
					temp = directory.mkdir();
				}
				if (temp) {
					// We need to transfer to a file
					CommonsMultipartFile photoInMemory = movie.getPoster();
					String fileName = photoInMemory.getOriginalFilename();
					// could generate file names as well
					File localFile = new File(directory.getPath(), fileName);
					// move the file from memory to the file
					photoInMemory.transferTo(localFile);
					movie.setFilename(localFile.getPath());
					System.out.println("File is stored at " + localFile.getPath());

					movie = movieDao.create(movie);
					for (Genre g : movie.getGenres()) {
						g = genreDao.get(g.getTitle());
						g.getMovies().add(movie);
						genreDao.update(g); // to maintain many to many
											// relationship
					}
				} else {
					System.out.println("Failed to create directory!");
				}
			}
		} catch (IllegalStateException e) {
			System.out.println("*** IllegalStateException: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("*** IOException: " + e.getMessage());
		} catch (MovieException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", e.getMessage());
		}
		
		return new ModelAndView("movie-success", "movies", movie);
	}

	@RequestMapping(value = "/movie/list", method = RequestMethod.GET)
	public ModelAndView listMovie(HttpServletRequest request) throws Exception {
		try {
			List<Movie> movies = movieDao.list();
			return new ModelAndView("movie-list", "movies", movies);
		} catch (MovieException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", e.getMessage());
		}
	}

	@RequestMapping(value = "/movie/search", method = RequestMethod.GET)
	public String showSearchMovieForm(HttpServletRequest request) throws Exception {
		return "movie-search";
	}

	@RequestMapping(value = "/movie/search", method = RequestMethod.POST)
	public ModelAndView searchMovie(HttpServletRequest request) throws Exception {
		try {
			List<Movie> movies = movieDao.search(request.getParameter("title"),request.getParameter("director"),request.getParameter("actor"),request.getParameter("actress"));
			return new ModelAndView("movie-list", "movies", movies);
		} catch (MovieException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", e.getMessage());
		}
	}
	
	@RequestMapping(value = "/movie/manage", method = RequestMethod.GET)
	public ModelAndView showManageForm(HttpServletRequest request) throws Exception {
		try {
			List<Movie> movies = movieDao.list();
			return new ModelAndView("movie-manage", "movies", movies);
		} catch (MovieException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", e.getMessage());
		}
	}

	@RequestMapping(value = "/movie/update", method = RequestMethod.GET)
	public ModelAndView showUpdateForm(HttpServletRequest request) throws Exception {
		try {
			Movie movie = movieDao.getByID(Long.parseLong(request.getParameter("movieID")));
			return new ModelAndView("movie-update", "movie", movie);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", e.getMessage());
		}
	}

	
	@RequestMapping(value = "/movie/update", method = RequestMethod.POST)
	public ModelAndView updateMovie(@ModelAttribute("movie") Movie movie) throws Exception {
		//System.out.println("---"+movieID);
		try {
			movieDao.update(movie.getMovieID(), movie.getTitle(), movie.getYear(), movie.getFilename(),
					movie.getActor(), movie.getActress(), movie.getDirector());
			List<Movie> movies = movieDao.list();
			return new ModelAndView("movie-manage", "movies", movies);
		} catch (MovieException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", e.getMessage());
		}
	}


	@RequestMapping(value = "/movie/delete", method = RequestMethod.POST)
	public ModelAndView deleteMovie(HttpServletRequest request) throws Exception {
		try {
			Movie m = movieDao.getByID(Long.parseLong(request.getParameter("movieID")));
			for (Genre g : m.getGenres()) {
				g = genreDao.get(g.getTitle());
				g.getMovies().remove(m);
				genreDao.update(g); // to maintain many to many
									// relationship
			}
			movieDao.delete(m);
			
			List<Movie> movies = movieDao.list();
			return new ModelAndView("movie-manage", "movies", movies);
		} catch (MovieException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", e.getMessage());
		}
	}

	@RequestMapping(value = "/movie/detail", method = RequestMethod.GET)
	public ModelAndView showMovieDetail(HttpServletRequest request) throws Exception {
		try {
		String title = request.getParameter("title");
		Movie movie = movieDao.get(title);
		Set<Review> reviews = movie.getReviews();
		Review review = new Review();
		review.setMovie(movie);
		ModelAndView mv = new ModelAndView();
		mv.addObject("review", review);
		mv.addObject("reviews", reviews);
		mv.addObject("movie", movie);
		mv.setViewName("movie-detail");
		return mv;
		} catch (MovieException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", e.getMessage());
		}
	}
	@RequestMapping(value = "/movie/recent", method = RequestMethod.GET)
	public ModelAndView showRecentMovie(HttpServletRequest request) throws Exception {
		try {
			List<Movie> movies = movieDao.listRecent();
			return new ModelAndView("movie-list", "movies", movies);
		} catch (MovieException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", e.getMessage());
		}
	}
}
