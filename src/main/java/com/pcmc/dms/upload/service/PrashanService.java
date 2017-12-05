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
import com.pcmc.dms.model.PrashasanModel;
import com.pcmc.dms.repository.PrashasanRepository;

@Component
public class PrashanService {
	
	@Autowired
	private PrashasanRepository repository;

	public void readBooksFromExcelFile(String excelFilePath) throws IOException {

		List<PrashasanModel> list = new ArrayList<PrashasanModel>();
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
			PrashasanModel prashasanModel = new PrashasanModel();

			while (cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();

				int columnIndex = nextCell.getColumnIndex();

				Object obj = CellValueHelper.getCellValue(nextCell);
				if (obj == null)
					obj = "";

				switch (columnIndex) {
				case 0: {
					String tableNumber = String.valueOf(obj);
					prashasanModel.setTableNumber(tableNumber);
					break;
				}
				case 1: {
					String nastiName = String.valueOf(obj);
					if(nastiName != null)
						prashasanModel.setNastiName(UnicodeHelper.stringToHTMLString(nastiName));
					break;
				}
				case 2: {
					String nastiNumber = String.valueOf(obj);
					prashasanModel.setNastiNumber(nastiNumber);
					break;
				}
				case 3: {
					String imagePath = String.valueOf(obj);
					prashasanModel.setImagePath(imagePath);
					break;
				}
				}
			}
			list.add(prashasanModel);
		}
		workbook.close();
		inputStream.close();

		// Save into database
		repository.saveList(list);
	}
}
