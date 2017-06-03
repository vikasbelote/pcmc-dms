package com.pcmc.dms.upload.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pcmc.dms.model.PropTaxModel;
import com.pcmc.dms.repository.PropTaxRepository;
import com.pcmc.dms.helper.*;

@Component
public class ProptaxService {
	
	@Autowired
	private PropTaxRepository propTaxRepository;
	
	public void readBooksFromExcelFile(String excelFilePath) throws IOException {

		List<PropTaxModel> listProptax = new ArrayList<PropTaxModel>();
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();
		
		boolean isFirstRow = true;
		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			if(isFirstRow) {
				isFirstRow = false;
				continue;
			}
			
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			PropTaxModel propTax = new PropTaxModel();

			while (cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();
				int columnIndex = nextCell.getColumnIndex();
				

				Object obj = CellValueHelper.getCellValue(nextCell);
				if(obj == null)
					obj = "";

				switch (columnIndex) {
				case 0: {
					String villageName = String.valueOf(obj);
					propTax.setVillageName(villageName);
					break;
				}
				case 1: {
					String propertyCode = String.valueOf(obj);
					propTax.setPropertyCode(propertyCode);
					break;
				}
				case 2: {
					String propHolderName = (String) CellValueHelper.getCellValue(nextCell);
					if (propHolderName != null)
						propTax.setPropertyHolderName(UnicodeHelper.stringToHTMLString(propHolderName));
					break;
				}
				case 3: {
					String gutNo = String.valueOf(obj);
					propTax.setGutNo(gutNo);
					break;
				}
				case 4: {
					String imagePath = String.valueOf(obj);
					propTax.setImagePath(imagePath);
					break;
				}
				}
			}
			listProptax.add(propTax);
		}
		workbook.close();
		inputStream.close();
		
		//Save into database
		propTaxRepository.savePropTaxList(listProptax);
	}
	
	
}
