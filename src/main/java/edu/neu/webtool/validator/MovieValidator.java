package edu.neu.webtool.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import edu.neu.webtool.dao.MovieDAO;
import edu.neu.webtool.exception.MovieException;
import edu.neu.webtool.pojo.Movie;
import edu.neu.webtool.pojo.Review;



public class MovieValidator implements Validator {

	
	@Autowired
	@Qualifier("movieDAO")
	MovieDAO movieDAO;
	
	@Override
	public boolean supports(Class aClass) {
		return aClass.equals(Movie.class)||aClass.equals(Review.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Movie movie = (Movie) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "error.invalid.movie", "title Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "year", "error.invalid.movie", "year Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "director", "error.invalid.movie", "director Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "actor", "error.invalid.movie", "actor name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "actress", "error.invalid.movie", "actress Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "time", "error.invalid.movie", "time Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "filename", "error.invalid.movie", "filename Name Required");
		if (errors.hasErrors()) {
            return;//Skip the rest of the validation rules
        }
		try {
			//Movie m = ;			
			if(movieDAO.get(movie.getTitle()) !=null) errors.rejectValue("title", "error.invalid.movie", "Movie already Exists");
		} catch (Exception e) {
			System.out.println("Exception in Movie Validator  "+e.getMessage());
		}
		
				
	}
}
