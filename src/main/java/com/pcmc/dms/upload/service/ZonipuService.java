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
import org.springframework.stereotype.Service;

import com.pcmc.dms.helper.CellValueHelper;
import com.pcmc.dms.model.ZonipuModel;
import com.pcmc.dms.repository.ZonipuRepository;

@Service
public class ZonipuService {
	
	@Autowired
	private ZonipuRepository repository;

	public void readBooksFromExcelFile(String excelFilePath) throws IOException {

		List<ZonipuModel> list = new ArrayList<ZonipuModel>();
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
			ZonipuModel model = new ZonipuModel();

			while (cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();

				int columnIndex = nextCell.getColumnIndex();

				Object obj = CellValueHelper.getCellValue(nextCell);
				if (obj == null)
					obj = "";

				switch (columnIndex) {
				case 0: {
					String nagarName = String.valueOf(obj);
					model.setNagarName(nagarName);
					break;
				}
				case 1: {
					String personName = String.valueOf(obj);
					model.setPersonName(personName);
					break;
				}
				case 2: {
					String roomNumber = String.valueOf(obj);
					model.setRoomNumber(roomNumber);
					break;
				}
				case 3: {
					String fileNumber = String.valueOf(obj);
					model.setFileNumber(fileNumber);
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
