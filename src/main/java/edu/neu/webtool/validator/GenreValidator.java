package edu.neu.webtool.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import edu.neu.webtool.dao.GenreDAO;
import edu.neu.webtool.exception.GenreException;
import edu.neu.webtool.pojo.Genre;


@Component
public class GenreValidator implements Validator {

	@Autowired
	@Qualifier("genreDao")
	GenreDAO genreDAO;
	
	@Override
	public boolean supports(Class aClass) {
		return Genre.class.equals(aClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Genre newGenre = (Genre) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "error.invalid.genre", "Genre Required");		
		if (errors.hasErrors()) {
            return;//Skip the rest of the validation rules
        }        	
		try {
			Genre c = genreDAO.get(newGenre.getTitle());
			if(c !=null)
				errors.rejectValue("title", "error.invalid.genre", "Genre already Exists");			
		} catch (GenreException e) {
			System.err.println("Exception in Genre Validator");
		}	
	}
}
