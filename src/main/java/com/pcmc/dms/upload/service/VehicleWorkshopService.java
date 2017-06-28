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
import com.pcmc.dms.model.VehicleWorkshopModel;
import com.pcmc.dms.repository.VehicleWorkshopRepository;

@Component
public class VehicleWorkshopService {

	@Autowired
	private VehicleWorkshopRepository vehicleWorkshopRepository;

	public void readBooksFromExcelFile(String excelFilePath) throws IOException {

		List<VehicleWorkshopModel> list = new ArrayList<VehicleWorkshopModel>();
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
			VehicleWorkshopModel vehicleWorkshopModel = new VehicleWorkshopModel();

			while (cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();

				int columnIndex = nextCell.getColumnIndex();

				Object obj = CellValueHelper.getCellValue(nextCell);
				if (obj == null)
					obj = "";

				switch (columnIndex) {
				case 0: {
					String tableNumber = String.valueOf(obj);
					vehicleWorkshopModel.setTableNumber(tableNumber);
					break;
				}
				case 1: {
					String nastiName = String.valueOf(obj);
					vehicleWorkshopModel.setNastiName(nastiName);
					break;
				}
				case 2: {
					String nastiNumber = String.valueOf(obj);
					vehicleWorkshopModel.setNastiNumber(nastiNumber);
					break;
				}
				case 3: {
					String imagePath = String.valueOf(obj);
					vehicleWorkshopModel.setImagePath(imagePath);
					break;
				}
				}
			}
			list.add(vehicleWorkshopModel);
		}
		workbook.close();
		inputStream.close();

		// Save into database
		vehicleWorkshopRepository.saveList(list);
	}

}
