package com.pcmc.dms.upload.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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

		Workbook workbook = new HSSFWorkbook(inputStream);
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

				switch (columnIndex) {
				case 0: {
					String villageName = (String) CellValueHelper.getCellValue(nextCell);
					propTax.setVillageName(villageName);
					break;
				}
				case 1: {

					Double d = (Double) CellValueHelper.getCellValue(nextCell);
					BigDecimal bg = new BigDecimal(d);
					String propertyCode = String.valueOf(bg);
					propTax.setPropertyCode(propertyCode);
					break;
				}
				case 2: {
					String propHolderName = (String) CellValueHelper.getCellValue(nextCell);
					if (propHolderName != null)
						propTax.setPropertyHolderName(UnicodeHelper.stringToHTMLString(propHolderName));
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
