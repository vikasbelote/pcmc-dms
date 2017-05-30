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
import com.pcmc.dms.model.LawModel;
import com.pcmc.dms.repository.LawRepository;

/*
 * Law Service
 */
@Component
public class LawService {
	
	@Autowired
	private LawRepository lawRepository;
	
	public void readBooksFromExcelFile(String excelFilePath) throws IOException {
		
		List<LawModel> lawModelList = new ArrayList<LawModel>();
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
			LawModel law = new LawModel();

			while (cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();
				int columnIndex = nextCell.getColumnIndex();

				switch (columnIndex) {
				case 0: {
					String caseNo = (String) CellValueHelper.getCellValue(nextCell);
					law.setCaseNo(caseNo);
					break;
				}
				case 1: {
					String courtName = (String) CellValueHelper.getCellValue(nextCell);
					law.setCourtName(courtName);
					break;
				}case 5: {
					String imagePathName = (String) CellValueHelper.getCellValue(nextCell);
					law.setImagePath(imagePathName);
					break;
				}
				
				}
			}
			lawModelList.add(law);
		}
		workbook.close();
		inputStream.close();
		
		lawRepository.saveLawList(lawModelList);
	}

}
