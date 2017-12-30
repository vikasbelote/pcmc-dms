package com.pcmc.dms.helper;

import org.apache.poi.ss.usermodel.Cell;

public class CellValueHelper {
	
	@SuppressWarnings({ "unused", "deprecation" })
	public static Object getCellValue(Cell cell) {
	    switch (cell.getCellType()) {
	    case Cell.CELL_TYPE_STRING:
	        return cell.getStringCellValue();
	 
	    case Cell.CELL_TYPE_BOOLEAN:
	        return cell.getBooleanCellValue();
	 
	    case Cell.CELL_TYPE_NUMERIC:{
	    	Double value = new Double(cell.getNumericCellValue());
	    	Integer intValue = value.intValue();
	    	return intValue;
	    }
	    
	    }
	 
	    return null;
	}
}
