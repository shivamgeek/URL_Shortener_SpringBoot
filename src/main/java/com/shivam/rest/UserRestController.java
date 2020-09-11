package com.shivam.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shivam.Entity.User;
import com.shivam.Service.UserService;

@RestController
@RequestMapping("/api_users")
public class UserRestController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/login/{email}/{password}")
	public User doLogin(@PathVariable("email") String email, @PathVariable("password")String password) {
		User user = userService.doLogin(email, password);
		return user;
	}
	
	@GetMapping("/hello")
	public String sayHello() {
		return "Hello User";
	}
	
}
