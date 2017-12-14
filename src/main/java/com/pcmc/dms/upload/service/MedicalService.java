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
import com.pcmc.dms.helper.UnicodeHelper;
import com.pcmc.dms.model.MedicalModel;
import com.pcmc.dms.repository.MedicalRepository;

@Service
public class MedicalService {
	
	@Autowired
	private MedicalRepository repository;

	public void readBooksFromExcelFile(String excelFilePath) throws IOException {

		List<MedicalModel> list = new ArrayList<MedicalModel>();
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
			MedicalModel model = new MedicalModel();

			while (cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();

				int columnIndex = nextCell.getColumnIndex();

				Object obj = CellValueHelper.getCellValue(nextCell);
				if (obj == null)
					obj = "";

				switch (columnIndex) {
				case 0: {
					String headOffice = String.valueOf(obj);
					model.setHeadOffice(headOffice);
					break;
				}
				case 1: {
					String ycmh = String.valueOf(obj);
					model.setYcmh(ycmh);
					break;
				}
				case 2: {
					String hospital = String.valueOf(obj);
					model.setHospital(hospital);
					break;
				}
				case 3: {
					String dispensaries = String.valueOf(obj);
					if(dispensaries != null)
						model.setDispensaries(UnicodeHelper.stringToHTMLString(dispensaries));
					break;
				}
				case 4: {
					String period = String.valueOf(obj);
					model.setPeriod(period);
					break;
				}
				case 5: {
					String tableNumber = String.valueOf(obj);
					model.setTableNumber(tableNumber);
					break;
				}
				case 6: {
					String fileNumber = String.valueOf(obj);
					model.setFileNumber(fileNumber);
					break;
				}
				case 7: {
					String keyward = String.valueOf(obj);
					model.setKeyward(keyward);
					break;
				}
				case 8: {
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
