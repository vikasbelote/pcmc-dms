package com.pcmc.dms.upload.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

import com.pcmc.dms.helper.CellValueHelper;
import com.pcmc.dms.helper.UnicodeHelper;
import com.pcmc.dms.model.BhoomiModel;
import com.pcmc.dms.repository.BhoomiRepository;

@Component
public class BhoomiService {
	
	@Autowired
	private BhoomiRepository repository;

	public void readBooksFromExcelFile(String excelFilePath) throws IOException {

		List<BhoomiModel> list = new ArrayList<BhoomiModel>();
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();

		boolean isFirstRow = true;
		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			if (isFirstRow) {
				isFirstRow = false;
				continue;
			}

			Iterator<Cell> cellIterator = nextRow.cellIterator();
			BhoomiModel model = new BhoomiModel();

			while (cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();

				int columnIndex = nextCell.getColumnIndex();

				Object obj = CellValueHelper.getCellValue(nextCell);
				if (obj == null)
					obj = "";

				switch (columnIndex) {
				case 0: {
					String villageName = String.valueOf(obj);
					model.setVillageName(villageName);
					break;
				}
				case 1: {
					String subjectName = String.valueOf(obj);
					if(subjectName != null)
						model.setSubjectName(UnicodeHelper.stringToHTMLString(subjectName));
					break;
				}
				case 2: {
					String serveNumber = String.valueOf(obj);
					if(serveNumber != null)
						model.setServeNumber(UnicodeHelper.stringToHTMLString(serveNumber));
					break;
				}
				case 3: {
					String gatNumber = String.valueOf(obj);
					model.setGatNumber(gatNumber);
					break;
				}
				case 4: {
					String hissaNumber = String.valueOf(obj);
					model.setHissaNumber(hissaNumber);
					break;
				}
				case 5: {
					String fileNumber = String.valueOf(obj);
					model.setFileNumber(fileNumber);
					break;
				}
				case 6: {
					String imagePath = String.valueOf(obj);
					model.setImagePath(imagePath);
					break;
				}
				}
			}
			list.add(model);
		}
		workbook.close();
		inputStream.close();

		// Save into database
		repository.saveList(list);
	}
}
