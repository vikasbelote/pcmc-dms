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

import com.pcmc.dms.master.VillageMaster;
import com.pcmc.dms.model.PropTaxModel;
import com.pcmc.dms.repository.PropTaxRepository;

@Controller
public class PropTaxController {
	
	@Autowired
	private PropTaxRepository propTaxRepository;
	
	/*
	 * Upload URL name and View name
	 */
	private static final String UPLOAD_URL = "uploadPropTax";

	private static final String SEARCH_URL = "propTax";
	
	/*
	 * View URL name and View name
	 */
	private static final String VIEW_URL = "viewPropTax";
	
	private static final String MODEL_NAME = "propTaxModel";
	
	private static final String SEARCH_VIEW_NAME = "searchPropTax";

	@RequestMapping(value = "/" + SEARCH_URL, method = RequestMethod.GET)
	public ModelAndView index() {
		
		List<VillageMaster> villageList = propTaxRepository.getMasterList(VillageMaster.class);
		
		ModelAndView modelAndView = new ModelAndView(SEARCH_VIEW_NAME, MODEL_NAME, new PropTaxModel());
		modelAndView.addObject("show", true);
		modelAndView.addObject("UPLOAD_URL", UPLOAD_URL);
		modelAndView.addObject("SEARCH_URL", SEARCH_URL);
		modelAndView.addObject("villageList", villageList);
		
		modelAndView.addObject("showDeptTable", false);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/" + SEARCH_URL, method = RequestMethod.POST)
	public ModelAndView search(@ModelAttribute(MODEL_NAME) PropTaxModel propTaxModel) {
		
		List<PropTaxModel> list = propTaxRepository.getPropTaxList(propTaxModel);
		List<VillageMaster> villageList = propTaxRepository.getMasterList(VillageMaster.class);
		
		ModelAndView modelAndView = new ModelAndView(SEARCH_VIEW_NAME,MODEL_NAME, propTaxModel);
		modelAndView.addObject("show", true);
		modelAndView.addObject("UPLOAD_URL", UPLOAD_URL);
		modelAndView.addObject("SEARCH_URL", SEARCH_URL);
		modelAndView.addObject("villageList", villageList);
		
		modelAndView.addObject("showDeptTable", true);
		modelAndView.addObject("searchList", list);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/"+VIEW_URL)
	public ModelAndView viewAudit(@RequestParam(value = "id", required = false) int entryId) {
		
		PropTaxModel propTaxModel = propTaxRepository.getModel(entryId, PropTaxModel.class);

		ModelAndView modelAndView = new ModelAndView(VIEW_URL, MODEL_NAME, propTaxModel);
		modelAndView.addObject("show", true);
		modelAndView.addObject("UPLOAD_URL", UPLOAD_URL);
		modelAndView.addObject("SEARCH_URL", SEARCH_URL);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/"+UPLOAD_URL, method = RequestMethod.GET)
	public ModelAndView upload(){
		
		ModelAndView modelAndView = new ModelAndView(UPLOAD_URL);
		modelAndView.addObject("show", false);
		modelAndView.addObject("UPLOAD_URL", UPLOAD_URL);
		modelAndView.addObject("SEARCH_URL", SEARCH_URL);
		
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
