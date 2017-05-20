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

import com.pcmc.dms.master.CourtMaster;
import com.pcmc.dms.model.LawModel;
import com.pcmc.dms.repository.LawRepository;

@Controller
public class LawController {
	
	@Autowired
	private LawRepository lawRepository;
	
	private static final String UPLOAD_URL = "uploadLaw";

	private static final String SEARCH_URL = "law";

	@RequestMapping(value = "/law", method = RequestMethod.GET)
	public ModelAndView index() {
		
		List<CourtMaster> courtList = lawRepository.getMasterList(CourtMaster.class);

		ModelAndView modelAndView = new ModelAndView("searchLaw", "lawModel", new LawModel());
		modelAndView.addObject("show", true);
		modelAndView.addObject("UPLOAD_URL", UPLOAD_URL);
		modelAndView.addObject("SEARCH_URL", SEARCH_URL);
		modelAndView.addObject("courtList", courtList);
		
		modelAndView.addObject("showDeptTable", false);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/law", method = RequestMethod.POST)
	public ModelAndView search(@ModelAttribute("lawModel") LawModel lawModel) {
		
		List<LawModel> list = lawRepository.getLawModelList(lawModel);
		List<CourtMaster> courtList = lawRepository.getMasterList(CourtMaster.class);
		
		ModelAndView modelAndView = new ModelAndView("searchLaw","lawModel", lawModel);
		modelAndView.addObject("show", true);
		modelAndView.addObject("UPLOAD_URL", UPLOAD_URL);
		modelAndView.addObject("SEARCH_URL", SEARCH_URL);
		modelAndView.addObject("courtList", courtList);
		
		modelAndView.addObject("showDeptTable", true);
		modelAndView.addObject("searchList", list);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/viewLaw")
	public ModelAndView viewAudit(@RequestParam(value = "id", required = false) int entryId) {
		
		LawModel lawModel = lawRepository.getLawModel(entryId);
		

		ModelAndView modelAndView = new ModelAndView("viewLaw", "lawModel", lawModel);
		modelAndView.addObject("show", true);
		modelAndView.addObject("UPLOAD_URL", UPLOAD_URL);
		modelAndView.addObject("SEARCH_URL", SEARCH_URL);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/uploadLaw", method = RequestMethod.GET)
	public ModelAndView upload(){
		
		ModelAndView modelAndView = new ModelAndView("uploadLaw");
		modelAndView.addObject("show", false);
		modelAndView.addObject("UPLOAD_URL", UPLOAD_URL);
		modelAndView.addObject("SEARCH_URL", SEARCH_URL);
		
		return modelAndView;
	}

	@RequestMapping(value = "/uploadLaw", method = RequestMethod.POST)
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
