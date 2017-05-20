package com.pcmc.dms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pcmc.dms.model.DepartmentModel;
import com.pcmc.dms.repository.MasterRepository;

@Controller
public class DepartmentMasterController {
	
	@Autowired
	private MasterRepository masterRepository;
	
	private static final String URL = "Department";
	
	private ModelAndView getCommonModelAndView(DepartmentModel master){
		List<DepartmentModel> list = masterRepository.getMasterList(DepartmentModel.class);
		ModelAndView modelAndView = new ModelAndView("department", "model" , master);
		modelAndView.addObject("masterList", list);
		modelAndView.addObject("postUrl", URL);
		return modelAndView;
	}
	
	@RequestMapping(value = URL, method = RequestMethod.GET)
	public ModelAndView getDepartment(){
		
		return getCommonModelAndView(new DepartmentModel());
	}
	
	@RequestMapping(value = URL, method = RequestMethod.POST)
	public String postDepartment(@ModelAttribute("model") DepartmentModel master){
		
		String name = master.getDeptName().toLowerCase();
		master.setRouteUrl(name);
		master.setTableName(name);
		masterRepository.saveMasterValue(master);
		return "redirect:/" + URL;
	}
	
	@RequestMapping(value = "edit" + URL)
	public ModelAndView editDepartment(@RequestParam("id") int rowId){
		
		DepartmentModel master = masterRepository.getMasterValue(rowId, DepartmentModel.class);
		return getCommonModelAndView(master);
	}
	
	@RequestMapping(value = "delete" + URL)
	public ModelAndView deleteDepartment(@RequestParam("id") int rowId){
		
	    masterRepository.deleteMasterValue(rowId, DepartmentModel.class);
		return getCommonModelAndView(new DepartmentModel());
	}
}
