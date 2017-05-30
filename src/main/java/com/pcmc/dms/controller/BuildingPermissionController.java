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
import com.pcmc.dms.model.BuildingPermissionModel;
import com.pcmc.dms.repository.BuildingPermissionRepository;

@Controller
public class BuildingPermissionController extends BaseController {
	
	@Autowired
	private BuildingPermissionRepository buildingPermissionRepository;
	
	/*
	 * Upload URL name and View name
	 */
	private static final String UPLOAD_URL = "uploadBup";

	private static final String SEARCH_URL = "bup";
	
	/*
	 * View URL name and View name
	 */
	private static final String VIEW_URL = "viewBup";
	
	private static final String MODEL_NAME = "bupModel";
	
	private static final String SEARCH_VIEW_NAME = "searchBup";

	@RequestMapping(value = "/" + SEARCH_URL, method = RequestMethod.GET)
	public ModelAndView index() {
		
		List<VillageMaster> villageList = buildingPermissionRepository.getMasterList(VillageMaster.class);
		
		ModelAndView modelAndView = new ModelAndView(SEARCH_VIEW_NAME, MODEL_NAME, new BuildingPermissionModel());
		modelAndView.addObject("show", true);
		modelAndView.addObject("UPLOAD_URL", UPLOAD_URL);
		modelAndView.addObject("SEARCH_URL", SEARCH_URL);
		modelAndView.addObject("villageList", villageList);
		
		modelAndView.addObject("showDeptTable", false);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/" + SEARCH_URL, method = RequestMethod.POST)
	public ModelAndView search(@ModelAttribute(MODEL_NAME) BuildingPermissionModel bupModel) {
		
		List<BuildingPermissionModel> list = buildingPermissionRepository.getBuildingPermissionList(bupModel);
		List<VillageMaster> villageList = buildingPermissionRepository.getMasterList(VillageMaster.class);
		
		ModelAndView modelAndView = new ModelAndView(SEARCH_VIEW_NAME,MODEL_NAME, bupModel);
		modelAndView.addObject("show", true);
		modelAndView.addObject("UPLOAD_URL", UPLOAD_URL);
		modelAndView.addObject("SEARCH_URL", SEARCH_URL);
		modelAndView.addObject("villageList", villageList);
		
		modelAndView.addObject("showDeptTable", true);
		modelAndView.addObject("searchList", list);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/"+VIEW_URL)
	public ModelAndView viewBup(@RequestParam(value = "id", required = false) int entryId, HttpSession session) {
		
		BuildingPermissionModel bupModel = buildingPermissionRepository.getBuildingPermissionModel(entryId);
		
		//move the file from C:\image folder to static\images folder
		String rootPath = session.getServletContext().getRealPath("/");
		fileHelper.copyFile(bupModel.getImagePath(), rootPath);
		
		ModelAndView modelAndView = new ModelAndView(VIEW_URL, MODEL_NAME, bupModel);
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
	public String upload(@RequestParam("file") CommonsMultipartFile file, HttpSession session) {
		String rootPath = session.getServletContext().getRealPath("/");
		String fileName = file.getOriginalFilename();
		try {
			byte barr[] = file.getBytes();
			BufferedOutputStream bout = new BufferedOutputStream(
					new FileOutputStream(rootPath + "/static/images/" + fileName));
			bout.write(barr);
			bout.flush();
			bout.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		return "redirect:/" + UPLOAD_URL;
	}
	
	
}
