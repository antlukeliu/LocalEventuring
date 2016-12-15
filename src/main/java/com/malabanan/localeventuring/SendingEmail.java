package com.malabanan.localeventuring;

import java.io.IOException;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

public class SendingEmail {

	
	public static void sendingWelcomeMessage(String toEmail) throws IOException{
		
	    Email from = new Email("localeventuring@gmail.com");
	    String subject = "Welcome to Local Eventuring";
	    Email to = new Email(toEmail); //this will be a variable
	    Content content = new Content("text/plain", "Welcome to Local Eventuring. Thank you for signing up with us. "
	    		+ " Please visit our site again at http://bit.ly/LocalEventuring");
	    
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
	}
}
