package com.pcmc.dms.upload.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pcmc.dms.helper.CellValueHelper;
import com.pcmc.dms.model.VivahNodhaniModel;
import com.pcmc.dms.repository.VivahNodhniRepository;

@Component
public class VivahNodhaniService {
	
	@Autowired
	private VivahNodhniRepository vivahNodhaniRepository;
	
	public void readBooksFromExcelFile(String excelFilePath) throws IOException {

		List<VivahNodhaniModel> listVivahNodhaniModel = new ArrayList<VivahNodhaniModel>();
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
			VivahNodhaniModel vivahNodhaniModel = new VivahNodhaniModel();

			while (cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();
				
				int columnIndex = nextCell.getColumnIndex();
				

				Object obj = CellValueHelper.getCellValue(nextCell);
				if(obj == null)
					obj = "";

				switch (columnIndex) {
				case 0: {
					String prabhagName = String.valueOf(obj);
					vivahNodhaniModel.setPrabhagName(prabhagName);
					break;
				}
				case 1: {
					String entityName = String.valueOf(obj);
					vivahNodhaniModel.setEntityName(entityName);
					break;
				}
				case 2: {
					String entityRegistrationNo = String.valueOf(obj);
					vivahNodhaniModel.setEntityRegistrationNo(entityRegistrationNo);
					break;
				}
				case 3: {
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					Date cellEntityRegistrationDate = nextCell.getDateCellValue();
					String entityRegistrationDate = df.format(cellEntityRegistrationDate);
					vivahNodhaniModel.setEntityRegistrationDate(entityRegistrationDate);
					break;
				}
				case 4: {
					String gattaNo = String.valueOf(obj);
					vivahNodhaniModel.setGattaNo(gattaNo);
					break;
				}
				case 5: {
					String imagePath = String.valueOf(obj);
					vivahNodhaniModel.setImagePath(imagePath);
					break;
				}
				}
			}
			listVivahNodhaniModel.add(vivahNodhaniModel);
		}
		workbook.close();
		inputStream.close();
		
		//Save into database
		vivahNodhaniRepository.saveList(listVivahNodhaniModel);
	}
	
}
