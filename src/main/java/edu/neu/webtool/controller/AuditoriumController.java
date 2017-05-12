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

import edu.neu.webtool.dao.AuditoriumDAO;
import edu.neu.webtool.dao.TheaterDAO;
import edu.neu.webtool.exception.AuditoriumException;
import edu.neu.webtool.pojo.Auditorium;
import edu.neu.webtool.pojo.Movie;
import edu.neu.webtool.pojo.Theater;


@Controller
@RequestMapping("/auditorium/*")
public class AuditoriumController {

	
	@Autowired
	@Qualifier("auditoriumDao")
	AuditoriumDAO auditoriumDAO;

	@Autowired
	@Qualifier("theaterDao")
	TheaterDAO theaterDao;
	@RequestMapping(value="/auditorium/add", method = RequestMethod.GET)
	public ModelAndView showForm(HttpServletRequest request) throws Exception {

		 String title = request.getParameter("title");
		 Theater t = theaterDao.get(title);
		 ModelAndView mv = new ModelAndView();
		 mv.setViewName("auditorium-form");
		 mv.addObject("auditorium", new Auditorium());
		 mv.addObject("theater", t);
		//return new ModelAndView("auditorium-form", "theater", t);
		 return mv;
	}
	@RequestMapping(value = "/auditorium/add", method = RequestMethod.POST)
	public ModelAndView addAuditorium(@ModelAttribute("auditorium") Auditorium auditorium,HttpServletRequest request) throws Exception {
		try {		
			String theaterID = request.getParameter("theaterID");
			Theater t = theaterDao.getByID(Long.parseLong(theaterID));

//			String title = request.getParameter("title");
//			Theater t = theaterDao.get(title);
			t.setAudiNum(t.getAudiNum()+1);
			auditorium.setTheater(t);//transient
//			auditorium.setRemain(auditorium.getSeat());
			auditorium = auditoriumDAO.create(auditorium);
		} catch (AuditoriumException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", e.getMessage());
		}
		List<Theater> theaters = theaterDao.list();
		return new ModelAndView("theater-manage", "theaters", theaters);
		
	}
}
