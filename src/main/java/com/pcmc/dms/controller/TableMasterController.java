package com.pcmc.dms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pcmc.dms.master.TableMaster;
import com.pcmc.dms.repository.MasterRepository;

@Controller
public class TableMasterController {
	
	@Autowired
	private MasterRepository masterRepository;
	
	private static final String URL = "TableNo";
	
	private ModelAndView getCommonModelAndView(TableMaster master){
		List<TableMaster> list = masterRepository.getMasterList(TableMaster.class);
		ModelAndView modelAndView = new ModelAndView("table", "model" , master);
		modelAndView.addObject("masterList", list);
		modelAndView.addObject("postUrl", URL);
		modelAndView.addObject("masterMenu", true);
		return modelAndView;
	}
	
	@RequestMapping(value = URL, method = RequestMethod.GET)
	public ModelAndView getGutNO(){
		
		return getCommonModelAndView(new TableMaster());
	}
	
	@RequestMapping(value = URL, method = RequestMethod.POST)
	public String postGutNO(@ModelAttribute("model") TableMaster master){
		
		masterRepository.saveMasterValue(master);
		return "redirect:/" + URL;
	}
	
	@RequestMapping(value = "edit" + URL)
	public ModelAndView editGutNo(@RequestParam("id") int rowId){
		
		TableMaster master = masterRepository.getMasterValue(rowId, TableMaster.class);
		return getCommonModelAndView(master);
	}
	
	@RequestMapping(value = "delete" + URL)
	public ModelAndView deleteGutNo(@RequestParam("id") int rowId){
		
	    masterRepository.deleteMasterValue(rowId, TableMaster.class);
		return getCommonModelAndView(new TableMaster());
	}
}
