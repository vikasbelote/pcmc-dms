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
import com.pcmc.dms.model.BuildingPermissionModel;
import com.pcmc.dms.repository.BuildingPermissionRepository;

@Component
public class BupService {
	
	@Autowired
	private BuildingPermissionRepository bupRepository;
	
	public void readBooksFromExcelFile(String excelFilePath) throws IOException {

		List<BuildingPermissionModel> bupModelList = new ArrayList<BuildingPermissionModel>();
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
			BuildingPermissionModel bupModel = new BuildingPermissionModel();

			while (cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();
				int columnIndex = nextCell.getColumnIndex();

				switch (columnIndex) {
				case 0: {
					String gattaNo = (String) CellValueHelper.getCellValue(nextCell);
					bupModel.setGattaNo(gattaNo);
					break;
				}
				case 1: {
					String bupNo = (String) CellValueHelper.getCellValue(nextCell);
					bupModel.setBuildingPermissionNo(bupNo);
					break;
				}
				case 2: {
					String serveNo = (String) CellValueHelper.getCellValue(nextCell);
					bupModel.setServeNo(serveNo);
					break;
				}
				case 3: {
					String imagePath = (String) CellValueHelper.getCellValue(nextCell);
					bupModel.setImagePath(imagePath);
					break;
				}
				}
			}
			bupModelList.add(bupModel);
		}
		workbook.close();
		inputStream.close();

		bupRepository.saveList(bupModelList);
	}
}
