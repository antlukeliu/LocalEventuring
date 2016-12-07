package com.malabanan.localeventuring;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

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

		
		return "home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {

		
		return "login";
	}
	
	@RequestMapping(value = "/accountpage", method = RequestMethod.POST)
	public String accountpage(Model model, HttpServletRequest request) {
		
		model.addAttribute("email", request.getParameter("email"));
		model.addAttribute("name", request.getParameter("name"));
		
		return "accountpage";
	}
	
	@RequestMapping(value = "/accountpage2", method = RequestMethod.GET)
	public String accountpage2(Model model, HttpServletRequest request) {
		
		model.addAttribute("email", request.getParameter("email"));
		model.addAttribute("name", request.getParameter("name"));
		
		return "accountpage2";
	}
	
	@RequestMapping(value = "/results", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request) {
		
		ArrayList<String> listofstr = new ArrayList<String>();
		for(int i=0; i < 21; i++){
			listofstr.add("this is a test");
		}
		
		
		model.addAttribute("test", listofstr);
		
		return "results";
	}

	@RequestMapping(value = "/addform", method = RequestMethod.GET)
	public String addform(Model model, HttpServletRequest request) {
		
		String str1 = request.getParameter("calendarlink");
		String calLink = "";
		
		if (calLink.startsWith("<")) {
			calLink = RandomMethods.gettingCalendar(str1);
		}
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				  "cloud_name", "localeventuring",
				  "api_key", "159929721183649",
				  "api_secret", "8RDgtenZPzOQURwBaVPwlPVb9PM"));
		
		String photo = request.getParameter("image");
	
		Venue venue = new Venue();
		

		try {
			
		Map result = cloudinary.uploader().upload(photo, ObjectUtils.emptyMap());
			
		String publicId = (String) result.get("url");
		
		logger.info(publicId);
		
		model.addAttribute("image", publicId);
		
		venue.setPhotoLink(publicId);
		
	
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		model.addAttribute("venuename", request.getParameter("venuename"));
		model.addAttribute("roomsize", request.getParameter("roomsize"));
		model.addAttribute("capacity", request.getParameter("capacity"));
		model.addAttribute("price", request.getParameter("price"));
		model.addAttribute("calendar", calLink);
		model.addAttribute("description", request.getParameter("description"));
		
		

		

		venue.setContactId(26);
		venue.setVenueName(request.getParameter("venuename"));
		venue.setRoomSize(Integer.parseInt(request.getParameter("roomsize")));
		venue.setCapacity(Integer.parseInt(request.getParameter("capacity")));
		venue.setPrice(Integer.parseInt(request.getParameter("price")));
		
		venue.setCalendarLink(calLink);
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
		contact.setAddress(request.getParameter("street") + request.getParameter("city") + request.getParameter("state")
				+ request.getParameter("zipcode"));

		DAOContact.addContact(contact);

		return "contactsummary";
	}

	
//	@RequestMapping(value = "/uploadphoto", method = RequestMethod.GET)
//	public String uploadPhoto(Model model, HttpServletRequest request) {
//
//		
//		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
//				  "cloud_name", "localeventuring",
//				  "api_key", "159929721183649",
//				  "api_secret", "8RDgtenZPzOQURwBaVPwlPVb9PM"));
	
//String photo = request.getParameter("image");
//		
//
//		try {
//			
//		Map result = cloudinary.uploader().upload(photo, ObjectUtils.emptyMap());
//		
//			// pull url from hash map generated by upload method 
//		String publicId = (String) result.get("url");
//		
//		// logger.info(publicId);
//		
//		model.addAttribute("image", publicId);
//		// logger.info("uploaded");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		//return "uploadphoto";
}
