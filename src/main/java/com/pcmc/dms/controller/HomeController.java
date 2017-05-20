package com.pcmc.dms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pcmc.dms.model.DepartmentModel;
import com.pcmc.dms.model.LoginModel;
import com.pcmc.dms.repository.HomeRepository;

@Controller
public class HomeController {

	@Autowired
	private HomeRepository homeRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {

		ModelAndView modelAndView = new ModelAndView("login", "loginModel", new LoginModel());
		return modelAndView;
	}

	@RequestMapping(value = "validateLogin", method = RequestMethod.POST)
	public String validateLogin(@ModelAttribute("loginModel") LoginModel loginModel, HttpSession session) {

		LoginModel loginModelDb = homeRepository.getDepartment(loginModel);
		if(loginModelDb != null) {
			
			DepartmentModel deptModel = loginModelDb.getDepartment();
			
			session.setAttribute("deptId", deptModel.getDeptId());
			session.setAttribute("deptName", deptModel.getDeptName());
			session.setAttribute("print", loginModelDb.getIsPrint());
			session.setAttribute("update", loginModelDb.getIsUpdate());
			session.setAttribute("download", loginModelDb.getIsDownload());
			
			return "redirect:/" + deptModel.getRouteUrl();
		}
		return "redirect:/";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

}
