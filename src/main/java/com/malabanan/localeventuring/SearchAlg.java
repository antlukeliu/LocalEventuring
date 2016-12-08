package com.malabanan.localeventuring;

import java.util.ArrayList;

public class SearchAlg {

	public static String getQueryString(String[] filters){
		String queryString = "FROM Book";
		ArrayList<String> filtered = new ArrayList<String>();
		
		
		if(filters.length == 0){
			return queryString;
		}
		
		if(filters.length > 0){
			queryString += " Where ";
		}
		
		for(String filter: filters){
			if(filter.equals("")){
				continue;
			}else{
				filtered.add(filter);
			}
		}
		
		for(int i=0; i<filtered.size(); i++){
			if(i==0){
				queryString += filtered.get(0);
			}else{
				queryString += " and " + filtered.get(i);
			}
			
		}
		
		return queryString;
	}
}
