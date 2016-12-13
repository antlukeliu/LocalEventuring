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

		HttpSession session;
		
		if (request.getParameter("id").equals("")) {
			return "login";
		} else {
			String email = request.getParameter("email");
			String fullname = request.getParameter("fullname");
			session = request.getSession(true);
			session.setAttribute("name", email);
			session.setAttribute("fullname", fullname);
		}
		
		//Fix this 
		String email = (String) session.getAttribute("name");
		int contactId = findingContactId(email);
		
		if(contactId == 0){
			model.addAttribute("error", "please sign in and hit continue");
		}
		
		List<Contact> contacts = DAOContact.getContacts("From Contact");
		for(Contact c: contacts){
			if(c.getEmail().equals(email)){
			contactId = c.getContactId();
			List<Venue> venues = DAOVenue.getVenues("FROM Venue Where contactId =" + contactId);
			model.addAttribute("venuesOwned", venues);
		}
		}
		model.addAttribute("email", request.getParameter("email"));
		model.addAttribute("fullname", request.getParameter("fullname"));
		
		
		
		return "accountpage";
	}

	@RequestMapping(value = "/accountpage", method = RequestMethod.GET)
	public String accountpage2(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession(true);
		String email = (String) session.getAttribute("name");
		String fullname = (String) session.getAttribute("fullname");
		if (email == null){
			return "login";
		}

	
		int contactId = findingContactId(email);
		
		
		if(contactId == 0){
			model.addAttribute("error", "please sign in and hit continue");
		}
		
		List<Contact> contacts = DAOContact.getContacts("From Contact");
		for(Contact c: contacts){
			if(c.getEmail().equals(email)){
			contactId = c.getContactId();
			List<Venue> venues = DAOVenue.getVenues("FROM Venue Where contactId =" + contactId);
			model.addAttribute("venuesOwned", venues);
			}
		}
		model.addAttribute("email", email);
		model.addAttribute("fullname", fullname);
		
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

		HttpSession session = request.getSession(true);
		String name = (String) session.getAttribute("name");
		
		
		if (name == null){
			return "login";
		}
		
		String venuename = request.getParameter("venuename");
		int roomsize = Integer.parseInt(request.getParameter("roomsize"));
		int capacity = Integer.parseInt(request.getParameter("capacity"));
		int price = Integer.parseInt(request.getParameter("price"));
		String category = request.getParameter("category");
		String str1 = request.getParameter("calendarlink");
		String calLink = "";
		String description = request.getParameter("description");
		String fullname = request.getParameter("fullname");
		String loginemail = request.getParameter("email");
		String address = request.getParameter("street");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zipcode = request.getParameter("zipcode");
		
		//Creating calLink
		if (str1.startsWith("<")) {
			calLink = RandomMethods.gettingCalendar(str1);
		}
		
		//Creating a new contact if it does not exist
		Contact contact = new Contact();
		int contactId = findingContactId(loginemail);
		
		if(contactId == 0){
			contact.setEmail(loginemail);
			contact.setName(fullname);
			DAOContact.addContact(contact);
		}
		
		contactId = findingContactId(loginemail);
		
		//Using Cloudinary to store an image
		
		
		String urlPic = "";
		System.out.println("upload: " + file.getOriginalFilename());

		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", PhotoUpload.getCloudName(), "api_key",
				PhotoUpload.getApiKey(), "api_secret", PhotoUpload.getApiSecret()));

		Venue venue = new Venue();
		
		try {
			Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
			urlPic = (String) uploadResult.get("url");
			
			System.out.println(urlPic);
			model.addAttribute("image", urlPic);
			
			venue.setPhotoLink(urlPic);
			System.out.println(urlPic);
		} catch (IOException e) {
			e.printStackTrace();
		}

		modelAdding(model,0,venuename,roomsize,capacity, 
				price, category, urlPic, calLink, description,
				address, city, state, zipcode);
		
		venue = settingVenue(contactId,venuename,roomsize,
				capacity, price, category, urlPic, calLink, description, 
				address, city, state, zipcode);
		
		DAOVenue.addVenue(venue);

		return "addresults";
	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String viewProfile(@RequestParam("venueId") int venueId, Model model, HttpServletRequest request) {

		HttpSession session = request.getSession(true);
		String name = (String) session.getAttribute("name");
		
		if (name == null){
			return "login";
		}
		
		List<Venue> venues = DAOVenue.getVenues("From Venue");

		if (venueId < 1) {
			model.addAttribute("venueList", venues);
			return "results";
		}

		int rankNum = venueId;
		String venueName = "";
		int roomSize = 0;
		int capacity = 0;
		int price = 0;
		String category ="";
		String photoLink = "";
		String calendarLink = "";
		String description = "";
		String street = "";
		String city = "";
		String state = "";
		String zipcode = "";

		for (Venue v : venues) {
			if (v.getVenueId() == venueId) {
				venueName = v.getVenueName();
				roomSize = v.getRoomSize();
				capacity = v.getCapacity();
				price = v.getPrice();
				category = v.getCategory();
				photoLink = v.getPhotoLink();
				calendarLink = v.getCalendarLink();
				description = v.getDescription();
				street = v.getStreet();
				city = v.getCity();
				state = v.getState();
				zipcode = v.getZipcode();
				break;
			}
		}
		
		if(venueName.equals("")){
			return "home";
		}

		modelAdding(model,rankNum,venueName,roomSize,capacity, 
				price, category, photoLink, calendarLink, description,
				street, city, state, zipcode);
		return "profile";
	}

	@RequestMapping(value = "/aboutus", method = RequestMethod.GET)
	public String aboutUs(){
		
		return "aboutus";
	}
	
	@RequestMapping(value = "/caldirections", method = RequestMethod.GET)
	public String caldirections(){
		
		return "caldirections";
	}
	
	@RequestMapping(value = "/updateinfo", method = {RequestMethod.GET, RequestMethod.POST})
	public String viewupdate(@RequestParam("venueId") int venueId, Model model, HttpServletRequest request){
		
		HttpSession session = request.getSession(true);
		String name = (String) session.getAttribute("name");
		//String email
		
		List<Venue> venues = DAOVenue.getVenues("From Venue");
		
		if (name == null){
			return "login";
		}
		
		if (venueId < 1) {
			model.addAttribute("venueList", venues);
			return "results";
		}
		
		int rankNum = venueId;
		int contactId = 0;
		String venueName = "";
		int roomSize = 0;
		int capacity = 0;
		int price = 0;
		String category ="";
		String photoLink = "";
		String calendarLink = "";
		String description = "";
		String street = "";
		String city = "";
		String state = "";
		String zipcode = "";

		for (Venue v : venues) {
			if (v.getVenueId() == venueId) {
				venueName = v.getVenueName();
				contactId = v.getContactId();
				roomSize = v.getRoomSize();
				capacity = v.getCapacity();
				price = v.getPrice();
				category = v.getCategory();
				photoLink = v.getPhotoLink();
				calendarLink = v.getCalendarLink();
				description = v.getDescription();
				street = v.getStreet();
				city = v.getCity();
				state = v.getState();
				zipcode = v.getZipcode();
				break;
			}
		}
		
		List<Contact> contacts = DAOContact.getContacts("From Contact where contactId =" +contactId);		

		for(Contact c: contacts){
			if(c.getEmail().equalsIgnoreCase(name)){
				continue;
			}else {
				return "accessdenied";
			}
		}
		
		model.addAttribute("email", name);
		model.addAttribute("contactid", contactId);
		modelAdding(model,rankNum,venueName,roomSize,capacity, 
				price, category, photoLink, calendarLink, description,
				street, city, state, zipcode);
		
		return "updateinfo";
	}
	
	
	@RequestMapping(value = "/viewupdate", method = RequestMethod.GET)
	public String ViewUpdate(Model model, HttpServletRequest request,  @RequestParam("file") MultipartFile file) {
		
		//Fix this method
		HttpSession session = request.getSession(true);
		String email = (String) session.getAttribute("name");
		
		if (email == null){
			return "login";
		}
		
		List<Venue> venues = DAOVenue.getVenues("From Venue");
		
		//Make sure I get the integer in update form
		int venueId = Integer.parseInt(request.getParameter("numId"));
		String venuename = request.getParameter("venuename");
		int contactId = Integer.parseInt(request.getParameter("contactid"));
		int roomsize = Integer.parseInt(request.getParameter("roomsize"));
		int capacity = Integer.parseInt(request.getParameter("capacity"));
		int price = Integer.parseInt(request.getParameter("price"));
		String category = request.getParameter("category");
		String str1 = request.getParameter("calendarlink");
		String calLink = "";
		String description = request.getParameter("description");
		String fullname = request.getParameter("fullname");
		String loginemail = request.getParameter("email");
		String address = request.getParameter("street");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zipcode = request.getParameter("zipcode");
		
		
		
		String urlPic = "";
		System.out.println("upload: " + file.getOriginalFilename());

		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", PhotoUpload.getCloudName(), "api_key",
				PhotoUpload.getApiKey(), "api_secret", PhotoUpload.getApiSecret()));

		Venue venue = new Venue();
		
		try {
			Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
			urlPic = (String) uploadResult.get("url");
			
			System.out.println(urlPic);
			model.addAttribute("image", urlPic);
			
			venue.setPhotoLink(urlPic);
			System.out.println(urlPic);
		} catch (IOException e) {
			e.printStackTrace();
		}

		venue = settingVenue(contactId,venuename,roomsize,
				capacity, price, category, urlPic, calLink, description, 
				address, city, state, zipcode);
		
		DAOVenue.updateVenue(venue);
			
		if(venuename.equals("")){
			return "home";
		}

		//modelAdding(model,rankNum,venueName,roomSize,capacity, 
		//		price, category, photoLink, calendarLink, description);
		
		return "viewupdate";
	}
	
	@RequestMapping(value = "/deletevenue", method = RequestMethod.POST)
	public String deleteBook(@RequestParam("rank") int rank,Model model, HttpServletRequest request){
		HttpSession session = request.getSession(true);
		String email = (String) session.getAttribute("name");
		
		if (email == null){
			return "login";
		}
		
		
		
		DAOVenue.deleteVenue(rank);
		List<Venue> venues = DAOVenue.getVenues("FROM Venue");
		
		
		model.addAttribute("bookList", venues);

		return "list";
	}
	
	//Methods to shorten amount of code used
	
	private static void modelAdding(Model model, int rankNum, String venueName, 
			int roomSize, int capacity, int price, String category, 
			String photoLink, String calendarLink, String description,
			String address, String city, String state, String zipcode){
		
		model.addAttribute("venueid", rankNum);
		model.addAttribute("venuename", venueName);
		model.addAttribute("roomsize", roomSize);
		model.addAttribute("capacity", capacity);
		model.addAttribute("price", price);
		model.addAttribute("category", category);
		model.addAttribute("photolink", photoLink);
		model.addAttribute("calendarlink", calendarLink);
		model.addAttribute("description", description);
		model.addAttribute("street", address);
		model.addAttribute("city", city);
		model.addAttribute("state", state);
		model.addAttribute("zipcode", zipcode);
	}
	
	private static int findingContactId(String email){
		List<Contact> contacts = DAOContact.getContacts("From Contact");
		int contactId = 0;
		
		for(Contact c: contacts){
			if (c.getEmail().equals(email)){
				contactId = c.getContactId();
			}
		}
	
		return contactId;
	}

	private static Venue settingVenue(int contactId, String venueName, int roomSize,
			int capacity, int price, String category, String urlPic,
			String calLink, String description, String address, String city,
			String state, String zipcode){
		
		Venue venue = new Venue();
		venue.setContactId(contactId);
		venue.setVenueName(venueName);
		venue.setRoomSize(roomSize);
		venue.setCapacity(capacity);
		venue.setPrice(price);
		venue.setCategory(category);
		venue.setPhotoLink(urlPic);
		venue.setCalendarLink(calLink);
		venue.setDescription(description);
		venue.setStreet(address);
		venue.setCity(city);
		venue.setState(state);
		venue.setZipcode(zipcode);

		return venue;
	}
	
	
}