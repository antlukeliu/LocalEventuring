package com.malabanan.localeventuring;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	public String login(Model model, HttpServletRequest request) {
		
		HttpSession sessionClear = request.getSession(true);
		sessionClear.invalidate();

		return "login";
	}

	@RequestMapping(value = "/accountpage", method = RequestMethod.POST)
	public String accountpage(Model model, HttpServletRequest request) {

		model.addAttribute("email", request.getParameter("email"));
		model.addAttribute("fullname", request.getParameter("fullname"));
		model.addAttribute("id", request.getParameter("id"));

		
		if (request.getParameter("id").equals("")) {
			return "login";
		} else {
			String token = request.getParameter("id");
			HttpSession session = request.getSession(true);
			session.setAttribute("name", token);
		}
		
		return "accountpage";
	}

	@RequestMapping(value = "/accountpage", method = RequestMethod.GET)
	public String accountpage2(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		String name = (String) session.getAttribute("name");
		if (name == null){
			return "login";
		}

		/*if (request.getParameter("id").equals("")) {
			return "login";
		} else {
			String token = request.getParameter("id");
			GoogleSignIn.setTokenId(token);
		}*/
		int contactId = 1;
		List<Venue> venues = DAOVenue.getVenues("FROM Venue Where contactId =" + contactId);
		model.addAttribute("email", request.getParameter("email"));
		model.addAttribute("fullname", request.getParameter("fullname"));
		model.addAttribute("venuesOwned", venues);
		
		return "accountpage";
	}

	@RequestMapping(value = "/results", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request) {

		List<Venue> venues = new ArrayList<Venue>();

		String price = "";
		if (request.getParameter("price") != null) {
			price = request.getParameter("price");
		}
		String capacity = "";
		if (request.getParameter("people") != null) {
			capacity = request.getParameter("people");
		}
		
		String[] filters = { price,  capacity};
		String queryString = SearchAlg.getQueryString(filters);
		venues = DAOVenue.getVenues(queryString);

		model.addAttribute("venueList", venues);

		return "results";
	}

	@RequestMapping(value = "/addform", method = RequestMethod.POST)
	public String addform(Model model, HttpServletRequest request, @RequestParam("file") MultipartFile file) {

		HttpSession session = request.getSession(false);
		String name = (String) session.getAttribute("name");
		
		if (name == null){
			return "login";
		}
		
		String str1 = request.getParameter("calendarlink");
		String calLink = "";
		
		String urlPic = "";
		System.out.println("upload: " + file.getOriginalFilename());

		if (str1.startsWith("<")) {
			calLink = RandomMethods.gettingCalendar(str1);
		}
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", PhotoUpload.getCloudName(), "api_key",
				PhotoUpload.getApiKey(), "api_secret", PhotoUpload.getApiSecret()));


		Venue venue = new Venue();
		
		try {
			Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
			urlPic = (String) uploadResult.get("url");
			// hmodel.setPictureurl(urlPic);
			model.addAttribute("image", urlPic);
			
			venue.setPhotoLink(urlPic);
			System.out.println(urlPic);
		} catch (IOException e) {
			e.printStackTrace();
		}

		model.addAttribute("venuename", request.getParameter("venuename"));
		model.addAttribute("roomsize", request.getParameter("roomsize"));
		model.addAttribute("capacity", request.getParameter("capacity"));
		model.addAttribute("price", request.getParameter("price"));
		model.addAttribute("category", request.getParameter("category"));
		model.addAttribute("calendar", calLink);
		model.addAttribute("description", request.getParameter("description"));

		venue.setContactId(26);
		venue.setVenueName(request.getParameter("venuename"));
		venue.setRoomSize(Integer.parseInt(request.getParameter("roomsize")));
		venue.setCapacity(Integer.parseInt(request.getParameter("capacity")));
		venue.setPrice(Integer.parseInt(request.getParameter("price")));
		venue.setCategory(request.getParameter("category"));
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

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String viewBook(@RequestParam("venueId") int venueId, Model model, HttpServletRequest request) {

		HttpSession session = request.getSession(false);
		String name = (String) session.getAttribute("name");
		
		if (name == null){
			return "login";
		}
		
		List<Venue> venues = DAOVenue.getVenues("From Venue");

		if (venueId < 1) {
			return "results";
		}

		int rankNum = venueId;
		String venueName = "";
		int roomSize = 0;
		int capacity = 0;
		int price = 0;
		String photoLink = "";
		String calendarLink = "";
		String description = "";

		for (Venue v : venues) {
			if (v.getVenueId() == venueId) {
				rankNum = venueId;
				venueName = v.getVenueName();
				roomSize = v.getRoomSize();
				capacity = v.getCapacity();
				price = v.getPrice();
				photoLink = v.getPhotoLink();
				calendarLink = v.getCalendarLink();
				description = v.getDescription();
				break;
			}
		}
		
		if(venueName.equals("")){
			return "home";
		}

		model.addAttribute("rank", rankNum);
		model.addAttribute("venuename", venueName);
		model.addAttribute("roomsize", roomSize);
		model.addAttribute("capacity", capacity);
		model.addAttribute("price", price);
		model.addAttribute("photolink", photoLink);
		model.addAttribute("calendarlink", calendarLink);
		model.addAttribute("description", description);

		return "profile";
	}

	@RequestMapping(value = "/aboutus", method = RequestMethod.GET)
	public String aboutUs(){
		
		return "aboutus";
	}
	
	// @RequestMapping(value = "/uploadphoto", method = RequestMethod.GET)
	// public String uploadPhoto(Model model, HttpServletRequest request) {
	//
	//
	// Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
	// "cloud_name", "localeventuring",
	// "api_key", "159929721183649",
	// "api_secret", "8RDgtenZPzOQURwBaVPwlPVb9PM"));

	// String photo = request.getParameter("image");
	//
	//
	// try {
	//
	// Map result = cloudinary.uploader().upload(photo, ObjectUtils.emptyMap());
	//
	// // pull url from hash map generated by upload method
	// String publicId = (String) result.get("url");
	//
	// // logger.info(publicId);
	//
	// model.addAttribute("image", publicId);
	// // logger.info("uploaded");
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }

	// return "uploadphoto";
}
