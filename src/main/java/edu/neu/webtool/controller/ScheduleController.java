package edu.neu.webtool.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.neu.webtool.dao.AuditoriumDAO;
import edu.neu.webtool.dao.MovieDAO;
import edu.neu.webtool.dao.ScheduleDAO;
import edu.neu.webtool.dao.TheaterDAO;
import edu.neu.webtool.exception.ScheduleException;
import edu.neu.webtool.pojo.Auditorium;
import edu.neu.webtool.pojo.Movie;
import edu.neu.webtool.pojo.Schedule;
import edu.neu.webtool.pojo.Theater;

@Controller
@RequestMapping("/schedule/*")
public class ScheduleController {
	@Autowired
	@Qualifier("scheduleDao")
	ScheduleDAO scheduleDao;
	@Autowired
	@Qualifier("movieDao")
	MovieDAO movieDao;
	@Autowired
	@Qualifier("theaterDao")
	TheaterDAO theaterDao;
	@Autowired
	@Qualifier("auditoriumDao")
	AuditoriumDAO auditoriumDao;

	@RequestMapping(value="/schedule/add", method = RequestMethod.GET)
	public ModelAndView initializeForm(HttpServletRequest request) throws Exception {		
		String title = request.getParameter("title");
		Theater t = theaterDao.get(title);
		//System.out.println(t);
		//Auditorium auditorium=auditoriumDao.get(Long.parseLong(audiID));
		Schedule schedule = new Schedule();
//		schedule.setAuditorium(auditorium);
//		schedule.setRemain(auditorium.getSeat());e
		ModelAndView mv = new ModelAndView();
		Set<Auditorium> auditoriums=t.getAuditoriums();
		System.out.println(auditoriums);
		mv.addObject("auditoriums",auditoriums);
		mv.addObject("movies", movieDao.list());
		mv.addObject("schedule", schedule);
		mv.setViewName("schedule-form");
		return mv;
	}
//	@RequestMapping(value="/schedule/add", method = RequestMethod.GET)
//	public ModelAndView initializeForm(HttpServletRequest request) throws Exception {		
//		String audiID = request.getParameter("audiID");
//		Auditorium auditorium=auditoriumDao.get(Long.parseLong(audiID));
//		Schedule schedule = new Schedule();
//		schedule.setAuditorium(auditorium);
////		schedule.setRemain(auditorium.getSeat());
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("movies", movieDao.list());
//		mv.addObject("schedule", schedule);
//		mv.setViewName("schedule-form");
//		return mv;
//	}
	
//	@RequestMapping(value="/schedule/newadd", method = RequestMethod.GET)
//	public String initializeNewForm(HttpServletRequest request) throws Exception {		
//		return "audi-form";
//	}
	
//	@RequestMapping(value="/schedule/showAudiForm", method = RequestMethod.POST)
//	public void newaddSchedule(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		boolean flag = false;
//		Set<Auditorium> auditoriums=null;
//		try{
//			String theaterName = request.getParameter("theater");
//			Theater theater=theaterDao.get(theaterName);
//			flag=true;
//			auditoriums=theater.getAuditoriums();
//		}catch (Exception e) {
//			System.out.println("Exception: " + e.getMessage());
//			flag=false;
//		}
//		
//		if(auditoriums.isEmpty()){
//			flag=false;
//		}
//		
//		JSONObject obj = new JSONObject();
//		obj.put("flag", flag);
//		obj.put("auditoriums",auditoriums);
//		PrintWriter out = response.getWriter();
//		out.println(obj);
//	}
	
	@RequestMapping(value = "/schedule/add", method = RequestMethod.POST)
	public ModelAndView addSchedule(@ModelAttribute("schedule") Schedule schedule, BindingResult result,HttpServletRequest request) throws Exception {		
		try {			
			SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			System.out.println("date: "+request.getParameter("date"));
			Date d = df.parse(request.getParameter("date"));
			schedule.setDate(d);
			System.out.println("date format: "+d);
			df = new SimpleDateFormat("hh:mm");
			d = df.parse(request.getParameter("startTime"));
			schedule.setStartTime(d);
			System.out.println(schedule.getInAuditorium());
			Auditorium auditorium=auditoriumDao.get(Long.valueOf(schedule.getInAuditorium()));
			System.out.println(auditorium);
			schedule.setAuditorium(auditorium);//transient	
			Movie movie = movieDao.get(schedule.getOnMovie());
			schedule.setMovie(movie);//transient	
			schedule = scheduleDao.create(schedule);
			System.out.println(movie);
			System.out.println(movie.getSchedules());
			return new ModelAndView("schedule-success", "schedule", schedule);			
		} catch (ScheduleException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage",  e.getMessage());
		}		
	}
	
	@RequestMapping(value="/schedule/list", method = RequestMethod.GET)
	public ModelAndView listScheduleOfACertainTheater(HttpServletRequest request) throws Exception {		
		String theaterName = request.getParameter("theaterName");
		String title = request.getParameter("title");
		Theater theater = theaterDao.get(theaterName);
		System.out.println(title);
		System.out.println(theaterName);

		List<Schedule> schedules = scheduleDao.getByTitleAndTheater(title,theater);
		ModelAndView mv = new ModelAndView();
		mv.addObject("schedules", schedules);
		mv.addObject("title", title);
		mv.addObject("theaterName", theaterName);
		mv.setViewName("order-form");
		return  mv;
	}
}
