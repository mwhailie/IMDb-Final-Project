package edu.neu.webtool.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Filter;
import org.hibernate.Query;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.neu.webtool.dao.MovieDAO;
import edu.neu.webtool.dao.TheaterDAO;
import edu.neu.webtool.exception.GenreException;
import edu.neu.webtool.exception.MovieException;
import edu.neu.webtool.exception.TheaterException;
import edu.neu.webtool.exception.UserException;
import edu.neu.webtool.pojo.Genre;
import edu.neu.webtool.pojo.Movie;
import edu.neu.webtool.pojo.Theater;
import edu.neu.webtool.pojo.User;
import edu.neu.webtool.validator.TheaterValidator;

@Controller
@RequestMapping("/theater/*")
public class TheaterController {
	@Autowired
	@Qualifier("theaterValidator")
	TheaterValidator theaterValidator;
	
	@Autowired
	@Qualifier("theaterDao")
	TheaterDAO theaterDao;
	
	@Autowired
	@Qualifier("movieDao")
	MovieDAO movieDao;
	
	@RequestMapping(value = "/theater/add", method = RequestMethod.GET)
	protected ModelAndView showForm() throws Exception {
		return new ModelAndView("theater-form", "theater", new Theater());
	}
	
	@RequestMapping(value = "/theater/add", method = RequestMethod.POST)
	protected ModelAndView addTheater(@ModelAttribute("theater") Theater theater, BindingResult result) throws Exception {
		System.out.print("in addTheater");
		theaterValidator.validate(theater, result);
		if (result.hasErrors()) {
			return new ModelAndView("theater-form", "theater", theater);
		}
		try {
			Theater t = theaterDao.addTheater(theater);			
			return new ModelAndView("theater-success", "theater", theater);
		} catch (TheaterException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage",  e.getMessage());
		}
	}
	
	@RequestMapping(value = "/theater/list", method = RequestMethod.GET)
	public ModelAndView listTheater(HttpServletRequest request) throws Exception {
		try {
			String title = request.getParameter("title");
			Movie movie = movieDao.get(title);
			List<Theater> theaters = theaterDao.list();
			ModelAndView mv= new ModelAndView("theater-list", "theaters", theaters);
			mv.addObject("movie",movie);
			return mv;			
		} catch (TheaterException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", e.getMessage());
		}			
	}

	@RequestMapping(value = "/theater/manage", method = RequestMethod.GET)
	public ModelAndView manageTheater(HttpServletRequest request) throws Exception {
		try {						
			List<Theater> theaters = theaterDao.list();
			return new ModelAndView("theater-manage", "theaters", theaters);			
		} catch (TheaterException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", e.getMessage());
		}			
	}
	
	@RequestMapping(value = "/theater/update", method = RequestMethod.POST)
	public void updateTheater(HttpServletRequest request, HttpServletResponse response) throws  Exception, UserException  {
		String theaterID = request.getParameter("theaterID");
		String theaterName = request.getParameter("theaterName");
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		//String email = request.getParameter("email");
		boolean flag = false;
		Theater t=null;
		try {
			t = theaterDao.getByID(Long.valueOf(theaterID));	
				
			t.setTheaterName(theaterName);
			t.setStreet(street);
			t.setCity(city);
			t.setState(state);
			theaterDao.update(t);
			flag=true;
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			flag=false;
		}
		JSONObject obj = new JSONObject();
		obj.put("flag", flag);
		obj.put("theater", t);
		PrintWriter out = response.getWriter();
		out.println(obj);
	}
	
	@RequestMapping(value = "/theater/delete", method = RequestMethod.POST)
	public ModelAndView deleteGenre(HttpServletRequest request) throws Exception {
		try {			
			Theater t = theaterDao.get(request.getParameter("theaterID"));
			theaterDao.delete(t);
			List<Theater> theaters = theaterDao.list();
			return new ModelAndView("theater-manage", "theaters", theaters);
		} catch (TheaterException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", e.getMessage());
		}
	}
	
	@RequestMapping(value = "/theater/search", method = RequestMethod.GET)
		public String showSearchTheaterForm(HttpServletRequest request) throws Exception {
			return "theater-search";
		}
	
	
	@RequestMapping(value = "/theater/search", method = RequestMethod.POST)
	public ModelAndView searchTheater(HttpServletRequest request) throws Exception {
		try {			
			//Theater t = theaterDao.get(request.getParameter("city"));
			String searchQuery = request.getParameter("city");
	        //String searchType = request.getParameter("searchType");
	        List<Theater> theaters  = theaterDao.search(searchQuery);

			return new ModelAndView("theater-list", "theaters", theaters);
			
		
		} catch (TheaterException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", e.getMessage());
		}
	}
}
