package com.shivam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shivam.Entity.User;

@Controller
public class HomeController {
	
	@GetMapping("/homepage")
	public String homepage(Model model) {
		model.addAttribute("date", new java.util.Date());
		return "homepage";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("userData", new User());
		return "register-user";
	}
	
}
