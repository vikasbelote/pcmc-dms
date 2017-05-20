package com.pcmc.dms.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pcmc.dms.master.SubDeptMaster;
import com.pcmc.dms.model.DepartmentModel;
import com.pcmc.dms.repository.MasterRepository;

@Controller
public class ZoneNoMasterController {
	
	@Autowired
	private MasterRepository masterRepository;
	
	private static final String URL = "ZoneNo";
	
	private ModelAndView getCommonModelAndView(SubDeptMaster master){
		List<SubDeptMaster> list = masterRepository.getMasterList(SubDeptMaster.class, URL);
		ModelAndView modelAndView = new ModelAndView("zone", "model" , master);
		modelAndView.addObject("masterList", list);
		modelAndView.addObject("postUrl", URL);
		modelAndView.addObject("subDepartmentMenu", true);
		
		List<DepartmentModel> deptList = masterRepository.getMasterList(DepartmentModel.class);
		modelAndView.addObject("deptList", deptList);
		
		
		
		return modelAndView;
	}
	
	
	@RequestMapping(value = URL, method = RequestMethod.GET)
	public ModelAndView getZoneNO(){
		
		return getCommonModelAndView(new SubDeptMaster());
	}
	
	@RequestMapping(value = URL, method = RequestMethod.POST)
	public String postZoneNO(@ModelAttribute("model") SubDeptMaster master, HttpServletRequest request) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		String departmetId = request.getParameter("departmentForm");
		Integer deptId = Integer.parseInt(departmetId);
		master.setSubDeptName(URL);
		masterRepository.saveMasterDeptValue(master, deptId);
		return "redirect:/" + URL;
	}
	
	@RequestMapping(value = "edit" + URL)
	public ModelAndView editZoneNo(@RequestParam("id") int subDeptId){
		
		SubDeptMaster master = masterRepository.getSubMasterValue(subDeptId, SubDeptMaster.class);
		return getCommonModelAndView(master);
	}
	
	@RequestMapping(value = "delete" + URL)
	public ModelAndView deleteZoneNo(@RequestParam("id") int subDeptId){
		
	    masterRepository.deleteSubMasterValue(subDeptId, SubDeptMaster.class);
		return getCommonModelAndView(new SubDeptMaster());
	}
	
}
