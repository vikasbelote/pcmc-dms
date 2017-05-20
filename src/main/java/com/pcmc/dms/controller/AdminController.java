package com.pcmc.dms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pcmc.dms.model.DepartmentModel;
import com.pcmc.dms.model.LoginModel;
import com.pcmc.dms.repository.AdminRepository;

@Controller
public class AdminController {

	@Autowired
	private AdminRepository adminRepository;

	@RequestMapping(value = "/admin")
	public ModelAndView index() {

		List<DepartmentModel> deptList = adminRepository.getDepartmentList();
		List<LoginModel> userList = adminRepository.getUserList();

		ModelAndView modelAndView = new ModelAndView("admin", "loginModel", new LoginModel());
		modelAndView.addObject("deptList", deptList);
		modelAndView.addObject("userList", userList);
		modelAndView.addObject("userMenu", true);
		
		return modelAndView;
	}

	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	public String createUser(@ModelAttribute("loginModel") LoginModel loginModel, HttpServletRequest request) {

		String btnValue = request.getParameter("btn");
		if (btnValue.equals("search")) {
			return "redirect:/admin";
		} else {
			String departmetId = request.getParameter("departmentForm");
			Integer deptId = Integer.parseInt(departmetId);
			loginModel = adminRepository.createUser(loginModel, deptId);

			return "redirect:/admin";
		}
	}

	@RequestMapping(value = "/editUser")
	public ModelAndView editUser(@RequestParam("id") int loginId) {

		LoginModel loginModel = adminRepository.getLoginModel(loginId);

		List<DepartmentModel> deptList = adminRepository.getDepartmentList();
		List<LoginModel> userList = adminRepository.getUserList();

		ModelAndView modelAndView = new ModelAndView("admin", "loginModel", loginModel);
		modelAndView.addObject("deptList", deptList);
		modelAndView.addObject("userList", userList);
		modelAndView.addObject("userMenu", true);

		return modelAndView;
	}

	@RequestMapping(value = "/deleteUser")
	public ModelAndView deleteUser(@RequestParam("id") int loginId) {

		adminRepository.deleteUser(loginId);

		List<DepartmentModel> deptList = adminRepository.getDepartmentList();
		List<LoginModel> userList = adminRepository.getUserList();

		ModelAndView modelAndView = new ModelAndView("admin", "loginModel", new LoginModel());
		modelAndView.addObject("deptList", deptList);
		modelAndView.addObject("userList", userList);
		modelAndView.addObject("userMenu", true);

		return modelAndView;
	}
}
