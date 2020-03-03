package com.ey.springboot.web.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController {


	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public String showWelcomePage(ModelMap model) {
		model.put("name", getLoggedinUserName());
		return "welcome";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String showWelcomePageGet(ModelMap model) {
		model.put("name", getLoggedinUserName());
		return "welcome";
	}

	private String getLoggedinUserName() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		
		return principal.toString();
	}
	
}
