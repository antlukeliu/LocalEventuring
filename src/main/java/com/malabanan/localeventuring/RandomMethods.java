package com.malabanan.localeventuring;

import javax.servlet.http.HttpSession;

//refactor later

public class RandomMethods {
	
	public static String gettingCalendar(String str1) {
		
		String calendar;
		String[] splits = str1.split(" ");	
		calendar = splits[1];
		calendar = "<iframe " + calendar + 
				" style=\"border: 0\" width=\"400\" height=\"300\" frameborder=\"0\" scrolling=\"no\"></iframe>";
		
		return calendar;
		
	}

}
