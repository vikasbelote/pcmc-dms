package com.pcmc.dms.controller;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.pcmc.dms.model.NagriSuvidhaModel;
import com.pcmc.dms.repository.NagriSuvidhaRepository;

@Controller
public class NagriSuvidhaController extends BaseController {
	
	@Autowired
	private NagriSuvidhaRepository repository;
	
	/*
	 * Upload URL name and View name
	 */
	private static final String UPLOAD_URL = "uploadNagriSuvidha";

	private static final String SEARCH_URL = "nagriSuvidha";
	
	/*
	 * View URL name and View name
	 */
	private static final String VIEW_URL = "viewNagriSuvidha";
	
	private static final String MODEL_NAME = "nagriSuvidhaModel";
	
	private static final String SEARCH_VIEW_NAME = "searchNagriSuvidha";

	@RequestMapping(value = "/" + SEARCH_URL, method = RequestMethod.GET)
	public ModelAndView index() {
		
		ModelAndView modelAndView = new ModelAndView(SEARCH_VIEW_NAME, MODEL_NAME, new NagriSuvidhaModel());
		modelAndView.addObject("show", true);
		modelAndView.addObject("UPLOAD_URL", UPLOAD_URL);
		modelAndView.addObject("SEARCH_URL", SEARCH_URL);
		modelAndView.addObject("showDeptTable", false);
		modelAndView.addObject("MODEL_NAME", MODEL_NAME);
		modelAndView.addObject("VIEW_URL", VIEW_URL);
		return modelAndView;
	}
	
	@RequestMapping(value = "/" + SEARCH_URL, method = RequestMethod.POST)
	public ModelAndView search(@ModelAttribute(MODEL_NAME) NagriSuvidhaModel model) {
		
		List<NagriSuvidhaModel> list = repository.getModelList(model);
		
		ModelAndView modelAndView = new ModelAndView(SEARCH_VIEW_NAME,MODEL_NAME, model);
		modelAndView.addObject("show", true);
		modelAndView.addObject("UPLOAD_URL", UPLOAD_URL);
		modelAndView.addObject("SEARCH_URL", SEARCH_URL);
		modelAndView.addObject("showDeptTable", true);
		modelAndView.addObject("searchList", list);
		modelAndView.addObject("MODEL_NAME", MODEL_NAME);
		modelAndView.addObject("VIEW_URL", VIEW_URL);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/"+VIEW_URL)
	public ModelAndView viewPropTax(@RequestParam(value = "id", required = false) int entryId,  HttpSession session) {
		
		NagriSuvidhaModel model = repository.getModel(entryId, NagriSuvidhaModel.class);
		
		//move the file from C:\image folder to static\images folder
		String LOCAL_FOLDER_PATH = this.localFolederPath + "\\nagrisuvidha\\";
		String rootPath = session.getServletContext().getRealPath("/");
		fileHelper.copyFile(model.getImagePath(), rootPath, LOCAL_FOLDER_PATH);

		ModelAndView modelAndView = new ModelAndView(VIEW_URL, MODEL_NAME, model);
		modelAndView.addObject("show", true);
		modelAndView.addObject("UPLOAD_URL", UPLOAD_URL);
		modelAndView.addObject("SEARCH_URL", SEARCH_URL);
		modelAndView.addObject("MODEL_NAME", MODEL_NAME);
		return modelAndView;
	}
	
	@RequestMapping(value = "/"+UPLOAD_URL, method = RequestMethod.GET)
	public ModelAndView upload(){
		
		ModelAndView modelAndView = new ModelAndView(UPLOAD_URL);
		modelAndView.addObject("UPLOAD_URL", UPLOAD_URL);
		return modelAndView;
	}

	@RequestMapping(value = "/" + UPLOAD_URL, method = RequestMethod.POST)
	public String upload(@RequestParam CommonsMultipartFile file, HttpSession session) {
		String rootPath = session.getServletContext().getRealPath("/");
		String fileName = file.getOriginalFilename();
		try {
			byte barr[] = file.getBytes();
			BufferedOutputStream bout = new BufferedOutputStream(
					new FileOutputStream(rootPath + "/images/" + fileName));
			bout.write(barr);
			bout.flush();
			bout.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return "redirect:/" + UPLOAD_URL;
	}
}
