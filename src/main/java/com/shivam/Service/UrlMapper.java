package com.shivam.Service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shivam.DAO.UrlRepository;
import com.shivam.Entity.Url;

@Service
public class UrlMapper {
	
	HashMap<String, Url> myUrlMap;
	
	@Autowired
	UrlRepository urlRepository;

	public UrlMapper() {
		super();
	}

	public HashMap<String, Url> getMyUrlMap() {
		return myUrlMap;
	}

	public void setMyUrlMap(HashMap<String, Url> myUrlMap) {
		this.myUrlMap = myUrlMap;
	}

	public void initiailiseMap() {
		List<Url> list = urlRepository.findAll();
		for(int i=0;i<list.size();i++) {
			myUrlMap.put(list.get(i).getShortUrl(), list.get(i));
		}
	}
}
