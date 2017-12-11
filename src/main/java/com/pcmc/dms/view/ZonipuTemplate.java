package com.pcmc.dms.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

public class ZonipuTemplate extends AbstractXlsxView {
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		XSSFSheet sheet = (XSSFSheet) workbook.createSheet("Data");
		sheet.setDefaultColumnWidth(30);

		// create style for header cells
		XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setFontName("Arial");
		style.setFont(font);

		// create header row
		XSSFRow header = sheet.createRow(0);

		header.createCell(0).setCellValue("Nagar Name");
		header.getCell(0).setCellStyle(style);
		
		header.createCell(1).setCellValue("Person Name");
		header.getCell(1).setCellStyle(style);
		
		header.createCell(2).setCellValue("Room Number");
		header.getCell(2).setCellStyle(style);
		
		header.createCell(3).setCellValue("File Number");
		header.getCell(3).setCellStyle(style);

		header.createCell(4).setCellValue("Image Name");
		header.getCell(4).setCellStyle(style);
	}
}