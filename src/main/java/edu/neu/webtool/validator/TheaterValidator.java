package edu.neu.webtool.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import edu.neu.webtool.dao.TheaterDAO;
import edu.neu.webtool.exception.TheaterException;
import edu.neu.webtool.pojo.Theater;
import edu.neu.webtool.pojo.Theater;



public class TheaterValidator implements Validator {
	@Autowired
	@Qualifier("theaterValidator")
	TheaterValidator theaterValidator;
	
	@Autowired
	@Qualifier("theaterDAO")
	TheaterDAO theater;
	
	@Override
	public boolean supports(Class aClass) {
		return aClass.equals(Theater.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Theater theater = (Theater) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "theaterName", "error.invalid.theater", "TheaterName Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "street", "error.invalid.street",
				"street Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "error.invalid.city",
				"city Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state", "error.invalid.state",
				"state Required");
	}
}
