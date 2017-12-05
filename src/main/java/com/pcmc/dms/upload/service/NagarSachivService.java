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
import com.pcmc.dms.model.NagarSachivModel;
import com.pcmc.dms.repository.NagarSachivRepository;

@Component
public class NagarSachivService {
	
	@Autowired
	private NagarSachivRepository repository;

	public void readBooksFromExcelFile(String excelFilePath) throws IOException {

		List<NagarSachivModel> list = new ArrayList<NagarSachivModel>();
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
			NagarSachivModel model = new NagarSachivModel();

			while (cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();

				int columnIndex = nextCell.getColumnIndex();

				Object obj = CellValueHelper.getCellValue(nextCell);
				if (obj == null)
					obj = "";

				switch (columnIndex) {
				case 0: {
					String bodyName = String.valueOf(obj);
					model.setBodyName(bodyName);
					break;
				}
				case 1: {
					String karypatrikaNumber = String.valueOf(obj);
					model.setKarypatrikaNumber(karypatrikaNumber);
					break;
				}
				case 2: {
					String karypatrikaDate = String.valueOf(obj);
					model.setKarypatrikaDate(karypatrikaDate);
					break;
				}
				case 3: {
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
