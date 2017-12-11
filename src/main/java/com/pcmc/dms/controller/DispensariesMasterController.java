package com.pcmc.dms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pcmc.dms.master.DispensariesMaster;
import com.pcmc.dms.repository.MasterRepository;

@Controller
public class DispensariesMasterController {
	
	@Autowired
	private MasterRepository masterRepository;
	
	private static final String URL = "Dispensaries";
	
	private ModelAndView getCommonModelAndView(DispensariesMaster master){
		List<DispensariesMaster> list = masterRepository.getMasterList(DispensariesMaster.class);
		ModelAndView modelAndView = new ModelAndView("dispensaries", "model" , master);
		modelAndView.addObject("masterList", list);
		modelAndView.addObject("postUrl", URL);
		modelAndView.addObject("masterMenu", true);
		return modelAndView;
	}
	
	@RequestMapping(value = URL, method = RequestMethod.GET)
	public ModelAndView get(){
		
		return getCommonModelAndView(new DispensariesMaster());
	}
	
	@RequestMapping(value = URL, method = RequestMethod.POST)
	public String post(@ModelAttribute("model") DispensariesMaster master){
		
		masterRepository.saveMasterValue(master);
		return "redirect:/" + URL;
	}
	
	@RequestMapping(value = "edit" + URL)
	public ModelAndView edit(@RequestParam("id") int rowId){
		
		DispensariesMaster master = masterRepository.getMasterValue(rowId, DispensariesMaster.class);
		return getCommonModelAndView(master);
	}
	
	@RequestMapping(value = "delete" + URL)
	public ModelAndView delete(@RequestParam("id") int rowId){
		
	    masterRepository.deleteMasterValue(rowId, DispensariesMaster.class);
		return getCommonModelAndView(new DispensariesMaster());
	}
}
