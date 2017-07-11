package com.pcmc.dms.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pcmc.dms.helper.ExcelPOIHelper;
import com.pcmc.dms.helper.MyCell;

@Controller
public class ReportController {

	private static final String PATH = "C:\\Report\\";

	@Autowired
	private ExcelPOIHelper excelPOIHelper;

	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public ModelAndView index() {

		File reportFolder = new File(PATH);
		File[] listOfReport = reportFolder.listFiles();

		List<String> reportNameList = new ArrayList<String>();

		for (File file : listOfReport) {

			System.out.println(file.getName());
			String fileName = file.getName().substring(0, file.getName().length() - 5);
			reportNameList.add(fileName);

		}

		ModelAndView modelAndView = new ModelAndView("report");
		modelAndView.addObject("reportMenu", true);
		modelAndView.addObject("reportNameList", reportNameList);
		return modelAndView;
	}

	@RequestMapping(value = "viewReport", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam(value = "name", required = false) String reportName) {

		ModelAndView modelAndView = new ModelAndView("showReport");

		String fileLocation = PATH + reportName + ".xlsx";
		if (fileLocation != null) {
			if (fileLocation.endsWith(".xlsx") || fileLocation.endsWith(".xls")) {
				try {
					Map<Integer, List<MyCell>> data = excelPOIHelper.readExcel(fileLocation);
					modelAndView.addObject("data", data);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		modelAndView.addObject("reportMenu", true);
		return modelAndView;
	}

	@RequestMapping(value = "downloadReport", method = RequestMethod.GET)
	public void downloadReport(@RequestParam(value = "name", required = false) String reportName,
			HttpServletResponse response) {
		
		String filename = PATH + reportName + ".xlsx";
		String name = reportName + ".xlsx";

		java.io.FileInputStream fileInputStream;
		try {
			fileInputStream = new java.io.FileInputStream(filename);
			
			ServletOutputStream outs = response.getOutputStream();
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + name + "\"");
			int bit = 256;
			
			while ((bit) >= 0) {
				bit = fileInputStream.read();
				outs.write(bit);
			}
			
			outs.flush();
			outs.close();
			fileInputStream.close();

			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
