package edu.neu.webtool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.neu.webtool.pojo.Genre;

@Controller
public class AdminLoginController {

	@RequestMapping(value="/admin.htm")
	public String showAdminPage(){			
		return "admin-home";
	}
}
