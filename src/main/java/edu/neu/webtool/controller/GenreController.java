package edu.neu.webtool.controller;

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
import edu.neu.webtool.pojo.Genre;
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
		return new ModelAndView("genre-success", "genre", genre);
		
	}

	@RequestMapping(value="/genre/add", method = RequestMethod.GET)
	public ModelAndView initializeForm() throws Exception {			
		return new ModelAndView("genre-form", "genre", new Genre());
	}
}
