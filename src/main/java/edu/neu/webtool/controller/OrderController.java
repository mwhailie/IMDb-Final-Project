package edu.neu.webtool.controller;

import java.io.PrintWriter;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import edu.neu.webtool.dao.AuditoriumDAO;
import edu.neu.webtool.dao.MovieDAO;
import edu.neu.webtool.dao.OrderDAO;
import edu.neu.webtool.dao.ScheduleDAO;
import edu.neu.webtool.dao.TicketDAO;
import edu.neu.webtool.dao.UserDAO;
import edu.neu.webtool.pojo.Auditorium;
import edu.neu.webtool.pojo.Movie;
import edu.neu.webtool.pojo.Order;
import edu.neu.webtool.pojo.Schedule;
import edu.neu.webtool.pojo.Ticket;
import edu.neu.webtool.pojo.User;
import edu.neu.webtool.view.MyView;

@Controller
@RequestMapping("/order/*")
public class OrderController {
	@Autowired
	@Qualifier("orderDao")
	OrderDAO orderDao;
	
	@Autowired
	@Qualifier("scheduleDao")
	ScheduleDAO scheduleDao;
	
	@Autowired
	@Qualifier("ticketDao")
	TicketDAO ticketDao;
	
	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;
	
	@Autowired
	@Qualifier("movieDao")
	MovieDAO movieDao;
	
	@Autowired
	@Qualifier("auditoriumDao")
	AuditoriumDAO auditoriumDao;
	
	@RequestMapping(value="/order/add", method = RequestMethod.GET)
	public String initializeForm(HttpServletRequest request) throws Exception {			
		return "order-form";
	}
	

	@RequestMapping(value="/order/add", method = RequestMethod.POST)
	public ModelAndView placeOrder(HttpServletRequest request) throws Exception {			
		String num = request.getParameter("number");		
		int number = Integer.parseInt(num);
		String schedule = request.getParameter("scheduleID");
		Long scheduleID = Long.parseLong(schedule);
		int totalprice = (int)Double.parseDouble(request.getParameter("totalprice"));
		Schedule s = scheduleDao.get(scheduleID);
		int remain = 0;
		for(Ticket t:s.getTickets()){
			if(!t.isSold()) remain++;
		}
		
		System.out.println("remain" +remain);
		if(number>remain){
			System.out.println("ticket not enough");
			return new ModelAndView("error","errorMessage","sorry, we do not have enough ticket for now, please try another one");
		}
		
		User user = (User) request.getSession().getAttribute("user");
		//User user2 = userDao.getByUsername((request.getParameter("name")));
		Order order = new Order();
		
		order.setTotal(totalprice);
		order.setUser(user);
		orderDao.create(order);
		Set<Ticket> tickets = new HashSet<Ticket>();
		for(int i =0; i <number;i++){
			int seat=s.getAuditorium().getSeat()-remain+1;
			Ticket t =ticketDao.getTicketBySchedule(scheduleID,seat);
			//ticketDao.getTicketBySchedule(scheduleID,seat);
			t.setSold(true);
			ticketDao.update(t);
			//order.getTickets().add(t);			
			tickets.add(t);
		}
		
		//Order order = new Order();
		//orderDao.create(order);
		//order.setTotal(totalprice);
		//order.setUser(u);
		//orderDao.update(order);
		//Order order = orderDao.placeOrder(user, totalprice);
		
		for(Ticket t :tickets){
			order.getTickets().add(t);
			orderDao.update(order);
		}
		Movie m = s.getMovie();
		Auditorium a = s.getAuditorium();
		ModelAndView mv = new ModelAndView();
		mv.addObject("order",order);
		mv.addObject("tickets",tickets);
		mv.addObject("ticketNum",tickets.size());
		mv.addObject("schedule",s);
		mv.addObject("movie",m);
		mv.addObject("auditorium",a);
		mv.setViewName("order-success");
		return mv;
	}
	
	@RequestMapping(value="/order/email", method = RequestMethod.POST)
	public void sendEmail(HttpServletRequest request, HttpServletResponse response) throws Exception {
			System.out.println("sending");
			
			final String username = "webtools2017spring@gmail.com";
			final String password = "springmvc";
			User user = (User) request.getSession().getAttribute("user");
			String ticketNum = request.getParameter("ticketNum");
			String orderID = request.getParameter("orderID");
			String auditorium = request.getParameter("auditorium");
			String scheduleID = request.getParameter("scheduleID");
			String movie = request.getParameter("movie");
//			Movie movie = movieDao.getByID(Long.parseLong(movieID));
//			Auditorium auditorium = auditoriumDao.get(Long.parseLong(movieID));
			Schedule s = scheduleDao.get(Long.parseLong(scheduleID));
			Order order = orderDao.get(Long.parseLong(orderID));
			boolean flag = false;
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");

			Session session = Session.getInstance(props,
			  new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					
					return new PasswordAuthentication(username, password);
				}
			  });
				
			try {
				
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("from-email@gmail.com"));
				message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(user.getEmail().getEmailAddress()));
				message.setSubject("Testing Subject");

				String mailM= new String("Dear " +user.getUsername()+", "
						+ "\n\n Thank you for shopping with us. Your order "+ order+ " has bean successfully place!"
						+ "\n\n Order detail:"
						+ "\n\n "+order
						+ "\n Total Price:"+order.getTotal()
						+ "\n Ticket Number:"+ticketNum+"\n auditorium: "+auditorium +"\n time: "+s.getStartTime()+"\n movie: "+movie);

				//mailM.concat("\n auditorium: "+auditorium +"\n time: "+s.getStartTime()+"\n movie: "+movie);

				message.setText(mailM);
				Transport.send(message);
				flag=true;
				System.out.println("Done");
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
			JSONObject obj = new JSONObject();
			obj.put("flag", flag);
			PrintWriter out = response.getWriter();
			out.println(obj);
	}
	@RequestMapping(value="/order/pdfticket", method = RequestMethod.GET)
	public ModelAndView showpdf(HttpServletRequest request) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		String ticketNum = request.getParameter("ticketNum");
		String orderID = request.getParameter("orderID");
		String auditorium = request.getParameter("auditorium");
		String scheduleID = request.getParameter("scheduleID");
		String movie = request.getParameter("movie");
//		Movie movie = movieDao.getByID(Long.parseLong(movieID));
//		Auditorium auditorium = auditoriumDao.get(Long.parseLong(movieID));
		Schedule s = scheduleDao.get(Long.parseLong(scheduleID));
		Order order = orderDao.get(Long.parseLong(orderID));
		

		String paragraph= new String("\n\nDear " +user.getUsername()+", "
				+ "\n\n Your order "+ order+ " has bean successfully place!"
				+ "\n\n Order detail:"
				+ "\n "+order
				+ "\n Total Price:"+order.getTotal()
				+ "\n Ticket Number:"+ticketNum
				+ "\n auditorium: "+auditorium +"\n time: "+s.getStartTime()+"\n movie: "+movie);

		View view = new MyView(paragraph);
		return new ModelAndView(view);
	}
}
