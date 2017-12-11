package com.pcmc.dms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pcmc.dms.master.NagarNameMaster;
import com.pcmc.dms.repository.MasterRepository;

@Controller
public class NagarNameMasterController {
	
	@Autowired
	private MasterRepository masterRepository;
	
	private static final String URL = "NagarName";
	
	private ModelAndView getCommonModelAndView(NagarNameMaster master){
		List<NagarNameMaster> list = masterRepository.getMasterList(NagarNameMaster.class);
		ModelAndView modelAndView = new ModelAndView("nagarName", "model" , master);
		modelAndView.addObject("masterList", list);
		modelAndView.addObject("postUrl", URL);
		modelAndView.addObject("masterMenu", true);
		return modelAndView;
	}
	
	@RequestMapping(value = URL, method = RequestMethod.GET)
	public ModelAndView get(){
		
		return getCommonModelAndView(new NagarNameMaster());
	}
	
	@RequestMapping(value = URL, method = RequestMethod.POST)
	public String post(@ModelAttribute("model") NagarNameMaster master){
		
		masterRepository.saveMasterValue(master);
		return "redirect:/" + URL;
	}
	
	@RequestMapping(value = "edit" + URL)
	public ModelAndView edit(@RequestParam("id") int rowId){
		
		NagarNameMaster master = masterRepository.getMasterValue(rowId, NagarNameMaster.class);
		return getCommonModelAndView(master);
	}
	
	@RequestMapping(value = "delete" + URL)
	public ModelAndView delete(@RequestParam("id") int rowId){
		
	    masterRepository.deleteMasterValue(rowId, NagarNameMaster.class);
		return getCommonModelAndView(new NagarNameMaster());
	}
}
