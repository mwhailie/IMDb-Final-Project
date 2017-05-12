package edu.neu.webtool.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
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


import edu.neu.webtool.dao.UserDAO;
import edu.neu.webtool.exception.UserException;
import edu.neu.webtool.pojo.User;
import edu.neu.webtool.validator.UserValidator;

@Controller
@RequestMapping("/user/*")
public class UserController {
	@Autowired
	UserDAO userDao;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	protected String goToUserHome(HttpServletRequest request) throws Exception {
		return "user-home";
	}
	
	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = "/user/userhome.htm", method = RequestMethod.GET)
	protected ModelAndView userHome(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		User u = (User)request.getSession().getAttribute("user");
		mv.addObject("orders", u.getOrders());
		mv.addObject("reviews",u.getReviews());
		mv.setViewName("user-home");
		return mv;		
	}

	@RequestMapping(value = "/user/login.htm", method = RequestMethod.GET)
	public String logIn(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "index";
	}
	@RequestMapping(value = "/user/logout.htm", method = RequestMethod.GET)
	public String logOut(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "index";
	}
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	protected ModelAndView loginUser(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();		
		ModelAndView mv = new ModelAndView();
		try {
			System.out.print(request.getParameter("username")+" "+request.getParameter("password"));
			User u = userDao.get(request.getParameter("username"), request.getParameter("password"));			
			
			if(u == null){
				System.out.println("UserName/Password does not exist");
				mv.addObject("errorMessage", "UserName/Password does not exist");
				mv.setViewName("error");
				return mv;
			}			
			session.setAttribute("user", u);
			mv.addObject("orders", u.getOrders());
			mv.addObject("reviews",u.getReviews());
			mv.setViewName("user-home");
			return mv;
		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			session.setAttribute("errorMessage", "error while login");
			mv.addObject("errorMessage", "UserName/Password does not exist");
			mv.setViewName("error");
			return mv;
		}
	}
	
	@RequestMapping(value = "/user/register", method = RequestMethod.GET)
	protected ModelAndView registerUser() throws Exception {
		System.out.println("registerUser");
		return new ModelAndView("user-register", "user", new User());
	}
	
	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	protected ModelAndView registerNewUser(HttpServletRequest request,  @ModelAttribute("user") User user, BindingResult result) throws Exception {
		validator.validate(user, result);
		if (result.hasErrors()) {
			return new ModelAndView("user-register", "user", user);
		}
		try {
			System.out.println("registerNewUser");
			System.out.println(user);
			User u = userDao.register(user);			
			request.getSession().setAttribute("user", u);			
			return new ModelAndView("user-home", "user", u);
		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}
	
	@RequestMapping(value = "/user/update.htm", method = RequestMethod.GET)
	public String showUpdateUserForm(HttpServletRequest request, HttpServletResponse response)  {
		return "user-update";
	}
	
	@RequestMapping(value = "/user/update.htm", method = RequestMethod.POST)
	public void updateUser(HttpServletRequest request, HttpServletResponse response) throws  Exception, UserException  {
		HttpSession session = request.getSession();		
		String personID = request.getParameter("personID");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//String email = request.getParameter("email");
		boolean flag = false;
		try {
			User u = userDao.get(Long.valueOf(personID));
			u.setFirstName(firstname);
			u.setLastName(lastname);
			u.setPassword(password);
			u.setUsername(username);
			userDao.update(u);
			flag=true;
			session.setAttribute("user", u);
		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			flag=false;
		}
		
		JSONObject obj = new JSONObject();
		obj.put("flag", flag);
		PrintWriter out = response.getWriter();
		out.println(obj);
	}
}
