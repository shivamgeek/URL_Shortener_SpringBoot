package com.shivam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shivam.Entity.Url;
import com.shivam.Entity.User;
import com.shivam.Service.GlobalUrlMapping;
import com.shivam.Service.UrlService;
import com.shivam.Service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	UrlService urlService;
	
	@Autowired
	GlobalUrlMapping mappingService;
	
	@PostMapping("/createShortUrl")
	public String createShortUrl(Model model, @RequestParam("originalUrl") String originalUrl,
			 					@RequestParam("userId") int userId) {
		
		User user = userService.findById(userId);
		model.addAttribute("userData", user);
		
		if(originalUrl == null || originalUrl.contentEquals("")) {
			model.addAttribute("urlError", "Empty URL!");
			return "user-homepage";
		}
		
		String urlReachableError = urlService.isUrlReachable(originalUrl) ;
		if(urlReachableError != null) {
			model.addAttribute("urlError", urlReachableError);
			return "user-homepage";
		}
		
		Url url = new Url();
		String shortUrl = urlService.shortenUrl(originalUrl);
		url.setShortUrl(shortUrl);
		url.setFullUrl(originalUrl);
		url.setExpirationDate(urlService.getOneYearLaterDate());
		url.setUser(user);
		user.addURL(url);
		
		urlService.save(url);
		System.out.println("MAPPING CREATED "+url.getFullUrl()+" ==>> "+url.getShortUrl());
		
		mappingService.getHm().put(shortUrl, url);
		
		model.addAttribute("urlData",url);
		return "user-homepage";
	}
	
	
	@PostMapping("/showUrlList")
	public String showUrlList(Model model, @RequestParam("userId") int userId){
		User user = userService.findById(userId);
		model.addAttribute("userData", user);
		List<Url> list = user.getUrlList();
		if(list == null || list.size() == 0) {
			model.addAttribute("urlListError", "Nothing to show yet!");
			return "user-homepage";
		}
		model.addAttribute("urlList", list);
		return "user-homepage";
	}
	
	@PostMapping("/deleteUrl")
	public String deleteUrl(Model model, @RequestParam("urlId") int urlId, @RequestParam("userId") int userId){
		urlService.deleteById(urlId);
		User user = userService.findById(userId);
		model.addAttribute("userData", user);
		List<Url> list = user.getUrlList();
		if(list == null || list.size() == 0) {
			model.addAttribute("urlListError", "Nothing to show yet!");
			return "user-homepage";
		}
		model.addAttribute("urlList", list);
		return "user-homepage";
	}
	
	
	
}
