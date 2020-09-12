package com.shivam.Service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shivam.Entity.Url;

@Service
public class GlobalUrlMapping {
	
	@Autowired
	HashMap<String, Url> hm;
	
	@Autowired
	UrlService urlService;

	public HashMap<String, Url> getHm() {
		return hm;
	}

	public void setHm(HashMap<String, Url> hm) {
		this.hm = hm;
	}

	public GlobalUrlMapping() {
		super();
	}
	
	@PostConstruct
	public void init_url_mapping() {
		List<Url> list = urlService.findAll();
		for(Url u : list) {
			hm.put(u.getShortUrl(), u);
		}
	}
	
}
