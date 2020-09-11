package com.shivam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shivam.Entity.User;
import com.shivam.Service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;
	
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
	public String saveUser(@ModelAttribute("userData") User user) {
		userService.save(user);
		return "redirect:/users/homepage";
	}
	
	@PostMapping("/login")
	public String loginUser(Model model, @RequestParam("email") String email, @RequestParam("password") String password) {
		User user = userService.loginUser(email, password);
		if(user == null) {
			model.addAttribute("loginError", "Invalid Credentials, try again");
			return "homepage";
		}
		model.addAttribute("userData", user);
		return "user-homepage";
	}
	
	
	@PostMapping("/createShortUrl")
	public String createShortUrl(Model model, @RequestParam("originalUrl") String originalUrl,
			 					@RequestParam("userId") int userId) {
		
		User user = userService.findById(userId);
		model.addAttribute("userData", user);
		
		if(originalUrl == null || originalUrl.contentEquals("")) {
			model.addAttribute("urlError", "Empty URL!");
			return "user-homepage";
		}
		
		
		return "user-homepage";
	}
	
}
