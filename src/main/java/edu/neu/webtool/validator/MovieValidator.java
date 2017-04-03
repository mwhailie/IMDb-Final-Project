package edu.neu.webtool.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import edu.neu.webtool.dao.MovieDAO;
import edu.neu.webtool.exception.MovieException;
import edu.neu.webtool.pojo.Movie;



public class MovieValidator implements Validator {

	
	@Autowired
	@Qualifier("movieDAO")
	MovieDAO movieDAO;
	
	@Override
	public boolean supports(Class aClass) {
		return aClass.equals(Movie.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Movie movie = (Movie) obj;
		
		try {
			Movie m = movieDAO.get(movie.getTitle());
			if(m !=null)
				errors.rejectValue("title", "error.invalid.movie", "Movie already Exists");			
		} catch (MovieException e) {
			System.err.println("Exception in Movie Validator");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.invalid.movie", "First Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.invalid.movie", "Last Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.invalid.movie", "User Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email.emailAddress", "error.invalid.email.emailAddress",	"Email Required");
		
	}
}
