package com.malabanan.localeventuring;

public class RandomMethods {
	
	public static String gettingCalendar(String str1) {
		
		String calendar;
		String[] splits = str1.split(" ");	
		calendar = splits[1];
		calendar = "<iframe " + calendar + "></iframe>";
		
		return calendar;
		
	}

}
