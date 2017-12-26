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

import com.pcmc.dms.master.TableMaster;
import com.pcmc.dms.model.VehicleWorkshopModel;
import com.pcmc.dms.repository.VehicleWorkshopRepository;


@Controller
public class VehicleWorkshopController extends BaseController {
	
	@Autowired
	private VehicleWorkshopRepository vehicleWorkshopRepository;
	
	/*
	 * Upload URL name and View name
	 */
	private static final String UPLOAD_URL = "uploadVehicleWorkshop";

	private static final String SEARCH_URL = "vehicleWorkshop";
	
	/*
	 * View URL name and View name
	 */
	private static final String VIEW_URL = "viewVehicleWorkshop";
	
	private static final String MODEL_NAME = "vehicleWorkshopModel";
	
	private static final String SEARCH_VIEW_NAME = "searchVehicleWorkshop";
	
	@RequestMapping(value = "/" + SEARCH_URL, method = RequestMethod.GET)
	public ModelAndView index() {
		
		List<TableMaster> tableMasterList = vehicleWorkshopRepository.getMasterList(TableMaster.class);
		
		ModelAndView modelAndView = new ModelAndView(SEARCH_VIEW_NAME, MODEL_NAME, new VehicleWorkshopModel());
		modelAndView.addObject("show", true);
		modelAndView.addObject("UPLOAD_URL", UPLOAD_URL);
		modelAndView.addObject("SEARCH_URL", SEARCH_URL);
		modelAndView.addObject("MODEL_NAME", MODEL_NAME);
		modelAndView.addObject("VIEW_URL", VIEW_URL);
		modelAndView.addObject("tableMasterList", tableMasterList);
		
		modelAndView.addObject("showDeptTable", false);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/" + SEARCH_URL, method = RequestMethod.POST)
	public ModelAndView search(@ModelAttribute(MODEL_NAME) VehicleWorkshopModel vehicleWorkshopModel) {
		
		List<VehicleWorkshopModel> list = vehicleWorkshopRepository.getVehicleWorkshopModelList(vehicleWorkshopModel);
		List<TableMaster> tableMasterList = vehicleWorkshopRepository.getMasterList(TableMaster.class);
		
		ModelAndView modelAndView = new ModelAndView(SEARCH_VIEW_NAME,MODEL_NAME, vehicleWorkshopModel);
		modelAndView.addObject("show", true);
		modelAndView.addObject("UPLOAD_URL", UPLOAD_URL);
		modelAndView.addObject("SEARCH_URL", SEARCH_URL);
		modelAndView.addObject("MODEL_NAME", MODEL_NAME);
		modelAndView.addObject("tableMasterList", tableMasterList);
		modelAndView.addObject("VIEW_URL", VIEW_URL);
		
		modelAndView.addObject("showDeptTable", true);
		modelAndView.addObject("searchList", list);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/"+VIEW_URL)
	public ModelAndView view(@RequestParam(value = "id", required = false) int entryId,  HttpSession session) {
		
		VehicleWorkshopModel vehicleWorkshopModel = vehicleWorkshopRepository.getModel(entryId, VehicleWorkshopModel.class);
		
		//move the file from C:\image folder to static\images folder
		String LOCAL_FOLDER_PATH = this.localFolederPath + "\\vehicle_workshop\\";
		String rootPath = session.getServletContext().getRealPath("/");
		fileHelper.copyFile(vehicleWorkshopModel.getImagePath(), rootPath, LOCAL_FOLDER_PATH);

		ModelAndView modelAndView = new ModelAndView(VIEW_URL, MODEL_NAME, vehicleWorkshopModel);
		modelAndView.addObject("show", true);
		modelAndView.addObject("UPLOAD_URL", UPLOAD_URL);
		modelAndView.addObject("SEARCH_URL", SEARCH_URL);
		modelAndView.addObject("MODEL_NAME", MODEL_NAME);
		modelAndView.addObject("VIEW_URL", VIEW_URL);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/"+UPLOAD_URL, method = RequestMethod.GET)
	public ModelAndView upload(){
		
		ModelAndView modelAndView = new ModelAndView(UPLOAD_URL);
		modelAndView.addObject("show", false);
		modelAndView.addObject("UPLOAD_URL", UPLOAD_URL);
		modelAndView.addObject("SEARCH_URL", SEARCH_URL);
		modelAndView.addObject("MODEL_NAME", MODEL_NAME);
		modelAndView.addObject("VIEW_URL", VIEW_URL);
		
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
