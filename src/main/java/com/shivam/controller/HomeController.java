package com.shivam.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shivam.Entity.User;
import com.shivam.Service.UrlService;
import com.shivam.Service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UrlService urlService;
	
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
	
	@PostMapping("/saveUser")
	public String saveUser(Model model, @Valid @ModelAttribute("userData") User user, BindingResult result) {
		if(user == null || result.hasErrors() == true) {
			return "register-user";
		}
		
		//Check is someone has already registerd with same email id
		if(userService.findUserByEmail(user.getEmail()) == true){
			model.addAttribute("emailError","This email is already registered");
			return "register-user";
		}
		user.setUserEnabled(true);
		user.setUserRole("SIGNED_IN_USER");
		userService.save(user);
		return "redirect:/homepage";
	}
	
	@PostMapping("/userLogin")
	public String loginUser(Model model, @RequestParam("email") String email, @RequestParam("password") String password) {
		System.out.println("**********########### REQUEST RECEIVED AT /userLogin");
		User user = userService.loginUser(email, password);
		if(user == null) {
			model.addAttribute("loginError", "Invalid Credentials, try again");
			return "homepage";
		}
		model.addAttribute("userData", user);
		return "user-homepage";
	}
	
}
