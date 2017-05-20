package com.pcmc.dms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pcmc.dms.master.VillageMaster;
import com.pcmc.dms.repository.MasterRepository;

@Controller
public class VillageMasterController {
	
	@Autowired
	private MasterRepository masterRepository;
	
	private static final String URL = "Village";
	
	private ModelAndView getCommonModelAndView(VillageMaster master){
		List<VillageMaster> list = masterRepository.getMasterList(VillageMaster.class);
		ModelAndView modelAndView = new ModelAndView("village", "model" , master);
		modelAndView.addObject("masterList", list);
		modelAndView.addObject("postUrl", URL);
		modelAndView.addObject("masterMenu", true);
		return modelAndView;
	}
	
	@RequestMapping(value = URL, method = RequestMethod.GET)
	public ModelAndView getGutNO(){
		
		return getCommonModelAndView(new VillageMaster());
	}
	
	@RequestMapping(value = URL, method = RequestMethod.POST)
	public String postGutNO(@ModelAttribute("model") VillageMaster master){
		
		masterRepository.saveMasterValue(master);
		return "redirect:/" + URL;
	}
	
	@RequestMapping(value = "edit" + URL)
	public ModelAndView editGutNo(@RequestParam("id") int rowId){
		
		VillageMaster master = masterRepository.getMasterValue(rowId, VillageMaster.class);
		return getCommonModelAndView(master);
	}
	
	@RequestMapping(value = "delete" + URL)
	public ModelAndView deleteGutNo(@RequestParam("id") int rowId){
		
	    masterRepository.deleteMasterValue(rowId, VillageMaster.class);
		return getCommonModelAndView(new VillageMaster());
	}
}
