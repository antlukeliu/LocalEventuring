package com.malabanan.localeventuring;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		
		Venue venue = new Venue();
		
		venue.setContactId(222);
		venue.setVenueName("GC");
		venue.setRoomSize(3242);
		venue.setCapacity(3);
		venue.setPrice(342);
		venue.setPhotoLink("43");
		venue.setCalendarLink("dfadf");
		venue.setDescription("4ksdjf;a");
		
		DAOVenue.addVenue(venue);
		
		return "home";
	}
	@RequestMapping(value = "/addform", method = RequestMethod.GET)
	public String addform(Model model, HttpServletRequest request) {
		
		model.addAttribute("venuename", request.getParameter("venuename"));
		model.addAttribute("roomsize", request.getParameter("roomsize"));
		model.addAttribute("capacity", request.getParameter("capacity"));
		model.addAttribute("price", request.getParameter("price"));
		model.addAttribute("photolink", request.getParameter("photolink"));
		model.addAttribute("calendarlink", request.getParameter("calendarlink"));
		model.addAttribute("description", request.getParameter("description"));
		
		Venue venue = new Venue(); 
		
		// venue.setContactId(222);
		venue.setVenueName(request.getParameter("venuename"));
		venue.setRoomSize(Integer.parseInt(request.getParameter("roomsize")));
		venue.setCapacity(Integer.parseInt(request.getParameter("capacity")));
		venue.setPrice(Integer.parseInt(request.getParameter("price")));
		venue.setPhotoLink(request.getParameter("photolink"));
		venue.setCalendarLink(request.getParameter("calendarlink"));
		venue.setDescription(request.getParameter("description"));
		
		DAOVenue.addVenue(venue);
		
		return "addresults";
	}
	
	@RequestMapping(value = "/addcontact", method = RequestMethod.GET)
	public String addcontact(Model model, HttpServletRequest request) {
		
		model.addAttribute("firstname", request.getParameter("firstname"));
		model.addAttribute("lastname", request.getParameter("lastname"));
		model.addAttribute("email", request.getParameter("email"));
		model.addAttribute("street", request.getParameter("street"));
		model.addAttribute("city", request.getParameter("city"));
		model.addAttribute("state", request.getParameter("state"));
		model.addAttribute("zipcode", request.getParameter("zipcode"));
		
		Contact contact = new Contact(); 
		
		// venue.setContactId(222);
		contact.setName(request.getParameter("firstname") + request.getParameter("lastname"));
		contact.setEmail(request.getParameter("email"));
		contact.setAddress(request.getParameter("street") + request.getParameter("city") 
			+ request.getParameter("state") + request.getParameter("zipcode"));

		
		DAOContact.addContact(contact);
		
		return "contactsummary";
}
}
