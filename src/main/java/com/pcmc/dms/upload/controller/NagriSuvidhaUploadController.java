package com.pcmc.dms.upload.controller;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pcmc.dms.upload.service.NagarSachivService;

@Controller
public class NagriSuvidhaUploadController {
	
	@Autowired
	private NagarSachivService service;
	
	//Just change department name
	private static final String DEPT_NAME = "NagriSuvidha";
	
	private static final String UPLOAD_URL = "uploadData" + DEPT_NAME;
	private static final String DOWNLOAD_URL = "downloadData" + DEPT_NAME;
	private static final String VIEW_NAME = DEPT_NAME + "Template";
	
	@RequestMapping(value = "/" + UPLOAD_URL)
	public ModelAndView view(){
		
		ModelAndView modelAndView = new ModelAndView("uploadData");
		modelAndView.addObject("UPLOAD_URL", UPLOAD_URL);
		modelAndView.addObject("DOWNLOAD_URL", DOWNLOAD_URL);
		return modelAndView;
	}
	
	@RequestMapping(value = "/" + UPLOAD_URL, method = RequestMethod.POST)
	public String upload(@RequestParam CommonsMultipartFile file, HttpSession session, final RedirectAttributes redirectAttributes) {
		String rootPath = session.getServletContext().getRealPath("/");
		String fileName = file.getOriginalFilename();
		try {
			String filePath = rootPath + "static\\uploadExcelFile\\" + fileName;
			byte barr[] = file.getBytes();
			BufferedOutputStream bout = new BufferedOutputStream(
					new FileOutputStream(filePath));
			bout.write(barr);
			bout.flush();
			bout.close();
			
			service.readBooksFromExcelFile(filePath);
			
			
			redirectAttributes.addFlashAttribute("message","File uploaded successfully.");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message","File not uploaded successfully.");
		}

		return "redirect:/" + UPLOAD_URL;
	}
	
	@RequestMapping(value ="/" + DOWNLOAD_URL, method = RequestMethod.GET)
	public ModelAndView downloadTemplate() {
		
		ModelAndView modelAndView = new ModelAndView(VIEW_NAME, "obj", null);
		return modelAndView;
	}
}
