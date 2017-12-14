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
import com.pcmc.dms.model.BhuyariModel;
import com.pcmc.dms.repository.BhuyariRepository;

@Component
public class BhuyariService {
	
	@Autowired
	private BhuyariRepository repository;

	public void readBooksFromExcelFile(String excelFilePath) throws IOException {

		List<BhuyariModel> list = new ArrayList<BhuyariModel>();
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
			BhuyariModel model = new BhuyariModel();

			while (cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();

				int columnIndex = nextCell.getColumnIndex();

				Object obj = CellValueHelper.getCellValue(nextCell);
				if (obj == null)
					obj = "";

				switch (columnIndex) {
				case 0: {
					String nivadaNumber = String.valueOf(obj);
					model.setNivadaNumber(nivadaNumber);
					break;
				}
				case 1: {
					String workName = String.valueOf(obj);
					if(workName != null)
						model.setWorkName(UnicodeHelper.stringToHTMLString(workName));
					break;
				}
				case 2: {
					String tendorName = String.valueOf(obj);
					model.setTendorName(tendorName);
					break;
				}
				case 3: {
					String budgetTartudeNumber = String.valueOf(obj);
					model.setBudgetTartudeNumber(budgetTartudeNumber);
					break;
				}
				case 4: {
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
