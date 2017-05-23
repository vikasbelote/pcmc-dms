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
import com.pcmc.dms.model.AuditModel;
import com.pcmc.dms.repository.AuditRepository;

@Component
public class AuditService {
	
	@Autowired
	private AuditRepository auditRepository;
	
	public void readBooksFromExcelFile(String excelFilePath) throws IOException {

		List<AuditModel> auditModelList = new ArrayList<AuditModel>();
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
			AuditModel audit = new AuditModel();

			while (cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();
				int columnIndex = nextCell.getColumnIndex();

				switch (columnIndex) {
				case 0: {
					String gutNo = (String) CellValueHelper.getCellValue(nextCell);
					audit.setGutNo(gutNo);
					break;
				}
				case 1: {
					String gattaNo = (String) CellValueHelper.getCellValue(nextCell);
					audit.setGattaNo(gattaNo);
					break;
				}
				case 2: {
					String docNo = (String) CellValueHelper.getCellValue(nextCell);
					audit.setDocNo(docNo);
					break;
				}
				case 3: {
					String imagePath = (String) CellValueHelper.getCellValue(nextCell);
					audit.setImagePath(imagePath);
					break;
				}
				}
			}
			auditModelList.add(audit);
		}
		workbook.close();
		inputStream.close();

		auditRepository.saveList(auditModelList);
	}
}
