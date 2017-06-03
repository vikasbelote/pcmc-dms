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

import com.pcmc.dms.master.GutNoMaster;
import com.pcmc.dms.master.RecordTypeMaster;
import com.pcmc.dms.model.AuditModel;
import com.pcmc.dms.repository.AuditRepository;


@Controller
public class AuditContoller extends BaseController {
	
	@Autowired
	private AuditRepository auditRepository;
	
	private static final String UPLOAD_URL = "uploadAudit";

	private static final String SEARCH_URL = "audit";

	@RequestMapping(value = "/audit", method = RequestMethod.GET)
	public ModelAndView index() {
		
		List<GutNoMaster> gutNoList = auditRepository.getMasterList(GutNoMaster.class);
		List<RecordTypeMaster> recordTypeList = auditRepository.getMasterList(RecordTypeMaster.class);

		ModelAndView modelAndView = new ModelAndView("search", "auditModel", new AuditModel());
		modelAndView.addObject("show", true);
		modelAndView.addObject("UPLOAD_URL", UPLOAD_URL);
		modelAndView.addObject("SEARCH_URL", SEARCH_URL);
		modelAndView.addObject("gutNoList", gutNoList);
		modelAndView.addObject("recordTypeList", recordTypeList);
		
		modelAndView.addObject("showDeptTable", false);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/audit", method = RequestMethod.POST)
	public ModelAndView search(@ModelAttribute("auditModel") AuditModel auditModel) {
		
		List<AuditModel> list = auditRepository.getAuditList(auditModel);
		List<GutNoMaster> gutNoList = auditRepository.getMasterList(GutNoMaster.class);
		List<RecordTypeMaster> recordTypeList = auditRepository.getMasterList(RecordTypeMaster.class);
		
		ModelAndView modelAndView = new ModelAndView("search","auditModel", auditModel);
		modelAndView.addObject("show", true);
		modelAndView.addObject("UPLOAD_URL", UPLOAD_URL);
		modelAndView.addObject("SEARCH_URL", SEARCH_URL);
		modelAndView.addObject("gutNoList", gutNoList);
		modelAndView.addObject("recordTypeList", recordTypeList);
		
		modelAndView.addObject("showDeptTable", true);
		modelAndView.addObject("searchList", list);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/viewAudit")
	public ModelAndView viewAudit(@RequestParam(value = "id", required = false) int entryId, HttpSession session) {
		
		AuditModel model = auditRepository.getAuditModel(entryId);
		
		//move the file from C:\image folder to static\images folder
		String LOCAL_FOLDER_PATH = "C:\\images\\audit\\";
		String rootPath = session.getServletContext().getRealPath("/");
		fileHelper.copyFile(model.getImagePath(), rootPath, LOCAL_FOLDER_PATH);
		
		ModelAndView modelAndView = new ModelAndView("view", "auditModel", model);
		modelAndView.addObject("show", true);
		modelAndView.addObject("UPLOAD_URL", UPLOAD_URL);
		modelAndView.addObject("SEARCH_URL", SEARCH_URL);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/uploadAudit", method = RequestMethod.GET)
	public ModelAndView upload(){
		
		ModelAndView modelAndView = new ModelAndView("upload");
		modelAndView.addObject("show", false);
		modelAndView.addObject("UPLOAD_URL", UPLOAD_URL);
		modelAndView.addObject("SEARCH_URL", SEARCH_URL);
		
		return modelAndView;
	}

	@RequestMapping(value = "/uploadAudit", method = RequestMethod.POST)
	public String upload(@RequestParam CommonsMultipartFile file, HttpSession session) {
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
