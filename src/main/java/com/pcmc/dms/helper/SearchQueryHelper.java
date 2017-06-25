package com.pcmc.dms.helper;

public class SearchQueryHelper {
	
	public String searchQuery(String[] columnName, String tableName) {
		
		
		StringBuilder sb = new StringBuilder();
		sb.append("select * from " + tableName + "where ");
		
		
		
		return sb.toString();
	}
	
}
