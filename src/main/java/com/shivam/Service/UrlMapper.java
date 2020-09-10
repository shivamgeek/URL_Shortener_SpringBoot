package com.shivam.Service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shivam.DAO.UrlRepository;
import com.shivam.Entity.URL;

@Service
public class UrlMapper {
	
	HashMap<String, URL> myUrlMap;
	
	@Autowired
	UrlRepository urlRepository;

	public UrlMapper() {
		super();
	}

	public HashMap<String, URL> getMyUrlMap() {
		return myUrlMap;
	}

	public void setMyUrlMap(HashMap<String, URL> myUrlMap) {
		this.myUrlMap = myUrlMap;
	}

	public void initiailiseMap() {
		List<URL> list = urlRepository.findAll();
		for(int i=0;i<list.size();i++) {
			myUrlMap.put(list.get(i).getShortUrl(), list.get(i));
		}
	}
}
