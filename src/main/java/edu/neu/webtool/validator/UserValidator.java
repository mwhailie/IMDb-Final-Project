package edu.neu.webtool.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import edu.neu.webtool.dao.UserDAO;
import edu.neu.webtool.exception.UserException;
import edu.neu.webtool.pojo.User;


@Component
public class UserValidator implements Validator {

	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;
	
	@Override
	public boolean supports(Class aClass) {
		return aClass.equals(User.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		User user = (User) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "error.invalid.user", "First Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "error.invalid.user", "Last Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.invalid.user", "User Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email.emailAddress", "error.invalid.email.emailAddress",
				"Email Required");

		
		try {
			User c = userDao.getByUsername(user.getUsername());
			if(c !=null)
				errors.rejectValue("username", "error.invalid.user", "User Name already Exists");			
		} catch (UserException e) {
			System.err.println("Exception in User Validator");
		}
	}
}
