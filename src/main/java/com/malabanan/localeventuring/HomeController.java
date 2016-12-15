package com.malabanan.localeventuring;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;


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

		model.addAttribute("url", Geocode.getLoginKey());
		
		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, HttpServletRequest request) {
		
		model.addAttribute("url", Geocode.getLoginKey());
		
		HttpSession sessionClear = request.getSession(true); 
		sessionClear.invalidate(); //delete session when you enter login

		return "login";
	}

	@RequestMapping(value = "/accountpage", method = RequestMethod.POST)
	public String accountpage(Model model, HttpServletRequest request) {

		model.addAttribute("email", request.getParameter("email"));
		model.addAttribute("fullname", request.getParameter("fullname"));
		model.addAttribute("id", request.getParameter("id"));

		HttpSession session;
		
		if (request.getParameter("id").equals("")) {
			model.addAttribute("url", Geocode.getLoginKey());
			return "login";
		} 
			String email = request.getParameter("email").toLowerCase();
			String fullname = request.getParameter("fullname");
			session = request.getSession(true);
			session.setAttribute("loginemail", email);
			session.setAttribute("fullname", fullname);
		
		
		//Fix this -- have to create contact is contactID == 0;
		int contactId = findingContactId(email);
		
		Contact newContact = new Contact();
		
		if(contactId == 0){
			newContact.setName(fullname);
			newContact.setEmail(email);
		}
		
		List<Contact> contacts = DAOContact.getContacts("From Contact");
		for(Contact c: contacts){
			if(c.getEmail().equalsIgnoreCase(email)){
			contactId = c.getContactId();
			List<Venue> venues = DAOVenue.getVenues("FROM Venue Where contactId =" + contactId);
			model.addAttribute("venuesOwned", venues);
			}
		}
		model.addAttribute("email", request.getParameter("email"));
		model.addAttribute("fullname", request.getParameter("fullname"));
		model.addAttribute("id", request.getParameter("id"));
		model.addAttribute("url", Geocode.getLoginKey());
		return "accountpage";
	}

	@RequestMapping(value = "/accountpage", method = RequestMethod.GET)
	public String accountpage2(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession(true);
		String email = (String) session.getAttribute("loginemail");
		String fullname = (String) session.getAttribute("fullname");
	
		if (email == null){
			model.addAttribute("url", Geocode.getLoginKey());
			return "login";
		}

		int contactId = findingContactId(email);
		
		Contact newContact = new Contact();
		
		if(contactId == 0){
			newContact.setName(fullname);
			newContact.setEmail(email);
		}
		
		List<Contact> contacts = DAOContact.getContacts("From Contact");
		for(Contact c: contacts){
			if(c.getEmail().equalsIgnoreCase(email)){
			contactId = c.getContactId();
			List<Venue> venues = DAOVenue.getVenues("FROM Venue Where contactId =" + contactId);
			model.addAttribute("venuesOwned", venues);
			}
		}
		model.addAttribute("email", email);
		model.addAttribute("fullname", fullname);
		model.addAttribute("url", Geocode.getLoginKey());
		return "accountpage";
	}

	@RequestMapping(value = "/searchresults", method = RequestMethod.GET)
	public String searchresults(Model model, HttpServletRequest request) {

		List<Venue> venues = new ArrayList<Venue>();

		String price = "";
		if (request.getParameter("price") != null) {
			price = request.getParameter("price");
		}
		String capacity = "";
		if (request.getParameter("people") != null) {
			capacity = request.getParameter("people");
		}
		
		String category = "";
		if (request.getParameter("category") != null) {
			category = request.getParameter("category");
		}
		
		String[] filters = { price,  capacity, category};
		String queryString = SearchAlg.getQueryString(filters);
		venues = DAOVenue.getVenues(queryString);

		model.addAttribute("venueList", venues);

		return "searchresults";
	}

	@RequestMapping(value = "/addform", method = RequestMethod.POST)
	public String addform(Model model, HttpServletRequest request, @RequestParam("file") MultipartFile file) {

		HttpSession session = request.getSession(true);
		String email = (String) session.getAttribute("loginemail");
		String urlPic = "";
		Venue venue = new Venue();
		
		if (email == null){
			model.addAttribute("url", Geocode.getLoginKey());
			return "login";
		}
		
		String venueName = request.getParameter("venueName");
		int roomSize = Integer.parseInt(request.getParameter("roomSize"));
		int capacity = Integer.parseInt(request.getParameter("capacity"));
		int price = Integer.parseInt(request.getParameter("price"));
		String category = request.getParameter("category");
		String str1 = request.getParameter("calendarLink");
		String calLink = "";
		String description = request.getParameter("description");
		String fullName = request.getParameter("fullName");
		String loginEmail = request.getParameter("email");
		String address = request.getParameter("street");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zipCode = request.getParameter("zipCode");
		
		//Creating calLink
		if (str1.startsWith("<")) {
			calLink = CalMethods.gettingCalendar(str1);
		}
		
		//Creating a new contact if it does not exist
		Contact contact = new Contact();
		int contactId = findingContactId(loginEmail);
		
		//this is needed in accountpage 
		if(contactId == 0){
			contact.setEmail(loginEmail);
			contact.setName(fullName);
			DAOContact.addContact(contact);
		}
		
		contactId = findingContactId(loginEmail);
		
		//Using Cloudinary to store an image
		
		//System.out.println("upload: " + file.getOriginalFilename());

		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", PhotoUpload.getCloudName(), "api_key",
				PhotoUpload.getApiKey(), "api_secret", PhotoUpload.getApiSecret()));
		
		try {
			Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()); // upload method to upload photo to cloudinary
			urlPic = (String) uploadResult.get("secure_url");
			
			// System.out.println(urlPic);
		} catch (IOException e) {
			e.printStackTrace();
		}

		venue = settingVenue(contactId,venueName,roomSize,
				capacity, price, category, urlPic, calLink, description, 
				address, city, state, zipCode);
		
		DAOVenue.addVenue(venue);
		
		calLink = venue.getCalendarLink();
		
		modelAdding(model,contactId,venueName,roomSize,capacity, 
				price, category, urlPic, calLink, description,
				address, city, state, zipCode);

		return "addsummary";
	}

	@RequestMapping(value = "/venueprofile", method = RequestMethod.GET)
	public String venueprofile(@RequestParam("venueId") int venueId, Model model, HttpServletRequest request) {

		HttpSession session = request.getSession(true);
		String loginEmail = (String) session.getAttribute("loginemail");
		
		if (loginEmail == null){
			model.addAttribute("url", Geocode.getLoginKey());
			return "login";
		}
		
		List<Venue> venues = DAOVenue.getVenues("From Venue");

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
		String zipCode = "";

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
				zipCode = v.getZipcode();
				break;
			}
		}
		
		model.addAttribute("email", loginEmail);
		modelAdding(model,venueId,venueName,roomSize,capacity, 
				price, category, photoLink, calendarLink, description,
				street, city, state, zipCode);
		
		String addressCode = GoogleGeocode.addressConcat(street, city, state, zipCode);
		
		GoogleGeocodeApi geocode = GoogleGeocode.getGeocode(addressCode);
		
		model.addAttribute("lat", geocode.getResults().get(0).getGeometry().getLocation().getLat());
		model.addAttribute("lng", geocode.getResults().get(0).getGeometry().getLocation().getLng());
		
		return "venueprofile";
	}
	
	@RequestMapping(value = "/emailform", method = RequestMethod.POST)
	public String emailform(Model model, HttpServletRequest requesting) throws IOException {
		
		HttpSession session = requesting.getSession(true);
		String loginemail = (String) session.getAttribute("loginemail");
		
		if (loginemail == null){
			model.addAttribute("url", Geocode.getLoginKey());
			return "login";
		}
		
	    Email from = new Email(requesting.getParameter("yourEmail"));
	    String subject = requesting.getParameter("Subject");
	    Email to = new Email(requesting.getParameter("toEmail")); //this will be a variable
	    Content content = new Content("text/plain", requesting.getParameter("Message"));
	    Mail mail = new Mail(from, subject, to, content);
	    
	    // concatenate First Name, Last name,message into a string and set into mail.
	    
	   
	    SendGrid sg = new SendGrid(Send.getApiKey()); // store in static class 
	    Request request = new Request();
	    try {
	      request.method = Method.POST;
	      request.endpoint = "mail/send";
	      request.body = mail.build();
	      Response response = sg.api(request);  //sendgrid sending the request to send email
	      System.out.println(response.statusCode);
	      System.out.println(response.body);
	      System.out.println(response.headers);
	    } catch (IOException ex) {
	      throw ex;
	    }
	    
	    List<Venue> venues = DAOVenue.getVenues("From Venue");
	    
	    int venueId = Integer.parseInt(requesting.getParameter("venueId"));
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
		String zipCode = "";

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
				zipCode = v.getZipcode();
				break;
			}
		}
		
		
		model.addAttribute("email", loginemail);
		modelAdding(model,venueId,venueName,roomSize,capacity, 
				price, category, photoLink, calendarLink, description,
				street, city, state, zipCode);
		return "venueprofile";

	    
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
	public String updateinfo(@RequestParam("venueId") int venueId, Model model, HttpServletRequest request){
		
		HttpSession session = request.getSession(true);
		String loginEmail = (String) session.getAttribute("loginemail");
		//String email
		
		List<Venue> venues = DAOVenue.getVenues("From Venue");
		
		if (loginEmail == null){
			model.addAttribute("url", Geocode.getLoginKey());
			return "login";
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
		String zipCode = "";

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
				zipCode = v.getZipcode();
				break;
			}
		}
		
		List<Contact> contacts = DAOContact.getContacts("From Contact where contactId =" +contactId);		

		for(Contact c: contacts){
			if(c.getEmail().equalsIgnoreCase(loginEmail)){
				continue;
			}else {
				return "accessdenied";
			}
		}
		
		model.addAttribute("email", loginEmail);
		model.addAttribute("contactId", contactId);
		modelAdding(model,rankNum,venueName,roomSize,capacity, 
				price, category, photoLink, calendarLink, description,
				street, city, state, zipCode);
		
		return "updateinfo";
	}
	
	
	@RequestMapping(value = "/viewupdate", method = RequestMethod.POST)
	public String viewupdate(Model model, HttpServletRequest request,  @RequestParam("file") MultipartFile file) {
		
		HttpSession session = request.getSession(true);
		String email = (String) session.getAttribute("loginemail");
		
		if (email == null){
			model.addAttribute("url", Geocode.getLoginKey());
			return "login";
		}
		
		List<Venue> venues = DAOVenue.getVenues("From Venue");
		
		//Make sure I get the integer in update form
		int venueId = Integer.parseInt(request.getParameter("venueId"));
		String venueName = request.getParameter("venueName");
		int contactId = Integer.parseInt(request.getParameter("contactId"));
		int roomSize = Integer.parseInt(request.getParameter("roomSize"));
		int capacity = Integer.parseInt(request.getParameter("capacity"));
		int price = Integer.parseInt(request.getParameter("price"));
		String category = request.getParameter("category");
		String str1 = request.getParameter("calendarLink");
		String calLink = "";
		String description = request.getParameter("description");
		String fullName = request.getParameter("fullName");
		String loginEmail = request.getParameter("email");
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zipCode = request.getParameter("zipCode");
		
		if (str1.startsWith("<")) {
			calLink = CalMethods.gettingCalendar(str1);
		}
		
		String urlPic = "";
		System.out.println("upload: " + file.getOriginalFilename());

		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", PhotoUpload.getCloudName(), "api_key",
				PhotoUpload.getApiKey(), "api_secret", PhotoUpload.getApiSecret()));

		Venue venue = new Venue();
		
		try {
			Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
			urlPic = (String) uploadResult.get("secure_url");
			
		//	System.out.println(urlPic);
		} catch (IOException e) {
			e.printStackTrace();
		}

		venue = settingVenue(contactId,venueName,roomSize,
				capacity, price, category, urlPic, calLink, description, 
				street, city, state, zipCode);
		
		venue.setVenueId(venueId);
		DAOVenue.updateVenue(venue);

		modelAdding(model,venueId,venueName,roomSize,capacity, 
				price, category, urlPic, calLink, description,
				street, city, state, zipCode);
		
		return "viewupdate";
	}
	
	@RequestMapping(value = "/deletevenue", method = RequestMethod.POST)
	public String deletevenue(@RequestParam("venueId") int venueId,Model model, HttpServletRequest request){
		HttpSession session = request.getSession(true);
		String email = (String) session.getAttribute("loginemail");
		
		if (email == null){
			model.addAttribute("url", Geocode.getLoginKey());
			return "login";
		}
		
		DAOVenue.deleteVenue(venueId);
		
		
		List<Contact> contacts = DAOContact.getContacts("From Contact");
		for(Contact c: contacts){
			if(c.getEmail().equalsIgnoreCase(email)){
			int contactId = c.getContactId();
			List<Venue> venues = DAOVenue.getVenues("FROM Venue Where contactId =" + contactId);
			model.addAttribute("venuesOwned", venues);
			}
		}

		return "accountpage";
	}
	
	//Methods to shorten amount of code used
	
	private static void modelAdding(Model model, int rankNum, String venueName, 
			int roomSize, int capacity, int price, String category, 
			String photoLink, String calendarLink, String description,
			String address, String city, String state, String zipCode){
		
		model.addAttribute("venueId", rankNum);
		model.addAttribute("venueName", venueName);
		model.addAttribute("roomSize", roomSize);
		model.addAttribute("capacity", capacity);
		model.addAttribute("price", price);
		model.addAttribute("category", category);
		model.addAttribute("photoLink", photoLink);
		model.addAttribute("calendarLink", calendarLink);
		model.addAttribute("description", description);
		model.addAttribute("street", address);
		model.addAttribute("city", city);
		model.addAttribute("state", state);
		model.addAttribute("zipCode", zipCode);
	}
	
	private static int findingContactId(String email){
		List<Contact> contacts = DAOContact.getContacts("From Contact");
		int contactId = 0;
		
		for(Contact c: contacts){
			if (c.getEmail().equalsIgnoreCase(email)){
				contactId = c.getContactId();
			}
		}
	
		return contactId;
	}

	private static Venue settingVenue(int contactId, String venueName, int roomSize,
			int capacity, int price, String category, String urlPic,
			String calLink, String description, String address, String city,
			String state, String zipCode){
		
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
		venue.setZipcode(zipCode);

		return venue;
	}
	} 
