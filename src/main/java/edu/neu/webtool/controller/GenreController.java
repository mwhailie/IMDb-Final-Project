package edu.neu.webtool.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.neu.webtool.dao.GenreDAO;
import edu.neu.webtool.exception.GenreException;
import edu.neu.webtool.exception.MovieException;
import edu.neu.webtool.pojo.Genre;
import edu.neu.webtool.pojo.Movie;
import edu.neu.webtool.validator.GenreValidator;


@Controller
@RequestMapping("/genre/*")
public class GenreController extends MyController {
	@Autowired
	@Qualifier("genreValidator")
	GenreValidator genreValidator;
	
	@Autowired
	@Qualifier("genreDao")
	GenreDAO genreDAO;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(genreValidator);
	}
	@RequestMapping(value="/genre/add", method = RequestMethod.GET)
	public ModelAndView initializeForm() throws Exception {			
		return new ModelAndView("genre-form", "genre", new Genre());
	}
	@RequestMapping(value = "/genre/add", method = RequestMethod.POST)
	public ModelAndView addGenre(@ModelAttribute("genre") Genre genre, BindingResult result) throws Exception {
		
		genreValidator.validate(genre, result);
		
		if (result.hasErrors()) {
			return new ModelAndView("genre-form", "genre", genre);
		}
		try {				
			genre = genreDAO.create(genre.getTitle());
		} catch (GenreException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", e.getMessage());
		}
		List<Genre> genres = genreDAO.list();
		return new ModelAndView("genre-manage", "genres", genres);
		
	}

	@RequestMapping(value="/genre/manage", method = RequestMethod.GET)
	public ModelAndView initializeList() throws Exception {			
		try {
			List<Genre> genres = genreDAO.list();
			return new ModelAndView("genre-manage", "genres", genres);
		} catch (GenreException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", e.getMessage());
		}
	}
	
	@RequestMapping(value = "/genre/update", method = RequestMethod.POST)
	public ModelAndView updateGenre(@ModelAttribute("genre") Genre genre) throws Exception {

		try {
			System.out.println("----"+genre.getGenreId());
			genreDAO.update(genre.getGenreId(),genre.getTitle());
			List<Genre> genres = genreDAO.list();
			return new ModelAndView("genre-manage", "genres", genres);
		} catch (GenreException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", e.getMessage());
		}
	}

	
	@RequestMapping(value = "/genre/delete", method = RequestMethod.POST)
	public ModelAndView deleteGenre(HttpServletRequest request) throws Exception {
		try {
			Genre g = genreDAO.get(request.getParameter("title"));
			genreDAO.delete(g);
			List<Genre> genres = genreDAO.list();
			return new ModelAndView("genre-manage", "genres", genres);
		} catch (GenreException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", e.getMessage());
		}
	}
}
