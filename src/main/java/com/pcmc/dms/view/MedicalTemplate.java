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

public class MedicalTemplate  extends AbstractXlsxView {
	
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

		header.createCell(0).setCellValue("Head Office");
		header.getCell(0).setCellStyle(style);
		
		header.createCell(1).setCellValue("YCMH");
		header.getCell(1).setCellStyle(style);
		
		header.createCell(2).setCellValue("Hospital");
		header.getCell(2).setCellStyle(style);
		
		header.createCell(3).setCellValue("Dispensaries");
		header.getCell(3).setCellStyle(style);

		header.createCell(4).setCellValue("Period");
		header.getCell(4).setCellStyle(style);
		
		header.createCell(5).setCellValue("Table Number");
		header.getCell(5).setCellStyle(style);
		
		header.createCell(6).setCellValue("File Number");
		header.getCell(6).setCellStyle(style);
		
		header.createCell(7).setCellValue("Keyward");
		header.getCell(7).setCellStyle(style);
		
		header.createCell(8).setCellValue("Image Name");
		header.getCell(8).setCellStyle(style);
	}
}
