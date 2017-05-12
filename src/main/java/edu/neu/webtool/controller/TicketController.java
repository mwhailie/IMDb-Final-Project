package edu.neu.webtool.controller;

import java.io.PrintWriter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.neu.webtool.dao.ScheduleDAO;
import edu.neu.webtool.dao.TicketDAO;

import edu.neu.webtool.pojo.Schedule;
import edu.neu.webtool.pojo.Ticket;

@Controller
@RequestMapping("/ticket/*")
public class TicketController {
	@Autowired
	@Qualifier("scheduleDao")
	ScheduleDAO scheduleDao;
	@Autowired
	@Qualifier("ticketDao")
	TicketDAO ticketDao;
	
	@RequestMapping(value="/ticket/add", method = RequestMethod.GET)
	public ModelAndView initializeForm(HttpServletRequest request) throws Exception {			
		//Auditorium auditorium=auditoriumDao.get(Long.parseLong(audiID));
		ModelAndView mv = new ModelAndView();
		mv.addObject("schedules", scheduleDao.list());
		mv.setViewName("ticket-form");
		return mv;
	}
	
	@RequestMapping(value="/ticket/add", method = RequestMethod.POST)
	public void setTicketPrice(HttpServletRequest request, HttpServletResponse response) throws Exception {			
		//Auditorium auditorium=auditoriumDao.get(Long.parseLong(audiID));
		boolean flag = false;
		
		try{
		String p = request.getParameter("price");
		int price = Integer.parseInt(p);
		
		String scheduleID = request.getParameter("scheduleID");
		Schedule schedule = scheduleDao.get(Long.parseLong(scheduleID));
		System.out.println(schedule.getAuditorium().getSeat());
		
			schedule.setTicketprice(price);
			scheduleDao.update(schedule);
			if(schedule.getTickets().size()!=0){
				System.out.println(schedule.getTickets().size());
				flag = false;
				JSONObject json = new JSONObject();
				json.put("flag", flag);
				PrintWriter out = response.getWriter();
				out.print(json);
				return;
			}
			for(int i =1;i<=schedule.getAuditorium().getSeat();i++){
				Ticket ticket = new Ticket();
				ticket.setPrice(price);
				ticket.setSchedule(schedule);
				ticket.setSeat(i);
				ticketDao.create(ticket);
			}
			
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		
		JSONObject json = new JSONObject();
		json.put("flag", flag);
		PrintWriter out = response.getWriter();
		out.print(json);
	}
}
