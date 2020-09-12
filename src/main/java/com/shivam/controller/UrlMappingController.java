package com.shivam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shivam.Service.GlobalUrlMapping;

@Controller
@RequestMapping("/go")
public class UrlMappingController {
	
	@Autowired
	GlobalUrlMapping mappingService;
	
	@GetMapping("/{url}")
	public String mapper(@PathVariable("url") String url) {
		if(mappingService.getHm().get(url) == null) {
			return "errorPage";
		}
		return "redirect:https://"+mappingService.getHm().get(url).getFullUrl();
	}
	
}
